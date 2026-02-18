package services;

import enums.OrderStatus;
import io.OrderIO;
import model.MenuItem;
import model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TestChefService {
    List<MenuItem> menuItems;
    Order order;
    List<Order> orders;


    @BeforeEach
    public void setup() {
        menuItems = null;
        order = new Order(1, OrderStatus.PLACED, 1, menuItems);
        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void testViewCurrentOrders() {
        try (MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)) {
            mocked.when(() -> OrderIO.addOrdersToFile(orders)).thenReturn(true);
            mocked.when(() -> OrderIO.getOrdersFromFile()).thenReturn(orders);
            List<Order> pendingOrders = ChefService.viewCurrentOrders();
            assertEquals(orders.get(0).orderId, pendingOrders.get(0).orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateOrderStatus() {
        try (MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)) {
            mocked.when(() -> OrderIO.addOrdersToFile(orders)).thenReturn(true);
            mocked.when(() -> OrderIO.getOrdersFromFile()).thenReturn(orders);
            boolean updateResult = ChefService.updateOrderStatus(1);
            assertTrue(updateResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
