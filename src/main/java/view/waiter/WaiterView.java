package view.waiter;

import env.Env;
import model.MenuItem;
import model.Order;
import services.MenuService;
import services.WaiterService;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaiterView {
    public static void waiterView(String name){
        Scanner scanner = ScannerUtil.getScanner();

        while(true){
            System.out.println(String.format("Welcome, %s to %s", name, Env.RESTAURANTNAME));
            System.out.println("\n1. View Menu\n2. Take Order\n3. Update order as Served ");
            int waiterChoice = scanner.nextInt();
            switch (waiterChoice){
                case 1:{
                    WaiterService.viewMenu();
                    break;
                }
                case 2: {
                    System.out.println("Taking Order:");
                    List<MenuItem> menuItems = new ArrayList<>();
                    System.out.println("Enter Order ID, table number");
                    int orderId = scanner.nextInt();
                    int tableNumber = scanner.nextInt();

                    while(true){
                        System.out.println("Enter item ID and quantity: ");
                        String itemId = scanner.next();
                        int quantity = scanner.nextInt();
                        if(quantity == -1){
                            break;
                        }
                        while(quantity!=0){
                            menuItems.add(MenuService.getMenuItem(itemId));
                            quantity-=1;
                        }
                    }
                    WaiterService.takeOrder(orderId, tableNumber, menuItems);
                    System.out.println("Order has been placed.");
                    break;
                }
                case 3 : {
                    System.out.println("Update Order as Served: ");
                    System.out.println("Enter order id");
                    int orderId = scanner.nextInt();
                    WaiterService.updateOrderAsServed(orderId);
                    break;
                }
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
