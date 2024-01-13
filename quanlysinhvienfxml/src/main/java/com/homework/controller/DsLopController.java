/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.Lop;
import com.homework.services.quanLyLop;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DsLopController implements Initializable {
    

    @FXML
    private TableColumn<Lop, String> tenLop;
    @FXML
    private TableView<Lop> tbLop;
    
    ObservableList<Lop> dsLop = FXCollections.observableArrayList();
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<Lop, String> maLop;
    @FXML
    private TableColumn<Lop, String> giaoVien;
    @FXML
    private Button btReload;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(DsLopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DsLopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FilteredList<Lop> fileteredData = new FilteredList<>(dsLop, b -> true);
        txtSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            fileteredData.setPredicate(Lop -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(Lop.getGiaoVien().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                if(Lop.getMaLop().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<Lop> sortData = new SortedList<>(fileteredData);
        
        sortData.comparatorProperty().bind(tbLop.comparatorProperty());
        
        tbLop.setItems(sortData);
        
    }    
    private void loadData() throws SQLException, ParseException {
        btReload();
        maLop.setCellValueFactory(new PropertyValueFactory<>("maLop"));
        tenLop.setCellValueFactory(new PropertyValueFactory<>("tenLop"));
        giaoVien.setCellValueFactory(new PropertyValueFactory<>("giaoVien"));
        
        
        
        quanLyLop qlMH = new quanLyLop();
        qlMH.docDanhSachLop(dsLop);
        tbLop.setItems(dsLop);      
        tbLop.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbLop.getColumns().addAll(this.maLop,this.tenLop,this.giaoVien);
    }

    @FXML
    private void btReload() throws SQLException {
        txtSearch.setText("");
        dsLop.clear();
        quanLyLop qlLop = new quanLyLop();
        qlLop.docDanhSachLop(dsLop);
    }
}
