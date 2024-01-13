/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.sinhVien;
import com.homework.doituong.sinhVien;
import com.homework.services.quanLySinhVien;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class DanhsachSVController implements Initializable {

    @FXML
    private TableColumn<sinhVien, Integer> maSV;
    @FXML
    private TableColumn<sinhVien, String> hoSV;
    @FXML
    private TableColumn<sinhVien, String> tenSV;
    @FXML
    private TableColumn<sinhVien, String> gTinh;
    @FXML
    private TableColumn<sinhVien, String> born;
    @FXML
    private TableColumn<sinhVien, String> queQuan;
    @FXML
    private TableColumn<sinhVien, String> maLop;
    /**
     * Initializes the controller class.
     */
    ObservableList<sinhVien> sVList = FXCollections.observableArrayList();
    @FXML
    private TableView<sinhVien> tableSinhVien;
    @FXML
    private Button btReload;
    @FXML
    private TextField txtSearch;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(DanhsachSVController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DanhsachSVController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        FilteredList<sinhVien> fileteredData = new FilteredList<>(sVList, b -> true);
        txtSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            fileteredData.setPredicate(sinhVien -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(String.valueOf(sinhVien.getMaSV()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if(sinhVien.getMaLop().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
                    return false;
            });
        });
        
        SortedList<sinhVien> sortData = new SortedList<>(fileteredData);
        
        sortData.comparatorProperty().bind(tableSinhVien.comparatorProperty());
        
        tableSinhVien.setItems(sortData);
    }    

    private void loadData() throws SQLException, ParseException {
        btReload();
        maSV.setCellValueFactory(new PropertyValueFactory<>("maSV"));
        hoSV.setCellValueFactory(new PropertyValueFactory<>("hoSV"));
        tenSV.setCellValueFactory(new PropertyValueFactory<>("tenSV"));
        gTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        born.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        queQuan.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        maLop.setCellValueFactory(new PropertyValueFactory<>("maLop"));
        
        
        quanLySinhVien qlSV = new quanLySinhVien();
        qlSV.docDanhSachSinhVien(sVList);
        tableSinhVien.setItems(sVList);      
        tableSinhVien.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableSinhVien.getColumns().addAll(this.maSV,this.hoSV,this.tenSV,this.gTinh,this.born,this.queQuan,this.maLop);
    }
    
    
    public void btSearch(ActionEvent evt) throws IOException, SQLException, ParseException {

    }
    
    @FXML
    public void btReload() throws SQLException, ParseException{
        txtSearch.setText("");
        sVList.clear();
        quanLySinhVien qlSV = new quanLySinhVien();
        qlSV.docDanhSachSinhVien(sVList);
    }
            
}
