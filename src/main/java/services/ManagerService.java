package services;

import enums.PaymentStatus;
import enums.WorkingStatus;
import io.BillIO;
import io.MenuItemsIO;
import io.OrderIO;
import io.UserDataIO;
import model.Bill;
import model.MenuItem;
import model.Order;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {
    public static Bill generateBill(int orderId){
        try {
            List<Order> orders = OrderIO.getOrdersFromFile();
            Order billOrder = null;
            for (Order order:orders){
                if (order.orderId==orderId){
                    billOrder = order;
                }
            }
            double billAmount = 0;
            for(MenuItem menuItem: billOrder.itemList){
                billAmount+=menuItem.price;
            }
            Bill bill = new Bill(orderId, billAmount, PaymentStatus.PENDING);
            List<Bill> bills = BillIO.getBillsFromFile();
            bills.add(bill);
            BillIO.addOrUpdateBillToFile(bills);
            return bill;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void receivePayment(int tableNumber){
        try {
            List<Bill> bills = BillIO.getBillsFromFile();
            double amount = 0;
            for (Bill bill: bills){
                if ((bill.billId == tableNumber) && (bill.paymentStatus == PaymentStatus.PENDING)){
                    bill.paymentStatus = PaymentStatus.PAID;
                    amount=bill.totalAmount;
                    break;
                }
            }
            BillIO.addOrUpdateBillToFile(bills);
            System.out.println(String.format("Payment Recieved for billId : %d, ₹%.2f",tableNumber,amount));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewSalesReports(){
        try {
            List<Bill> bills = BillIO.getBillsFromFile();
            double totalAmountReceived = 0;
            double totalAmountToBeReceived = 0;
            for (Bill bill: bills){
                if (bill.paymentStatus == PaymentStatus.PAID){
                    totalAmountReceived+=bill.totalAmount;
                }else{
                    totalAmountToBeReceived+=bill.totalAmount;
                }
            }
            System.out.println(String.format("Sales Report:\n Total Sales: %.2f\nAmount Received : %.2f\nAmount to be Received : %.2f",(totalAmountReceived+totalAmountToBeReceived),totalAmountReceived, totalAmountToBeReceived));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewOrdersReport(){
        try {
            List<Order> orders = OrderIO.getOrdersFromFile();
            int totalOrders = 0;
            double totalAmount = 0;
            double avgOrderValue = 0;
            for (Order order : orders){
                totalOrders+=1;
                for (MenuItem menuItem:order.itemList){
                    totalAmount+=menuItem.price;
                }
            }
            avgOrderValue = totalAmount/totalOrders;
            System.out.println(String.format("Order Report :\nTotal number of orders : %d\nAverage order value: %.2f",totalOrders, avgOrderValue));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addStaff(User user){

            List<User> users = UserDataIO.loadFromFile();
            if(users == null){
                users = new ArrayList<>();
            }
            users.add(user);
            UserDataIO.saveToFile(users);
            return true;
    }

    public static boolean toggleWorkingStatus(int id) {

        List<User> users = UserDataIO.loadFromFile();

        for (User user : users) {
            if (user.getId() == id) {

                if (user.getWorkingStatus() == WorkingStatus.ACTIVE) {
                    user.setWorkingStatus(WorkingStatus.INACTIVE);
                } else {
                    user.setWorkingStatus(WorkingStatus.ACTIVE);
                }

                UserDataIO.saveToFile(users);
                return true;
            }
        }
        return false;
    }

    public static boolean updatePhoneNumber(int id, int phone) {

        List<User> users = UserDataIO.loadFromFile();

        for (User user : users) {
            if (user.getId() == id) {
                user.setPhone(phone);
                UserDataIO.saveToFile(users);
                return true;
            }
        }
        return false;
    }

    public static boolean updatePassword(int id, char[] passwordChar) {

        List<User> users = UserDataIO.loadFromFile();

        String newPassword = new String(passwordChar);

        for (User user : users) {
            if (user.getId() == id) {
                user.setPassword(newPassword);
                UserDataIO.saveToFile(users);
                return true;
            }
        }

        java.util.Arrays.fill(passwordChar, ' ');
        return false;
    }

    public static void addMenuItem(String id, String name, double price){
        try {
            List<MenuItem> menuItems = MenuItemsIO.loadFromFile();
            menuItems.add(new MenuItem(id, name, price));
            MenuItemsIO.addMenuToFile(menuItems);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
