package view.receptionist;

import env.Env;
import services.ReceptionistService;
import util.ScannerUtil;

import java.util.Scanner;

public class ReceptionistView {
    public static void receptionistView(String name) throws Exception {
        Scanner scanner = ScannerUtil.getScanner();

        while(true){
            System.out.println(String.format("Welcome, %s to %s", name, Env.RESTAURANTNAME));
            try {
                System.out.println("\n1. Book Table\n2. Generate Bill\n3. Recieve Payment\n4. Logout");
                int receptionistChoice = scanner.nextInt();
                switch (receptionistChoice){
                    case 1:{
                        ReceptionistService.bookTableForWalkIns();
                        break;
                    }
                    case 2:{
                        System.out.println("Enter Table Number for Bill:");
                        int tableNumber = scanner.nextInt();
                        ReceptionistService.generateBill(tableNumber);
                        break;
                    }
                    case 3:{
                        System.out.println("Enter Table Number for Receiving Payment: ");
                        int tableNumber = scanner.nextInt();
                        ReceptionistService.receivePayment(tableNumber);
                        break;
                    }
                    case 4:{
                        System.out.println("Logging out...");
                        return;
                    }
                    default:
                        System.out.println("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
