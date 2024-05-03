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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@RestController
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
    public List<Products> showProduct(@PathVariable String Name) throws IOException {
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

    @GetMapping("/Requests") // Buscar producto por nombre para ponerlo en la lista
    public List<tAgency> showRequests(@PathVariable String Name) throws IOException {
        List<tAgency> listadatos = new LectorJSON().readJSON_accounts(Config.Ruta_Accounts);
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
                        item.setStock( (item.getStock() - tuple.getQuantity()) ); // Restamos la cantidad que necesita el pedido
                    } else {
                        tuple.setQuantity(0);
                        valid.add(tuple); // Ponemos a cero la cantidad del pedido y no se resta stock
                    }
                    break;  // Salir del bucle una vez encontrado
                }
            }
        }
        GuardarJSON Guardado = new GuardarJSON();
        Guardado.guardarJSON_products(Config.Ruta_ProductsJson,stock);
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

        switch (newRequest.getAgency()) {
            case "DHL":
                Save.setDate(now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                break;
            case "UPS":
                List<ndData> pobs = new LectorJSON().leerJSON_ndData(Config.Ruta_ndDataJson);
                for (int i = 0; i < pobs.size(); i++) {
                    if (pobs.get(i).getMsCode().equals(newRequest.getZone())) {
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
        }
        Save.setType(newRequest.getType());
        Save.setId(UUID.randomUUID());
        Save.setName(newRequest.getName());
        Save.setAgency(newRequest.getAgency());
        Save.setDirr(newRequest.getDirr());

        List<Tuple> before_items = check_stock(newRequest.getItems());
        Integer before_units = 0;
        for (Tuple item : before_items) {
            before_units += item.getQuantity();
        }

        List<Tuple> items = check_stock(newRequest.getItems());
        Save.setItems(items);

        Integer units = 0;
        Float weigth = Float.valueOf(0);
        for (Tuple item : items) {
            units += item.getQuantity();
        }
        if (before_units == units) {
            Save.setState("Listo y completo");
        } else {
            Save.setState("Listo y faltante");
        }

        Save.setPostal(newRequest.getPostal());
        Save.setUnits(units);



                /*
                Estados:
                    User, el que hace la peticion
                        Listo y completo
                        Listo y faltante
                    Warehouse, el que lo envia
                        Enviado y completo
                        Enviado y faltante
                    User, el que lo recibbe
                        Recibido y completo
                        Recibido y faltante
                    User?
                        Perdido
                 */


    }

    public void imprimir_albarán(tAgency request) {
        Document document = new Document();

        try {
            // Configurar PdfWriter para escribir en el documento
            PdfWriter.getInstance(document, new FileOutputStream( ("albaran_" + request.getId().toString() + ".pdf") ));

            // Abrir el documento para escribir
            document.open();

            document.add(new Paragraph("ID del pedido: " + request.getId()));
            document.add(new Paragraph("Agencia: " + request.getAgency()));
            if (request.getAgency() == "UPS") {
                document.add(new Paragraph("Fecha de llegada: " + request.getDate()));
            } else {
                document.add(new Paragraph("Fecha de pedido: " + request.getDate()));
            }

            document.add(new Paragraph("Zona: " + request.getZone()));
            document.add(new Paragraph("Nombre: " + request.getName()));
            document.add(new Paragraph("Dirección: " + request.getDirr()));
            document.add(new Paragraph("Código postal: " + request.getPostal()));
            document.add(new Paragraph("Tipo: " + request.getType()));
            document.add(new Paragraph("")); // Espacio

            document.add(new Paragraph("Productos:"));
            for (Tuple temp : request.getItems()) {
                document.add(new Paragraph("Producto: " + temp.getName() + ", cantidad: " + temp.getQuantity()));
            }
            document.add(new Paragraph("Unidades totales: " + request.getUnits()));
            document.add(new Paragraph("Peso: " + request.getWeigth()));
            document.add(new Paragraph("Estado del pedido: " + request.getState()));

            // Cerrar el documento
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("PDF de albarán " + request.getId() + " creado exitosamente.");
    }

    public void imprimir_etiqueta(tAgency request) {
        Document document = new Document();

        try {
            // Configurar PdfWriter para escribir en el documento
            PdfWriter.getInstance(document, new FileOutputStream( ("albaran_" + request.getId().toString() + ".pdf") ));

            // Abrir el documento para escribir
            document.open();

            document.add(new Paragraph("Agencia: " + request.getAgency()));
            switch (request.getAgency()) {
                case "DHL":
                    document.add(new Paragraph("Fecha de pedido: " + request.getDate()));
                    document.add(new Paragraph("Tipo: " + request.getType()));
                    document.add(new Paragraph("ID del pedido: " + request.getId()));
                    document.add(new Paragraph("Nombre: " + request.getName()));
                    document.add(new Paragraph("Dirección: " + request.getDirr()));

                    document.add(new Paragraph("Productos:"));
                    for (Tuple temp : request.getItems()) {
                        document.add(new Paragraph("Producto: " + temp.getName() + ", cantidad: " + temp.getQuantity()));
                    }
                    break;
                case "UPS":
                    document.add(new Paragraph("Fecha de llegada: " + request.getDate()));
                    document.add(new Paragraph("Tipo: " + request.getType()));
                    document.add(new Paragraph("ID del pedido: " + request.getId()));
                    document.add(new Paragraph("Nombre: " + request.getName()));
                    document.add(new Paragraph("Dirección: " + request.getDirr()));
                    document.add(new Paragraph("Peso: " + request.getWeigth()));
                    break;
                case "FEDEX":
                    document.add(new Paragraph("Fecha de pedido: " + request.getDate()));
                    document.add(new Paragraph("Tipo: " + request.getType()));
                    document.add(new Paragraph("ID del pedido: " + request.getId()));
                    document.add(new Paragraph("Nombre: " + request.getName()));
                    document.add(new Paragraph("Dirección: " + request.getDirr()));

                    document.add(new Paragraph("Código postal: " + request.getPostal()));
                    document.add(new Paragraph("Peso: " + request.getWeigth()));
                    document.add(new Paragraph("Unidades totales: " + request.getUnits()));
                    break;
                default:
                    System.out.println("Agencia de transporte mal especificada, se guardará solo la fecha y hora de pedido");
            }



            // Cerrar el documento
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Etiqueta de pedido " + request.getId() + " creado exitosamente.");




    }



}
