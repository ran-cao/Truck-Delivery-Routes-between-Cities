
/**
 * Write a description of class Cargo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cargo implements Comparable<Cargo>
{
    // instance variables - replace the example below with your own
    int weight;
    String warehouseName;
    int number;
    
    /**
     * Constructor for objects of class Cargo
     */
    public Cargo(int weight, String warehouseName, int number)
    {
        // initialise instance variables
        this.weight = weight;
        this.warehouseName = warehouseName;
        this.number = number;
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String toString(){
        return warehouseName+"("+number+")"+": "+weight;
    }
    
    public int compareTo(Cargo c){
        return weight - c.weight;
    }
    
    public int getWeight(){return weight;}
    
    public String getWarehouseName(){return warehouseName;}
    
    public int getNumber(){return number;}
}
