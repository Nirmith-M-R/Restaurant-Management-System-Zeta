package services;

import enums.OrderStatus;
import io.OrderIO;
import model.MenuItem;
import model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TestWaiterService {
    List<MenuItem> menuItems;
    Order order;
    List<Order> orders;

    @BeforeEach
    public void setup() {
        menuItems = new ArrayList<>();
        order = new Order(1, OrderStatus.PLACED, 1, menuItems);
        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void testViewMenu() throws Exception {
        menuItems.add(new MenuItem("1", "Tea", 20));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        WaiterService.viewMenu();
        String printed = output.toString();
        assertTrue(printed.contains("1 | Tea | ₹20"));
    }

    @Test
    public void testTakeOrder() throws Exception {
        try (MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)) {
            mocked.when(() -> OrderIO.getOrdersFromFile()).thenReturn(orders);
            mocked.when(() -> OrderIO.addOrdersToFile(orders)).thenReturn(true);
        }
        Order received = WaiterService.takeOrder(1, 1, menuItems);
        assertEquals(order.orderId, received.orderId);
        assertEquals(order.tableNumber, received.tableNumber);
    }

    @Test
    public void testUpdateOrderAsServed() throws Exception {
        try (MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)) {
            mocked.when(() -> OrderIO.getOrdersFromFile()).thenReturn(orders);
            mocked.when(() -> OrderIO.addOrdersToFile(orders)).thenReturn(true);
        }
        ByteArrayOutputStream updateOrderOutput = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(updateOrderOutput));
        WaiterService.updateOrderAsServed(1);
        System.setOut(originalOutput);
        System.out.println(updateOrderOutput.toString());
        assertTrue(updateOrderOutput.toString().contains("Order served for table."));
    }
}
