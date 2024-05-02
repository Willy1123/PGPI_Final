package org.ufv.es.practica2;

import org.junit.jupiter.api.Test;
import org.ufv.es.practica2.controller.Controller_ndData;
import org.ufv.es.practica2.domain.ndData;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ControllerNdDataTest {

    @Test
    void ndData() {
        Controller_ndData controllerNdData = new Controller_ndData();
        List<ndData> result = controllerNdData.ndData();
        assertNotNull(result);
        assertTrue(result instanceof List);
        assertTrue(result.get(0) instanceof ndData);
    }
    @Test
    void cambiarndData() throws IOException {
        // Crear una instancia de la clase Controller
        Controller_ndData controllerNdData = new Controller_ndData();

        // Crear un ID único para la prueba
        UUID id = UUID.fromString("ce39358c-9312-457d-b57c-2b7f823042d9"); // Utilizo un ID existente

        // Crear una instancia de ndData con datos de prueba
        ndData testData = new ndData();
        testData.setIDs(id);
        testData.setMsCode("TestMsCode");
        testData.setYear("2007");
        testData.setEstCode("Prueba");
        testData.setEstimate(14.0f);
        testData.setSe(1.3f);
        testData.setLowerCIB(12.7f);
        testData.setUpperCIB(15.4f);
        testData.setFlag("");
        // Llamar al método cambiarndData() con el ID y los datos de prueba
        controllerNdData.cambiarndData(id, testData);
        // Recuperar los datos actualizados utilizando el método ndData()
        List<ndData> result = controllerNdData.ndData();
        // Verificar que los datos recuperados coinciden con los datos de prueba
        boolean isUpdated = false;
        for (int i = 0; i < result.size(); i++) {
            ndData data = result.get(i);                if (data.getID().equals(id)) {
                // Comprobar que el campo MsCode coincide con los datos de prueba
                assertEquals(testData.getMsCode(), data.getMsCode());
                isUpdated = true;
                break;
            }
        }
        // Asegurarse de que los datos se hayan actualizado
        assertTrue(isUpdated, "Los datos no se actualizaron");
    }
    @Test
    void eliminarndData() throws IOException {// Crear una instancia de la clase Controller
        Controller_ndData controllerNdData = new Controller_ndData();
        // Crear un ID único para la prueba
        UUID id = UUID.fromString("ce39358c-9312-457d-b57c-2b7f823042d9"); // Use un ID existente de sus datos

        // Llamar al método eliminarndData() con el ID de los datos de prueba
        controllerNdData.eliminarndData(id);
        // Recuperar los datos actualizados utilizando el método ndData()
        List<ndData> result = controllerNdData.ndData();

        // Verificar que los datos recuperados no contienen los datos de prueba
        boolean isDeleted = true;
        for (ndData data : result) {
            if (data.getID().equals(id)) {
                // Si los datos de prueba aún están presentes, la prueba falla
                isDeleted = false;
                break;
            }
        }
    }

    @Test
    void agregarndData() throws IOException {
        // Crear una instancia de la clase Controller
        Controller_ndData controllerNdData = new Controller_ndData();

        // Crear una instancia de ndData con datos de prueba
        ndData testData = new ndData();
        //genero un ID random y relleno el objeto con información
        testData.setID();
        testData.setMsCode("TestMsCode");
        testData.setYear("2007");
        testData.setEstCode("Prueba");
        testData.setEstimate(14.0f);
        testData.setSe(1.3f);
        testData.setLowerCIB(12.7f);
        testData.setUpperCIB(15.4f);
        testData.setFlag("TestFlag");
        // Llamar al método agregarndData() con los datos de prueba
        controllerNdData.agregarndData(testData);
        // Recuperar los datos actualizados utilizando el método ndData()
        List<ndData> result = controllerNdData.ndData();
        // Verificar que los datos recuperados contienen los datos de prueba
        boolean isAdded = false;
        for (ndData data : result) {
            if (data.getMsCode().equals(testData.getMsCode())) {
                // Si los datos de prueba están presentes, la prueba pasa
                isAdded = true;
                break;
            }
        }
        // Asegurarse de que los datos se hayan agregado
        assertTrue(isAdded, "Los datos no se agregaron");
    }

    @Test
    void msCode() {
        Controller_ndData controllerNdData = new Controller_ndData();
        List<ndData> result = controllerNdData.MsCode();
        assertNotNull(result);
        assertTrue(result instanceof List);
        assertTrue(result.get(0) instanceof ndData);
    }


}