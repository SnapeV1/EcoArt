/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupes.entities;

import java.time.LocalDate;

/**
 *
 * @author hammeda
 */
public class User {
    private int id;
    private String CIN;
    private String nom;
    private String prenom;
    private LocalDate DateNaissance;
    private int age;
    private String pic;
    private String email;
   
    private int number;
    private String password;
    public User(int id, String nom, String prenom, String email, LocalDate DateNaissance, int number) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.DateNaissance = DateNaissance;
        this.number = number;
    }

    public User(int id, String CIN, String nom, String prenom, LocalDate DateNaissance, int age, String pic, String email, int number, String password) {
        this.id = id;
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.DateNaissance = DateNaissance;
        this.age = age;
        this.pic = pic;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
