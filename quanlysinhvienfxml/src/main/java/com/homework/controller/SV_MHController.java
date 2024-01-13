/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.Hoc;
import com.homework.doituong.sinhVien;
import com.homework.services.quanLyHoc;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
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
public class SV_MHController implements Initializable {

    @FXML
    private TableColumn<Hoc, String> maMH;
    @FXML
    private TableColumn<Hoc, String> ngayDangKy;
    @FXML
    private TableColumn<Hoc, Double> diem;
    @FXML
    private TableColumn<Hoc, Integer> submit;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<Hoc> tbSV_MH;
    @FXML
    private TableColumn<Hoc, Integer> maSV;

    /**
     * Initializes the controller class.
     */
    ObservableList<Hoc> dsHoc = FXCollections.observableArrayList();
    @FXML
    private TextField txtSearchSV;
    @FXML
    private Button btReload;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(SV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FilteredList<Hoc> fileteredData = new FilteredList<>(dsHoc, b -> true);
        txtSearch.textProperty().addListener((observable,oldValue,newValue) -> {
            fileteredData.setPredicate(Hoc -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
               
                if(Hoc.getMaMH().toLowerCase().contains(lowerCaseFilter) ){
                    return true;
                }
                
                else
                    return false;
            });
        });
        
        SortedList<Hoc> sortData = new SortedList<>(fileteredData);
        
        sortData.comparatorProperty().bind(tbSV_MH.comparatorProperty());
        
        tbSV_MH.setItems(sortData);
        
        
        txtSearchSV.textProperty().addListener((observable,oldValue,newValue) -> {
            fileteredData.setPredicate(Hoc -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
               
                if(Integer.toString(Hoc.getMaSV()).equals(lowerCaseFilter)  ){
                    return true;
                }
                
                else
                    return false;
            });
        });
        
        
        sortData.comparatorProperty().bind(tbSV_MH.comparatorProperty());
        
        tbSV_MH.setItems(sortData);
    }    
    private void loadData() throws SQLException, ParseException {
        btReload();
        maMH.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        maSV.setCellValueFactory(new PropertyValueFactory<>("MaSV"));
        ngayDangKy.setCellValueFactory(new PropertyValueFactory<>("NgayDangKy"));
        diem.setCellValueFactory(new PropertyValueFactory<>("Diem"));
        submit.setCellValueFactory(new PropertyValueFactory<>("submit"));
        
        
        
        quanLyHoc qlHoc = new quanLyHoc();
        qlHoc.docDanhSachHoc(dsHoc);
        tbSV_MH.setItems(dsHoc);      
        tbSV_MH.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbSV_MH.getColumns().addAll(this.maSV,this.maSV,this.ngayDangKy,this.diem,this.submit);
    }

    @FXML
    private void btReload() {
        txtSearch.setText("");
        txtSearchSV.setText("");
        dsHoc.clear();
        quanLyHoc qlHoc = new quanLyHoc();
        try {
            qlHoc.docDanhSachHoc(dsHoc);
        } catch (SQLException ex) {
            Logger.getLogger(SV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
