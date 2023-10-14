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
import static groupes.gui.AddMemController.idOwner;
import groupes.services.GroupFunctions;
import groupes.services.Member;
import groupes.services.MiscFunctions;
import groupes.services.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class GroupPanelController implements Initializable {
static int idOwner=1;


    @FXML
    private TableView<User> MemGTable;
    @FXML
    private JFXButton AddMbtn;
    @FXML
    private JFXButton DeleteMembtn;
    @FXML
    private TableColumn<User, String> NomCol;
    @FXML
    private TableColumn<User, String> PrenomCol;
    @FXML
    private TableColumn<User, String> EmailCol;
    @FXML
    private TableColumn<User, LocalDate> DateNColl;
    @FXML
    private TableColumn<User, Integer> NumberCol;
    @FXML
    private Text GPName=new Text();
    @FXML
    private TextField NomSearch;
    @FXML
    private ComboBox<String> YearCB= new ComboBox<>();
    @FXML
    private JFXButton SearchGPbtn;
    @FXML
    private Text FilterText=new Text();
    @FXML
    private ImageView Resetimg;
    @FXML
    private Text GroupPanelTXT=new Text();
    @FXML
    private Text NameTXT=new Text();
    @FXML
    private Text YearTXT=new Text();
    @FXML
    private Text LanguageText=new Text();
    int OwnerID=1;
    @FXML
    private TextField GNChange;
    @FXML
    private ImageView ConfirmImg;
       GroupFunctions GF=new GroupFunctions();
       MiscFunctions MF=new MiscFunctions();
    @FXML
    private ImageView ReturnImg;
    @FXML
    private AnchorPane LogoAnchor2;
    @FXML
    private ImageView logo2;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Platform.runLater(() -> {
        try {
            GF.GetMemList(GPName, MemGTable, NomCol, PrenomCol, EmailCol, DateNColl, NumberCol);
             MF.setLogoForGroup(GF.GetSelectedGID(), logo2);
        } catch (SQLException ex) {
            Logger.getLogger(GroupPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int year = 1920; year <= 2005; year++) {
            YearCB.getItems().add(String.valueOf(year));
        }
    });
}
 

    @FXML
    private void AddMember(MouseEvent event) throws IOException {
        Stage stage = (Stage) AddMbtn.getScene().getWindow();
            stage.close();
          FXMLLoader groupPanelLoader = new FXMLLoader(getClass().getResource("AddMem.fxml"));
            Parent groupPanelRoot = groupPanelLoader.load();
            Scene groupPanelScene = new Scene(groupPanelRoot);
         Stage newStage = new Stage();
            newStage.setScene(groupPanelScene);
            newStage.show();
    }

    @FXML
    private void DeleteMem(MouseEvent event) throws SQLException {
        
        GF.DeleteMember(GPName,MemGTable,NomCol,PrenomCol,EmailCol,DateNColl,NumberCol) ;

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
     
     
     
     
     
     
     
     
     
     
     
    private void setGName() throws SQLException{
    int Gid=GF.GetSelectedGID();
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

    @FXML
    private void SearchGP(MouseEvent event) throws SQLException {
        java.sql.Connection  connection = MyConnection.getCon();
        java.sql.PreparedStatement  preparedStatement ;
        String textFieldValue = NomSearch.getText();
        int Gid=GF.GetSelectedGID();
         ObservableList<User> UserList = FXCollections.observableArrayList();

       String selectedYearString = YearCB.getValue();
       
        
    if(("".equals(textFieldValue))&&((YearCB.getValue() == null))){
    FilterText.setText("No Filters Were Applied");
    }
    else{FilterText.setText("");
    if((!"".equals(textFieldValue))&&((YearCB.getValue() == null))){
    String sql = "SELECT * FROM user JOIN membre ON user.id = membre.UserID WHERE user.nom = ? AND membre.GroupID = ? AND membre.Role != 'Owner'";
  
   preparedStatement = connection.prepareStatement(sql);
   preparedStatement.setString(1, textFieldValue);
   preparedStatement.setInt(2, Gid);
   
   }
    else if(("".equals(textFieldValue))&&((YearCB.getValue() != null))){
    String sql = "SELECT * FROM user JOIN membre ON user.id = membre.UserID WHERE YEAR(date_naissance) = ? AND membre.GroupID = ? AND membre.Role != 'Owner'";
  Integer.parseInt(selectedYearString);
   preparedStatement = connection.prepareStatement(sql);
   preparedStatement.setString(1, selectedYearString);
   preparedStatement.setInt(2, Gid);
   
    }
    else{
     String sql = "SELECT * FROM user JOIN membre ON user.id = membre.UserID WHERE YEAR(date_naissance) = ? AND user.nom=? AND user.id=membre.UserID AND membre.GroupID=? AND Membre.Role!=?";
  Integer.parseInt(selectedYearString);
    preparedStatement = connection.prepareStatement(sql);
   preparedStatement.setString(1, selectedYearString);
   preparedStatement.setString(2, textFieldValue);
   preparedStatement.setInt(3, Gid);
   preparedStatement.setString(4, "Owner");
    }
    
        java.sql.ResultSet resultSet2 = preparedStatement.executeQuery(); 

        
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
        setGName();
    
    
    }
    
    
        
        
        
    }

    @FXML
    private void ResetFilters(MouseEvent event) throws SQLException {
        NomSearch.setText("");
        YearCB.setValue(null);
         GF.GetMemList(GPName,MemGTable,NomCol,PrenomCol,EmailCol,DateNColl,NumberCol);
    }
private boolean isFrench = false;
    @FXML
    private void SwitchLanguage(MouseEvent event) {
        isFrench = !isFrench;

        GroupPanelTXT.setText("panneau de controle");
        NameTXT.setText("Nom :");
        YearTXT.setText("Annee :");
        LanguageText.setText("fr");
        SearchGPbtn.setText("Rechercher");
        AddMbtn.setText("Ajouter");
        DeleteMembtn.setText("Effacer");
        
        if (isFrench) {
            
            GroupPanelTXT.setText("panneau de controle");
            NameTXT.setText("Nom :");
            YearTXT.setText("Annee :");
            LanguageText.setText("fr");
            SearchGPbtn.setText("Rechercher");
            AddMbtn.setText("Ajouter");
            DeleteMembtn.setText("Effacer");
        } else {
          
            GroupPanelTXT.setText("Control Panel");
            NameTXT.setText("Name:");
            YearTXT.setText("Year:");
            LanguageText.setText("en");
            SearchGPbtn.setText("Search");
            AddMbtn.setText("Add");
            DeleteMembtn.setText("Delete");
        }
    }
       
    
    
    
    

    @FXML
    private void ConfirmGNAME(MouseEvent event) {
        String NewName=GNChange.getText();
        
        System.out.println(NewName);
        if("".equals(NewName)){
       
        
    }
        else {
             try {
         String UpdateGNQuery="UPDATE groups SET nom = ? WHERE Gid = ?;";
        java.sql.Connection  connection= MyConnection.getCon();
    
        java.sql.PreparedStatement  preparedStatement ;
         preparedStatement = connection.prepareStatement(UpdateGNQuery);
   preparedStatement.setString(1, NewName);
   preparedStatement.setInt(2, GF.GetSelectedGID());
   int rowsUpdated;
           
                rowsUpdated = preparedStatement.executeUpdate();
            
   if (rowsUpdated > 0) {
        System.out.println("Group Name updated successfully");
        setGName();
    } else {
        System.out.println("No updates");
    }
   } catch (SQLException ex) {
             System.out.println(ex.getMessage());            }
}
        
}

    @FXML
    private void Return(MouseEvent event) {
    try {
        GF.navigateIMGToFXML("../gui/YourGroups.fxml", Resetimg);
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
}



