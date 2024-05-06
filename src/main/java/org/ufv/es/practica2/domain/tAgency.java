package org.ufv.es.practica2.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.ufv.es.practica2.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class tAgency {
    /*
        DHL, UPS(CAUCULO), FEDEX, dia ped
        DHL, hora ped
        3, tipo ped
        3, id, nombre y dirr ped
        DHL, listado de articulos
        UPS, FEDEX peso del pedido
        FEDEX, codigo postal
        FEDEX, cantidad de unidades total en la entrega
    */

    @SerializedName("date")
    private String date; // De aqui tambien se sacará la hora de ser necesario (DHL)
    @SerializedName("type")
    private Boolean type; // 0 estandar, 1 urgente
    @SerializedName("id")
    private UUID id;
    @SerializedName("nameCampaign")
    private String nameCampaign;
    @SerializedName("dir")
    private String dir;
    @SerializedName("items")
    private List<Tuple> items;
    @SerializedName("weigth")
    private Float weigth;
    @SerializedName("postal")
    private String postal;
    @SerializedName("units")
    private Integer units;
    @SerializedName("zone")
    private String zone;
    @SerializedName("state")
    private String state;
    @SerializedName("agency")
    private String agency;

    public tAgency() {
    }

    public tAgency(String  date, Boolean type, UUID id, String nameCampaign, String dir, List items, Float weigth, String postal, Integer units, String zone, String state, String agency) {
        this.date = date;//
        this.type = type;//
        this.id = id;//
        this.nameCampaign = nameCampaign;//
        this.dir = dir;//
        this.items = items;//
        this.weigth = weigth;//
        this.postal = postal;//
        this.units = units;//
        this.zone = zone;//
        this.state = state;
        this.agency = agency;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getNameCampaign() {
        return nameCampaign;
    }

    public void setNameCampaign(String nameCampaign) {
        this.nameCampaign = nameCampaign;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public List<Tuple> getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public Float getWeigth() {
        return weigth;
    }

    public void setWeigth(Float weigth) {
        this.weigth = weigth;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        zone = zone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public static void ordenarPorUltimoElemento(List<tAgency> listaPedidos) {
        // Invertimos el orden de la lista
        Collections.reverse(listaPedidos);

        // Guardamos la lista invertida en el archivo MsCode_json.json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaPedidos);
        try {
            FileWriter writer = new FileWriter(Config.Ruta_Pedidos_bkp);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
