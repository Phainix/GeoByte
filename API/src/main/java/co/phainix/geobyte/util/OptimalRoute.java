package co.phainix.geobyte.util;

import co.phainix.geobyte.model.OptimalRouteLocation;
import co.phainix.geobyte.model.OptimalRoutePath;
import co.phainix.geobyte.model.OptimalRouteResponsePath;

import java.util.*;

public class OptimalRoute {
    public Map<Integer, OptimalRouteLocation> locations = new HashMap<>();
    public int origin;
    public int destination;

    public int minStop = 1;
    public int maxStop = 4;

    public double maxKm;
    public double maxCost;

    public int costPerKm = 1;

    public Map<Integer, int[]> paths = new HashMap<>();

    public OptimalRoute(Map<Integer, OptimalRouteLocation> locations, int origin, int destination) {
        this.locations = locations;
        this.origin = origin;
        this.destination = destination;
    }

    public Map<Integer, OptimalRouteLocation> getLocations() {
        return locations;
    }

    public void setLocations(Map<Integer, OptimalRouteLocation> locations) {
        this.locations = locations;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public List<OptimalRoutePath> getOptimisedPath() {

        int loopCount = 0;
        // Calculate distance between each point and all other points, this is in km  —> sqrt((Xa - Xb) + (Ya - Yb))
        double[][] distances = new double[locations.size()][locations.size()];
        int[] possibleIntLoc = new int[locations.size() - 2]; // Get the locations that can be possible intermediate locations, total locations - origin - destination
        int possibleIntLocCount = 0;

        Map<Integer, Integer> indexLocationMap = new HashMap<>(); // Map location name to index for future use
        List<OptimalRoutePath> pathList = new ArrayList<OptimalRoutePath>();

        for (Map.Entry<Integer, OptimalRouteLocation> valA: locations.entrySet()) {
            OptimalRouteLocation locA = valA.getValue();
            System.out.println(locA);

            indexLocationMap.put(locA.getName(), valA.getKey());

            if(!(locA.getName() == origin) && !(locA.getName() == destination)) {
                possibleIntLoc[possibleIntLocCount] = locA.getName();
                possibleIntLocCount++;
            }

            System.out.println(possibleIntLoc.toString());

            for (Map.Entry<Integer, OptimalRouteLocation> valB: locations.entrySet()) {
                OptimalRouteLocation locB = valB.getValue();
                if(valA.getKey() >= valB.getKey()) {
                    distances[valA.getKey()][valB.getKey()] = distances[valB.getKey()][valA.getKey()];
                    continue;
                }

                double distance = Math.sqrt(Math.pow(locA.getX() - locB.getX(), 2) + Math.pow(locA.getY() - locB.getY(), 2));
                distance = Math.round(distance * 100) / 100.00;

                System.out.println(locA.getName() + " to " + locB.getName() + " is " + distance + "km");
                distances[valA.getKey()][valB.getKey()] = distance;
                loopCount++;
            }
        }
        System.out.println(Arrays.deepToString(distances).replace("], ", "]\n"));
        System.out.println(possibleIntLoc);
        System.out.println(loopCount);
        System.out.println();

        // Find all possible stops
        int stops = minStop;
        while (stops <= maxStop) {
            getPossiblePaths(possibleIntLoc, possibleIntLoc.length, stops);
            stops++;
        }

        // Calculate the distance & cost per path
        for (Map.Entry<Integer, int[]> entry : paths.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue().length + ", ");
            System.out.print(entry.getValue());

            double km = 0.0;
            double kmCost = 0.0;
            double clearingCost = 0.0;

            int[] path = entry.getValue();
            OptimalRouteResponsePath[] paths = new OptimalRouteResponsePath[entry.getValue().length + 2];

            // paths[0] = locations.get(indexLocationMap.get(origin));
             paths[0] = new OptimalRouteResponsePath(0, 0, origin);

            int originIndex = indexLocationMap.get(origin);
            OptimalRouteLocation originLocation = locations.get(originIndex);

            int destinationIndex = indexLocationMap.get(destination);
            OptimalRouteLocation destinationLocation = locations.get(destinationIndex);

            System.out.print(", ");
            String pathDesc = originLocation.getName() + " - ";

            // Loop through each generated path
            for(int i = 0; i < path.length; i++) {

                int currentLocationIndex = indexLocationMap.get(path[i]);
                OptimalRouteLocation currentLocation = locations.get(currentLocationIndex);

                pathDesc += currentLocation.getName() + " - ";

                if(i == 0) { // This is the first location, calculate distance in km from origin to here
                    km += distances[originIndex][currentLocationIndex];
                    clearingCost += currentLocation.getClearing_cost();

                    paths[i + 1] = new OptimalRouteResponsePath(km, clearingCost, path[i]);
                } else { // This is a middle location
                    int prevLocationIndex = indexLocationMap.get(path[i-1]);
                    OptimalRouteLocation prevLocation = locations.get(prevLocationIndex);

                    km += distances[prevLocationIndex][currentLocationIndex];
                    clearingCost += currentLocation.getClearing_cost();

                    paths[i + 1] = new OptimalRouteResponsePath(km, clearingCost, path[i]);
                }

                if(i == path.length - 1) { // This is the last location, calculate distance in km from here to destination
                    km += distances[currentLocationIndex][destinationIndex];
                }
                // paths[i + 1] = locations.get(indexLocationMap.get(path[i]));
                // paths[i + 1] = new OptimalRouteResponsePath(km, clearingCost);
            }

            // paths[entry.getValue().length + 1] = locations.get(indexLocationMap.get(destination));
             paths[entry.getValue().length + 1] = new OptimalRouteResponsePath(km, clearingCost, destination);

            km = Math.round(km * 100) / 100.00;

            kmCost = costPerKm * km;

            pathDesc += destinationLocation.getName();

            System.out.print(pathDesc + " $" + (kmCost + clearingCost) + ", " + km + "km");

            System.out.println();

            if(entry.getKey() == 0) {
                maxCost = kmCost + clearingCost;
                maxKm = km;
            }

            if(kmCost + clearingCost > maxCost) maxCost = kmCost + clearingCost;
            if(km > maxKm) maxKm = km;

            pathList.add(entry.getKey(), new OptimalRoutePath(path, pathDesc, km, kmCost, clearingCost, kmCost + clearingCost, paths));
        }

        System.out.println(maxCost);
        System.out.println(maxKm);

        for (int i = 0; i < pathList.size(); i++) {
            pathList.get(i).setCostComparison(pathList.get(i).totalCost / maxCost);
            pathList.get(i).setKmComparison(pathList.get(i).kmCost / maxKm);
        }

        Collections.sort(pathList, new Comparator<OptimalRoutePath>(){
            public int compare(OptimalRoutePath o1, OptimalRoutePath o2) {
                return o1.totalComparison == o2.totalComparison ? 0 : (o1.totalComparison < o2.totalComparison ? -1 : 1) ;
            }
        });

        pathList.get(0).setOptimal(true);
        pathList.get(pathList.size() - 1).setWorst(true);

        System.out.println(pathList);

        return pathList;
    }

    private void getPossiblePaths(int[] possibleIntLoc, int length, int stops) {
        int data[] = new int[stops];
        getPath(possibleIntLoc, length, stops, 0, data, 0);
    }

    private void getPath(int possibleIntLoc[], int length, int size, int index, int data[], int i) {
        // Current combination is ready to be printed, print it
        if (index == size)
        {
            System.out.println(data);
            paths.put(paths.size(), data.clone());
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= length)
            return;

        // current is included, put next at next location
        data[index] = possibleIntLoc[i];
        getPath(possibleIntLoc, length, size, index+1, data, i+1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        getPath(possibleIntLoc, length, size, index, data, i+1);

        return;
    }
}
