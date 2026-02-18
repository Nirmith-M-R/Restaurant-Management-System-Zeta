package services;

import enums.TableStatus;
import io.TableBookingIO;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookTableService {
    public static void bookTable() {
        try {
            Map<Integer, TableStatus> tables = TableBookingIO.getTablesAvailability();
            AtomicInteger bookedTableNum = new AtomicInteger();
            for (Map.Entry<Integer, TableStatus> entry : tables.entrySet()) {
                Integer tableNum = entry.getKey();
                TableStatus tableStatus = entry.getValue();
                if (tableStatus == TableStatus.AVAILABLE) {
                    tables.put(tableNum, TableStatus.UNAVAILABLE);
                    bookedTableNum.set(tableNum);
                    break;
                }
            }
            TableBookingIO.setTableAvailability(tables);
            System.out.println("Your Table is booked\nYour Table number is : " + bookedTableNum);
        } catch (Exception e) {
            System.out.println("Sorry, We are unable to book table right now.");

        }
    }
}
