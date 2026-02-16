package main;

import services.BookTableService;

public class RMS {
    public static void main(String[] args) {
//        List<User> users = new ArrayList<>();
//        users.add(new User("abc", 1234567890, 2, "GESPRTYUI", IdProofType.AADHAR, "123", UserType.MANAGER, WorkingStatus.ACTIVE));
//        UserDataIO.saveToFile(users);
        BookTableService.bookTable();
    }
}
