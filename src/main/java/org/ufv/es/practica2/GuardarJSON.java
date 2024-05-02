package org.ufv.es.practica2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GuardarJSON {
    public static void guardarJSON(String nombreFichero, List<ndData> listaDatos){
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
