package com.example.demo.data;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoitureTest {
    Voiture voiture ;
    String marque = "BMW";
    int prix = 10000;

    @BeforeEach
    void init() {
        String marque = marque;
        int prix = prix;
        voiture = new Voiture(marque, prix);
    }

    @Test
    void creerVoiture(){
        assertEquals(prix, voiture.getPrix());
        assertEquals(marque, voiture.getMarque());
    }

}