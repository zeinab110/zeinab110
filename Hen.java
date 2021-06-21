public class Hen extends Animal {
    static int number=0;
    public Hen() {
        super();
        this.sort=number;
        number++;
        this.name="hen" ;
        this.SizeInWareHouse=1;
        this.timePeriod=2;
        this.Price=100;
    }
}

