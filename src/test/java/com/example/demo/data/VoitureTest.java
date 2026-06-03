package com.example.demo.data;

import main.java.com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoitureTest {
    Voiture voiture ;

    @Test
    void creerVoiture(){
        String marque = "BMW";
        int prix = 100000;
        voiture = new Voiture("BMW", 100000);

        assertEquals(prix, voiture.getPrix());
        assertEquals(marque, voiture.getMarque());
    }

}