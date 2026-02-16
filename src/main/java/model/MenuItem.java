package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuItem {
    public String itemId;
    public String name;
    public double price;


    @JsonCreator
    public MenuItem(
            @JsonProperty("itemId") String itemId,
            @JsonProperty("name") String name,
            @JsonProperty("price") double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

}
