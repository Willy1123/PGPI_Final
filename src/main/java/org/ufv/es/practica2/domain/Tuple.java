package org.ufv.es.practica2.domain;
import java.util.UUID;

public class Tuple {
    private UUID id;
    private int quantity;

    public Tuple() {

    }

    public Tuple(UUID id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
