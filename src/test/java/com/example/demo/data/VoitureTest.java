package com.example.demo.data;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoitureTest {
    Voiture voiture ;

    @Before
    void init() {
        String marque = "BMW";
        int prix = 100000;
        voiture = new Voiture("BMW", 100000);
    }

    @Test
    void creerVoiture(){
        assertEquals(prix, voiture.getPrix());
        assertEquals(marque, voiture.getMarque());
    }

}