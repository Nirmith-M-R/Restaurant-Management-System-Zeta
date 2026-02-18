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
            System.out.println("\n1. View Menu\n2. Take Order\n3. Update order as Served ");
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
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
