package com.mycompany.airlinenetwork;

import com.opencsv.bean.CsvBindByName;
import java.util.HashMap;
import java.util.Map;

public class Airline {

    @CsvBindByName
    public String code;
    @CsvBindByName
    public String name;
    @CsvBindByName
    public String country;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static Map<String, String> getMapping(){
        Map<String, String> mapping = new 
                      HashMap<String, String>(); 
        mapping.put("CODE", "code"); 
        mapping.put("NAME", "name"); 
        mapping.put("COUNTRY", "country");
        
        return mapping;
    }
    
    @Override
    public String toString(){
        return "code - " + code + ", name - " + name + ", country - " + country;
    }
    
}
