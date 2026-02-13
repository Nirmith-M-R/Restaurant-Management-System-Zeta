package services;

import Enums.UserType;
import Model.User;

import java.util.List;

import static io.UserDataIO.loadFromFile;

public class AuthService {
    public static boolean login(int id, String password){
        List<User> users = loadFromFile();
        for (User user : users) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
