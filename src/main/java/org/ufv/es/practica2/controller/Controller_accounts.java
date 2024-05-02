package org.ufv.es.practica2.controller;

import org.ufv.es.practica2.Config;
import org.ufv.es.practica2.LectorJSON;
import org.ufv.es.practica2.domain.Accounts;

import java.util.List;

public class Controller_accounts {
    public String authenticate(String username, String password) {
        List<Accounts> accounts = new LectorJSON().readJSON_accounts(Config.Ruta_Accounts);

        for (Accounts acc : accounts) {
            if ((acc.getUsername().equals(username)) && (acc.getPassword().equals(password))){
                return acc.getRole();
            }
        }
        return null; // failed
    }

}
