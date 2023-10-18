/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package groupes.gui;
import groupes.services.ChatFunctions;
import MyConnection.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import groupes.services.Conversation;
import groupes.services.GroupFunctions;
import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hammeda
 */
public class ChatController implements Initializable {

    @FXML
    private ListView<String> conversationListView;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private Text EmailTXT;
    @FXML
    private JFXButton SendMessagebtn;
int idOwner=3;
private String selectedItem; 
ChatFunctions ChatF = new ChatFunctions();
    @FXML
    private ListView<String> conversationListView1;
    @FXML
    private ImageView ReturnImg;
    GroupFunctions groupF=new GroupFunctions();
    @FXML
    private Text you;
public void setSelectedItem(String selectedItem) {
    this.selectedItem = selectedItem;
}
int test=0;

@Override
public void initialize(URL url, ResourceBundle rb) {
  
        try {
            ChatF.loadConversation(conversationListView,conversationListView1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        you.setText(ChatF.getEmailbyID(idOwner));
    
}


    
   



  
    public void setEmail(String email) {
        EmailTXT.setText(email);
        
    }

    @FXML
    private void SendMessage(MouseEvent event) throws SQLException, IOException  {
         
  if (!messageTextArea.getText().isEmpty()||(messageTextArea.getText().length()>1)) {
        String messageText = messageTextArea.getText();

     
        boolean containsBadWords = ChatF.BadWordCheck(messageText);

        if (containsBadWords) {
            test++;
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("BE RESPECTFUL");
            alert.showAndWait();
            messageTextArea.setText("****");
        }

        
        ChatF.Send(idOwner, EmailTXT, messageTextArea);
        ChatF.loadConversation(conversationListView, conversationListView1);
        if(test==3){ try {
            test=0;
        groupF.navigateIMGToFXML("../gui/Accueil.fxml", ReturnImg);
    } catch (IOException ex) {
        ex.getMessage();
    }
        }
    }    }

    @FXML
    private void Return(MouseEvent event) {
    
    
    
    try {
        groupF.navigateIMGToFXML("../gui/Conversation.fxml", ReturnImg);
    } catch (IOException ex) {
        ex.getMessage();
    }
    }
   
    
    
    
    
}
