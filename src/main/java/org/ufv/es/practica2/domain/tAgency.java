package org.ufv.es.practica2.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.time.format.DateTimeFormatter;

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



    private String date; // De aqui tambien se sacará la hora de ser necesario (DHL)

    private Boolean type; // 0 estandar, 1 urgente

    private UUID id;

    private String name;

    private String dirr;

    private List<Tuple> items;

    private Float weigth;

    private String postal;

    private Integer units;

    private String zone;

    public tAgency() {
    }

    public tAgency(String  date, Boolean type, UUID id, String name, String dirr, List items, Float weigth, String postal, Integer units, String zone) {
        this.date = date;
        this.type = type;
        this.id = id;
        this.name = name;
        this.dirr = dirr;
        this.items = items;
        this.weigth = weigth;
        this.postal = postal;
        this.units = units;
        this.zone = zone;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirr() {
        return dirr;
    }

    public void setDirr(String dirr) {
        this.dirr = dirr;
    }

    public List getItems() {
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


}
