/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.Lop;
import com.homework.services.quanLyLop;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class ThemLopController implements Initializable {

    @FXML
    private TextField txtMaLop;
    @FXML
    private TextField txtTenLop;
    @FXML
    private TextField txtGVCN;
    @FXML
    private Button btInsert;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btClear;
    @FXML
    private Button btCheck;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Lop> dsLop = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        quanLyLop qlLop = new quanLyLop();
        try {
            qlLop.docDanhSachLop(dsLop);
        } catch (SQLException ex) {
            Logger.getLogger(ThemLopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtGVCN.setText("null");
    }    
    @FXML
    public void btInsert(ActionEvent evt) throws SQLException{
        if(txtMaLop.getText().isEmpty() || txtTenLop.getText().isEmpty()){
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nhap thong tin!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            return;
        }
        else{
            dsLop.clear();    
                try {
                    quanLyLop qlLop = new quanLyLop();
                    qlLop.docDanhSachLop(dsLop);
                } catch (SQLException ex) {
                    Logger.getLogger(ThemLopController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            boolean check = false;
            for(Lop lop : this.dsLop){
                if(lop.getMaLop().toLowerCase().equals(txtMaLop.getText().toLowerCase())){
                    check = true;
                }
            }
            if(check == true){
                txtGVCN.setText("null");
                txtTenLop.setText("");
                txtMaLop.setText("");
                Exception e = new Exception("An exception!!!!!!!");
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ma Lop ton tai!");
                alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                alert.showAndWait();
                return;
            }
            
            Statement stmt = null;
            try (Connection conn = com.homework.services.JdbcUtils.getConn()){   
                    if(txtGVCN.getText().isEmpty()){
                        txtGVCN.setText("null");
                    }
                   
                    String sql = "INSERT INTO lop (MaLop,TenLop,GiaoVien) VALUES ('"+ txtMaLop.getText() + "','"+txtTenLop.getText() + "','"+ txtGVCN.getText() + "')";
                    stmt = (Statement) conn.createStatement();
                    stmt.executeUpdate(sql);
                    Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(sql);
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
                

            } 
                
        }
        
    
    @FXML
    public void btClear(ActionEvent evt){
        txtGVCN.setText("null");
        txtTenLop.setText("");
        txtMaLop.setText("");
        
    }

    @FXML
    private void btCheck(ActionEvent event) { 
        dsLop.clear();  
         try {
                quanLyLop qlLop = new quanLyLop();
                qlLop.docDanhSachLop(dsLop);
            } catch (SQLException ex) {
            Logger.getLogger(ThemLopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean checkTonTai = false;
        for(Lop lop : this.dsLop){
            if(lop.getMaLop().toLowerCase().equals(txtMaLop.getText().toLowerCase())){
                txtTenLop.setText(lop.getTenLop());
                txtGVCN.setText(lop.getGiaoVien());
                checkTonTai = true;
            }          
        }
        if(checkTonTai == false){
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("MaLop ko ton tai!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            txtTenLop.setText("");
            txtGVCN.setText("null");
            txtMaLop.setText("");
            return;
        }
    } 

    @FXML
    private void btUpdate(ActionEvent event) {
        dsLop.clear();
        boolean check = false;
         try {
                quanLyLop qlLop = new quanLyLop();
                qlLop.docDanhSachLop(dsLop);
            } catch (SQLException ex) { 
            Logger.getLogger(ThemLopController.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(txtMaLop.getText().isEmpty() || txtTenLop.getText().isEmpty()){
             Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nhap du thong tin!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
         }
         else{
             for(Lop lop : this.dsLop){
                 if(lop.getMaLop().toLowerCase().equals(txtMaLop.getText().toLowerCase())){
                     check = true;
                 }
             }
             if(check == false){
                    txtTenLop.setText("");
                     txtGVCN.setText("");
                     Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("MaLop ko ton tai!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
                     return;
             }
          try (Connection conn = com.homework.services.JdbcUtils.getConn()){
                String query = "update lop set TenLop ='"+txtTenLop.getText() + "',GiaoVien ='"+ txtGVCN.getText() + "' where MaLop ='" + txtMaLop.getText() +"'";
                Statement stmt = null;
                stmt = (Statement) conn.createStatement();
                stmt.execute(query);
                Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Ok!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
        }   catch (SQLException ex) { 
                Logger.getLogger(ThemLopController.class.getName()).log(Level.SEVERE, null, ex);
            } 
         }
    }
        
    
    }
    
    

