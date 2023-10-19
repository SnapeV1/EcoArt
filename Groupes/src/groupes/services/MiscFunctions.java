/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupes.services;

import MyConnection.MyConnection;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author hammeda
 */
public class MiscFunctions {
    
    
  
    public void setLogoForGroup(int id,ImageView logo2) {
    try {
      
        String query = "SELECT logo FROM groups WHERE Gid = ?";

          java.sql.PreparedStatement preparedStatement = MyConnection.getInstance().getCnx()
                                    .prepareStatement(query) ;
        preparedStatement.setInt(1, id);

       
        java.sql.ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
           
            String path = resultSet.getString("logo");

            if (path != null) {
               
                  File imageFile = new File(path);

               if (imageFile.exists()) {
                     FileInputStream fileInputStream=null;
                    
                      try {
                          fileInputStream = new FileInputStream(imageFile);
                      } catch (FileNotFoundException ex) {
                          Logger.getLogger(MiscFunctions.class.getName()).log(Level.SEVERE, null, ex);
                      }
            Image logoImage = new Image(fileInputStream);

                    
                    logo2.setImage(logoImage);
                }
            }
        }

      
        resultSet.close();
        preparedStatement.close();
     
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        
    }
}

    
}
