package com.mycompany.airlinenetwork;

import java.util.HashMap;
import java.util.List;

public class Algorithms {

    static void DFS(HashMap<String, Airport> airports, List<Airline> airlines, String sourceCode, String destinationCode){
        Airport source = airports.get(sourceCode);
        Airport destination = airports.get(destinationCode);
        
        for(Airline airline : airlines){
            
        }
        
        
    }
    
    private void DFSRecursively(HashMap<String, Airport> airports, String airlineCode, Airport source, Airport destination){
        
    }
    
    static void BFS(HashMap<String, Airport> airports, List<Airline> airlines, String sourceCode, String destinationCode){
        
    }
}
