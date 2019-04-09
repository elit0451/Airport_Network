package com.mycompany.airlinenetwork;

import java.util.Comparator;

public class DistanceTo implements Comparator<DistanceTo>{

    String destinationCode;
    float weight;
    String sourceCode;

    public DistanceTo(String destinationCode, float weight, String sourceCode) {
        this.destinationCode = destinationCode;
        this.weight = weight;
        this.sourceCode = sourceCode;
    }
    
    public String getDestinationCode() {
        return destinationCode;
    }
    
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    
    @Override
    public boolean equals(Object o){
        return this.destinationCode.equals(((DistanceTo) o).getDestinationCode());
    }
    
    @Override
    public int compare(DistanceTo dist1, DistanceTo dist2) {
        if(dist1.weight < dist2.weight)
            return 1;
        else if (dist1.weight > dist2.weight)
            return -1;
        return 0;
    }

    
    
}
