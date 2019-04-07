package com.mycompany.airlinenetwork;

public class Connection {
    Airport destination;
    String airlineCode;
    float distance;
    float time;
    Connection next;
    
    public Connection(Airport to, String airlnCode, float dist, float duration){
        destination = to;
        airlineCode = airlnCode;
        distance = dist;
        time = duration;
        next = null;
    }

    public Airport getDestination() {
        return destination;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public float getDistance() {
        return distance;
    }

    public float getTime() {
        return time;
    }
    
    public Connection getNext() {
        return next;
    }

    public void setNext(Connection next) {
        this.next = next;
    }
    
    
}
