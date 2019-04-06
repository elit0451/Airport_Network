package com.mycompany.airlinenetwork;

import java.util.HashMap;
import java.util.Map;

public class Airport {
    
    public String code;
    public String name;
    public String city;
    public String country;
    public String latitude;
    public String longitude;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    public static Map<String, String> getMapping(){
        Map<String, String> mapping = new 
                      HashMap<String, String>(); 
        mapping.put("CODE", "code"); 
        mapping.put("NAME", "name"); 
        mapping.put("CITY", "city"); 
        mapping.put("COUNTRY", "country");
        mapping.put("LATITUDE", "latitude"); 
        mapping.put("LONGITUDE", "longitude");
        
        return mapping;
    }
}