package io;

import Enums.IdProofType;
import Enums.UserType;
import Enums.WorkingStatus;
import Model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUserDataIO {
    @Test
    public void addData(){
        List<User> users = new ArrayList<>();
        users.add(new User("abc", 1234567890, 2, "GESPRTYUI", IdProofType.AADHAR, "123", UserType.MANAGER, WorkingStatus.ACTIVE));
        UserDataIO.saveToFile(users);
    }
}
