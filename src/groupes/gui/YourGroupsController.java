/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package groupes.gui;

import Utilisateur.Utilisateur;
import com.jfoenix.controls.JFXButton;
import groupes.entities.Groupe;
import groupes.services.ChatFunctions;
import groupes.services.GroupFunctions;
import gui.homePage.HomPageController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hammeda
 */
public class YourGroupsController implements Initializable {
    Utilisateur current;
     public void setUtilisateur(Utilisateur current){
        this.current=current;
         System.out.println("YourGroups" + current+" id : "+current.getId());
          try {
          
         int IDOwner=(int)current.getId();
       
        GroupF.showOwnedGroups(IDOwner,OwnedGtable,nameColumn,sizeColumn);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
       
    } 
    }
long idOwner;
    @FXML
    private TableView<Groupe> OwnedGtable;
    @FXML
    private JFXButton CreateGBtn;
    @FXML
    private TableColumn<Groupe, String> nameColumn;
    @FXML
    private TableColumn<Groupe, Integer> sizeColumn;
GroupFunctions GroupF=new GroupFunctions();
    @FXML
    private JFXButton ConfigureGbtn;
    @FXML
    private JFXButton DeleteGbtn;
    GroupFunctions groupF=new GroupFunctions();
    ChatFunctions CF=new ChatFunctions();
    @FXML
    private ImageView ReturnImg;
    @FXML
    private Text GGNameText;
 
    public void initialize(URL url, ResourceBundle rb) {
   
    
    }

    @FXML
    private void GoToCreateG(MouseEvent event) {
     try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreationGroupe.fxml"));
            Parent root = loader.load();
            
            
               CreationGroupeController controllerGroupe =loader.getController();
              controllerGroupe.setUtilisateur(current);
              
            Stage cStage= (Stage) this.ConfigureGbtn.getScene().getWindow();
            cStage.setWidth(860);
            cStage.setHeight(720);
              
            ConfigureGbtn.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ConfigureGroup(MouseEvent event) {
   
        
        if(OwnedGtable.getSelectionModel().getSelectedIndex()!=-1){
         groupF.FetchSelectedUser(OwnedGtable);
         
          try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupPanel.fxml"));
            Parent root = loader.load();
            
            
               GroupPanelController controllerGroupe =loader.getController();
              controllerGroupe.setUtilisateur(current);
              
            Stage cStage= (Stage) this.ConfigureGbtn.getScene().getWindow();
            cStage.setWidth(900);
            cStage.setHeight(650);
              
            ConfigureGbtn.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }}
       
    
    @FXML
    private void DeleteGroup(MouseEvent event) throws SQLException {
         int IDOwner=(int)idOwner;
         System.out.println("delete"+IDOwner);
groupF.DeleteGroupF(OwnedGtable, nameColumn);
OwnedGtable.refresh();
groupF.showOwnedGroups(IDOwner,OwnedGtable, nameColumn, sizeColumn);
       
    }



    

    @FXML
    private void Return(MouseEvent event) {
    
    
    
    
        try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../gui/homePage/homPage.fxml"));
            Parent root = loader.load();
            
            
               HomPageController controllerGroupe =loader.getController();
              controllerGroupe.setUtilisateur(current);
              
            Stage cStage= (Stage) this.ConfigureGbtn.getScene().getWindow();
            cStage.setWidth(750);
            cStage.setHeight(600);
              
            ConfigureGbtn.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    







}
