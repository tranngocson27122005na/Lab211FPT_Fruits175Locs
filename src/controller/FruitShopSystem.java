package controller;

import utilities.Utilities;
import model.Fruit;
import model.Order;
import java.util.ArrayList;

public class FruitShopSystem {
    private ArrayList<Fruit> fruitList = new ArrayList<>();
    private ArrayList<Order> currentOrder = new ArrayList<>();
    private ArrayList<Invoice> invoices = new ArrayList<>();

    public void addDefaultFruit() {
        fruitList.add(new Fruit("F001", "Tao", 1.5, 100, "Viet Nam"));
        fruitList.add(new Fruit("F002", "Chuoi", 0.8, 150, "Viet Nam"));
        fruitList.add(new Fruit("F003", "Cam", 2.0, 120, "Viet Nam"));
        fruitList.add(new Fruit("F004", "Xoai", 2.5, 80, "Viet Nam"));
        fruitList.add(new Fruit("F005", "Dua Hau", 1.2, 90, "Viet Nam"));
        fruitList.add(new Fruit("F006", "Oi", 1.0, 110, "Viet Nam"));
        fruitList.add(new Fruit("F007", "Mit", 1.8, 70, "Viet Nam"));
        fruitList.add(new Fruit("F008", "Sau Rieng", 3.5, 60, "Viet Nam"));
        fruitList.add(new Fruit("F009", "Thanh long", 1.3, 100, "Viet Nam"));
        fruitList.add(new Fruit("F010", "Chom Chom", 2.2, 75, "Viet Nam"));
    }

    private boolean isFruitExist(String fruitId) {
        for (Fruit f : fruitList) {
            if (f.getFruitId().equalsIgnoreCase(fruitId.trim())) {
                return true;
            }
        }
        return false;
    }

    private Fruit findFruitByName(String name) {
        for (Fruit f : fruitList) {
            if (f.getFruitName().equalsIgnoreCase(name.trim())) {
                return f;
            }
        }
        return null;
    }

    public void createFruit() {
        while (true) {
            System.out.println("Enter fruit ID:");
            String id = Utilities.getString();

            if (isFruitExist(id)) {
                System.out.println("Fruit ID already exists. Please enter a different ID.");
                continue;
            }

            System.out.println("Enter fruit name:");
            String name = Utilities.getString();
            Fruit existing = findFruitByName(name);
            if (existing != null) {
                System.out.println("Fruit name already exists!");
                System.out.println("Enter additional quantity to add:");
                int addQty = Utilities.getInt();
                existing.setQuantity(existing.getQuantity() + addQty);
                System.out.println("Updated quantity: " + existing.getQuantity());
                return;
            }

            System.out.println("Enter fruit price:");
            double price = Utilities.getDouble();
            System.out.println("Enter fruit quantity:");
            int quantity = Utilities.getInt();
            System.out.println("Enter fruit origin:");
            String origin = Utilities.getString();

            fruitList.add(new Fruit(id, name, price, quantity, origin));
            System.out.println("Fruit added successfully!");

            System.out.println("Add another fruit? (yes/no)");
            String choice = Utilities.getString().trim().toUpperCase();
            if (!choice.equals("YES")) {
                displayFruitList();
                break;
            }
        }
    }

    public void displayFruitList() {
        if (fruitList.isEmpty()) {
            System.out.println("No fruits available!");
            return;
        }

        System.out.printf("\n%-5s %-20s %-12s %8s\n", "No.", "Fruit Name", "Origin", "Price($)");
        for (int i = 0; i < fruitList.size(); i++) {
            Fruit f = fruitList.get(i);
            System.out.printf("%-5d %-20s %-12s %8.2f\n", 
                    i + 1, f.getFruitName(), f.getOrigin(), f.getFruitPrice());
        }
    }

