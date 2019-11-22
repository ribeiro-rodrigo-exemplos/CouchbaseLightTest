package br.com.m2msolutions.couchbaselighttest;

public class GeoPoint {
    private String type;
    private float[][] coordinates;

    public GeoPoint() {
    }

    public GeoPoint(String type, float[][] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[][] coordinates) {
        this.coordinates = coordinates;
    }
}
