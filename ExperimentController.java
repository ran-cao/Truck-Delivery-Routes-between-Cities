import java.util.*;
import java.io.*;
/**
 * The ExperimentController class works for conducting the experiment
 * 
 * @author Zhanfan Yu
 * @author Ran Cao
 * @version 12/7/2018
 */
public class ExperimentController
{
    private City centerCity;
    private ArrayList<Truck> truckList;
    int distTravelled = 0;
    int TotalDis = 0;
    int totalWeight = 0;
    /**
     * Main!
     */
    public static void main(String[] args){
        ExperimentController r = new ExperimentController();
        r.run();
    }

    /**
     * Constructor for objects of class ExperimentController
     */
    public ExperimentController(){
        truckList = new ArrayList<Truck>();
    }

    /**
     * The run method read files first and run the experiment
     */
    public void run(){
        //read the center file
        readCenter();
        //read the road file
        readRoads();
        //read the warehouse file
        readWarehouses();
        //calculate shortest path for the center city
        shortestPath(centerCity,true);

        int i = 0;
        Truck t;
        do{        
            t = new Truck(centerCity);
            System.out.println("Truck "+ (i+1) +":");
            //send the truck from the start city
            truckStart(centerCity,t);
            System.out.println("Distance traveled:"+ (t.getDistance()+distTravelled));
            //calculate the total distance
            TotalDis += t.getDistance()+distTravelled;
            distTravelled = 0;
            System.out.println();
            i++;
            //totalWeight = totalWeight + 500 - t.capacity;
        }while(t.distance != 0);         
        //print out the total distance
        System.out.println("Total distance traveled:" + TotalDis);

        //System.out.println("Total weight send:" + totalWeight);
    }  

    /**
     * Read the Center file
     */
    public void readCenter(){
        try{
            File centerF = new File("center.txt");
            Scanner sc = new Scanner(centerF);
            String center = sc.nextLine();
            //initial the global variable of the center
            centerCity = new City(center);
            vertexMap.put(center,centerCity);
            sc.close();
        }catch(Exception e){System.out.println("Exception Occurrred"+e);}
    }

    /**
     * Read the road file
     */
    public void readRoads(){
        try{
            File centerF = new File("roads-2.txt");
            Scanner sc = new Scanner(centerF);
            sc.nextLine();
            while(sc.hasNextLine()){
                //read the line
                String line = sc.nextLine();
                //split the line into string array
                String[] words = line.split(" ");
                //add road to the graph
                addRoad(words[0],words[1],Integer.parseInt(words[2]));
            }
            sc.close();
        }catch(Exception e){System.out.println("Exception Occurrred"+e);} 
    }

    /**
     * Read the warehouse file
     */
    public void readWarehouses(){
        try{
            File centerF = new File("warehouses-2.txt");
            Scanner sc = new Scanner(centerF);
            sc.nextLine();
            while(sc.hasNextLine()){
                //read the line
                String line = sc.nextLine();
                //split the line into string array
                String[] words = line.split(" ");
                //add road to the graph
                City temp = getCity(words[0]);
                int cargoSize = words.length - 1;
                Cargo[] tempList = new Cargo[cargoSize]; // a cargo list for each warehouse
                int cargoNum = 1;
                for(int i=1; i<words.length;i++){
                    tempList[i-1] = new Cargo(Integer.parseInt(words[i]),words[0],cargoNum); // can be replaced by i
                    cargoNum++;
                }
                Warehouse tempW = new Warehouse(tempList,words[0]);
                temp.w = tempW;
            }
            sc.close();
        }catch(Exception e){System.out.println("Exception Occurrred"+e);} 
    }

    /**
     * Get the next City for the truck
     *
     * @param  currentC  the city that the truck currently at
     * @param  t  the truck
     * @return    the road to next city
     */
    public Road nextCity(City currentC, Truck t){
        //creat priority queue for sorting the road
        PriorityQueue<Road> pRoad = new PriorityQueue<Road>();
        for(Road i: currentC.neigborList){//go over all the element in the  neigborlist
            int capacity = t.capacity;
            int j = 0;
            if(i.v.w.cargoArr.size() != 0 && i.v.w.cargoArr.get(0)<=capacity){
                //if the city satisfied the requirement 
                //we calculate how many cargos we can ship to the warehouse
                while(j<i.v.w.cargoArr.size() && i.v.w.cargoArr.get(j) <= capacity){                   
                    capacity-= i.v.w.cargoArr.get(j);                    
                    j++;
                }
                //set the number of cargos we can ship to the warehouse
                i.setCompare(j);
                //add the road to the priority queue
                pRoad.add(i);
            }
        }

        //return the nonvisited road. if not return null.
        if(pRoad.size()!=0){
            while(pRoad.peek().v.truckVisited){
                pRoad.poll();
                if(pRoad.size()==0)return null;
            }
        }else return null;
        return pRoad.poll();

    }

