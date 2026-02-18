package services;

import io.MenuItemsIO;
import model.MenuItem;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMenuService {
    @Test
    public void testGetMenuItem() {
        List<MenuItem> menuItems;
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("1", "tea", 200.0));
        try (MockedStatic<MenuItemsIO> mocked = Mockito.mockStatic(MenuItemsIO.class)) {
            mocked.when(() -> MenuItemsIO.loadFromFile()).thenReturn(menuItems);
            MenuItem item = MenuService.getMenuItem("1");
            assertTrue(menuItems.get(0).name.equals(item.name));
        }
    }
}
