package io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import enums.OrderStatus;
import env.Env;
import model.MenuItem;
import model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TestOrderIO {
    List<MenuItem> menuItems;
    Order order;
    List<Order> orders;


    @BeforeEach
    public void setup(){
        menuItems = null;
        order = new Order(1, OrderStatus.PLACED, 1, menuItems);
        orders = new ArrayList<>();
        orders.add(order);
    }

    @Test
    public void testAddOrdersToFile() throws IOException {
        try {
            ObjectMapper mapperMock = mock(ObjectMapper.class);
            ObjectWriter writerMock = mock(ObjectWriter.class);

            when(mapperMock.writerWithDefaultPrettyPrinter())
                    .thenReturn(writerMock);

            doNothing().when(writerMock)
                    .writeValue(any(File.class), any());

            OrderIO.mapper = mapperMock;

            boolean res = OrderIO.addOrdersToFile(orders);
            assertTrue(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetOrdersFromFile() throws IOException {
        try {
            ObjectMapper mapperMock = mock(ObjectMapper.class);

            when(mapperMock.readValue(any(File.class),
                            any(TypeReference.class))
            ).thenReturn(orders);

            OrderIO.mapper = mapperMock;

            List<Order> res = OrderIO.getOrdersFromFile();
            assertEquals(orders, res);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
