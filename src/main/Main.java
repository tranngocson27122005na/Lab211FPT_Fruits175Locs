package main;

import controller.FruitShopSystem;
import utilities.Utilities;

public class Main {
    public static void main(String[] args) {
        FruitShopSystem shop = new FruitShopSystem();
        System.out.println(Utilities.getCurrentTimeAndDate());

        shop.addDefaultFruit();
        shop.getChoice();
    }
}
