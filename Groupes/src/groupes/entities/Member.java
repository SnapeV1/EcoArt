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
public class Member extends User{
    private int groupID;
    private String Role;

    public Member(int id, String nom, String prenom, String email, LocalDate DateNaissance, int number) {
        super(id, nom, prenom, email, DateNaissance, number);
    }

    public Member(int groupID, String Role, int id, String nom, String prenom, String email, LocalDate DateNaissance, int number) {
        super(id, nom, prenom, email, DateNaissance, number);
        this.groupID = groupID;
        this.Role = Role;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
   

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
    
}