    public void shopping() {
        if (fruitList.isEmpty()) {
            System.out.println("No fruits available!");
            return;
        }

        while (true) {
            displayFruitList();
            System.out.print("Select item number: ");
            int itemNumber = Utilities.getInt();
            while (itemNumber < 1 || itemNumber > fruitList.size()) {
                System.out.print("Invalid number. Select again: ");
                itemNumber = Utilities.getInt();
            }

            Fruit selected = fruitList.get(itemNumber - 1);
            System.out.println("You selected: " + selected.getFruitName());
            System.out.print("Enter quantity: ");
            int qty = Utilities.getInt();

            if (qty > selected.getQuantity()) {
                System.out.println("Not enough quantity! Available: " + selected.getQuantity());
                continue;
            }

            selected.setQuantity(selected.getQuantity() - qty);
            currentOrder.add(new Order(selected.getFruitName(), qty, selected.getFruitPrice()));
            System.out.println("Item added to order.");

            System.out.println("Add another item? (yes/no)");
            String choice = Utilities.getString().trim().toUpperCase();
            if (!choice.equals("YES")) {
                if (!currentOrder.isEmpty()) {
                    displayOrderSummary(currentOrder);

                    System.out.print("Enter your name: ");
                    String name = Utilities.getString();
                    System.out.print("Enter your phone: ");
                    String phone = Utilities.getString();
                    System.out.print("Enter your address: ");
                    String address = Utilities.getString();

         
                    invoices.add(new Invoice(name, phone, address,
                            Utilities.getCurrentTimeAndDate(), currentOrder));
                    currentOrder.clear();
                    System.out.println("Order placed successfully!");
                }
                break;
            }
        }
    }

    private void displayOrderSummary(ArrayList<Order> orderList) {
        if (orderList.isEmpty()) {
            System.out.println("No items in order!");
            return;
        }

        double total = 0;
        System.out.println("\nProduct        | Quantity | Price($) | Amount($)");
        for (Order o : orderList) {
            double amount = o.getTotalPrice();
            total += amount;
            System.out.printf("%-15s %-9d %-9.2f %-9.2f\n", 
                    o.getFruitName(), o.getQuantity(), o.getPrice(), amount);
        }
        System.out.printf("Total: %.2f$\n", total);
    }

