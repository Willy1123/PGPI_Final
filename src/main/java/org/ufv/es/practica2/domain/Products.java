package org.ufv.es.practica2.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Products {
    //Creamos los atributos de la clase ndData
    @SerializedName("id")
    private UUID id;
    @SerializedName("name")
    private String name;

    @SerializedName("stock")
    private int stock;

    @SerializedName("description")
    private String description;

    @SerializedName("expiration_date")
    private String expiration_date;

    //Creamos los constructores
    public Products() {
    }

    public Products(UUID id, String name, int stock, String description, String expiration_date) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.expiration_date = expiration_date;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id=UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public static void ordenarporId(List<Products> listaProducts){
        //leemos el archivo cp-national-datafile.json y creamos el archivo MsCode_json.json con los datos ordenados por MsCode

        //ordenamos la lista por MsCode
        Collections.sort(listaProducts, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return String.valueOf(o1.getId()).compareTo(String.valueOf(o2.getId()));
            }
        });
        //guardamos la lista ordenada en el archivo MsCode_json.json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaProducts);
        try {
            FileWriter writer = new FileWriter("./src/main/resources/Products_json.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}