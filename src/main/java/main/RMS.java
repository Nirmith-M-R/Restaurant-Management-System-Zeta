package main;

import Enums.IdProofType;
import Enums.UserType;
import Enums.WorkingStatus;
import Model.User;
import io.UserDataIO;

import java.util.ArrayList;
import java.util.List;

public class RMS {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("abc", 1234567890, 2, "GESPRTYUI", IdProofType.AADHAR, "123", UserType.MANAGER, WorkingStatus.ACTIVE));
        UserDataIO.saveToFile(users);
    }
}
