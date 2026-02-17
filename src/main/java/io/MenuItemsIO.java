package io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import env.Env;
import model.MenuItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MenuItemsIO {
    protected static ObjectMapper mapper = new ObjectMapper();

    public static List<MenuItem> loadFromFile() throws Exception {
        File file = new File(Env.MENUITEMS);
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
