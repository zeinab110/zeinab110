public class Milk extends Product{
    static final String nameOfProduct = "Milk";
    static final int timeOfCorrupting = 4;
    static final int Price = 25 ;
    static final int SizeInWareHouse = 1 ;
    static final int Time = 5 ;
    public Milk(int currentTime, int x_position, int y_position, boolean collected) {
        super(nameOfProduct,currentTime, x_position, y_position, collected);
    }
}
