package co.phainix.geobyte.model;

public class OptimalRouteLocation {

    public int x;
    public int y;
    public double clearing_cost;
    public int name;

    public OptimalRouteLocation(int x, int y, double clearing_cost, int name) {
        this.x = x;
        this.y = y;
        this.clearing_cost = clearing_cost;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getClearing_cost() {
        return clearing_cost;
    }

    public void setClearing_cost(double clearing_cost) {
        this.clearing_cost = clearing_cost;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", clearing_cost=" + clearing_cost +
                ", name=" + name +
                '}';
    }

}
