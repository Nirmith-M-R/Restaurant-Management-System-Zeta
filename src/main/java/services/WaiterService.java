package services;

import enums.OrderStatus;
import io.MenuItemsIO;
import io.OrderIO;
import model.MenuItem;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class WaiterService {

    private final List<Order> activeOrders = new ArrayList<>();

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

    public static Order takeOrder(int OrderID, int tableNo, List<model.MenuItem> items) {

        Order order = new Order(OrderID, OrderStatus.PLACED, tableNo, items);

        try {
            List<Order> orders = OrderIO.getOrdersFromFile();
            orders.add(order);
            OrderIO.addOrdersToFile(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }


    public static void updateOrderAsServed(int orderID) {
        try {
            List<Order> orders = OrderIO.getOrdersFromFile();
            for (Order order : orders) {
                if (order.orderId == orderID) {
                    order.orderStatus = OrderStatus.SERVED;
                    break;
                }
            }
            OrderIO.addOrdersToFile(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Order served for table.");
    }
}
