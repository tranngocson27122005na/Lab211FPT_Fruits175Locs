package controller;

import model.Order;
import java.util.ArrayList;

public class Invoice {
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String shoppingTime;
    private ArrayList<Order> orders = new ArrayList<>();
    private double totalAmount;

    public Invoice(String customerName, String customerPhone, String customerAddress, String shoppingTime, ArrayList<Order> orders) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.shoppingTime = shoppingTime;
        this.orders = new ArrayList<>(orders); 
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getShoppingTime() {
        return shoppingTime;
    }

    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders); 
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (Order order : orders) {
            totalAmount += order.getTotalPrice();
        }
        return totalAmount;
    }
    
}//end class
