/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package groupes.gui;

import MyConnection.MyConnection;
import com.jfoenix.controls.JFXButton;
import groupes.services.GroupFunctions;
import groupes.services.TempUser;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hammeda
 */
public class AddMemController implements Initializable {
static int idOwner=1;
    @FXML
    private TableView<User> GTable;
    @FXML
    private TableColumn<User, String> TName;
    @FXML
    private TableColumn<User, String> TPrenom;
    @FXML
    private TableView<TempUser> GmembersTable;
    @FXML
    private TableColumn<User, String> TEmail;
    @FXML
    private TableColumn<User, String> GNameCell;
    @FXML
    private TableColumn<User, String> GEmailCell;
    @FXML
    private JFXButton AddGmember;
    @FXML
    private JFXButton DeleteGMbtn;
    @FXML
    private JFXButton ConfirmGAbtn;
@FXML
    private Text GGNameText= new Text();
    @FXML
    private Text AddMemTXT= new Text();
    @FXML
    private Text LanguageText= new Text();
    @FXML
    private ImageView ReturnImg;
    GroupFunctions groupF=new GroupFunctions();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

     try {
        groupF.affichageMems(GGNameText,GNameCell,GEmailCell);
   
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
           ShowUsersG();
           
     }
    
    

    @FXML
    private void AddGmember(MouseEvent event) {
        
       groupF.AddGmember(GTable, TName, TPrenom, GmembersTable, TEmail, GNameCell, GEmailCell);
    }

    @FXML
    private void DeleteGM(MouseEvent event) {
        

    TempUser selectedUser = GmembersTable.getSelectionModel().getSelectedItem();

if ( selectedUser!= null) {
    ObservableList<TempUser> data = GmembersTable.getItems();
    data.remove(selectedUser);

    
    GmembersTable.refresh();
}
    }

    @FXML
    private void ConfirmGA(MouseEvent event) throws SQLException, IOException {
      
       groupF.confirm(GmembersTable, ConfirmGAbtn);

    }

    
    public void ShowUsersG() {
     
        try {
            ObservableList<User> list=groupF.GetUserList();
            TName.setCellValueFactory(new PropertyValueFactory<>("nom"));
            TPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            TEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            GTable.setItems(list);
            System.out.println("Setting items fil TableView jawou behi AAA");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    
  
private boolean isFrench = false;
    @FXML
    private void SwitchLanguage(MouseEvent event) {
     isFrench = !isFrench;
        
        if (isFrench) {
            
          
            AddMemTXT.setText("Ajouter membre");
            ConfirmGAbtn.setText("Confirmer :");
            LanguageText.setText("fr");
            DeleteGMbtn.setText("Effacer");
            AddGmember.setText("Ajouter");
            
        } else {
          
            
            AddMemTXT.setText("Add Member");
            ConfirmGAbtn.setText("Confirm :");
            LanguageText.setText("en");
            DeleteGMbtn.setText("Delete");
            AddGmember.setText("Add");
        }
    }
       
   

    @FXML
    private void Return(MouseEvent event) {
        try {
        groupF.navigateIMGToFXML("../gui/YourGroups.fxml", ReturnImg);
    } catch (IOException ex) {
        ex.getMessage();
    }
    } 
}
