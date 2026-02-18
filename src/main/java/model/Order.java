package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.OrderStatus;

import java.util.List;

public class Order {
    public int orderId;
    public OrderStatus orderStatus;
    public int tableNumber;
    public List<MenuItem> itemList;

    @JsonCreator
    public Order(
            @JsonProperty("orderId") int orderId,
            @JsonProperty("orderStatus") OrderStatus orderStatus,
            @JsonProperty("tableNumber") int tableNumber,
            @JsonProperty("itemList") List<MenuItem> itemList) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.tableNumber = tableNumber;
        this.itemList = itemList;
    }
}
