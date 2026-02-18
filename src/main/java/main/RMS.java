package main;

import java.util.Scanner;

import env.Env;
import util.ScannerUtil;
import view.chef.ChefView;
import view.customer.CustomerView;
import view.manager.ManagerView;
import view.receptionist.ReceptionistView;
import view.waiter.WaiterView;

public class RMS {
    public static void main(String[] args) {
        Scanner scanner = ScannerUtil.getScanner();
        System.out.println("Welcome to " + Env.RESTAURANTNAME);

        while (true) {
            try {
                System.out.println("\n1. Login\n2. Exit");
                int choice = scanner.nextInt();

                if (choice == 2) {
                    System.out.println("Exiting...");
                    break;
                }

                if (choice == 1) {
                    System.out.print("Enter User ID: ");
                    int id = scanner.nextInt();

                    System.out.print("Enter Password: ");
                    String password = scanner.next();

                    model.User user = services.AuthService.login(id, password);

                    if (user.getUserType() == enums.UserType.INVALID) {
                        System.out.println("Invalid credentials. Please try again.");
                        continue;
                    }

                    System.out.println("Login Successful as " + user.getUserType());

                    switch (user.getUserType()) {
                        case MANAGER:
                            ManagerView.managerView(user.getName());
                            break;
                        case CUSTOMER:
                            CustomerView.customerView(user.getName());
                            break;
                        case CHEF:
                            ChefView.chefView(user.getName());
                            break;
                        case WAITER:
                            WaiterView.waiterView(user.getName());
                            break;
                        case RECEPTIONIST:
                            ReceptionistView.receptionistView(user.getName());
                            break;
                        default:
                            System.out.println("Role not supported yet.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
