package services;

import enums.OrderStatus;
import enums.PaymentStatus;
import io.BillIO;
import io.MenuItemsIO;
import io.OrderIO;
import model.MenuItem;
import model.Order;
import org.junit.jupiter.api.Test;
import model.Bill;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestReceptionistService {
    @Test
    void testReceivePayment_marksBillPaid() throws Exception {
        List<Bill> bills = new ArrayList<>();
        Bill bill1 = new Bill(1, 500.0, PaymentStatus.PENDING);
        bills.add(bill1);
        BillIO.addOrUpdateBillToFile(bills);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        ReceptionistService.receivePayment(1);
        List<Bill> updatedBills = BillIO.getBillsFromFile();
        assertEquals(PaymentStatus.PAID, updatedBills.get(0).paymentStatus);
        //assertTrue(output.toString().contains("Payment Recieved for billId : 1"));
    }
    @Test
    void testReceivePayment_marksBillUnPaid() throws Exception {
        List<Bill> bills = new ArrayList<>();
        Bill bill2 = new Bill(2, 300.0, PaymentStatus.PENDING);
        bills.add(bill2);
        BillIO.addOrUpdateBillToFile(bills);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        ReceptionistService.receivePayment(1);

        List<Bill> updatedBills = BillIO.getBillsFromFile();
        assertEquals(PaymentStatus.PENDING, updatedBills.get(0).paymentStatus);
        //assertFalse(output.toString().contains("Payment Recieved for billId : 2"));
    }

    @Test
    void testBookTableForWalkIns() {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try (MockedStatic<BookTableService> mocked = mockStatic(BookTableService.class)) {
            System.setOut(new PrintStream(output));
            ReceptionistService.bookTableForWalkIns();
            mocked.verify(BookTableService::bookTable);
        } finally {
            System.setOut(originalOut);
        }
        String printed = output.toString();
        assertTrue(printed.contains("Booking table for walk-in customer"));
    }
    @Test
    public void testGenerateBill() throws Exception {
        MenuItem m1 = new MenuItem("4","Sandwich",400);
        MenuItem m2 = new MenuItem("5","Noodles",300);
        List<MenuItem> itemList = Arrays.asList(m1, null, m2);
        Order order = new Order(4, OrderStatus.SERVED,3,itemList);
        List<Order> orders = List.of(order);

        try (MockedStatic<OrderIO> mocked = Mockito.mockStatic(OrderIO.class)) {
            when(OrderIO.getOrdersFromFile()).thenReturn(orders);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(output));
            ReceptionistService.generateBill(3);
            System.setOut(originalOut);
            assertTrue(output.toString().contains("Bill generated successfully for table 3"));
        }
    }
}
