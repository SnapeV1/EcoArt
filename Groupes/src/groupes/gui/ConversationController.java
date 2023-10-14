/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package groupes.gui;

import MyConnection.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.PreparedStatement;
import groupes.services.TempUser;
import groupes.services.ChatFunctions;
import groupes.services.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hammeda
 */

public class ConversationController implements Initializable {
int idOwner=1;
ChatFunctions ChatF=new ChatFunctions();
    @FXML
    private TableView<User> GTable;
    @FXML
    private TableColumn<User, String> TName;
    @FXML
    private TableColumn<User, String> TPrenom;
    @FXML
    private TableColumn<User, String> TEmail;
    @FXML
    private JFXButton OpenConvbtn;
    @FXML
    private ImageView ReturnImg;

    /**
     * Initializes the controller class.
     */
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
ChatF.ShowUsersG(GTable,TName,TPrenom,TEmail);

    }





    
    

    @FXML
    private void OpenConv(MouseEvent event) throws SQLException {
        User selectedUser = GTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedUser.getId());       
        ChatFunctions CF=new ChatFunctions();
        CF.GetEmailTotalk(selectedUser.getEmail());
         try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
Parent chatRoot = loader.load();
ChatController chatController = loader.getController();


chatController.setEmail(selectedUser.getEmail());
    
        Scene chatScene = new Scene(chatRoot);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(chatScene);
        currentStage.show();
        
         } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
        
        
        
        
        
        
    }

    @FXML
    private void Return(MouseEvent event) {
    }




    }    
    




