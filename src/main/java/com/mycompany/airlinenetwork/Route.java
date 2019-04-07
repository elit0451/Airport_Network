package com.mycompany.airlinenetwork;

import java.util.HashMap;
import java.util.Map;

public class Route {
    public String airline_code;
    public String source_code;
    public String destination_code;
    public String distance;
    public String time;

    public String getAirline_code() {
        return airline_code;
    }

    public void setAirline_code(String airline_code) {
        this.airline_code = airline_code;
    }

    public String getSource_code() {
        return source_code;
    }

    public void setSource_code(String source_code) {
        this.source_code = source_code;
    }

    public String getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(String destination_code) {
        this.destination_code = destination_code;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public static String[] getMapping(){
        return new String[] { "airline_code", "source_code", "destination_code", "distance", "time" };
    }
}
