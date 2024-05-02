package org.ufv.es.practica2.controller;

import org.springframework.web.bind.annotation.*;
import org.ufv.es.practica2.Config;
import org.ufv.es.practica2.GuardarJSON;
import org.ufv.es.practica2.LectorJSON;
import org.ufv.es.practica2.domain.ndData;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller_ndData {

    @GetMapping("/ndData")
    public List<ndData> ndData() {
        List<ndData> listadatos;
        listadatos = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson);
        return listadatos;
    }

    @PutMapping("/ndData/{id}")
    public void cambiarndData(@PathVariable UUID id, @RequestBody ndData ndData) throws IOException {
        List<ndData> listadatos;
        listadatos = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson);
        UUID aux = ndData.getID();
        //cambio el elemento de la lista
            for(int i=0;i<listadatos.size();i++) {
                if (listadatos.get(i).getID().equals(id)) {
                    listadatos.set(i, ndData);
                }
            }
        //reescribo el archivo json
        GuardarJSON Guardado = new GuardarJSON();
        Guardado.guardarJSON_ndData(Config.Ruta_ndDataJson,listadatos);

    }
    @DeleteMapping("/ndData/{id}")
    public void eliminarndData(@PathVariable UUID id) throws IOException {
        List<ndData> listadatos;
        listadatos = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson);
        // encontrar y eliminar el elemento de la lista
        for(int i=0;i<listadatos.size();i++) {
            if (listadatos.get(i).getID().equals(id)) {
                listadatos.remove(i);
            }
        }
        // reescribir el archivo json
        GuardarJSON Guardado = new GuardarJSON();
        Guardado.guardarJSON_ndData(Config.Ruta_ndDataJson, listadatos);
    }

    //Metodo post crea una petición para añadir un nuevo elemento a la lista
    //y lo guarda en el archivo json
    @PostMapping("/ndData")
    public void agregarndData(@RequestBody ndData nuevoNdData) throws IOException {
        List<ndData> listadatos;
        nuevoNdData.setID();
        listadatos = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson);
        // agregar el nuevo elemento a la lista
        listadatos.add(nuevoNdData);
        // reescribir el archivo json
        GuardarJSON Guardado = new GuardarJSON();
        Guardado.guardarJSON_ndData(Config.Ruta_ndDataJson, listadatos);
    }

    @GetMapping("/ndData/MsCode")
    public List<ndData> MsCode() {
        List<ndData> lista1;
        List<ndData> lista2;
        ndData datos= new ndData();
        //Primero leo el archivo con todos los datos para reordenarlos en caso de que haya habido cambios o creaciones de nuevos objetos
        lista1 = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson);
        datos.ordenarporMsCode(lista1);
        //leo el archivo json con los datos ya reordenados para mandarselo al frontend
        lista2 = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson_bkp);
        return lista2;
    }

}