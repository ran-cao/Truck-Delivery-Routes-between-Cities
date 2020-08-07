import java.util.*;
/**
 * Write a description of class Warehouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Warehouse
{
    // instance variables - replace the example below with your own
    PriorityQueue<Cargo> cargoList;
    ArrayList<Integer> cargoArr;
    String nameW;
    
    /**
     * Constructor for objects of class Warehouse
     */
    public Warehouse(Cargo[] cl, String warehouseName)
    {
        // initialise instance variables
        cargoList = new PriorityQueue<Cargo>();
        cargoArr = new ArrayList<Integer>();
        
        for(Cargo i:cl){
            cargoList.add(i);
            cargoArr.add(i.weight);
        }
        Collections.sort(cargoArr);
        
        nameW = warehouseName;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public PriorityQueue<Cargo> getCargoList(){return cargoList;}
    
    public String getName(){return nameW;}
    
}
