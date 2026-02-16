package io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.OrderStatus;
import env.Env;
import model.MenuItem;
import model.Order;
import model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderIO {
    protected static ObjectMapper mapper = new ObjectMapper();

    public static boolean addOrdersToFile(List<Order> orders) throws Exception {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(Env.ORDERS), orders);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            throw new Exception("Error in saving data..");
        }
    }

    public static List<Order> getOrdersFromFile() throws Exception {

        File file = new File(Env.ORDERS);
        if (!file.exists()) {
            throw new Exception("File not found");
        }

        try {
            return mapper.readValue(
                    file,
                    new TypeReference<>() {
                    }
            );

        } catch (IOException e) {
            throw new Exception("Error loading data: " + e.getMessage());
        }

    }
}
