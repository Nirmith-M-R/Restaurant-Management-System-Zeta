package dataInitializer;


//This file is not part of the project, it is just created to add some dummy data for testing

import enums.TableStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import env.Env;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InitializeTable {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveToFile() {
        try {
            Map<Integer, TableStatus> tables = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                tables.put(i + 1, TableStatus.AVAILABLE);
            }
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(Env.TABLEBOOKED), tables);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        InitializeTable.saveToFile();
    }

}