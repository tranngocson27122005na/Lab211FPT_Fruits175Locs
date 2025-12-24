package model;

public class Fruit {
    private String fruitId;
    private String fruitName;
    private double fruitPrice;
    private int quantity;
    private String origin;

    public Fruit(String fruitId, String fruitName, double fruitPrice, int quantity, String origin) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.fruitPrice = fruitPrice;
        this.quantity = quantity;
        this.origin = origin;
    }

    public String getFruitId() {
        return fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public double getFruitPrice() {
        return fruitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Override
    public String toString() {
        return fruitId + " - " + fruitName + " (" + origin + ") " + fruitPrice + "$ qty:" + quantity;
    }
}
