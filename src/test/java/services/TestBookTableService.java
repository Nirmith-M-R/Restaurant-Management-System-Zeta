package services;

import enums.TableStatus;
import io.OrderIO;
import io.TableBookingIO;
import model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestBookTableService {

    Map<Integer, TableStatus> tables;

    @BeforeEach
    void setup(){
        tables = new HashMap<>();
        tables.put(1,TableStatus.AVAILABLE);
        tables.put(2,TableStatus.AVAILABLE);
        tables.put(3,TableStatus.AVAILABLE);
        tables.put(4,TableStatus.AVAILABLE);
        tables.put(5,TableStatus.AVAILABLE);
    }

    @Test
    public void testBookTable() throws Exception {
        try (MockedStatic<TableBookingIO> mocked = Mockito.mockStatic(TableBookingIO.class)) {
            mocked.when(()->TableBookingIO.getTablesAvailability()).thenReturn(tables);
            BookTableService.bookTable();
            assertEquals(tables.get(1), TableStatus.UNAVAILABLE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
