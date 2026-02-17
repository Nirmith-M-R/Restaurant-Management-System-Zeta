package services;

import enums.TableStatus;
import io.MenuItemsIO;
import io.TableBookingIO;
import model.MenuItem;

import java.util.List;
import java.util.Map;

public class CustomerService {

    public static void viewMenu() {
        List<MenuItem> menu = null;
        try {
            menu = MenuItemsIO.loadFromFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (model.MenuItem item : menu) {
            System.out.println(item.itemId + " | " + item.name + " | ₹" + item.price);
        }
    }

    public void bookTableForWalkIns(){
        System.out.println("Booking table...");
        BookTableService.bookTable();
    }

    public static void checkTableAvailability() {
        try {
            Map<Integer, TableStatus> tables = TableBookingIO.getTablesAvailability();

            System.out.println("\nTable Availability:\n");

            boolean anyAvailable = false;

            for (Map.Entry<Integer, TableStatus> entry : tables.entrySet()) {
                Integer tableNum = entry.getKey();
                TableStatus status = entry.getValue();

                System.out.println("Table " + tableNum + " : " + status);

                if (status == TableStatus.AVAILABLE) {
                    anyAvailable = true;
                }
            }

            if (!anyAvailable) {
                System.out.println("\nNo tables available right now.");
            }

        } catch (Exception e) {
            System.out.println("Unable to fetch table availability.");
        }
    }
}
