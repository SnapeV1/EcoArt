/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package groupes.gui;

import MyConnection.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import groupes.services.GroupFunctions;
import groupes.services.MiscFunctions;
import groupes.services.TempUser;
import groupes.services.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hammeda
 */
public class CreationGroupeController implements Initializable {

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
    private TextField Gname;
int idOwner=1;
GroupFunctions GF=new GroupFunctions();
MiscFunctions MF=new MiscFunctions();
    @FXML
    private ImageView ReturnImg;
    @FXML
    private ImageView logo;
    @FXML
    private Button importbtn;
    @FXML
    private AnchorPane AnchorLogo;
    @FXML
    private Text GroupNError;
    @FXML
    private Text GGNameText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowUsersG();
    }    

    
    
    
    
    
    
    public ObservableList<User> GetUserList() throws SQLException{
        
           ObservableList<User> UserList = FXCollections.observableArrayList();

    String query = "SELECT * FROM User";
    
    try ( java.sql.PreparedStatement pst = MyConnection.getInstance().getCnx()
                                    .prepareStatement(query);
           java.sql.ResultSet resultSet = pst.executeQuery()) {

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
    
    
    
    
    
    
    }
    
    return UserList;
    
    
    }
    
    public void ShowUsersG() {
     
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

    @FXML
    private void AddGmember(MouseEvent event) {
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


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Add the new User object to the existing data
    existingData.add(tempUser);
     GNameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
     GEmailCell.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
}

    @FXML
    private void DeleteGM(MouseEvent event) {
    

    TempUser selectedUser = GmembersTable.getSelectionModel().getSelectedItem();

if ( selectedUser!= null) {
    // Remove the selected item from the data source (ObservableList)
    ObservableList<TempUser> data = GmembersTable.getItems();
    data.remove(selectedUser);

    
    GmembersTable.refresh();
}
}

    @FXML
    private void ConfirmGA(MouseEvent event) throws SQLException, IOException {
        String GnameText = Gname.getText();
        int creatorUserID = idOwner; 
Image logoImage = logo.getImage();
if(logoImage == null){GroupNError.setText("Import a logo");} 



else if (!GnameText.isEmpty() && !GF.groupExists(GnameText) && !GnameText.matches("^[0-9].*")){
    String insertSql = "INSERT INTO groups (Gid, nom, size,logo) VALUES (0, ?, 0,?)";
byte[] logoBytes =MF.imageToByteArray(logoImage);
     java.sql.PreparedStatement preparedStatement = MyConnection.getInstance().getCnx()
                                    .prepareStatement(insertSql);

preparedStatement.setString(1, GnameText);
preparedStatement.setBytes(2, logoBytes);
preparedStatement.executeUpdate();

String maxGidQuery = "SELECT MAX(Gid) FROM groups";

java.sql.PreparedStatement maxGidStatement = MyConnection.getInstance().getCnx()
                                    .prepareStatement(maxGidQuery);
java.sql.ResultSet maxGidResult = maxGidStatement.executeQuery();
int index = 0;

if (maxGidResult.next()) {
    index = maxGidResult.getInt(1);
}
maxGidResult.close();
maxGidStatement.close();

ObservableList<TempUser> data = GmembersTable.getItems();

String insertMem = "INSERT INTO membre (GroupID, UserID, Role) VALUES (?, ?, ?)";


java.sql.PreparedStatement InsertMem = MyConnection.getInstance().getCnx()
                                    .prepareStatement(insertMem);
preparedStatement.setInt(1, index);
preparedStatement.setInt(2, creatorUserID);
preparedStatement.setString(3, "Owner");
preparedStatement.addBatch();

for (TempUser user : data) {
    String email = user.getEmail();
    String querySql = "SELECT * FROM User WHERE email = ?";
java.sql.PreparedStatement queryStatement = MyConnection.getInstance().getCnx()
                                    .prepareStatement(insertMem);   
    queryStatement.setString(1, email);
    java.sql.ResultSet resultSet2 = queryStatement.executeQuery();

    if (resultSet2.next()) {
        int userId = resultSet2.getInt("id");
        System.out.println("gid : " + index + "  id " + userId);
        preparedStatement.setInt(1, index);
        preparedStatement.setInt(2, userId);
        preparedStatement.setString(3, "Member");
        preparedStatement.addBatch();
    }
    resultSet2.close();
    queryStatement.close();
}

preparedStatement.executeBatch();
    GF.updateAllGroupsSize();
    
    Stage stage = (Stage) ConfirmGAbtn.getScene().getWindow();
            stage.close();
          FXMLLoader groupPanelLoader = new FXMLLoader(getClass().getResource("YourGroups.fxml"));
            Parent groupPanelRoot = groupPanelLoader.load();
            Scene groupPanelScene = new Scene(groupPanelRoot);
         Stage newStage = new Stage();
            newStage.setScene(groupPanelScene);
            newStage.show();
}
else if(GnameText.isEmpty()){GroupNError.setText("invalid name");
    }

else if(GF.groupExists(GnameText)){GroupNError.setText("Name Already Exists");}
else if(GnameText.matches("^[0-9].*")){GroupNError.setText("Names can't start with a number");}



    }

    @FXML
    private void Return(MouseEvent event) {
         try {
        GF.navigateIMGToFXML("../gui/YourGroups.fxml", ReturnImg);
    } catch (IOException ex) {
        ex.getMessage();
    }
    }

    @FXML
    private void importer(MouseEvent event) {
        GF.importerImg(AnchorLogo, logo);
        
    }
    }
    



    
    

    
    
    
    
    
    
    
