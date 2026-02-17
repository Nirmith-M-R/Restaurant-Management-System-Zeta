package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.PaymentStatus;

public class Bill {
    public String billId;
    public double totalAmount;
    public PaymentStatus paymentStatus;


    @JsonCreator
    public Bill(@JsonProperty("billId") String billId,
                @JsonProperty("totalAmount") double totalAmount,
                @JsonProperty("paymentStatus") PaymentStatus paymentStatus) {
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }
}
