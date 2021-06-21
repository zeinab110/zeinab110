public class Feather extends Product {
    static final String nameOfProduct = "Feather";
    static final int timeOfCorrupting = 4;
    static final int Price = 20;
    static final int SizeInWareHouse = 1  ;
    static final int Time = 3 ;
    public Feather(int currentTime, int x_position, int y_position, boolean collected) {
        super(nameOfProduct,currentTime, x_position, y_position,collected );
    }
}
