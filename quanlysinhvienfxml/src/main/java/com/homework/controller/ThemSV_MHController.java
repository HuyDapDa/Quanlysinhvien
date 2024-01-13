/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import static com.homework.controller.ThemSinhVienController.getDateFromString;
import com.homework.doituong.Hoc;
import com.homework.doituong.MonHoc;
import com.homework.doituong.sinhVien;
import com.homework.services.quanLyHoc;
import com.homework.services.quanLyMonHoc;
import com.homework.services.quanLySinhVien;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HOME
 */
public class ThemSV_MHController implements Initializable {

    @FXML
    private TextField txtMaMH;
    @FXML
    private TextField txtMaSV;
    @FXML
    private DatePicker datePick;
    @FXML
    private TextField txtDiem;
    @FXML
    private ComboBox<Integer> cbSubmit;
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
    boolean checkData = false;
    ObservableList<sinhVien> dsSV = FXCollections.observableArrayList();
    ObservableList<Hoc> dsHoc = FXCollections.observableArrayList();
    ObservableList<MonHoc> dsMH = FXCollections.observableArrayList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Integer> list = cbSubmit.getItems();
        cbSubmit.setEditable(false);
        list.add(0);
        list.add(1);
        txtDiem.setText("0.0");
        txtMaSV.setText("");
        txtMaSV.setText("");
        datePick.setValue(LocalDate.now());
        cbSubmit.setValue(0);
    }

    @FXML
    private void btInsert(ActionEvent event) throws Exception {

        dsHoc.clear();
        dsSV.clear();
        dsMH.clear();
        quanLyHoc qlHoc = new quanLyHoc();
        quanLySinhVien qlSV = new quanLySinhVien();
        quanLyMonHoc qlMH = new quanLyMonHoc();
        try {
            qlSV.docDanhSachSinhVien(dsSV);
            qlHoc.docDanhSachHoc(dsHoc);
            qlMH.docDanhSachMonHoc(dsMH);
        } catch (SQLException ex) {
            Logger.getLogger(ThemSV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ThemSV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean checkSV = false;
        boolean checkMH = false;
        for (sinhVien sv : this.dsSV) {
            if (Integer.toString(sv.getMaSV()).equals(txtMaSV.getText())) {
                checkSV = true;
            }

        }

        for (MonHoc mh : this.dsMH) {
            if (mh.getMaMH().toLowerCase().equals(txtMaMH.getText().toLowerCase())) {
                checkMH = true;
            }
        }

        if (checkSV == false && checkMH == false) {
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("MaSV hoac MaMH ko ton tai!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            return;
        }
        Statement stmt = null;
        Date d = new Date();
        try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
            String sql = "INSERT INTO hoc (MaMH,MaSV,NgayDangKy,Diem,submit,time) VALUES ('"
                    + txtMaMH.getText() + "','"
                    + txtMaSV.getText() + "','"
                    + datePick.getValue().toString() + "','"
                    + 0 + "','"
                    + 0 + "','"
                    + String.valueOf(d.getTime()) + "')";
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(sql);
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ThemSV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtDiem.setText("0.0");
        txtMaMH.setText("");
        txtMaSV.setText("");
        datePick.setValue(LocalDate.now());
        cbSubmit.setValue(0);
        cbSubmit.setDisable(false);
        txtDiem.setDisable(false);
 
    }

    @FXML
    private void btUpdate(ActionEvent event) {
        if (checkData == false) {
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Check first!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            return;
        }

        if (txtMaMH.getText().isEmpty() || txtMaSV.getText().isEmpty()) {
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nhap MaSV va MaMH!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            return;
        }

        dsHoc.clear();
        dsSV.clear();
        dsMH.clear();
        quanLyHoc qlHoc = new quanLyHoc();
        quanLySinhVien qlSV = new quanLySinhVien();
        quanLyMonHoc qlMH = new quanLyMonHoc();
        try {
            qlSV.docDanhSachSinhVien(dsSV);
            qlHoc.docDanhSachHoc(dsHoc);
            qlMH.docDanhSachMonHoc(dsMH);
        } catch (SQLException ex) {
            Logger.getLogger(ThemSV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ThemSV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!sinhVien.isNumeric(txtDiem.getText())) {
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Diem phai la so va khong am");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            return;
        }
        if(Double.parseDouble(txtDiem.getText()) < 0  || Double.parseDouble(txtDiem.getText()) > 10)
        {
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("-1 < Diem < 11");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            return;
            
        }
        
        try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
            String query = "update hoc set Diem ='"
                    + Double.parseDouble(txtDiem.getText())
                    + "',submit ='"
                    + cbSubmit.getValue()
                    + "' where MaSV ='" + txtMaSV.getText() + "' and MaMH ='"
                    + txtMaMH.getText() + "' and NgayDangKy ='"
                    + datePick.getValue() + "'";
            Statement stmt = null;
            stmt = (Statement) conn.createStatement();
            stmt.execute(query);
            if (cbSubmit.getValue().equals(1)) {
                cbSubmit.setDisable(true);
                txtDiem.setDisable(true);
            } else {
                cbSubmit.setDisable(false);
                txtDiem.setDisable(false);
            }
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Ok!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ThemSV_MHController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtDiem.setText("0.0");
        txtMaMH.setText("");
        txtMaSV.setText("");
        datePick.setValue(LocalDate.now());
        cbSubmit.setValue(0);
        cbSubmit.setDisable(false);
        txtDiem.setDisable(false);
        checkData = false;
    }

    @FXML
    private void btCheck(ActionEvent event) throws SQLException, ParseException {
        boolean check = false;
        if (txtMaMH.getText().isEmpty() || txtMaSV.getText().isEmpty()) {
            cbSubmit.setDisable(false);
            txtDiem.setDisable(false);
            datePick.setValue(LocalDate.of(1970, 01, 01));
            txtDiem.setText("0.0");
            cbSubmit.setValue(0);
        }
        dsSV.clear();
        dsMH.clear();
        dsHoc.clear();

        quanLyHoc qlHoc = new quanLyHoc();
        qlHoc.docDanhSachHoc(dsHoc);
        for (Hoc hc : this.dsHoc) {
            if (hc.getMaMH().toLowerCase().equals(txtMaMH.getText().toLowerCase()) && Integer.toString(hc.getMaSV()).equals(txtMaSV.getText())) {

                datePick.setValue(getDateFromString(sinhVien.F.format(hc.getNgayDangKy()), formatter));
                txtDiem.setText(Double.toString(hc.getDiem()));
                cbSubmit.setValue(hc.isSubmit());
                check = true;
                checkData = true;

            }

        }
        if (check == false) {
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du lieu ko ton tai!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            return;
        }
        if (cbSubmit.getValue().equals(1)) {
            cbSubmit.setDisable(true);
            txtDiem.setDisable(true);
        } else {
            cbSubmit.setDisable(false);
            txtDiem.setDisable(false);
        }

    }

    @FXML
    private void btClear(ActionEvent event) {
        txtDiem.setText("0.0");
        txtMaMH.setText("");
        txtMaSV.setText("");
        datePick.setValue(LocalDate.now());
        cbSubmit.setValue(0);
        cbSubmit.setDisable(false);
        txtDiem.setDisable(false);
    }

}
