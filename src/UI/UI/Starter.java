/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DB.*;
import UI.*;
import student.FillDB;
/**
 *
 * @author simonnewham
 */
public class Starter extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //DataBase.init_UserDB();
        DataBase DB = new DataBase();
        FillDB.init_UserDB(DB);
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
       
    }
    
}
