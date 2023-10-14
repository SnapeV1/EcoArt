/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupes.services;

import MyConnection.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import groupes.entities.Groupe;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 *
 * @author hammeda
 */
public class GroupFunctions {
    
    int idOwner=1;
    
public void showOwnedGroups(TableView<Groupe> OwnedGtable, TableColumn<Groupe, String> nameColumn, TableColumn<Groupe, Integer> sizeColumn) throws SQLException {
    try {
        updateAllGroupsSize();
        List<Integer> groupIds = getGids(idOwner);
        ObservableList<Groupe> groupList = FXCollections.observableArrayList();

        if (groupIds.isEmpty()) {
            // Handle the case when the user doesn't own any groups
            // You can display a message or handle it as needed
        } else {
            for (int groupId : groupIds) {
                String query = "SELECT Gid, nom, Size,logo FROM groups WHERE Gid = ?"; 

                java.sql.Connection connection = MyConnection.getCon();
                java.sql.PreparedStatement preparedStatement2 = connection.prepareStatement(query);
                preparedStatement2.setInt(1, groupId);

                java.sql.ResultSet resultSet2 = preparedStatement2.executeQuery();

                while (resultSet2.next()) {
                    Groupe Grp = new Groupe(
                        resultSet2.getInt("Gid"),
                        resultSet2.getString("nom"),
                        resultSet2.getInt("Size"),
                        loadImageFromResultSet(resultSet2)
                            
                    );

                    groupList.add(Grp);
                }
            }

            ObservableList<Groupe> groupObservableList = FXCollections.observableArrayList(groupList);
            OwnedGtable.setItems(groupObservableList);

           
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



public List<Integer> getGids(int userId) throws SQLException {
    List<Integer> groupIds = new ArrayList<>();

    String sql = "SELECT GroupID FROM membre WHERE userId = ? AND Role = ?";
    java.sql.Connection connection = MyConnection.getCon();
    java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setInt(1, userId);
    preparedStatement.setString(2, "Owner");

    java.sql.ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()) {
        int groupId = resultSet.getInt("GroupID");
        groupIds.add(groupId);
    }

    return groupIds;
}

public void navigateToFXML(String fxmlFileName,JFXButton btn) throws IOException {
 
       Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
          FXMLLoader groupPanelLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent groupPanelRoot = groupPanelLoader.load();
            Scene groupPanelScene = new Scene(groupPanelRoot);
         Stage newStage = new Stage();
            newStage.setScene(groupPanelScene);
            newStage.show();
}
public void navigateIMGToFXML(String fxmlFileName,ImageView img) throws IOException {
 
       Stage stage = (Stage) img.getScene().getWindow();
            stage.close();
          FXMLLoader groupPanelLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent groupPanelRoot = groupPanelLoader.load();
            Scene groupPanelScene = new Scene(groupPanelRoot);
         Stage newStage = new Stage();
            newStage.setScene(groupPanelScene);
            newStage.show();
}

    public void GetMemList(Text GPName,TableView<User> MemGTable,TableColumn<User, String> NomCol,TableColumn<User, String> PrenomCol,TableColumn<User, String> EmailCol,TableColumn<User, LocalDate> DateNColl,TableColumn<User, Integer> NumberCol) throws SQLException{
        
           updateAllGroupsSize();
          MiscFunctions MF=new MiscFunctions();
         
 ObservableList<User> UserList = FXCollections.observableArrayList();
   java.sql.Connection connection = MyConnection.getCon();
        String query = "SELECT * FROM User, membre, groups WHERE User.id = membre.UserID AND membre.GroupID = ? AND membre.Role != ? AND groups.Gid = membre.GroupID";
        java.sql.PreparedStatement preparedStatement2 = connection.prepareStatement(query);
        System.out.println(GetSelectedGID());
         preparedStatement2.setInt(1, GetSelectedGID());
         preparedStatement2.setString(2, "Owner");
     
     java.sql.ResultSet resultSet2 = preparedStatement2.executeQuery();
     
     
     resultSet2 = preparedStatement2.executeQuery(); 

        while (resultSet2.next()) {
            User user1 = new User(
                resultSet2.getInt("id"),
                resultSet2.getString("nom"),
                resultSet2.getString("prenom"),
                resultSet2.getString("email"),
                resultSet2.getDate("date_naissance").toLocalDate(),
                resultSet2.getInt("number")
                   
                    
            );
            System.out.println(user1.getNom());
            UserList.add(user1);
        }
        
            NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            PrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            DateNColl.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));
            NumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
            


            
            MemGTable.setItems(UserList);
              
            System.out.println("Setting items fil TableView jawou behi AAA");
    setGName(GPName);
    
    
     }
    
