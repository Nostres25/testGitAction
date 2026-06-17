package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatistiqueController {

    @Autowired
    Statistique statistique;
// ça ne fonctionne pas, une exception renvoie tjr un code 200 avec un body null
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(PasDeVoitureException.class)
//    public void handleException1() {
//    }


    @GetMapping(value = "/statistique")
    public Echantillon getStatistiques() throws PasDeVoitureException {
        try{
            return statistique.prixMoyen();
        } catch (ArithmeticException e) {
            throw new PasDeVoitureException();
        }
    }

    @PostMapping("/voiture")
    public void creerVoiture(@RequestBody Voiture voiture){
        statistique.ajouter(voiture);
    }

}