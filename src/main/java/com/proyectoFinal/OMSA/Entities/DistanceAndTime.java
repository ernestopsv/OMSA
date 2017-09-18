package com.proyectoFinal.OMSA.Entities;

/**
 * Created by Dany on 15/09/2017.
 */
public class DistanceAndTime {
    private double distance;
    private double duration;
    private double duration_Traffic;

    public DistanceAndTime(double distance, double duration, double duration_Traffic) {
        this.distance = distance;
        this.duration = duration;
        this.duration_Traffic = duration_Traffic;
    }

    public double getDistance() {
        return distance;
    }

    public DistanceAndTime() {
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDuration_Traffic() {
        return duration_Traffic;
    }

    public void setDuration_Traffic(double duration_Traffic) {
        this.duration_Traffic = duration_Traffic;
    }
}