    public void setGName(Text GPName) throws SQLException{
    int Gid=GetSelectedGID();
        System.out.println(Gid);
    
    String query = "SELECT nom FROM groups WHERE Gid=?";
    String Gname="Gname isn't recieving data";
   
    java.sql.Connection connection = MyConnection.getCon();
           java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Gid);
           java.sql.ResultSet resultSet = preparedStatement.executeQuery(); 
            if (resultSet.next()) {
            // Retrieve the value from the result set
            Gname = resultSet.getString("nom");}
           System.out.println(Gname);
           
     GPName.setText(Gname);
        
    }
 
   
   public void FetchSelectedUser(TableView<Groupe> OwnedGtable){
   
   Groupe selectedGroup = OwnedGtable.getSelectionModel().getSelectedItem();

if (selectedGroup != null) {
    String groupName = selectedGroup.getName();  // Replace with the group's name
int groupId = -1;  // Initialize to a default value

// Create a SQL query to get the ID of the group based on its name
String selectGroupQuery = "SELECT Gid FROM groups WHERE nom = ?";

try {
    java.sql.Connection connection = MyConnection.getCon();
    java.sql.PreparedStatement selectGroupStatement = connection.prepareStatement(selectGroupQuery);
    selectGroupStatement.setString(1, groupName);
    java.sql.ResultSet resultSet = selectGroupStatement.executeQuery();

    if (resultSet.next()) {
        groupId = resultSet.getInt("Gid");
    }

    resultSet.close();
    selectGroupStatement.close();
    connection.close();
} catch (SQLException ex) {
    System.out.println("Error: " + ex.getMessage());
}

if (groupId == -1) {
    // Handle the case where the group with the given name doesn't exist
    System.out.println("Group not found");
} else {
    // Use groupId to update the tempmail table

   if (groupId != -1) {
    // Create an SQL query to update the tempmail table
    String updateTempMailQuery = "UPDATE tempmail SET idGroup = ? WHERE id = 1";

    try {
        java.sql.Connection connection = MyConnection.getCon();
        java.sql.PreparedStatement updateTempMailStatement = connection.prepareStatement(updateTempMailQuery);
        updateTempMailStatement.setInt(1, groupId); // Set the group ID
        int rowsUpdated = updateTempMailStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("tempmail table updated successfully");
        } else {
            System.out.println("No records were updated");
        }

        updateTempMailStatement.close();
        connection.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}  
}
   }
   }
   
   
   
   public int GetSelectedGID() throws SQLException{
   int  Gid=-1;
String sql = "SELECT idGroup FROM tempmail WHERE id = ?";
   java.sql.Connection connection = MyConnection.getCon();
java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);

preparedStatement.setInt(1, 1); // Assuming you want to select the item with id = 1

java.sql.ResultSet resultSet = preparedStatement.executeQuery();

if (resultSet.next()) {
  Gid = resultSet.getInt("idGroup");
}
resultSet.close();
    preparedStatement.close();
return Gid;
   }
  public void updateAllGroupsSize() throws SQLException {
    String updateQuery = "UPDATE groups AS g SET g.size = (SELECT COUNT(*) FROM membre AS m WHERE m.GroupID = g.Gid)";

    java.sql.Connection connection = MyConnection.getCon();
    java.sql.PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
    int rowsUpdated = preparedStatement.executeUpdate();

    if (rowsUpdated > 0) {
        System.out.println("Sizes of all groups updated successfully");
    } else {
        System.out.println("No records were updated");
    }
}
 




public void DeleteMember(Text GPName,TableView<User> MemGTable,TableColumn<User, String> NomCol,TableColumn<User, String> PrenomCol,TableColumn<User, String> EmailCol,TableColumn<User, LocalDate> DateNColl,TableColumn<User, Integer> NumberCol) throws SQLException{
 int id=1;
        int index = MemGTable.getSelectionModel().getSelectedIndex();
    
    if (index <= -1) {
        return;
    }
    int Gid=GetSelectedGID();
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to remove the member : " + EmailCol.getCellData(index) + "?");
         Optional<ButtonType> result = alert.showAndWait();
            

if (result.isPresent() && result.get() == ButtonType.OK) {
   String sql = "SELECT GroupID FROM membre WHERE userId = ? AND Role = ?";
    java.sql.Connection connection = MyConnection.getCon();
           java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
             
   
    preparedStatement.setInt(1, id);
    preparedStatement.setString(2, "Owner");

               java.sql.ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
       
      
    }
    String email = EmailCol.getCellData(index);
    
        String sqlDrop = "DELETE FROM membre WHERE GroupID = ? AND UserID = (SELECT id FROM User WHERE email = ?)";
   connection = MyConnection.getCon();
      preparedStatement = connection.prepareStatement(sqlDrop);
         preparedStatement.setInt(1, Gid);
    preparedStatement.setString(2, email);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
        System.out.println("Group Member Deleted");
    } else {
        System.out.println("nothing happened ");
    }}
        updateAllGroupsSize();
 GetMemList(GPName,MemGTable,NomCol,PrenomCol,EmailCol,DateNColl,NumberCol);
 
}  






