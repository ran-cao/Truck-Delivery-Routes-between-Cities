
/**
 * Write a description of class Road here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Road implements Comparable<Road>
{
    City u, v;
    int w;
    int numbOfCargo;

    public Road(City u, City v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public void setCompare(int numbOfCargo){this.numbOfCargo = numbOfCargo;}

    @Override
    public int compareTo(Road r){ 
        int temp1 = w/(numbOfCargo);
        int temp2 = r.w/(r.numbOfCargo);
        if(w>35) temp1 = temp1*2;
        if(r.w>35) temp2 = temp2*2;

        if(temp1 == temp2) return w - r.w;
        else if(temp1<temp2) return -1;
        else return 1;

        // int temp1 = numbOfCargo;
        // int temp2 = r.numbOfCargo;

        // if(temp1 == temp2) return w - r.w;
        // else if(temp1<temp2) return 1;
        // else return -1;
    }
}
