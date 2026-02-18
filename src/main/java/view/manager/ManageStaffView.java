package view.manager;

import services.ManagerService;
import util.ScannerUtil;

import java.io.Console;
import java.util.Scanner;

public class ManageStaffView {

    public static void manageStaff() {

        Scanner scanner = ScannerUtil.getScanner();
        System.out.println("Manage Staff Data:\n1. Change Working Status\n2. Change staff data");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> changeWorkingStatus(scanner);
            case 2 -> changeStaffData(scanner);
            default -> System.out.println("Invalid Input.");
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
                Console console = System.console();
                if (console == null) {
                    System.out.println("Run in terminal!");
                    return;
                }

                char[] password = console.readPassword("Enter new password: ");
                boolean updated = ManagerService.updatePassword(id, password);

                if (updated)
                    System.out.println("Password updated.");
                else
                    System.out.println("User not found.");
            }
            default -> System.out.println("Invalid input");
        }
    }
}
