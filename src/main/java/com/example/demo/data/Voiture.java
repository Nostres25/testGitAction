package com.example.demo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Voiture {
    int id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    // Définir l'identifiant de la voiture
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                '}';
    }
}