    /**
     * Run the truck from the given city
     * 
     * @param  nextC  the city that the truck will travel next
     * @param   t  the truck 
     */
    public void truckStart(City currentC, Truck t){
        if(!currentC.shortPathDone) shortestPath(currentC,false);
        int size = currentC.neigborList.size(); 

        if(size != 0){
            //if(!currentC.neigborList.get(i).v.truckVisited && currentC.neigborList.get(i).v.w.cargoList.size() != 0){
            Road road = nextCity(currentC,t);
            if(road != null){
                truckTravel(road.v,t);
                City nextCity = road.v;
                t.visitedList.add(nextCity.name);                        
                distTravelled+=road.w; 
                int disPerCargo = (500 - t.capacity)/road.w;
                System.out.println("dist: "+road.w);
                //System.out.println("dist: "+road.w +" distance per cargo: "+ disPerCargo);
                truckStart(nextCity,t);
            }
            else if(!t.shipDone) {
                t.setDistance(currentC.disFromStart);
                t.shipDone = true;
                for(String i: t.visitedList){getCity(i).truckVisited = false;}
            }
        }

    }

    /**
     * Check whether travel is possible
     *
     * @param  nextC  the city that the truck will travel next
     * @param   t  the truck 
     * @return    boolean value that whether the truck will travel or not
     */
    public boolean truckTravel(City nextC, Truck t){
        t.setNextCity(nextC);
        return t.shipment();
    }

    Map<String, City> vertexMap = new HashMap<String, City>(); 
    /**
     * Add an undirected edge
     *
     * @param  start  the name of the start city
     * @param  end  the name of the end city
     * @param  w  the weighted number for edge
     */
    public void addRoad(String start, String end, int w) {
        City u = getCity(start);
        City v = getCity(end);
        u.nbs.add(new Road(u, v, w));
        v.nbs.add(new Road(v, u, w));
    }

    /**
     * Add a directed edge
     *
     * @param  start  the name of the start city
     * @param  end  the name of the end city
     * @param  w  the weighted number for edge
     */
    public void addDirectedRoad(String start, String end, int w) {
        City u = getCity(start);
        City v = getCity(end);
        u.nbs.add(new Road(u, v, w));
    }

    /**
     * Retrieve vertex associated with the given name
     *
     * @param  name  the name of the city
     * @return    the city
     */
    private City getCity(String name) {
        City v = vertexMap.get(name);
        if (v == null) {
            v = new City(name);
            vertexMap.put(name, v);
        }
        return v;
    }

    /**
     * find the shortest path from the given start vertex
     *
     * @param  startCity  the start city
     * @param  center  whether the city is the center or not
     */
    public void shortestPath(City startCity, boolean center) {
        reset();
        PriorityQueue<City> q = new PriorityQueue<City>();
        ArrayList<Road> arr = new ArrayList<Road>();
        q.add(startCity);
        startCity.dist = 0;
        startCity.shortPathDone = true;

        while (!q.isEmpty()) {
            City u = q.poll();
            if (u.visited) continue;
            u.visited = true;

            //add the city to the neighbor list
            if(u.name != startCity.name && u.w != null) arr.add(new Road(startCity, u, u.dist));

            //store the distance betweent the city and the center in the city
            if(center) u.disFromStart = u.dist;

            //System.out.println(u.name + " " + u.dist + " " + ((u.prev==null)?"":u.prev.name));
            for (Road e: u.nbs) {
                City v = e.v;
                if (v.dist > u.dist + e.w) {
                    q.remove(v);
                    v.dist = u.dist + e.w;
                    v.prev = u;
                    q.add(v);
                }
            }
        }

        startCity.neigborList = arr;
    }

    /**
     * Reset the parameters of all vertices
     */
    public void reset() {
        for (City v: vertexMap.values()) {
            v.dist = Integer.MAX_VALUE;
            v.visited = false;
            v.prev = null;
        }
    }
}
