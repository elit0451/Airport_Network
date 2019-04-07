package com.mycompany.airlinenetwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    List<Airline> airlines;
    HashMap<String, Airport> airports;
    List<Route> routes;
    
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run(){
        
        //Verify if you can load the CSV files  
        boolean isRunning = loadData();
        
        //Build graph
        buildGraph();
        
        Scanner reader = new Scanner(System.in);
        String inputStr;
        int inputNr;
       
        while(isRunning)
        {
            showMenu();
            inputStr = reader.nextLine();
            
            if(tryParseInt(inputStr))
                inputNr = Integer.parseInt(inputStr);
            else
                inputNr = -1;
            
            switch (inputNr)
            {
                case 1: //airlineJourneySearch();
                    break;
                case 2: //shortestDistance();
                    break;
                case 3: //shortestTime();
                    break;
                case 4: //widestAirlineCoverage();
                    break;
                case 5: isRunning = false;
                    break;
                default: System.out.println("Wrong input! Try again!");
                    break;
            }
             
        }
    }
    
    private void showMenu() {
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("Commands:");
        System.out.println("1) Use same ailine company from A to B (DFS && BFS)");
        System.out.println("2) Display shortest distance between A and B");
        System.out.println("3) Display fastest route between A and B");
        System.out.println("4) Show the ailine with the widest coverage");
        System.out.println("5) End program");
        System.out.println();
        System.out.println("Please choose a command:");
    }
    
    boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
    }
    
    private boolean loadData(){
        boolean isSuccessful = false;
        List<Airport> listOfAirports; 
        airports = new HashMap<String, Airport>();
        
        try{
            airlines = Helpers.generateArrayFromCSV(Airline.class, "/Users/elitsa/Downloads/airlines.txt", Airline.getMapping());
            listOfAirports = Helpers.generateArrayFromCSV(Airport.class, "/Users/elitsa/Downloads/airports.txt", Airport.getMapping());
            routes = Helpers.generateArrayFromCSV(Route.class, "/Users/elitsa/Downloads/routes.txt", Route.getMapping());
            isSuccessful = true;
            
            for(Airport airport : listOfAirports){
                airports.put(airport.getCode(), airport);
            }
            /* RM
            System.out.println(airlines.size());
            System.out.println(airports.size());
            System.out.println(routes.size());
            */

        }
        catch(Exception e){
            System.out.println("Something went wrong while loading the files - " + e.getMessage());
        }
        
        return isSuccessful;
    }

    private void buildGraph() {
        for(Airport airport : airports.values()){
            Connection pointer = airport.getConnection();
            
            for(Route route : routes){
                if(route.getSource_code().equals(airport.getCode())){
                    Connection newConn = new Connection(airports.get(route.getDestination_code()), route.getAirline_code(), route.getDistance(), route.getTime());

                    if(pointer == null){
                        airport.setConnection(newConn);
                        pointer = airport.getConnection();
                    }
                    else{
                        pointer.setNext(newConn);
                        pointer = newConn;
                    }
                }
            }
        }
        
    }
    
}

