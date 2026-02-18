package services;

import enums.UserType;
import model.User;

import java.util.List;

import static io.UserDataIO.loadFromFile;

public class AuthService {
    public static User login(int id, String password){
        List<User> users = loadFromFile();
        for (User user : users) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                return user;
            }
        }
        return new User(UserType.INVALID);
    }
}
