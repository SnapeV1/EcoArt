/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package groupes.gui;

import MyConnection.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import groupes.entities.Groupe;
import static groupes.gui.AddMemController.idOwner;
import groupes.services.ChatFunctions;
import groupes.services.GroupFunctions;
import groupes.services.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hammeda
 */
public class YourGroupsController implements Initializable {
    
int idOwner=3;
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
    try {
        GroupF.showOwnedGroups(OwnedGtable,nameColumn,sizeColumn);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
       
    } 
    
    }

    @FXML
    private void GoToCreateG(MouseEvent event) {
    try {
        GroupF.navigateToFXML("../gui/CreationGroupe.fxml", CreateGBtn);
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void ConfigureGroup(MouseEvent event) {
    try {
        
        if(OwnedGtable.getSelectionModel().getSelectedIndex()!=-1){
         groupF.FetchSelectedUser(OwnedGtable);
        groupF.navigateToFXML("../gui/GroupPanel.fxml", ConfigureGbtn);
    } }catch (IOException ex) {
        System.out.println(ex.getMessage());
    
    }
    }

    @FXML
    private void DeleteGroup(MouseEvent event) throws SQLException {
groupF.DeleteGroupF(OwnedGtable, nameColumn);
groupF.showOwnedGroups(OwnedGtable, nameColumn, sizeColumn);
    }



    

    @FXML
    private void Return(MouseEvent event) {
    try {
        groupF.navigateIMGToFXML("../gui/Accueil.fxml", ReturnImg);
    } catch (IOException ex) {
        ex.getMessage();
    }
    }
    







}
