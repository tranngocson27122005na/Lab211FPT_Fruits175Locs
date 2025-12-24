package model;

public class Order {
    private String fruitName;
    private int quantity;
    private double price;

    public Order(String fruitName, int quantity, double price) {
        this.fruitName = fruitName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return fruitName + " x" + quantity + " @ " + price + " => " + getTotalPrice();
    }
}
