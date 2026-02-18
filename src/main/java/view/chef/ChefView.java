package view.chef;

import env.Env;
import model.MenuItem;
import model.Order;
import services.ChefService;
import util.ScannerUtil;

import java.util.List;
import java.util.Scanner;

public class ChefView {

    public static void chefView(String name){
        Scanner scanner = ScannerUtil.getScanner();
        while (true){
            System.out.println(String.format("Welcome, %s to %s", name, Env.RESTAURANTNAME));
            System.out.println("\nEnter your choice:\n1. View Current orders\n2. Update order status\n");
            int chefChoice = scanner.nextInt();

            switch (chefChoice){
                case 1: {
                    List<Order> orders = ChefService.viewCurrentOrders();
                    System.out.println("Order Id | Order Status | ItemName");
                    for (Order order: orders){
                        for (MenuItem menuItem : order.itemList){
                            System.out.println(String.format("%d\t%s\t%s",order.orderId, order.orderStatus, menuItem.name));
                        }
                    }
                    break;
                }
                case 2:{
                    List<Order> orders = ChefService.viewCurrentOrders();
                    System.out.println("Enter Order id: ");
                    int orderId = scanner.nextInt();
                    if(ChefService.updateOrderStatus(orderId)){
                        System.out.println("Order has been updated.");
                    }
                    break;
                }
            }
        }
    }
}
