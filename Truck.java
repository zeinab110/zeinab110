
import java.util.ArrayList;

public class Truck {
    private int final_capacity;
    private int time_needed;
    private int currunt_capacity;
    ArrayList<String> inTruck;


    public Truck() {
        this.final_capacity = 20;
        this.time_needed = 10;
        this.inTruck = new ArrayList<>();
    }

    public boolean Load(String name, int capacity){
        if (currunt_capacity+capacity<=final_capacity){
            inTruck.add(name);
            //TODO
            return true;
        }
        else {
            //TODO
            return false;
        }
    }

    public boolean Unload (String name){
        boolean found = false;
        int index = -1;
        for (int i=0 ; i<inTruck.size() ; i++){
            if (inTruck.get(i).equals(name)){
                found = true;
                index = i;
                //TODO
                break;
            }
            else {
                found = false;
                index = -1;
                //TODO
            }
        }
        if (found){
            if (inTruck.remove(name)){
                //TODO
                return true;
            }
            else return false;
        }
        else {
            //TODO
            return false;
        }
    }

    public boolean Go (){
        //TODO
        return false;
    }
}

