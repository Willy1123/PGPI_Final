package org.ufv.es.practica2.domain;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Tuple {
    @SerializedName("productName")
    private String productName;
    @SerializedName("quantity")
    private int quantity;

    public Tuple() {

    }

    public Tuple(String name, int quantity) {
        this.productName = name;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
