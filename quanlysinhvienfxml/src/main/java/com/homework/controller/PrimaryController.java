/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.userPassword;
import com.homework.doituong.userPassword;
import com.homework.quanlysinhvienfxml.App;
import com.homework.quanlysinhvienfxml.App;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author HOME
 */
public class PrimaryController implements Initializable {

    private static Scene scene;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btClear;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @FXML
    public void btLogin(ActionEvent evt) throws IOException {
        
        userPassword up = new userPassword();
        
        if(up.check(txtUsername.getText(), txtPassword.getText())){
//            Stage stage = new Stage();
//            scene = new Scene(loadFXML("menuQuanLy"), 379,306);
//            stage.setResizable(false);
//            stage.setTitle("Menu");
//            stage.setScene(scene);
//            stage.show();
              App.setRoot("menuQuanLy");
            
            
        }
        else{
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fail!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
        }
    }

    @FXML
    private void btClear(ActionEvent event) {
        txtPassword.setText("");
        txtUsername.setText("");
    }
}
