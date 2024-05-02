package org.ufv.es.practica2.controller;

import org.springframework.web.bind.annotation.*;
import org.ufv.es.practica2.Config;
import org.ufv.es.practica2.GuardarJSON;
import org.ufv.es.practica2.LectorJSON;
import org.ufv.es.practica2.domain.Products;
import org.ufv.es.practica2.domain.ndData;
import org.ufv.es.practica2.domain.tAgency;
import java.util.ArrayList;
import org.ufv.es.practica2.domain.Tuple;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class Controler_tAgency {

    /*
    @GetMapping("/Products") // Todos los productos
    public List<Products> products() {
        List<Products> listadatos;
        listadatos = new LectorJSON().leerJSON_Products(Config.Ruta_ProductsJson);
        return listadatos;
    }
     */

    @GetMapping("/Products/{Name}") // Buscar producto por nombre para ponerlo en la lista
    public List<Products> removeProduct(@PathVariable String Name) throws IOException {
        List<Products> listadatos;
        listadatos = new LectorJSON().leerJSON_Products(Config.Ruta_ProductsJson);
        // encontrar y eliminar el elemento de la lista
        for(int i=0;i<listadatos.size();i++) {
            if (!listadatos.get(i).getName().contains(Name)) {
                listadatos.remove(i);
            }
        }
        return listadatos;
    }


    public List<Tuple> check_stock(List<Tuple> request) {
        List<Products> stock = new LectorJSON().leerJSON_Products(Config.Ruta_ProductsJson);
        List<Tuple> valid = new ArrayList<>();


        for (Tuple tuple : request) { // request
            String name = tuple.getName(); // cada item
            for (Products item : stock) {
                if (item.getName().equals(name)) { // buscamos en inventario
                    if (item.getStock() >= tuple.getQuantity()) { // si hay stock
                        valid.add(tuple); // lo añadimos a la salida
                    }
                    break;  // Salir del bucle una vez encontrado
                }
            }
        }
        return valid;

    }

    /*
    Datos que le tienen que llegar:
        type: boolean
        name String, nombre agencia
        dirr String
        items List,
            lista de tuplas (id_producto, cantidad)
        postal String
        zone String, nombre zona
     */


    @PostMapping("/Request")
    public void addProduct(@RequestBody tAgency newRequest) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        tAgency Save = new tAgency();

        switch (newRequest.getName()) {
            case "DHL":
                Save.setDate(now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                break;
            case "UPS":
                List<ndData> pobs = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson);
                for (int i = 0; i < pobs.size(); i++) {
                    if (pobs.get(i).getMsCode().equals(newRequest.getName())) {
                        now = now.plusDays((int) pobs.get(i).getEstimate().floatValue());
                    }
                }
                if (newRequest.getType() == Boolean.FALSE) {
                    Save.setDate(now.plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                } else {
                    Save.setDate(now.plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                break;
            case "FEDEX":
                Save.setDate(now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                break;
            default:
                System.out.println("Agencia de transporte mal especificada, se guardará solo la fecha y hora de pedido");
                Save.setDate(now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));


                Save.setType(newRequest.getType());
                Save.setId(UUID.randomUUID());
                Save.setName(newRequest.getName());
                Save.setDirr(newRequest.getDirr());

                List<Tuple> items = check_stock(newRequest.getItems());
                Save.setItems(items);

                Integer units = 0;
                Float weigth = Float.valueOf(0);
                for (Tuple item : items) {
                    units += item.getQuantity();
                }

                Save.setPostal(newRequest.getPostal());
                Save.setUnits(units);


        }

    }
}
