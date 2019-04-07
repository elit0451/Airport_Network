package com.mycompany.airlinenetwork;

import com.opencsv.bean.CsvBindByName;
import java.util.HashMap;
import java.util.Map;

public class Airline {
    
    String code;
    String name;
    String country;

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

    public static String[] getMapping(){
        return new String[] { "code", "name", "country" };
    }
    
    @Override
    public String toString(){
        return "code - " + code + ", name - " + name + ", country - " + country;
    }
    
}
