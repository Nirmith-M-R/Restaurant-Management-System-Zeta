package io;

import model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import env.Env;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDataIO {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveToFile(List<User> users) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(Env.USERDATAFILE), users);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static List<User> loadFromFile() {
        List<User> users = new ArrayList<>();

        try {
            File file = new File(Env.USERDATAFILE);
            if (file.exists()) {
                users = mapper.readValue(
                        file,
                        new TypeReference<List<User>>() {
                        }
                );
            }

        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        return users;
    }
}
