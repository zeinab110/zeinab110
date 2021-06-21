public class Eggs extends Product {
    static final String nameOfProduct = "Egg";
    static final int timeOfCorrupting = 4;
    static final int Price = 15 ;
    static final int SizeInWareHouse = 1 ;
    static final int Time = 2 ;
    public Eggs(int currentTime, int x_position, int y_position, boolean collected) {
        super(nameOfProduct,currentTime, x_position, y_position, collected);
    }
}
