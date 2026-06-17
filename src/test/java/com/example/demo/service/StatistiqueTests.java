package com.example.demo.service;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.demo.service.StatistiqueImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StatistiqueTests {

    @Test
    void testPrixMoyenSansVoiture() throws Exception {
        StatistiqueImpl stats = new StatistiqueImpl();

        assertThrows(ArithmeticException.class, stats::prixMoyen);
    }

    @Test
    void testPrixMoyen() throws Exception {
        StatistiqueImpl stats = new StatistiqueImpl();

        int prixTotal = 0;

        Voiture[] voitures = {
                new Voiture("Mercedes", 200000),
                new Voiture("Toyota", 500000)
        };

        for (Voiture voiture : voitures) {
            prixTotal += voiture.getPrix();
            stats.ajouter(voiture);
        }

        int nombreVoitures = voitures.length;

        Echantillon echantillon = stats.prixMoyen();

        assertEquals(echantillon.getNombreDeVoitures(), nombreVoitures);
        assertEquals(echantillon.getPrixMoyen(), prixTotal/nombreVoitures);

    }
}