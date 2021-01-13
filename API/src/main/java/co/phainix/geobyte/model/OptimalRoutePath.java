package co.phainix.geobyte.model;

import java.util.Arrays;

public class OptimalRoutePath {

    public int[] stops;
    public OptimalRouteResponsePath[] paths;
    public String path;

    public double km;
    public double kmCost;
    public double clearingCost;

    public double totalCost;

    public double kmComparison;
    public double costComparison;

    public double totalComparison;

    public boolean isOptimal;
    public boolean isWorst;

    public OptimalRoutePath(int[] stops, String path, double km, double kmCost, double clearingCost, double totalCost, OptimalRouteResponsePath[] paths) {
        this.stops = stops;
        this.path = path;
        this.km = km;
        this.kmCost = kmCost;
        this.clearingCost = clearingCost;
        this.totalCost = totalCost;
        this.paths = paths;
    }

    public int[] getStops() {
        return stops;
    }

    public void setStops(int[] stops) {
        this.stops = stops;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getKmCost() {
        return kmCost;
    }

    public void setKmCost(double kmCost) {
        this.kmCost = kmCost;
    }

    public double getClearingCost() {
        return clearingCost;
    }

    public void setClearingCost(double clearingCost) {
        this.clearingCost = clearingCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getKmComparison() {
        return kmComparison;
    }

    public void setKmComparison(double kmComparison) {
        this.kmComparison = kmComparison;
        this.totalComparison = this.kmComparison * this.costComparison;
    }

    public double getCostComparison() {
        return costComparison;
    }

    public void setCostComparison(double costComparison) {
        this.costComparison = costComparison;
        this.totalComparison = this.kmComparison * this.costComparison;
    }

    public boolean isOptimal() {
        return isOptimal;
    }

    public void setOptimal(boolean optimal) {
        isOptimal = optimal;
    }

    public boolean isWorst() {
        return isWorst;
    }

    public void setWorst(boolean worst) {
        isWorst = worst;
    }

    public double getTotalComparison() {
        return totalComparison;
    }

    public void setTotalComparison(double totalComparison) {
        this.totalComparison = totalComparison;
    }

    public OptimalRouteResponsePath[] getPaths() {
        return paths;
    }

    public void setPaths(OptimalRouteResponsePath[] paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "Path{" +
                "locations=" + Arrays.toString(stops) +
                ", path='" + path + '\'' +
                ", km=" + km +
                ", kmCost=" + kmCost +
                ", clearingCost=" + clearingCost +
                ", totalCost=" + totalCost +
                ", kmComparison=" + kmComparison +
                ", costComparison=" + costComparison +
                ", totalComparison=" + totalComparison +
                ", isOptimal=" + isOptimal +
                ", isWorst=" + isWorst +
                ", paths=" + Arrays.toString(paths) +
                '}';
    }

}