public void AddGmember(TableView<User> GTable,TableColumn<User, String> TName,TableColumn<User, String> TPrenom,TableView<TempUser> GmembersTable,TableColumn<User, String> TEmail,TableColumn<User, String> GNameCell,TableColumn<User, String> GEmailCell){

  int index = GTable.getSelectionModel().getSelectedIndex();
    
    if (index <= -1) {
        return;
    }
    
    String nom = TName.getCellData(index);
    String email = TEmail.getCellData(index);


    // Create a new User object with the extracted data
    TempUser tempUser = new TempUser("","");
    tempUser.setName(nom);
    tempUser.setEmail(email);

        System.out.println(tempUser.getName());
        ObservableList<TempUser> existingData = GmembersTable.getItems();

    existingData.add(tempUser);
     GNameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
     GEmailCell.setCellValueFactory(new PropertyValueFactory<>("Email"));



}

public int getGid(int ID) throws SQLException{
int Gid = 0 ;


    String sql = "SELECT GroupID FROM membre WHERE userId = ? AND Role = ?";
  java.sql.Connection  connection = MyConnection.getCon();
   java.sql.PreparedStatement  preparedStatement = connection.prepareStatement(sql);
             
   
    preparedStatement.setInt(1, idOwner);
    preparedStatement.setString(2, "Owner");

               
   
   java.sql.ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
       
        Gid = resultSet.getInt("GroupiD"); // GroupID
    }
return Gid;


}
public void affichageMems(Text GGNameText,TableColumn<User, String>GNameCell,TableColumn<User, String>GEmailCell) throws SQLException{
    

setGName(GGNameText);
     
 ObservableList<User> UserList = FXCollections.observableArrayList();

        String query = "SELECT * FROM User,membre,groups WHERE User.id=membre.UserID AND groups.Gid=? AND Membre.Role!=?";
       java.sql.Connection connection = MyConnection.getCon();
        java.sql.PreparedStatement preparedStatement2 = connection.prepareStatement(query);
         preparedStatement2.setInt(1, GetSelectedGID());
         preparedStatement2.setString(2, "Owner");
     
     java.sql.ResultSet resultSet2 = preparedStatement2.executeQuery();
     
     
     

        while (resultSet2.next()) {
            User user1 = new User(
                resultSet2.getInt("id"),
                resultSet2.getString("nom"),
                resultSet2.getString("prenom"),
                resultSet2.getString("email"),
                resultSet2.getDate("date_naissance").toLocalDate(),
                resultSet2.getInt("number")
                   
                    
            );
            System.out.println(user1.getNom());
            UserList.add(user1);
        }
          
           
                GNameCell.setCellValueFactory(new PropertyValueFactory<>("nom"));
                GEmailCell.setCellValueFactory(new PropertyValueFactory<>("email"));
                System.out.println("affichagemems() mrigl");
    }


