package com.example.demo.web;

import com.example.demo.data.Voiture;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoitureController {

    @GetMapping("/bonjour")
    public String disBonjour(){
        return "Bonjour";
    }

    @GetMapping("/voiture")
    public Voiture getVoiture(){
        Voiture v = new Voiture();
        v.setId(1);
        return v;
    }

    @PostMapping("/voiture")
    public void creerVoiture(@RequestBody Voiture voiture) throws PasDeVoitureException {
        if(voiture.getId() == 0){
            throw new PasDeVoitureException();
        }
        System.out.println(voiture);
    }

}
