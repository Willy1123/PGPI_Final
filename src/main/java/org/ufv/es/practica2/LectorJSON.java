package org.ufv.es.practica2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.ufv.es.practica2.domain.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LectorJSON {
    public static List leerJSON_ndData(String nombrefich){
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

    public static List leerJSON_Products(String nombrefich){
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
        Products[] product = gson.fromJson(json, Products[].class);
        // Crea una lista para almacenar los objetos ndData
        List<Products> listaDatosPr = new ArrayList<>();
        // Itera a través del array ndData y agrega cada objeto a la lista
        for(int i = 0; i < product.length; i++){
            listaDatosPr.add(product[i]);
        }
        // Devuelve la lista de objetos ndData
        return listaDatosPr;
    }

    public static List readJSON_accounts(String fileName) {
        String json = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null){
                json += line;
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Accounts[] accounts = gson.fromJson(json, Accounts[].class);
        List<Accounts> accountsList = new ArrayList<>();
        for(int i = 0; i < accounts.length; i++){
            accountsList.add(accounts[i]);
        }
        return accountsList;
    }

    public static List<tAgency> leerJSON_Pedidos(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new Gson();
            TypeToken<List<tAgency>> token = new TypeToken<List<tAgency>>() {};
            return gson.fromJson(br, token.getType());
        }
    }


}
