package services;

import enums.OrderStatus;
import model.MenuItem;
import model.Order;
import io.MenuItemsIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestCustomerService {
    List<MenuItem> menuItems;
    Order order;
    List<Order> orders;

    @BeforeEach
    public void setup() {
        menuItems = new ArrayList<>();

    }

    @Test
    public void testViewMenu() throws Exception {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("2", "Coffee", 200));
        MenuItemsIO.addMenuToFile(menuItems);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        CustomerService.viewMenu();

        System.setOut(originalOut);
        String printed = output.toString();

        assertTrue(printed.contains("2 | Coffee | ₹200"));
    }

    @Test
    public void testBookTable() {
        CustomerService service = new CustomerService();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try {
            System.setOut(new PrintStream(output));
            CustomerService.bookTable();
        } finally {
            System.setOut(originalOut);
        }
        String printed = output.toString();
        assertTrue(printed.contains("Booking table."));
    }

    @Test
    void testCheckTableAvailability() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output));
            CustomerService.checkTableAvailability();
        } finally {
            System.setOut(originalOut);
        }

        String printed = output.toString();
        assertTrue(printed.contains("Table"));
    }
}
