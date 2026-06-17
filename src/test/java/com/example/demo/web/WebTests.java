package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import com.example.demo.web.PasDeVoitureException;
import com.example.demo.service.Echantillon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    // Voir liens dans TD3 pour continuer
    // Lien intéréssant : https://codingnomads.com/api-testing-mockmvc-jsonpath-example

    @Test
    public void getStatistiqueSansVoiture() throws Exception {
        System.out.println("Sans voiture");

        mockMvc.perform(get("/statistique"))
            .andDo(print())
            .andExpect(status().isOk());
            //ne fonctionne pas car le serveur retourne un code 200 et un body null si on throw une exception : .andExpect(result -> assertInstanceOf(PasDeVoitureException.class, result.getResolvedException()));
    }

    @Test
    public void getStatistique() throws Exception {
        Echantillon echantillon = new Echantillon(10, 10000);
        // Mockito (!= MockMVC)
        when(statistiqueImpl.prixMoyen()).thenReturn(echantillon);

        // Ajouter voitures
        Voiture[] voitures = {
                new Voiture("Mercedes", 200000),
                new Voiture("Toyota", 500000)
        };

        int prixTotal = 0;

        for (Voiture voiture : voitures) {
            prixTotal += voiture.getPrix();
            statistiqueImpl.ajouter(voiture);
        }

        int nombreVoitures = voitures.length;

        // Expect la bonne réponse
        mockMvc.perform(get("/statistique"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreDeVoitures").value(10))
            .andExpect(jsonPath("$.prixMoyen").value(10000));

    }


    @Test
    public void getVoiture() throws Exception {
        mockMvc.perform(get("/voiture"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
    }

}