package com.mycompany.airlinenetwork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Algorithms {

    static void DFS(HashMap<String, Airport> airports, List<Airline> airlines, String sourceCode, String destinationCode){
        
        if(!airports.containsKey(sourceCode) || !airports.containsKey(destinationCode))
            throw new IndexOutOfBoundsException("One or both of the specified airports do(es)n't exist");
        
        for(Airline airline : airlines){
            StopWatch stopWatch = new StopWatch();
            boolean pathFound = DFSRecursively(airports, airline.getCode(), sourceCode, destinationCode, new HashSet<String>());
            
            if(pathFound){
                System.out.println(" ------- \n");
                System.out.println("It took " + stopWatch.elapsedTime() + "ms");
                System.out.println("Ailine " + airline.getCode() + " connects " + sourceCode + " to " + destinationCode);
                break;
            }
        }
    }
    
    private static boolean DFSRecursively(HashMap<String, Airport> airportsGraph, String airlineCode, String sourceCode, String destinationCode, HashSet<String> visitedAirports){
        
        if(visitedAirports.contains(sourceCode))
            return false;
        
        //mark airport source as visited
        visitedAirports.add(sourceCode);
        
        if(sourceCode.equals(destinationCode))
            return true;
        
        Connection pointer = airportsGraph.get(sourceCode).getConnection();
        
        do{
            while(pointer != null && !pointer.getAirlineCode().equals(airlineCode)){
                pointer = pointer.getNext();
            }
            if(pointer != null){
            if(DFSRecursively(airportsGraph, airlineCode, pointer.getDestination().getCode(), destinationCode, visitedAirports))
                return true;
            pointer = pointer.getNext();
            }
        }while(pointer != null);
        
        return false;
        
    }
    
    static void BFS(HashMap<String, Airport> airports, List<Airline> airlines, String sourceCode, String destinationCode){
        HashSet<String> visitedAirports = new HashSet<String>();
        
        if(!airports.containsKey(sourceCode) || !airports.containsKey(destinationCode))
            throw new IndexOutOfBoundsException("One or both of the specified airports do(es)n't exist");
        
        for(Airline airline : airlines){
            StopWatch stopWatch = new StopWatch();
            boolean pathFound = BFSIterative(airports, sourceCode, destinationCode, airline.getCode(), new HashSet<String>());
            
            if(pathFound){
                System.out.println(" ------- \n");
                System.out.println("It took " + stopWatch.elapsedTime() + "ms");
                System.out.println("Ailine " + airline.getCode() + " connects " + sourceCode + " to " + destinationCode);
                break;
            }
        }
         
    }
    
    private static boolean BFSIterative(HashMap<String, Airport> airportsGraph, String sourceCode, String destinationCode, String airlineCode, HashSet<String> visitedAirports){
        Queue<String> queuedAirports = new LinkedList<String>();  
        String currentAirportCode;
        Connection pointer;
        
        //enqueueing 
        queuedAirports.add(sourceCode);
        //marking as visited
        visitedAirports.add(sourceCode);
        
        while(queuedAirports.peek() != null){
            
            //FIFO
            currentAirportCode =  queuedAirports.remove();
            
            //if we have reached our destination
            if(currentAirportCode.equals(destinationCode))
                return true;
            
            //selecting one of the edges/connections
            pointer = airportsGraph.get(currentAirportCode).getConnection();
            
            while(pointer != null){
                String pointerNextCode = pointer.getDestination().getCode();
                
                //if connection flown with the airline we are looking for we enqueue and mark as visited; if not we skip to the next connection 
                if(pointer.getAirlineCode().equals(airlineCode) && !visitedAirports.contains(pointerNextCode)){
                    queuedAirports.add(pointerNextCode);
                    visitedAirports.add(pointerNextCode);
                }
                
                pointer = pointer.getNext();
            }
        }
        
        return false;
    }
    
    static void distDijkstraAlg(HashMap<String, Airport> airportsGraph, String sourceCode, String destinationCode){
        
        if(!airportsGraph.containsKey(sourceCode) || !airportsGraph.containsKey(destinationCode))
            throw new IndexOutOfBoundsException("One or both of the specified airports do(es)n't exist");
        
        HashMap<String, DistanceTo> minDistanceQueue = new HashMap<String, DistanceTo>();
        Stack<DistanceTo> visitedQueue = new Stack<DistanceTo>(); 
        String currentSource = sourceCode;
        HashSet<String> visitedAirports = new HashSet<String>();
        StopWatch stopWatch = new StopWatch();
        
        //construct priority queue
        for(String airportCode : airportsGraph.keySet()){
            float distance = Float.MAX_VALUE;
            
            if(airportCode.equals(sourceCode))
                distance = 0;
            
            minDistanceQueue.put(airportCode, new DistanceTo(airportCode, distance, ""));
        }
        
        while(!minDistanceQueue.isEmpty()){
            DistanceTo current = minDistanceQueue.remove(currentSource);
            visitedQueue.push(current);
            visitedAirports.add(current.getDestinationCode());
            
            if(current.getDestinationCode().equals(destinationCode))
                break;
            
            //getting the connection from the source airport
            Connection currentPointer = airportsGraph.get(current.getDestinationCode()).getConnection();
            
            while(currentPointer != null){
                
                if(!visitedAirports.contains(currentPointer.getDestination().getCode())){
                    float totalCost = current.getWeight() + currentPointer.getDistance();

                    //compare if the weight of the source + the weight of the edge is less than the currently stored weight
                    if(totalCost < minDistanceQueue.get(currentPointer.getDestination().getCode()).getWeight()){
                        //update the total cost
                        minDistanceQueue.get(currentPointer.getDestination().getCode()).setWeight(totalCost);
                        //update the source to be the one where the weight from was the smallest
                        minDistanceQueue.get(currentPointer.getDestination().getCode()).setSourceCode(currentSource);
                    }
                }
                
                currentPointer = currentPointer.getNext();
            }
            
            currentSource = getSmallestDistanceCode(minDistanceQueue, visitedQueue);
        }
        
        System.out.println(" ------- \n");
        System.out.println("It took " + stopWatch.elapsedTime() + "ms");
        
        //getting the destination node and its source
        DistanceTo destinationNode = visitedQueue.pop();
        String fromNode = destinationNode.getSourceCode();
        ArrayList<String> pathNodes = new ArrayList<String>();
        
        float finalDistance = destinationNode.getWeight();
        pathNodes.add(destinationNode.getDestinationCode());
        
        //getting the shortest path
        while(!visitedQueue.empty()){
            destinationNode = visitedQueue.pop();
            
            if(fromNode.equals(destinationNode.getDestinationCode())){
                fromNode = destinationNode.getSourceCode();
                pathNodes.add(destinationNode.getDestinationCode());
            }
        }
        
        printPathNodes(pathNodes);
    }

    private static String getSmallestDistanceCode(HashMap<String, DistanceTo> minDistanceQueue, Stack<DistanceTo> visitedQueue) {
        float minValue = Float.MAX_VALUE;
        String smallestValueCode = "";
       
        for(DistanceTo dt : minDistanceQueue.values()){
            if(dt.getWeight() <= minValue && !visitedQueue.contains(dt)){
                minValue = dt.getWeight();
                smallestValueCode = dt.getDestinationCode();
            }
        }
        
        return smallestValueCode;
    }
    
    static void timeDijkstraAlg(HashMap<String, Airport> airportsGraph, String sourceCode, String destinationCode){
        
        if(!airportsGraph.containsKey(sourceCode) || !airportsGraph.containsKey(destinationCode))
            throw new IndexOutOfBoundsException("One or both of the specified airports do(es)n't exist");
        
        HashMap<String, DistanceTo> minTimeQueue = new HashMap<String, DistanceTo>();
        Stack<DistanceTo> visitedQueue = new Stack<DistanceTo>(); 
        String currentSource = sourceCode;
        HashSet<String> visitedAirports = new HashSet<String>();
        StopWatch stopWatch = new StopWatch();
        
        //construct priority queue
        for(String airportCode : airportsGraph.keySet()){
            float time = Float.MAX_VALUE;
            
            if(airportCode.equals(sourceCode))
                time = 0;
            
            minTimeQueue.put(airportCode, new DistanceTo(airportCode, time, ""));
        }
        
        while(!minTimeQueue.isEmpty()){
            
            DistanceTo current = minTimeQueue.remove(currentSource);
            visitedQueue.push(current);
            visitedAirports.add(current.getDestinationCode());
            
            if(current.getDestinationCode().equals(destinationCode))
                break;
            
            //getting the connection from the source airport
            Connection currentPointer = airportsGraph.get(current.getDestinationCode()).getConnection();
            
            while(currentPointer != null){
                
                if(!visitedAirports.contains(currentPointer.getDestination().getCode())){
                    //addiing 1h leg
                    float totalCost = current.getWeight() + currentPointer.getTime() + 1;

                    //compare if the weight of the source + the weight of the edge is less than the currently stored weight
                    if(totalCost < minTimeQueue.get(currentPointer.getDestination().getCode()).getWeight()){
                        //update the total cost
                        minTimeQueue.get(currentPointer.getDestination().getCode()).setWeight(totalCost);
                        //update the source to be the one where the weight from was the smallest
                        minTimeQueue.get(currentPointer.getDestination().getCode()).setSourceCode(currentSource);
                    }
                }
                
                currentPointer = currentPointer.getNext();
            }
            
            currentSource = getSmallestDistanceCode(minTimeQueue, visitedQueue);
        }
        
        
        System.out.println(" ------- \n");
        System.out.println("It took " + stopWatch.elapsedTime() + "ms");
        
        //getting the destination node and its source
        DistanceTo destinationNode = visitedQueue.pop();
        String fromNode = destinationNode.getSourceCode();
        ArrayList<String> pathNodes = new ArrayList<String>();
        
        //removing the final time leg
        float finalDistance = destinationNode.getWeight() - 1;
        
        pathNodes.add(destinationNode.getDestinationCode());
        
        //getting the shortest path
        while(!visitedQueue.empty()){
            destinationNode = visitedQueue.pop();
            
            if(fromNode.equals(destinationNode.getDestinationCode())){
                fromNode = destinationNode.getSourceCode();
                pathNodes.add(destinationNode.getDestinationCode());
            }
        }
        
        printPathNodes(pathNodes);
    }
    
    private static void printPathNodes(ArrayList<String> pathNodes){
        for(int i = pathNodes.size() - 1; i >= 0; i--){
            System.out.print(pathNodes.get(i));
            
            if(i != 0)
                System.out.print(" -> ");
        }
    }

}
