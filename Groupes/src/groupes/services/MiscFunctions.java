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
import java.io.IOException;
import java.sql.SQLException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author hammeda
 */
public class MiscFunctions {
    
    
    public byte[] imageToByteArray(Image image) {
    try {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
        return null; // Handle the exception appropriately in your application
    }
}
    public void setLogoForGroup(int id,ImageView logo2) {
    try {
        // Establish a database connection
        java.sql.Connection connection = MyConnection.getCon();

        // Define your SQL query to retrieve the logo where Gid = id
        String query = "SELECT logo FROM groups WHERE Gid = ?";

        // Create a prepared statement with the query
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

       
        java.sql.ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
           
            java.sql.Blob blob = resultSet.getBlob("logo");

            if (blob != null) {
               
                byte[] logoData = blob.getBytes(1, (int) blob.length());

                if (logoData.length > 0) {
                    
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(logoData);
                    Image logoImage = new Image(inputStream);

                    
                    logo2.setImage(logoImage);
                }
            }
        }

      
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        
    }
}

    
}
