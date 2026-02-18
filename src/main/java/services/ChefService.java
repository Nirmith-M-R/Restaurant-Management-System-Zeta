package services;

import enums.OrderStatus;
import io.OrderIO;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class ChefService {
    static List<Order> pendingOrders = new ArrayList<>();

    public static List<Order> viewCurrentOrders(){
        try {
            List<Order> orders = OrderIO.getOrdersFromFile();

            for (Order order : orders) {
                if (order.orderStatus == OrderStatus.PLACED){
                    order.orderStatus = OrderStatus.PROCESSING;
                }
            }
            OrderIO.addOrdersToFile(orders);
            return orders.stream()
                    .filter(order -> order.orderStatus == OrderStatus.PROCESSING)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static boolean updateOrderStatus(int orderId){
        try {
            List<Order> orders = OrderIO.getOrdersFromFile();
            for (Order order:orders){
                if (order.orderId == orderId){
                    order.orderStatus = OrderStatus.PREPARED;
                    break;
                }
            }
            OrderIO.addOrdersToFile(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
