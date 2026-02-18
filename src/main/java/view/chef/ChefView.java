package view.chef;

import env.Env;
import util.ScannerUtil;

import java.util.Scanner;

public class ChefView {
    Scanner scanner = ScannerUtil.getScanner();

    public static void chefView(String name){
        while (true){
            System.out.println(String.format("Welcome, %s to %s", name, Env.RESTAURANTNAME));
            System.out.println("\nEnter your choice:\n1. View Current ");
        }
    }
}
