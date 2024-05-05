package org.ufv.es.practica2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ufv.es.practica2.domain.Products;
import org.ufv.es.practica2.domain.ndData;
import org.ufv.es.practica2.domain.tAgency;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GuardarJSON {
    public static void guardarJSON_ndData(String nombreFichero, List<ndData> listaDatos){
        //guardado de JSON con formato serialized
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaDatos);
        try {
            FileWriter writer = new FileWriter(nombreFichero);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJSON_products(String nombreFichero, List<Products> listaDatos){
        //guardado de JSON con formato serialized
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaDatos);
        try {
            FileWriter writer = new FileWriter(nombreFichero);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJSON_pedidos(String nombreFichero, List<tAgency> listaDatos){
        //guardado de JSON con formato serialized
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaDatos);
        try {
            FileWriter writer = new FileWriter(nombreFichero);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
