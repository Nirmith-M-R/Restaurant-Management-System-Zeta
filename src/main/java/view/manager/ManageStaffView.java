package view.manager;

import services.ManagerService;
import util.ScannerUtil;

import java.io.Console;
import java.util.Scanner;

public class ManageStaffView {

    public static void manageStaff() {

        Scanner scanner = ScannerUtil.getScanner();
        {
            try {
                System.out.println("Manage Staff Data:\n1. Change Working Status\n2. Change staff data");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> changeWorkingStatus(scanner);
                    case 2 -> changeStaffData(scanner);
                    default -> System.out.println("Invalid Input.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }


    private static void changeWorkingStatus(Scanner scanner) {

        System.out.println("Enter userId: ");
        int id = scanner.nextInt();

        boolean updated = ManagerService.toggleWorkingStatus(id);

        if (updated)
            System.out.println("Working status updated.");
        else
            System.out.println("User not found.");
    }


    private static void changeStaffData(Scanner scanner) {

        try {
            System.out.println("Enter userId: ");
            int id = scanner.nextInt();

            System.out.println("Change:\n1. Phone number\n2. Password");

            int changeChoice = scanner.nextInt();

            switch (changeChoice) {

                case 1 -> {
                    System.out.println("Enter new phone number: ");
                    int phno = scanner.nextInt();

                    boolean updated = ManagerService.updatePhoneNumber(id, phno);

                    if (updated)
                        System.out.println("Phone updated.");
                    else
                        System.out.println("User not found.");
                }

                case 2 -> {
                    System.out.println("Enter Password: ");
                    String password = scanner.next();
                    boolean updated = ManagerService.updatePassword(id, password.toCharArray());

                    if (updated)
                        System.out.println("Password updated.");
                    else
                        System.out.println("User not found.");
                }
                default -> System.out.println("Invalid input");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }
    }
}
