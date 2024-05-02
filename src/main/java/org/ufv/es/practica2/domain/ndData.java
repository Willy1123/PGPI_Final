package org.ufv.es.practica2.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.ufv.es.practica2.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ndData {
    //Creamos los atributos de la clase ndData
    @SerializedName("_id")
    private UUID id;
    @SerializedName("mscode")
    private String msCode;

    @SerializedName("year")
    private String year;

    @SerializedName("estCode")
    private String estCode;

    @SerializedName("estimate")
    private Float estimate;

    @SerializedName("se")
    private Float se;

    @SerializedName("lowerCIB")
    private Float lowerCIB;

    @SerializedName("upperCIB")
    private Float upperCIB;

    @SerializedName("flag")
    private String flag;
    //Creamos los constructores
    public ndData() {
    }
    public ndData(UUID ID, String msCode, String year, String estCode, Float estimate, Float se, Float lowerCIB, Float upperCIB, String flag) {
        this.id=UUID.randomUUID();
        this.msCode = msCode;
        this.year = year;
        this.estCode = estCode;
        this.estimate = estimate;
        this.se = se;
        this.lowerCIB = lowerCIB;
        this.upperCIB = upperCIB;
        this.flag = flag;
    }
    //Creamos los getters y setters
    public UUID getID() {
        return id;
    }
    public void setID() {
        this.id=UUID.randomUUID();
    }
    public void setIDs(UUID id) {
        this.id = id;
    }
    public String getMsCode() {
        return msCode;
    }
    public void setMsCode(String msCode) {
        this.msCode = msCode;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getEstCode() {
        return estCode;
    }

    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    public Float getEstimate() {
        return estimate;
    }

    public void setEstimate(Float estimate) {
        this.estimate = estimate;
    }

    public Float getSe() {
        return se;
    }

    public void setSe(Float se) {
        this.se = se;
    }

    public Float getLowerCIB() {
        return lowerCIB;
    }

    public void setLowerCIB(Float lowerCIB) {
        this.lowerCIB = lowerCIB;
    }

    public Float getUpperCIB() {
        return upperCIB;
    }

    public void setUpperCIB(Float upperCIB) {
        this.upperCIB = upperCIB;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    public static void ordenarporMsCode(List<ndData> listaDatos){
        //leemos el archivo cp-national-datafile.json y creamos el archivo MsCode_json.json con los datos ordenados por MsCode

        //ordenamos la lista por MsCode
        Collections.sort(listaDatos, new Comparator<ndData>() {
            @Override
            public int compare(ndData o1, ndData o2) {
                return o1.getMsCode().compareTo(o2.getMsCode());
            }
        });
        //guardamos la lista ordenada en el archivo MsCode_json.json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaDatos);
        try {
            FileWriter writer = new FileWriter(Config.Ruta_ndDataJson_bkp);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}