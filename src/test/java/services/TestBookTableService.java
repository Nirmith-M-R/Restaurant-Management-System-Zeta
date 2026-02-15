package services;

import Enums.TableStatus;
import io.TableBookingIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

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

        when(TableBookingIO.getTablesAvailability()).thenReturn(tables);
        BookTableService.bookTable();

    }
}