public void confirm ( TableView<TempUser>GmembersTable,JFXButton ConfirmGAbtn) throws SQLException{

int test=0;
    
        
    ObservableList<TempUser> data = GmembersTable.getItems();
             int Gid=GetSelectedGID();
    
      String insertMem = "INSERT INTO membre (GroupID, UserID,Role) VALUES (?, ?,?)";
   java.sql.Connection connection = MyConnection.getCon();
    java.sql.PreparedStatement PreparedSInsertMem = connection.prepareStatement(insertMem);
     //fetch all the data mel Gmembers w hothom fil members + Gid()
     for (TempUser user : data) {
     
      String email = user.getEmail();
      String querySql = "SELECT * FROM User WHERE email = ?";
       java.sql.PreparedStatement queryStatement = connection.prepareStatement(querySql);
       queryStatement.setString(1, email);
          java.sql.ResultSet  resultSet2 = queryStatement.executeQuery();
            
          
             if (resultSet2.next()) {
            int userId = resultSet2.getInt("id");
            PreparedSInsertMem.setInt(1, Gid);
            PreparedSInsertMem.setInt(2, userId);
            PreparedSInsertMem.setString(3, "Member");
               PreparedSInsertMem.addBatch();
               test=1;
             }
              resultSet2.close();
        queryStatement.close();
    }
          PreparedSInsertMem.executeBatch();
updateAllGroupsSize();
     
        
        
        Stage stage = (Stage) ConfirmGAbtn.getScene().getWindow();
            stage.close();
          FXMLLoader groupPanelLoader = new FXMLLoader(getClass().getResource("../gui/GroupPanel.fxml"));
            Parent groupPanelRoot;
        try {
            groupPanelRoot = groupPanelLoader.load();
       
            Scene groupPanelScene = new Scene(groupPanelRoot);
         Stage newStage = new Stage();
            newStage.setScene(groupPanelScene);
            newStage.show();

         } catch (IOException ex) {
ex.getMessage();
         }


}


  
    public ObservableList<User> GetUserList() throws SQLException{
        
           ObservableList<User> UserList = FXCollections.observableArrayList();

    String query = "SELECT * FROM User WHERE user.id!=?";
    
    java.sql.Connection connection = MyConnection.getCon();
           java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idOwner);
           java.sql.ResultSet resultSet = preparedStatement.executeQuery(); 

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
    
    
    public void importerImg (AnchorPane main,ImageView img){
        FileChooser open = new FileChooser();
         open.getExtensionFilters().add(new FileChooser.ExtensionFilter("open Image File","*png","*jpg"));
         
        File file = open.showOpenDialog(main.getScene().getWindow());

        if (file != null) {
           

             Image image = new Image(file.toURI().toString(), 101, 127, false, true);
              img.setImage(image);
    
}}
    
    private byte[] loadImageFromResultSet(java.sql.ResultSet rs) throws SQLException {
    InputStream inputStream = rs.getBinaryStream("logo");
    
    if (inputStream != null) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    
}
            return null;
    
}
    
    
    public void DeleteGroupF(TableView<Groupe>OwnedGtable,TableColumn<Groupe, String>nameColumn) throws SQLException{
    Groupe selectedGroup = OwnedGtable.getSelectionModel().getSelectedItem();

if (selectedGroup != null) {
    String groupName = nameColumn.getCellData(selectedGroup);
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the group: " + groupName + "?");
            Optional<ButtonType> result = alert.showAndWait();
            

        java.sql.Connection connection = MyConnection.getCon();
if (result.isPresent() && result.get() == ButtonType.OK) {
        connection.setAutoCommit(false);

        String deleteMembersQuery = "DELETE FROM membre WHERE membre.GroupID IN (SELECT Gid FROM groups WHERE nom = ?)";
        java.sql.PreparedStatement deleteMembersStatement = connection.prepareStatement(deleteMembersQuery);
        deleteMembersStatement.setString(1, groupName);
        deleteMembersStatement.executeUpdate();


        // Now, delete the group itself
        String deleteGroupQuery = "DELETE FROM groups WHERE nom = ?";
        java.sql.PreparedStatement deleteGroupStatement = connection.prepareStatement(deleteGroupQuery);
        deleteGroupStatement.setString(1, groupName);
        deleteGroupStatement.executeUpdate();

        // Commit the transaction to make the changes permanent
        connection.commit();

        // Set the group name in a String variable
        String deletedGroupName = groupName;

        // Close the database resources
       

        // Optionally, you can do something with the deleted group name
        System.out.println("Deleted group: " + deletedGroupName);

        try {
            // Roll back the transaction in case of an exception
            connection.rollback();
        } catch (SQLException rollbackException) {
            rollbackException.printStackTrace();
        }
        } else {
    System.out.println("No item selected.");
}}else {
            
            System.out.println("Deletion canceled.");
    
}
}
    public boolean groupExists(String groupName) {
    boolean exists = false;

    try {
        // Establish a database connection
        java.sql.Connection connection = MyConnection.getCon();

        // Create a SQL query to check if a group with the given name exists
        String query = "SELECT COUNT(*) FROM groups WHERE nom = ?";
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, groupName);

        // Execute the query
        java.sql.ResultSet resultSet = preparedStatement.executeQuery();

        // Check if the result contains any rows (i.e., a group with the same name exists)
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            exists = count > 0;
        }

        // Close the resources
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any exceptions that may occur during database access
    }

    return exists;
}

}