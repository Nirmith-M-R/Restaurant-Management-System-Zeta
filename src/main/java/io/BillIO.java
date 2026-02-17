package io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import env.Env;
import model.Bill;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BillIO {
    protected static ObjectMapper mapper = new ObjectMapper();

    public static boolean addOrUpdateBillToFile(List<Bill> bills) throws Exception {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(Env.BILL), bills);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            throw new Exception("Error in saving data..");
        }
    }

    public static List<Bill> getBillsFromFile() throws Exception {
        File file = new File(Env.BILL);
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
