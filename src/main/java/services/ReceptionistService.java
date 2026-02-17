package services;

import enums.PaymentStatus;
import io.BillIO;
import io.OrderIO;
import model.Bill;
import model.MenuItem;
import model.Order;

import java.util.List;

public class ReceptionistService {
    public void bookTableForWalkIns(){
        System.out.println("Booking table for walk-in customer...");
        BookTableService.bookTable();
    }

    public static void receivePayment(int tableNumber){
        try {
            List<Bill> bills = BillIO.getBillsFromFile();
            double amount = 0;
            for (Bill bill: bills){
                if (bill.billId == tableNumber) {
                    if (bill.paymentStatus == PaymentStatus.PENDING) {
                        bill.paymentStatus = PaymentStatus.PAID;
                        amount = bill.totalAmount;
                        break;
                    }
                }
            }
            BillIO.addOrUpdateBillToFile(bills);
            System.out.println(String.format("Payment Recieved for billId : %d, ₹%f",tableNumber,amount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateBill(int tableNumber) throws Exception {
        List<Order> orders = OrderIO.getOrdersFromFile();
        double totalAmount = 0;

        for (Order order : orders) {
            for (MenuItem item : order.itemList) {
                if (item == null) continue;
                totalAmount += item.price;
            }
        }
        Bill bill = new Bill(tableNumber,totalAmount, PaymentStatus.PENDING);

    }
}

