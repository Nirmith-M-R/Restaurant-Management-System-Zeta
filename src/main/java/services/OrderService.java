package services;

import enums.OrderStatus;
import io.OrderIO;
import model.Order;

import java.util.List;

public class OrderService {
    public static void createOrder(Order order){
        try {
            List<Order> orders = OrderIO.getOrdersFromFile();
            orders.add(order);
            boolean orderStatus = OrderIO.addOrdersToFile(orders);
            if (orderStatus) {
                System.out.println("Order has been created successfully");
            }
        } catch (Exception e) {
            System.out.println("Unable to create order");
        }
    }

    public static void updateStatus(int orderId, OrderStatus orderStatus){
        try {
            List<Order> orders = OrderIO.getOrdersFromFile();
            for(Order order:orders){
                if (order.orderId == orderId){
                    order.orderStatus=orderStatus;
                    break;
                }
            }
            boolean orderUpdateStatus = OrderIO.addOrdersToFile(orders);
            if (orderUpdateStatus){
                System.out.printf("Order update to %s successfully.%n", orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to create order");
        }
    }
}
