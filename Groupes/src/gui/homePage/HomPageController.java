/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.homePage;

import Utilisateur.Utilisateur;
import Utilisateur.UtilisateurService;
import groupes.gui.ConversationController;
import groupes.gui.YourGroupsController;
import gui.Admin.Utilisateurs.UtilisateurPropertiesController;
import gui.EspacePersonel.EspacePersonelController;
import gui.ReclamationClient.PageReclamationsController;
import gui.SignIn.SignInController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Utilisateur 2
 */
public class HomPageController implements Initializable {

    @FXML
    private ImageView picphoto;
    @FXML
    private Hyperlink profil;
    @FXML
    private Hyperlink disconnect;
    @FXML
    private Button home;
    @FXML
    private Button shop;
    @FXML
    private Button formation;
    @FXML
    private Button Event;
    @FXML
    private Button contact;
    @FXML
    private TextField userSearch;
    @FXML
    private Label errorLabel;
    Utilisateur current;
    
    UtilisateurService service=UtilisateurService.getInstance();
    @FXML
    private ImageView imageLogo;
    @FXML
    private Button groups;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onProfil(ActionEvent event) {
           try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../EspacePersonel/EspacePersonel.fxml"));
            Parent root = loader.load();
            
            
              EspacePersonelController controller=loader.getController();
              controller.setter(current,0);
              
            Stage cStage= (Stage) this.contact.getScene().getWindow();
            cStage.setWidth(710);
            cStage.setHeight(740);
              
            contact.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
    @FXML
    private void onDisconnect(ActionEvent event) {
        try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../SignIn/SignIn.fxml"));
            Parent root = loader.load();
            
            
             SignInController controller=loader.getController();
              
              
            Stage cStage= (Stage) this.Event.getScene().getWindow();
            cStage.setWidth(720);
            cStage.setHeight(520);
              
            Event.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onHome(ActionEvent event) {
    }

    @FXML
    private void onShop(ActionEvent event) {
    }

    @FXML
    private void onFormation(ActionEvent event) {
    }

    @FXML
    private void onEvent(ActionEvent event) {
    }

    @FXML
    private void onContact(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ReclamationClient/PageReclamations.fxml"));
            Parent root = loader.load();
            
            
             PageReclamationsController controller=loader.getController();
            controller.setUtilisateur(current);
            
            Stage cStage= (Stage) this.contact.getScene().getWindow();
            cStage.setWidth(620);
            cStage.setHeight(550);
              
            contact.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
    
    
    
    @FXML
    private void OnSearch(ActionEvent event) {
        String username = this.userSearch.getText();
        Utilisateur found = service.chercher(username);
        if(found==null)
            errorLabel.setText("No user Found");
        else{
            try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Admin/Utilisateurs/UtilisateurProperties.fxml"));
            Parent root = loader.load();
            
            
              UtilisateurPropertiesController controllerUtilisateur =loader.getController();
              controllerUtilisateur.setter(current,found,1);
              
            Stage cStage= (Stage) this.Event.getScene().getWindow();
            cStage.setWidth(720);
            cStage.setHeight(720);
              
            Event.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
        
    }
    
    public void setUtilisateur(Utilisateur current){
        this.current=current;
    }

    @FXML
    private void onConversation(MouseEvent event) {
        try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../groupes/gui/Conversation.fxml"));
            Parent root = loader.load();
            
            
               ConversationController controllerGroupe =loader.getController();
              controllerGroupe.setUtilisateur(current);
              
            Stage cStage= (Stage) this.Event.getScene().getWindow();
            cStage.setWidth(720);
            cStage.setHeight(720);
              
            Event.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
    
    
    
    @FXML
    private void onGroups(ActionEvent event) {
           try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../groupes/gui/YourGroups.fxml"));
            Parent root = loader.load();
            
            
               YourGroupsController controllerGroupe =loader.getController();
              controllerGroupe.setUtilisateur(current);
              
            Stage cStage= (Stage) this.Event.getScene().getWindow();
            cStage.setWidth(720);
            cStage.setHeight(720);
              
            Event.getScene().setRoot(root);
            
              
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
