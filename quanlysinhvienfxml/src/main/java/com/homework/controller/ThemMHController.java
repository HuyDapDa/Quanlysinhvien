/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.MonHoc;
import static com.homework.doituong.sinhVien.isNumeric;
import com.homework.services.quanLyMonHoc;
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
public class ThemMHController implements Initializable {

    @FXML
    private TextField txtMaMH;
    @FXML
    private TextField txtTenMH;
    @FXML
    private TextField txtSoTinChi;
    @FXML
    private Button btCheck;
    @FXML
    private Button btInsert;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btClear;

    /**
     * Initializes the controller class.
     */
    ObservableList<MonHoc> dsMH = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btInsert(ActionEvent event) {
        if(txtMaMH.getText().isEmpty() || txtTenMH.getText().isEmpty() || txtSoTinChi.getText().isEmpty()){
                Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nhap thong tin!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
        }
        else{
            if(!isNumeric(txtSoTinChi.getText().toString())){
                Exception e = new Exception("An exception!!!!!!!");
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("So tin chi phai la chu so!");
                alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                alert.showAndWait();
            }
            else{
                Statement stmt = null;
                try {
                    dsMH.clear();
                    quanLyMonHoc qlMH = new quanLyMonHoc();
                    qlMH.docDanhSachMonHoc(dsMH);
                } catch (SQLException ex) { 
                    Logger.getLogger(ThemMHController.class.getName()).log(Level.SEVERE, null, ex);
                }
                boolean check = false;
                for(MonHoc mh : this.dsMH){
                    if(txtMaMH.getText().toLowerCase().equals(mh.getMaMH().toLowerCase())){
                        check = false;
                        Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Ton tai MaMH!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
                        return;
                    }
                    else{
                        check = true;
                    }
                }
                
                if(check == true){  
                    try (Connection conn = com.homework.services.JdbcUtils.getConn()){
                        stmt = (Statement) conn.createStatement();
                        String query = "INSERT INTO monhoc " + "VALUES ('" + txtMaMH.getText().toString() + "','" + txtTenMH.getText().toString() + "'," + Integer.parseInt(txtSoTinChi.getText().toString()) + ")";
                        stmt.executeUpdate(query);
                        Exception e = new Exception("An exception!!!!!!!");
                        StringWriter sw = new StringWriter();
                        e.printStackTrace(new PrintWriter(sw));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Ok!");
                        alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                        alert.showAndWait();
                    } catch (SQLException ex) {
                        Logger.getLogger(ThemMHController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            }
        }
    }

    @FXML
    private void btUpdate(ActionEvent event) {
        if(txtMaMH.getText().isEmpty() || txtTenMH.getText().isEmpty() || txtSoTinChi.getText().isEmpty()){
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nhap thong tin!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
        }
        else{
            try {
            dsMH.clear();
            quanLyMonHoc qlMH = new quanLyMonHoc();
            qlMH.docDanhSachMonHoc(dsMH);
        }   catch (SQLException ex) { 
                Logger.getLogger(ThemMHController.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean checkMH = false;
            for(MonHoc mh : this.dsMH){
            if(mh.getMaMH().toLowerCase().equals(txtMaMH.getText().toLowerCase())){
                  checkMH = true;
            }
            }
            if(checkMH == false){
                    Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Ko ton tai mon hoc!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                     alert.showAndWait();
                return;
            }
            if(!isNumeric(txtSoTinChi.getText())){
                    Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("So tin chi phai la chu so!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
            }
            else{
                try (Connection conn = com.homework.services.JdbcUtils.getConn()){
                String query = "update monhoc set TenMH ='"+txtTenMH.getText() + "',SoTinChi ='"+ Integer.parseInt(txtSoTinChi.getText()) + "' where MaMH ='" + txtMaMH.getText() +"'";
                Statement stmt = null;
                stmt = (Statement) conn.createStatement();
                stmt.execute(query);
                    Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("OK!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ThemMHController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.toString(),"Thong bao",JOptionPane.INFORMATION_MESSAGE);
        }
            }
        }
    }

    @FXML
    private void btClear(ActionEvent event) {
        txtMaMH.setText("");
        txtTenMH.setText("");
        txtSoTinChi.setText("0");
    }

    @FXML
    private void btCheck(ActionEvent event) {
        if(txtMaMH.getText().isEmpty()){
            txtTenMH.setText("");
            txtSoTinChi.setText("0");
        }
        boolean check = false;
        try {
            dsMH.clear();
            quanLyMonHoc qlMH = new quanLyMonHoc();
            qlMH.docDanhSachMonHoc(dsMH);
        } catch (SQLException ex) { 
            Logger.getLogger(ThemMHController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(MonHoc mh : this.dsMH){
            if(mh.getMaMH().toLowerCase().equals(txtMaMH.getText().toLowerCase())){
                txtTenMH.setText(mh.getTenMH());
                txtSoTinChi.setText(Integer.toString(mh.getSoTinChi()));
                check = true;
            }    
        }
        if(check == false){
                txtTenMH.setText("");
                txtSoTinChi.setText("0");
                    Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("MaMH ko ton tai!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
        }
    }
    
}
