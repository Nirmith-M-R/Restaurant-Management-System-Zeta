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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestOrderService {
    List<MenuItem> menuItems;
    Order order;
    List<Order> orders;


    @BeforeEach
    public void setup(){
        menuItems = null;
        order = new Order(1, OrderStatus.PLACED, 1, menuItems);
        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void testCreateOrder() throws Exception {
        try (MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)) {
            when(OrderIO.addOrdersToFile(orders)).thenReturn(true);
            when(OrderIO.getOrdersFromFile()).thenReturn(orders);
        }
        ByteArrayOutputStream createOrderOutput = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(createOrderOutput));
        OrderService.createOrder(order);
        System.setOut(originalOut);
        assertTrue(createOrderOutput.toString().contains("Order has been created successfully"));

    }

    @Test
    public void testUpdateOrder() throws Exception {
        try (MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)) {
            when(OrderIO.addOrdersToFile(orders)).thenReturn(true);
            when(OrderIO.getOrdersFromFile()).thenReturn(orders);
        }
        ByteArrayOutputStream updateOrderOutput = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(updateOrderOutput));
        OrderService.updateStatus(1, OrderStatus.PREPARED);
        System.setOut(originalOut);
        System.out.println(updateOrderOutput.toString());
        assertTrue(updateOrderOutput.toString().contains("Order update to PREPARED successfully."));
    }
}
