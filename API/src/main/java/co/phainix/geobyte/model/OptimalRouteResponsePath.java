package co.phainix.geobyte.model;

public class OptimalRouteResponsePath {

    public double km;
    public double clearing_cost;
    public int location_id;

    public OptimalRouteResponsePath(double km, double clearing_cost, int location_id) {
        this.km = km;
        this.clearing_cost = clearing_cost;
        this.location_id = location_id;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getClearing_cost() {
        return clearing_cost;
    }

    public void setClearing_cost(double clearing_cost) {
        this.clearing_cost = clearing_cost;
    }
}
