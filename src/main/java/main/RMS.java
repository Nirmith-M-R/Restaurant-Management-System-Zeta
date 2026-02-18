package main;

import io.MenuItemsIO;
import model.MenuItem;
import model.User;
import services.AuthService;
import services.BookTableService;
import services.WaiterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RMS {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Your Restaurant 🏨");
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            int id;
            String password;
            choice = scanner.nextInt();
            System.out.println("LogIn as \n1.Waiter\n2.Chef\n3.Manager\n4.Customer\n5.Receptionist");
            System.out.println("Enter userId: ");
            id = scanner.nextInt();
            System.out.println("Enter password: ");
            password = scanner.next();
            User login = AuthService.login(id, password);
            break;
        }
        while(true){
            switch (choice) {
                case 1: {
                    System.out.println("Welcome, you waiter!");
                    System.out.println("1. View Menu\n2. Take Order\n3. Update order as Served");
                    int waiterChoice = scanner.nextInt();
                    switch (waiterChoice) {
                        case 1:
                            WaiterService.viewMenu();
                            break;
                        case 2: {
                            System.out.println("Enter orderId: ");
                            int orderId = scanner.nextInt();
                            System.out.println("Enter table number: ");
                            int tableNumber = scanner.nextInt();
                            List<MenuItem> menuItems = MenuItemsIO.loadFromFile();
                            WaiterService.takeOrder(orderId, tableNumber, menuItems);
                            System.out.println("Hey Waiter, Your order has been sent to kitchen");
                            break;
                        }
                        case 3: {
                            System.out.println("Enter orderId");
                            int orderId = scanner.nextInt();
                            WaiterService.updateOrderAsServed(orderId);
                            break;
                        }
                        default:
                            System.out.println("Please enter correct choice");

                    }
                }
            }
        }
    }
}
