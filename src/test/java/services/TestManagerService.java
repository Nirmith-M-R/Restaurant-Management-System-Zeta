package services;

import enums.OrderStatus;
import enums.PaymentStatus;
import io.BillIO;
import io.OrderIO;
import io.UserDataIO;
import model.Bill;
import model.MenuItem;
import model.Order;
import model.User;
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

public class TestManagerService {
    List<MenuItem> menuItems;
    Order order;
    List<Order> orders;
    List<Bill> bills;
    List<User> users;

    @BeforeEach
    public void setup(){
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("1", "tea", 200.0));
        order = new Order(1, OrderStatus.PLACED, 1, menuItems);
        orders = new ArrayList<>();
        orders.add(order);
        bills = new ArrayList<>();
        bills.add(new Bill(1, 200.0, PaymentStatus.PENDING));
        users = new ArrayList<>();
    }

    @Test
    public void testGenerateBill() throws Exception {
        try(MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)){
            when(OrderIO.getOrdersFromFile()).thenReturn(orders);
            Bill generatedBill = ManagerService.generateBill(1);
            assertEquals(200, generatedBill.totalAmount);
        }
    }

    @Test
    public void testRecievePayment(){
        try(MockedStatic<BillIO> mocked = Mockito.mockStatic(BillIO.class)){
            when(BillIO.getBillsFromFile()).thenReturn(bills);
            ManagerService.receivePayment(1);
            assertEquals(PaymentStatus.PAID, bills.get(0).paymentStatus);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testViewSalesReport(){
        bills.add(new Bill(2, 500.0, PaymentStatus.PAID));
        try(MockedStatic<BillIO> mocked = Mockito.mockStatic(BillIO.class)){
            mocked.when(()->BillIO.getBillsFromFile()).thenReturn(bills);
            ByteArrayOutputStream salesReportOutput = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(salesReportOutput));
            ManagerService.viewSalesReports();
            assertTrue(salesReportOutput.toString().contains("Sales Report:\n Total Sales: 700.00\nAmount Received : 500.00\nAmount to be Received : 200.00"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testViewOrdersReport(){
        List<MenuItem> menuItems2 = new ArrayList<>();
        menuItems2.add(new MenuItem("2", "Dosa", 500.0));
        Order order2 = new Order(1, OrderStatus.PLACED, 1, menuItems2);
        orders.add(order2);
        try(MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)){
            mocked.when(()->OrderIO.getOrdersFromFile()).thenReturn(orders);
            ByteArrayOutputStream viewOrdersReportOutput = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(viewOrdersReportOutput));
            ManagerService.viewOrdersReport();
            assertTrue(viewOrdersReportOutput.toString().contains("Order Report :\nTotal number of orders : 2\nAverage order value: 350.00"));
        }
    }

//    @Test
//    public void testAddStaff(){
//        try(MockedStatic<UserDataIO> mocked = Mockito.mockStatic(UserDataIO.class)){
//            mocked.when(()-> UserDataIO.loadFromFile()).thenReturn(users);
//            ManagerService.addStaff();
//        }
//    }

    // test addStaff also
    // test manage staff also
}
