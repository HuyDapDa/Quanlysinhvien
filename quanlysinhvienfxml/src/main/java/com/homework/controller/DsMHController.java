/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.MonHoc;
import com.homework.doituong.sinhVien;
import com.homework.services.quanLyMonHoc;
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
public class DsMHController implements Initializable {


    @FXML
    private TableColumn<MonHoc, String> maMH;
    @FXML
    private TableColumn<MonHoc, String> tenMH;
    @FXML
    private TableColumn<MonHoc, Integer> soTinChi;
    @FXML
    private TableView<MonHoc> tbMH;
    /**
     * Initializes the controller class.
     */
    
    ObservableList<MonHoc> dsMH = FXCollections.observableArrayList();
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btReload;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(DsMHController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DsMHController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FilteredList<MonHoc> fileteredData = new FilteredList<>(dsMH, b -> true);
        txtSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            fileteredData.setPredicate(MonHoc -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(MonHoc.getMaMH().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if(MonHoc.getTenMH().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
                    return false;
            });
        });
        
        SortedList<MonHoc> sortData = new SortedList<>(fileteredData);
        
        sortData.comparatorProperty().bind(tbMH.comparatorProperty());
        
        tbMH.setItems(sortData);
    }    
    
    private void loadData() throws SQLException, ParseException {
        btReload();
        maMH.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        tenMH.setCellValueFactory(new PropertyValueFactory<>("TenMH"));
        soTinChi.setCellValueFactory(new PropertyValueFactory<>("SoTinChi"));
        
        
        
        quanLyMonHoc qlMH = new quanLyMonHoc();
        qlMH.docDanhSachMonHoc(dsMH);
        tbMH.setItems(dsMH);      
        tbMH.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbMH.getColumns().addAll(this.maMH,this.tenMH,this.soTinChi);
    }

    @FXML
    private void btReload() throws SQLException {
        txtSearch.setText("");
        dsMH.clear();
        quanLyMonHoc qlMonHoc = new quanLyMonHoc();
        qlMonHoc.docDanhSachMonHoc(dsMH);
    }
    
}
