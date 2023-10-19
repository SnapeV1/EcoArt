/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupes.services;

import java.time.LocalDate;

/**
 *
 * @author hammeda
 */
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate DateNaissance;
    private int number;

    public User(int id, String nom, String prenom, String email, LocalDate DateNaissance, int number) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.DateNaissance = DateNaissance;
        this.number = number;
    }

  

     
    
    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(LocalDate DateNaissance) {
        this.DateNaissance = DateNaissance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
}
