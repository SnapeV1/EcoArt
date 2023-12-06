/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.entities;

import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class EventUser {
    private int id;
    private String nom;
    private LocalDate date;
    private String lieu;
    private String description;
    private String image;
    private int prix;
    private String pathQR ;

    public EventUser() {
    }

    public EventUser(int id, String nom, LocalDate date, String lieu, String description, String image, int prix) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public EventUser(int id, String nom, LocalDate date, String lieu, String description, String image, int prix, String pathQR) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.pathQR = pathQR;
    }

    public EventUser(String nom, LocalDate date, String lieu, String description, String image, int prix, String pathQR) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.pathQR = pathQR;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getPathQR() {
        return pathQR;
    }

    public void setPathQR(String pathQR) {
        this.pathQR = pathQR;
    }

    @Override
    public String toString() {
        return "EventUser{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", lieu=" + lieu + ", description=" + description + ", image=" + image + ", prix=" + prix + ", pathQR=" + pathQR + '}';
    }

    public Object getDate_a() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
