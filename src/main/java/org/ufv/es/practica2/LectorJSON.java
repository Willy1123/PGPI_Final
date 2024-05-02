package org.ufv.es.practica2;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorJSON {
    public static List leerJSON(String nombrefich){
        String json = "";

        // Intenta leer el archivo JSON
        try {
            // Crea un BufferedReader para leer el archivo
            BufferedReader br = new BufferedReader(new FileReader(nombrefich));
            String linea;

            // Lee el archivo línea por línea
            while((linea = br.readLine()) != null){
                // Agrega cada línea al String json
                json += linea;
            }
            // Cierra el BufferedReader
            br.close();
        }
        catch (Exception e){
            // Imprime la traza del error en caso de una excepción
            e.printStackTrace();
        }
        // Crea una instancia de Gson para manejar JSON
        Gson gson = new Gson();
        // Convierte el String json en un array de objetos ndData
        ndData[] ndData = gson.fromJson(json, ndData[].class);
        // Crea una lista para almacenar los objetos ndData
        List<ndData> listaDatosnd = new ArrayList<>();
        // Itera a través del array ndData y agrega cada objeto a la lista
        for(int i = 0; i < ndData.length; i++){
            listaDatosnd.add(ndData[i]);
        }
        // Devuelve la lista de objetos ndData
        return listaDatosnd;
    }

}
