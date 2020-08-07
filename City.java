import java.util.*;
/**
 * Write a description of class City here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class City implements Comparable<City>
{
    String name;
    List<Road> nbs = new ArrayList<Road>();
    int dist = Integer.MAX_VALUE;
    boolean visited = false;
    City prev;
    Warehouse w;
    ArrayList<Road> neigborList;
    //int id;
    int disFromStart;
    boolean shortPathDone;
    boolean truckVisited;

    public City(String n) {
        name = n;
        shortPathDone = false;
        truckVisited =  false;
    }

    @Override
    public int compareTo(City v) {
        if(dist == v.dist){
            return name.compareTo(v.name);
        }
        else return dist - v.dist;
    }

    public void setWarehouse(Warehouse w){this.w = w;}

    public Warehouse getWarehouse(Warehouse w){return w;}

    public ArrayList<Road> getNeigborList(){return neigborList;}
}
