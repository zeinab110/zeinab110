public class Product {
    String nameOfProduct;
    int X_position;
    int Y_position;
    int PX_position;
    int PY_position;
    int CurrentTime;
    boolean Collected;
    int SizeInWarehouse;
    int Price;

    public Product(String nameOfProduct ,int currentTime, int x_position, int y_position, boolean harvested) {
        CurrentTime = currentTime;
        X_position = x_position;
        Y_position = y_position;
        PX_position = x_position;
        PY_position = y_position;
        Collected = harvested;
        this.nameOfProduct = nameOfProduct;
    }


    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }


    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public int getSizeInWarehouse() {
        return SizeInWarehouse;
    }

    public void setSizeInWarehouse(int sizeInWarehouse) {
        SizeInWarehouse = sizeInWarehouse;
    }
    public int getX_position() {
        return X_position;
    }

    public int getY_position() {
        return Y_position;
    }

    public int getPX_position() {
        return PX_position;
    }

    public void setPX_position(int PX_position) {
        this.PX_position = PX_position;
    }

    public int getPY_position() {
        return PY_position;
    }

    public void setPY_position(int PY_position) {
        this.PY_position = PY_position;
    }
    public void setCurrentTime(int currentTime) {
        CurrentTime = currentTime;
    }

    public void setX_position(int x_position) {
        X_position = x_position;
    }

    public void setY_position(int y_position) {
        Y_position = y_position;
    }

    public void setCollected(boolean collected) {
        Collected = collected;
    }

    public boolean isCollected() {
        return Collected;
    }
    public int getCurrentTime() {
        return CurrentTime;
    }
}
