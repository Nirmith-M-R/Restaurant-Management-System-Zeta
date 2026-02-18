package view.manager;

import services.ManagerService;
import util.ScannerUtil;
import java.util.Scanner;
import services.CustomerService;

public class ManagerView {

    public static void managerView(String name) {
        Scanner scanner = ScannerUtil.getScanner();

        while (true) {
            try{
                System.out.println(String.format("Welcome Manager %s!", name));
                System.out.println("\n1. View Menu");
                System.out.println("2. Add Menu Item");
                System.out.println("3. Manage Staff");
                System.out.println("4. Add Staff");
                System.out.println("5. View Sales Reports");
                System.out.println("6. View Orders Reports");
                System.out.println("7. Generate Bill");
                System.out.println("8. Receive Payment");
                System.out.println("9. Logout");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        CustomerService.viewMenu();
                        break;
                    case 2:
                        System.out.println("Enter Item ID: ");
                        String id = scanner.next();
                        System.out.println("Enter Item Name: ");
                        String itemName = scanner.next();
                        System.out.println("Enter Price: ");
                        double price = scanner.nextDouble();
                        ManagerService.addMenuItem(id, itemName, price);
                        System.out.println("Menu Item Added.");
                        break;
                    case 3:
                        ManageStaffView.manageStaff();
                        break;
                    case 4:
                        AddStaffView.addStaffView();
                        break;
                    case 5:
                        ManagerService.viewSalesReports();
                        break;
                    case 6:
                        ManagerService.viewOrdersReport();
                        break;
                    case 7:
                        System.out.println("Enter Table Number (Order ID) for Bill:");
                        int tableNumBill = scanner.nextInt();
                        try {
                            model.Bill bill = ManagerService.generateBill(tableNumBill);
                            System.out.println(String.format("Bill Generated: %.2f", bill.totalAmount));
                        } catch (Exception e) {
                            System.out.println("Error generating bill: " + e.getMessage());
                        }
                        break;
                    case 8:
                        System.out.println("Enter Table Number (Bill ID) for Payment:");
                        int tableNumPay = scanner.nextInt();
                        ManagerService.receivePayment(tableNumPay);
                        break;
                    case 9:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid Input.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
