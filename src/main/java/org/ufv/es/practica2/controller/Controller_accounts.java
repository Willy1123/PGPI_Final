package org.ufv.es.practica2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ufv.es.practica2.Config;
import org.ufv.es.practica2.LectorJSON;
import org.ufv.es.practica2.domain.Accounts;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class Controller_accounts {

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            List<Accounts> accounts = new LectorJSON().readJSON_accounts(Config.Ruta_Accounts);

            for (Accounts acc : accounts) {
                if ((acc.getUsername().equals(authRequest.getUsername())) && (acc.getPassword().equals(authRequest.getPassword()))){
                    return ResponseEntity.ok(acc.getRole());
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // Clase auxiliar para recibir la solicitud de autenticación
    public static class AuthRequest {
        private String username;
        private String password;

        public AuthRequest() {}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
