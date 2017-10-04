/*
 * This class is responsible for initating the program.
 * This is done by loading the login.fxml file when the program is run.
 */
package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import UI.*;

/**
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */

public class Starter extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
       
    }
    
}
