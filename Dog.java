public class Dog extends Animal{
    static int number=0;
    public Dog() {
        super();
        this.sort=number;
        number++;
        this.name="dog" ;
        this.SizeInWareHouse=2;
        this.timePeriod=0;
        this.Price=100;
    }
}
