package view.login;

import enums.UserType;
import env.Env;
import model.User;
import services.AuthService;

import java.io.Console;
import java.util.Scanner;

public class LoginView {
    public static void loginView(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("Welcome to, %s", Env.RESTAURANTNAME));
        System.out.println("======= Login Page =======");
        System.out.println("Enter user ID: ");
        int id = scanner.nextInt();
        Console console = System.console();
        if (console == null) return;
        char[] passwordChar = console.readPassword("Enter password: ");
        String password = new String(passwordChar);
        User user  = AuthService.login(id, password);

        switch (user.getUserType()){
            case WAITER : {
                break;
            }
            case MANAGER : {
                break;
            }
            case RECEPTIONIST: {
                break;
            }
            case CUSTOMER: {
                break;
            }
            case CHEF:{
                break;
            }
            case INVALID: {
                System.out.println("UserID or Password is wrong.");
                break;
            }
        }
    }
}