    public void viewInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoices available!");
            return;
        }

        for (Invoice inv : invoices) {
            System.out.println("\nCustomer Name: " + inv.getCustomerName());
            System.out.println("Phone: " + inv.getCustomerPhone());
            System.out.println("Address: " + inv.getCustomerAddress());
            System.out.println("Shopping Time: " + inv.getShoppingTime());

            for (Order o : inv.getOrders()) {
                System.out.printf("Product: %-12s | Quantity: %-5d | Price: %-6.2f | Amount: %-6.2f\n",
                        o.getFruitName(), o.getQuantity(), o.getPrice(), o.getTotalPrice());
            }
            System.out.printf("Total Amount: %.2f$\n", inv.getTotalAmount());
            System.out.println("--------------------------------------------------");
        }
    }

    public void displayMenu() {
        System.out.println("\nFRUIT SHOP SYSTEM");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.print("(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program): ");
    }

    public void getChoice() {
        while (true) {
            displayMenu();
            int choice = Utilities.getIntInRange(1, 4);
            switch (choice) {
                case 1:
                    createFruit();
                    break;
                case 2:
                    viewInvoices(); 
                    break;
                case 3:
                    shopping();
                    break;
                case 4:
                    System.out.println("Exiting the system. Thank you for using the program!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
}// end class




/*
package controller;

import model.Fruit;
import model.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FruitShopSystem {
    private ArrayList<Fruit> fruitList = new ArrayList<>();
    private ArrayList<Order> currentOrder = new ArrayList<>();
//    private Map<String, ArrayList<Order>> orders = new HashMap<>(); // customerName -> order items
    private ArrayList<Invoice> invoices = new ArrayList<>();

    public void addDefaultFruit() {
        fruitList.add(new Fruit("F001", "Táo", 1.5, 100, "Việt Nam"));
        fruitList.add(new Fruit("F002", "Chuối", 0.8, 150, "Việt Nam"));
        fruitList.add(new Fruit("F003", "Cam", 2.0, 120, "Việt Nam"));
        fruitList.add(new Fruit("F004", "Xoài", 2.5, 80, "Việt Nam"));
        fruitList.add(new Fruit("F005", "Dưa hấu", 1.2, 90, "Việt Nam"));
        fruitList.add(new Fruit("F006", "Ổi", 1.0, 110, "Việt Nam"));
        fruitList.add(new Fruit("F007", "Mít", 1.8, 70, "Việt Nam"));
        fruitList.add(new Fruit("F008", "Sầu riêng", 3.5, 60, "Việt Nam"));
        fruitList.add(new Fruit("F009", "Thanh long", 1.3, 100, "Việt Nam"));
        fruitList.add(new Fruit("F010", "Chôm chôm", 2.2, 75, "Việt Nam"));
    }
    
    
    private boolean isFruitExist(String fruitId) {
        for (Fruit fruit : fruitList) {
            if (fruit.getFruitId().equalsIgnoreCase(fruitId.trim())) {
                return true;
            }
        }
        return false;
    }

    public void createFruit() {
        while (true) {
            System.out.println("Enter fruit ID:");
            String id = Utilities.getString();

            if (isFruitExist(id)) {
                System.out.println("Fruit ID already exists. Please enter a different ID.");
                continue;
            }

            System.out.println("Enter fruit name:");
            String name = Utilities.getString();
            Fruit existing = findFruitByName(name);
            if (existing != null) {
                System.out.println("Fruit name " + name + " exists already!");
                System.out.println("Enter additional quantity to add to existing fruit:");
                int additionalQuantity = Utilities.getInt();
                existing.setQuantity(existing.getQuantity() + additionalQuantity);
                System.out.println("Updated. New qty: " + existing.getQuantity());
                return;
            }

            System.out.println("Enter fruit price:");
            double price = Utilities.getDouble();
            System.out.println("Enter fruit quantity:");
            int quantity = Utilities.getInt();
            System.out.println("Enter fruit origin:");
            String origin = Utilities.getString();
            fruitList.add(new Fruit(id, name, price, quantity, origin));
            System.out.println("Fruit added successfully!");
            System.out.println("Do you want to add another fruit? (yes/no)");
            String choice = Utilities.getString().trim().toUpperCase();
            if (!choice.equals("YES")) {
                displayFruitList();
                break;
            }
        }
    }

    public void displayFruitList() {
        if (fruitList.isEmpty()) {
            System.out.println("No fruits available!");
            return;
        }

        System.out.println("\nList of Fruit:");
        System.out.printf("%-5s %-20s %-12s %8s\n", "No.", "Fruit Name", "Origin", "Price($)");
        for (int i = 0; i < fruitList.size(); i++) {
            Fruit fruit = fruitList.get(i);
            System.out.printf("%-5d %-20s %-12s %8.2f\n", (i + 1), fruit.getFruitName(),
                    fruit.getOrigin(), fruit.getFruitPrice());
        }
    }

    private Fruit findFruitByName(String name) {
        for (Fruit f : fruitList) {
            if (f.getFruitName().equalsIgnoreCase(name.trim())) {
                return f;
            }
        }
        return null;
    }

    public void shopping() {
        if (fruitList.isEmpty()) {
            System.out.println("No fruits available for shopping!");
            return;
        }
        while (true) {
            displayFruitList();
            System.out.print("Select item number: ");
            int itemNumber = Utilities.getInt();
            while ((itemNumber - 1) < 0 || (itemNumber - 1) >= fruitList.size()) {
                System.out.print("Invalid item number. Please select a valid item number: ");
                itemNumber = Utilities.getInt();
            }
            Fruit selectedFruit = fruitList.get(itemNumber - 1);
            System.out.println("You selected: " + selectedFruit.getFruitName());
            System.out.print("Enter quantity: ");
            int quantity = Utilities.getInt();
            if (quantity > selectedFruit.getQuantity()) {
                System.out.println("Insufficient quantity available. Available " + selectedFruit.getFruitName() + " quantity: " + selectedFruit.getQuantity());
                continue;
            }
            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);
            currentOrder.add(new Order(selectedFruit.getFruitName(), quantity, selectedFruit.getFruitPrice()));
            System.out.println("Item added to order.");
            System.out.println("Do you want to add another item? (yes/no)");
            String choice = Utilities.getString().toUpperCase();
            if (!choice.equals("YES")) {
                if (!currentOrder.isEmpty()) {
                    displayOrderSummary(currentOrder);
                    System.out.print("Input your name: ");
                    String customerName = Utilities.getString();
                    System.out.print("Input your phone number: ");
                    String phoneNumber = Utilities.getString();
                    System.out.print("Input your address: ");
                    String address = Utilities.getString();

                    // Save invoice (copy of current order)
                    invoices.add(new Invoice(customerName, phoneNumber, address, Utilities.getCurrentTimeAndDate(), currentOrder));
                    // Save orders map -> store a copy so clearing currentOrder won't affect saved data
                    orders.put(customerName, new ArrayList<>(currentOrder));

                    System.out.println("Order placed successfully!");
                    currentOrder.clear(); // Clear current order after saving
                }
                break;
            }
        }
    }

    private void displayOrderSummary(ArrayList<Order> order) {
        if (order.isEmpty()) {
            System.out.println("No items in this order!");
            return;
        }

        // Aggregate by fruit name
        Map<String, Order> aggregated = new HashMap<>();
        for (Order item : order) {
            String name = item.getFruitName();
            if (aggregated.containsKey(name)) {
                Order ex = aggregated.get(name);
                ex.setQuantity(ex.getQuantity() + item.getQuantity());
            } else {
                aggregated.put(name, new Order(name, item.getQuantity(), item.getPrice()));
            }
        }

        System.out.println("\nProduct        | Quantity | Price($) | Amount($)");
        double total = 0;
        for (Order item : aggregated.values()) {
            double amount = item.getTotalPrice();
            total += amount;
            System.out.printf("%-15s %-9d %-9.2f %-9.2f\n",
                    item.getFruitName(), item.getQuantity(), item.getPrice(), amount);
        }
        System.out.printf("Total: %.2f$\n", total);
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available!");
            return;
        }

        for (String customer : orders.keySet()) {
            System.out.println("\nCustomer: " + customer);
            displayOrderSummary(orders.get(customer));
        }
    }

    public void viewInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoices available!");
            return;
        }

        for (Invoice invoice : invoices) {
            System.out.println("\nCustomer Name: " + invoice.getCustomerName());
            System.out.println("Phone: " + invoice.getCustomerPhone());
            System.out.println("Address: " + invoice.getCustomerAddress());
            System.out.println("Shopping Time: " + invoice.getShoppingTime());

            for (Order order : invoice.getOrders()) {
                System.out.printf("Product: %-12s | Quantity: %-5d | Price: %-6.2f | Amount: %-6.2f\n",
                        order.getFruitName(), order.getQuantity(), order.getPrice(), order.getTotalPrice());
            }
            System.out.printf("Total Amount: %.2f$\n", invoice.getTotalAmount());
            System.out.println("--------------------------------------------------");
        }
    }

    public void displayMenu() {
        System.out.println("\nFRUIT SHOP SYSTEM");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.print("(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program): ");
    }

    public void getChoice() {
        while (true) {
            displayMenu();
            int choice = Utilities.getIntInRange(1, 4);
            switch (choice) {
                case 1:
                    createFruit();
                    break;
                case 2:
                    viewInvoices(); // or viewOrders() depending teacher's requirement
//                    viewOrders();
                    break;
                case 3:
                    shopping();
                    break;
                case 4:
                    System.out.println("Exiting the system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
}// end class

    public void displayMenu() {
        System.out.println("\nFRUIT SHOP SYSTEM");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.print("(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program): ");
    }

    public void getChoice() {
        while (true) {
            displayMenu();
            int choice = Utilities.getIntInRange();
            switch (choice) {
                case 1:
                    createFruit();
                    break;
                case 2:
//                    viewOrders();
                    viewInvoices();
                    break;
                case 3:
                    shopping();
                    break;
                case 4:
                    System.out.println("Exiting the system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
*/