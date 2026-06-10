package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import com.example.demo.web.PasDeVoitureException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {
    // TODO à faire

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    // Voir liens dans TD3 pour continuer
    // Lien intéréssant : https://codingnomads.com/api-testing-mockmvc-jsonpath-example

    @Test
    public void getStatistique() {

        // Expect exception pas de voiture
        mockMvc.perform(get("/statistique"))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasDeVoitureException));

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
            .andExpect(jsonPath("nombreDeVoitures").value(nombreVoitures))
            .andExpect(jsonPath("prixMoyen").value(prixTotal/nombreVoitures));

    }


    @Test
    public void creerVoiture() {

        mockMvc.perform(get("/voiture"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(model().attribute("id", 1))
            .andExpect(jsonPath("id").value(1));


    }

}