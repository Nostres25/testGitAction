package com.example.demo.service;

import main.java.com.example.demo.data.Voiture;
import main.java.com.example.demo.service.Echantillon;
import main.java.com.example.demo.service.Statistique;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StatistiqueImpl implements Statistique {

    List<Voiture> voitures = new ArrayList<Voiture>();

    @Override
    public void ajouter(Voiture voiture) {
        voitures.add(voiture);
    }

    @Override
    public Echantillon prixMoyen() throws ArithmeticException {
        int prixTotal = 0;
        int nombreDeVoitures = 0;
        Iterator<Voiture> iterator = voitures.iterator();
        while(iterator.hasNext()){
            prixTotal = prixTotal + iterator.next().getPrix();
            nombreDeVoitures++;
        }
        return new Echantillon(nombreDeVoitures, prixTotal/nombreDeVoitures);
    }
}