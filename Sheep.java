public class Sheep extends Animal{
    static int number;
    public Sheep() {
        super();
        this.sort=number;
        number++;
        this.name="sheep" ;
        this.SizeInWareHouse=1;
        this.timePeriod=5;
        this.Price=400;
    }
}
