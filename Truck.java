import java.util.*;

/**
 * Write a description of class Truck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Truck
{
    int capacity;
    int distance;
    City nextCity; // where the truck is nextly positioned
    ArrayList<Cargo> cargoList;
    PriorityQueue<City> city;
    boolean shipDone;
    ArrayList<String> visitedList;

    public Truck(City start){
        //currentWeight = 500;
        capacity = 500;
        cargoList = new ArrayList<Cargo>();
        //nextWarehouse = location;
        nextCity = start;
        distance = 0;
        shipDone = false;
        visitedList = new ArrayList<String>();
    }        

    public void setNextCity(City nextC){
        nextCity = nextC;
    }

    public boolean shipment(){
        PriorityQueue<Cargo> temp = nextCity.w.cargoList;
        ArrayList<Cargo> temp2 = new ArrayList<Cargo>();
        String str;
        if(temp.size() != 0){
            while(temp.size() != 0 && temp.peek().weight <= capacity){
                Cargo cTemp = temp.poll();
                capacity = capacity - cTemp.weight;
                cargoList.add(cTemp);
                temp2.add(cTemp); 
                nextCity.w.cargoArr.remove(0);
            } 
            int totalW = 0;
            String cargoStr = "";

            str = "Deliver to warehouse " + nextCity.w.nameW + " total weight: ";
            
            for(int i=0; i<temp2.size();i++){
                totalW+=temp2.get(i).weight;
                cargoStr+=temp2.get(i);  
                if(i!=temp2.size()-1){cargoStr+=", ";}
            }
            str+=totalW +" (["+cargoStr+"]) ";

            //int dist = nextCity.;
            //str+="dist: "+dist ; 
            if(totalW == 0) return false;
        }else return false;
        System.out.print(str);
        return true;
    }

    // print out the distance from a single point to the center city
    public void setDistance(int x){
        distance = distance+x;
        //System.out.println(distance);
    }

    public int getDistance(){
        return distance;
    }
}