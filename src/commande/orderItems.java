/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commande;

import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;

/**
 *
 * @author User
 */
public class orderItems {
    long id_c;
    LocalDate date;
    String product;
    int quantite ;
    double prix;

    public orderItems(long id_c, LocalDate date, String product, int quantite, double prix) {
        this.id_c = id_c;
        this.date = date;
        this.product = product;
        this.quantite = quantite;
        this.prix = prix;
    }

    public long getNumC() {
        return id_c;
    }

    public void setNumC(long numC) {
        this.id_c = numC;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public orderItems() {
    }
    
    
}
