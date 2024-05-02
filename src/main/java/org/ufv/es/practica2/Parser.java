package org.ufv.es.practica2;

import org.ufv.es.practica2.domain.ndData;

public class Parser {

    public static ndData Separar(String listaDatos){
        String[] datos = listaDatos.split(",");
        ndData nddata = new ndData();
        //esta función va dato a dato y los va metiendo en el objeto ndData

//metemos cada dato en el objeto ndData
        //asignamos el id con UUID
        for (int i=0;i<datos.length;i++){
            if(datos[i].equals(" ")){
                datos[i]="0";
            }
        }
        int num=0;
        nddata.setMsCode(datos[0]);
        nddata.setYear(datos[1]);
        nddata.setEstCode(datos[2]);
        nddata.setEstimate(Float.parseFloat(datos[3]));
        nddata.setSe(Float.parseFloat(datos[4]));
        nddata.setLowerCIB(Float.parseFloat(datos[5]));
        nddata.setUpperCIB(Float.parseFloat(datos[6]));
        nddata.setFlag(datos[7]);

        return nddata;
    }
}
