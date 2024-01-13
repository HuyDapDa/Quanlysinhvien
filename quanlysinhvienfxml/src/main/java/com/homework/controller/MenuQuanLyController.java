/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.quanlysinhvienfxml.App;
import com.homework.quanlysinhvienfxml.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class MenuQuanLyController implements Initializable {

    private static Scene scene;
    @FXML
    private Button btInsert;
    @FXML
    private Button btLop;
    @FXML
    private Button themLop;
    @FXML
    private Button btMH;
    @FXML
    private Button themMH;
    @FXML
    private Button btHoc;
    @FXML
    private Button btDangKyMon;
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
    public void btDanhSachSV(ActionEvent evt) throws IOException {
            Stage stage = new Stage();
            scene = new Scene(loadFXML("danhsachSV"), 600, 463);
            stage.setResizable(false);
            stage.setTitle("Danh Sach");
            stage.setScene(scene);

            stage.show();
        
    }
    @FXML
    public void btInsert(ActionEvent evt) throws IOException {
            Stage stage = new Stage();
            scene = new Scene(loadFXML("themSinhVien"), 305, 400);
            stage.setResizable(false);
            stage.setTitle("ThemSinhVien");
            stage.setScene(scene);

            stage.show();
        
    }

    @FXML
    private void btLop(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            scene = new Scene(loadFXML("dsLop"), 498, 400);
            stage.setResizable(false);
            stage.setTitle("Danh Sach");
            stage.setScene(scene);

            stage.show();
    }

    @FXML
    private void themLop(ActionEvent event) throws IOException {
        Stage stage = new Stage();
            scene = new Scene(loadFXML("themLop"), 278, 263);
            stage.setResizable(false);
            stage.setTitle("Them_Sua Lop");
            stage.setScene(scene);

            stage.show();
    }

    @FXML
    private void btMH(ActionEvent event) throws IOException {
         Stage stage = new Stage();
            scene = new Scene(loadFXML("dsMH"), 419, 400);
            stage.setResizable(false);
            stage.setTitle("Danh sach");
            stage.setScene(scene);

            stage.show();
    }

    @FXML
    private void themMH(ActionEvent event) throws IOException {
         Stage stage = new Stage();
            scene = new Scene(loadFXML("themMH"), 293, 223);
            stage.setResizable(false);
            stage.setTitle("Them_Sua Mon Hoc");
            stage.setScene(scene);

            stage.show();
    }

    @FXML
    private void btHoc(ActionEvent event) throws IOException {
         Stage stage = new Stage();
            scene = new Scene(loadFXML("SV_MH"), 600, 400);
            stage.setResizable(false);
            stage.setTitle("Them_Sua Mon Hoc");
            stage.setScene(scene);

            stage.show();
    }

    @FXML
    private void btDangKyMon(ActionEvent event) throws IOException {
        Stage stage = new Stage();
            scene = new Scene(loadFXML("themSV_MH"), 317, 327);
            stage.setResizable(false);
            stage.setTitle("Them_Sua Mon Hoc");
            stage.setScene(scene);

            stage.show();
    }
}
