/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupes.services;

import MyConnection.MyConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import groupes.services.MiscFunctions;
/**
 *
 * @author hammeda
 */
public class ChatFunctions {

    public ChatFunctions() {
    }
 
    int idOwner=1;
     public int getUserIDByEmail(String email) {
    int userId = -1; 

    try {
       
        String query = "SELECT id FROM User WHERE email = ?";

       
      java.sql.Connection  connection = MyConnection.getCon();
      java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);

       
        preparedStatement.setString(1, email);

        
        java.sql.ResultSet resultSet = preparedStatement.executeQuery();

       
        if (resultSet.next()) {
            userId = resultSet.getInt("id");
        }

      
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }

    return userId;
}
     
     
    public void Send(int idOwner,Text EmailTXT, TextArea messageTextArea) throws SQLException{
      LocalDateTime currentDateTime = LocalDateTime.now();
        String insertSql = "INSERT INTO Conversation (idUser1, idUser2, Date_MSG, Msg) VALUES (?, ?, ?, ?)";
        
         java.sql.Connection connection = MyConnection.getCon();
     java.sql.PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
     
         preparedStatement.setInt(1, idOwner);
    preparedStatement.setInt(2, getUserIDByEmail( EmailTXT.getText())); 
    preparedStatement.setObject(3, currentDateTime);
    preparedStatement.setString(4, messageTextArea.getText());
        
        preparedStatement.executeUpdate();
            preparedStatement.close();
                connection.close();
messageTextArea.clear();
     
     
     
     }
     
    public void GetEmailTotalk(String mail) throws SQLException{
    java.sql.Connection connection = MyConnection.getCon();
    String newEmail = mail; 
    String updateQuery = "UPDATE tempmail SET Email = ? WHERE id = 1";
   java.sql.PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
    preparedStatement.setString(1, newEmail);
     int rowsUpdated = preparedStatement.executeUpdate();
     preparedStatement.close();
    connection.close();
    }
    public void loadConversation(ListView<String> conversationListView,ListView<String> conversationListView1) throws SQLException {
    conversationListView.getItems().clear(); 
java.sql.Connection connection = MyConnection.getCon();
 String selectQuery = "SELECT Email FROM tempmail WHERE id = ?";
 int id=1;
 int userId;
  String email = null;
 java.sql.PreparedStatement  preparedStatement = connection.prepareStatement(selectQuery);
 preparedStatement.setInt(1, id);
 java.sql.ResultSet resultSet = preparedStatement.executeQuery();
  if (resultSet.next()) {
email = resultSet.getString("Email");

    }
  userId=getUserIDByEmail(email);
    try {
        
        String query = "SELECT Date_MSG, Msg FROM conversation WHERE idUser1 = ? AND idUser2 = ? ORDER BY Date_MSG";
conversationListView1.getItems().clear();
        
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idOwner);
        preparedStatement.setInt(2, userId);
        

        
         resultSet = preparedStatement.executeQuery();

       
        while (resultSet.next()) {
            java.sql.Timestamp timestamp = resultSet.getTimestamp("Date_MSG");
            String message = resultSet.getString("Msg");
            String formattedMessage = formatMessage(timestamp, message);
            
            conversationListView.getItems().add(formattedMessage);
        }

       
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    
    
    String query = "SELECT Date_MSG, Msg FROM conversation WHERE idUser1 = ? AND idUser2 = ? ORDER BY Date_MSG";
    preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, userId);
    preparedStatement.setInt(2, idOwner);
   java.sql.ResultSet resultSet2 = preparedStatement.executeQuery();
    while (resultSet2.next()) {
            java.sql.Timestamp timestamp = resultSet2.getTimestamp("Date_MSG");
            String message = resultSet2.getString("Msg");
            String formattedMessage = formatMessage(timestamp, message);
            System.out.println(message);
            conversationListView1.getItems().add(formattedMessage);
        }
       resultSet.close();
        preparedStatement.close();
        connection.close();
}
    
      private String formatMessage(java.sql.Timestamp timestamp, String message) {
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    String formattedTimestamp = "[" + dateFormat.format(timestamp) + "] ";

    
    return formattedTimestamp + message;
    
    
}
      public ObservableList<User> GetUserList() throws SQLException{
        
           ObservableList<User> UserList = FXCollections.observableArrayList();

    String query = "SELECT * FROM User WHERE id!=?";
    
   
            java.sql.Connection connection = MyConnection.getCon();
    java.sql.PreparedStatement  preparedStatement = connection.prepareStatement(query);
          preparedStatement.setInt(1, idOwner);
           java.sql.ResultSet resultSet = preparedStatement.executeQuery() ;

        while (resultSet.next()) {
            User user1 = new User(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getString("email"),
                resultSet.getDate("date_naissance").toLocalDate(),
                resultSet.getInt("number")
                    
                    
            );
           
            UserList.add(user1);
            System.out.println(user1.getNom());
        }
    
    
    
    
    
    
    
    
    return UserList;
    
    
    }
      public void ShowUsersG(TableView<User> GTable,TableColumn<User, String> TName,TableColumn<User, String> TPrenom,TableColumn<User, String> TEmail) {
     
        try {
            ObservableList<User> list=GetUserList();
            TName.setCellValueFactory(new PropertyValueFactory<>("nom"));
            TPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            TEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            GTable.setItems(list);
            System.out.println("Setting items fil TableView jawou behi AAA");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
}
