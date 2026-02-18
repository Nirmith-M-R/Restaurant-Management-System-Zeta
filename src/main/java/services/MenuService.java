package services;

import io.MenuItemsIO;
import model.MenuItem;

import java.util.List;

public class MenuService {
    public static MenuItem getMenuItem(String id){
        List<MenuItem> menuItemsList;
        try {
            menuItemsList = MenuItemsIO.loadFromFile();
            for (MenuItem menuItem: menuItemsList){
                if (menuItem.itemId.equals(id)){
                    return menuItem;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return menuItemsList.get(0);
    }
}
