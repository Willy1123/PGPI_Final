package org.ufv.es.practica2.domain;
import java.util.UUID;

public class Tuple {
    private String name;
    private int quantity;

    public Tuple() {

    }

    public Tuple(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
