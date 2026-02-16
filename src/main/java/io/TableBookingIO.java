package io;

import enums.TableStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import env.Env;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TableBookingIO {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<Integer, TableStatus> getTablesAvailability() throws Exception {
        File file = new File(Env.TABLEBOOKED);
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
            System.out.println("Error loading data: " + e.getMessage());
            throw new Exception("Unable To Load File");
        }

    }

    public static void setTableAvailability(Map<Integer,TableStatus> tables) throws Exception {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(Env.TABLEBOOKED), tables);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            throw new Exception("Error in saving data..");
        }
    }
}
