package com.mycompany.airlinenetwork;

import java.util.HashMap;
import java.util.Map;

public class Route {
    public String airline_code;
    public String source_code;
    public String destination_code;
    public float distance;
    public float time;

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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
    
    public static Map<String, String> getMapping(){
        Map<String, String> mapping = new 
                      HashMap<String, String>(); 
        mapping.put("AIRLINE_CODE", "airline_code"); 
        mapping.put("SOURCE_CODE", "source_code"); 
        mapping.put("DESTINATION_CODE", "destination_code"); 
        mapping.put("DISTANCE", "distance");
        mapping.put("TIME", "time"); 
        
        return mapping;
    }
}
