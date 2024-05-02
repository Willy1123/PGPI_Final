package org.ufv.es.practica2.controller;

import org.springframework.web.bind.annotation.*;
import org.ufv.es.practica2.Config;
import org.ufv.es.practica2.GuardarJSON;
import org.ufv.es.practica2.LectorJSON;
import org.ufv.es.practica2.domain.Products;
import org.ufv.es.practica2.domain.ndData;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
public class Controller_Products {

    @GetMapping("/Products")
    public List<ndData> ndData() {
        List<ndData> listadatos;
        listadatos = new LectorJSON().leerJSON_ndData(Config.Ruta_ProductsJson);
        return listadatos;
    }

    @PutMapping("/Products/{id}")
    public void changeProduct(@PathVariable int id, @RequestBody Products prodcuts) throws IOException {
        List<Products> listadatos;
        listadatos = new LectorJSON().leerJSON_ndData(Config.Ruta_ProductsJson);
        int aux = prodcuts.getId();
        //cambio el elemento de la lista
            for(int i=0;i<listadatos.size();i++) {
                if (listadatos.get(i).getId() == (id)) {
                    listadatos.set(i, prodcuts);
                }
            }
        //reescribo el archivo json
        GuardarJSON Guardado = new GuardarJSON();
        Guardado.guardarJSON_products(Config.Ruta_ProductsJson,listadatos);

    }

    //Metodo post crea una petición para añadir un nuevo elemento a la lista
    //y lo guarda en el archivo json
    @PostMapping("/Products")
    public void addProduct(@RequestBody Products newProduct) throws IOException {
        List<Products> listadatos;
        listadatos = new LectorJSON().leerJSON_Products(Config.Ruta_ProductsJson);

        Random random = new Random();
        boolean unique;

        // Mantén intentando hasta obtener un ID que no esté ya en listadatos
        do {
            int newId = random.nextInt(100); // Genera un número aleatorio entre 0 y 99
            unique = listadatos.stream().noneMatch(p -> p.getId() == newId);
            newProduct.setId(newId);
        } while (!unique);

        // agregar el nuevo elemento a la lista
        listadatos.add(newProduct);
        // reescribir el archivo json
        GuardarJSON guardado = new GuardarJSON();
        guardado.guardarJSON_products(Config.Ruta_ProductsJson, listadatos);
    }



    @DeleteMapping("/Products/{id}")
    public void removeProduct(@PathVariable int id) throws IOException {
        List<Products> listadatos;
        listadatos = new LectorJSON().leerJSON_Products(Config.Ruta_ProductsJson);
        // encontrar y eliminar el elemento de la lista
        for(int i=0;i<listadatos.size();i++) {
            if (listadatos.get(i).getId() == (id)) {
                listadatos.remove(i);
            }
        }
        // reescribir el archivo json
        GuardarJSON Guardado = new GuardarJSON();
        Guardado.guardarJSON_products(Config.Ruta_ProductsJson, listadatos);
    }



    @GetMapping("/Products/IdCode")
    public List<Products> IdCode() {
        List<Products> lista1;
        List<Products> lista2;
        Products products = new Products();
        //Primero leo el archivo con todos los datos para reordenarlos en caso de que haya habido cambios o creaciones de nuevos objetos
        lista1 = new LectorJSON().leerJSON_Products(Config.Ruta_ProductsJson);
        products.ordenarporId(lista1);
        //leo el archivo json con los datos ya reordenados para mandarselo al frontend
        lista2 = new LectorJSON().leerJSON_Products(Config.Ruta_ProductsJson_bkp);
        return lista2;
    }

}