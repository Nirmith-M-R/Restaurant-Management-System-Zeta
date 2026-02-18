package view.customer;

import env.Env;
import model.MenuItem;
import services.CustomerService;
import services.MenuService;
import services.WaiterService;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerView {
    public static void customerView(String name) {
        Scanner scanner = ScannerUtil.getScanner();

        while (true) {
            try {
                System.out.println(String.format("Welcome, %s to %s", name, Env.RESTAURANTNAME));
                System.out.println("\n1. View Menu\n2. Check Availability of Tables\n3. Book a Table\n4. Logout");
                int customerChoice = scanner.nextInt();
                switch (customerChoice) {
                    case 1: {
                        CustomerService.viewMenu();
                        break;
                    }
                    case 2: {
                        CustomerService.checkTableAvailability();
                        break;
                    }
                    case 3: {
                        CustomerService.bookTable();
                        break;
                    }
                    case 4: {
                        System.out.println("Logging out...");
                        return;
                    }
                    default:
                        System.out.println("Invalid input");
                }
            } catch (Exception exception) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
