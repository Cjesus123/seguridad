package com.tutorial.authservice.utiles;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Key: " + base64Key);
    }
    //wIXZCWObK5oSdZriKq17XKf77UanlW/ZP/X8A7h50YPlMKcNNWIYsA+B1yJdGCHzpA02RQHqeu3JpNsI150oeQ==
}
