/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.homework.controller;

import com.homework.doituong.Lop;
import com.homework.doituong.sinhVien;
import com.homework.services.quanLyLop;
import com.homework.services.quanLySinhVien;
import static com.homework.services.quanLySinhVien.isValidDate;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
public class ThemSinhVienController implements Initializable {

    @FXML
    private ComboBox<String> cbGioiTinh;

    /**
     * Initializes the controller class.
     */
    ObservableList<sinhVien> sVList = FXCollections.observableArrayList();
    ObservableList<Lop> lopList = FXCollections.observableArrayList();
    @FXML
    private TextField maSV;
    @FXML
    private TextField hoSV;
    @FXML
    private TextField tenSV;
    private TextField ngaySinh;
    @FXML
    private TextField queQuan;
    @FXML
    private TextField maLop;
    @FXML
    private Button btInsert;
    @FXML
    private Button btSua;
    @FXML
    private DatePicker datePick;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    int dem = 0;
    
    public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
    }
    
    public static LocalDate
    getDateFromString(String string,
                      DateTimeFormatter format)
    {
        // Converting the string to date
        // in the specified format
        LocalDate date = LocalDate.parse(string, format);
 
        // Returning the converted date
        return date;
    }
    @FXML
    private Button btClear;
    @FXML
    private Button btCheck;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = cbGioiTinh.getItems();
        cbGioiTinh.setEditable(false);
        list.add("null");
        list.add("Nam");
        list.add("Nu");
        
        try {
            quanLySinhVien qlSV = new quanLySinhVien();
            qlSV.docDanhSachSinhVien(sVList);
        } catch (SQLException ex) {
            Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(sinhVien sv : this.sVList){
                dem++;
            }
         maSV.setText(Integer.toString(dem+1));
         hoSV.setText("");
         tenSV.setText("");
         queQuan.setText("null");
         cbGioiTinh.setValue("null");
         datePick.setValue(LocalDate.of(1970, 01, 01));
         queQuan.setText("null");
         maLop.setText("null");
        
//        FilteredList<sinhVien> fileteredData = new FilteredList<>(sVList, b -> true);
//        maSV.textProperty().addListener((observable,oldValue,newValue) -> {
//            fileteredData.setPredicate(sinhVien -> {
//                    for (sinhVien sv : ThemSinhVienController.this.sVList) {
//                        if(Integer.toString(sinhVien.getMaSV()).equals(maSV.getText())){
//                            hoSV.setText(sinhVien.getHoSV());
//                            tenSV.setText(sinhVien.getTenSV());
//                            cbGioiTinh.setValue(sinhVien.getGioiTinh());
//                            
//                            datePick.setValue(getDateFromString(sinhVien.F.format(sinhVien.getNgaySinh()), formatter));
//                            queQuan.setText(sinhVien.getQueQuan());
//                            maLop.setText(sinhVien.getMaLop());
//                            return true;
//                        }
//                        else if(maSV.getText().isEmpty()){
//                            hoSV.setText("");
//                            tenSV.setText("");
//                            cbGioiTinh.setValue("null");
//                            datePick.setValue(LocalDate.of(1970, 01, 01));
//                            queQuan.setText("null");
//                            maLop.setText("null");
//                            return true;
//                        }              
//                    }
//                    return false;
//            });
//        });
    }    
    
    @FXML
    public void btInsert(ActionEvent evt) throws IOException, SQLException {
        dem = 0;
        sVList.clear();    
        quanLySinhVien qlSV = new quanLySinhVien();
        quanLyLop qlLop = new quanLyLop();
            try {
                qlLop.docDanhSachLop(lopList);
                qlSV.docDanhSachSinhVien(sVList);
            } catch (ParseException ex) {
                Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            
            for(sinhVien sv : this.sVList){
                dem++;
            }
             maSV.setText(Integer.toString(dem+1));
        if(hoSV.getText().isEmpty() || tenSV.getText().isEmpty()){
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
            sVList.clear();    
                try {
                    qlSV.docDanhSachSinhVien(sVList);
                } catch (ParseException ex) {
                    Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            boolean check = false;
            for(sinhVien sv : this.sVList){
                if(maSV.getText().equals(Integer.toString(sv.getMaSV()))){
                    Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("MaSV ton tai!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
                    return;
                }

            }
            boolean checkLop = false;
            for(Lop lop : this.lopList){
                if(maLop.getText().toLowerCase().equals(lop.getMaLop().toLowerCase())){
                    checkLop = true;
                }
            }
            if(checkLop == false){
                Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("MaLop khong ton tai!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
                    return;
            }
            Statement stmt = null;
            try (Connection conn = com.homework.services.JdbcUtils.getConn()){                     
                    String sql = "INSERT INTO sinhvien (MaSV,HoSV,TenSV,GioiTinh,NgaySinh,QueQuan,MaLop) VALUES ('"
                            + "2051010" + maSV.getText() + "','"
                            +hoSV.getText() + "','"
                            +tenSV.getText() + "','"
                            +cbGioiTinh.getValue() + "','"
                            +datePick.getValue() + "','"
                            +queQuan.getText() + "','"
                            +maLop.getText()+ "')";
                    Exception e = new Exception("An exception!!!!!!!");
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("OK!");
                    alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                    alert.showAndWait();
                    stmt = (Statement) conn.createStatement();
                    stmt.executeUpdate(sql);
                    
                

            }
        }
        try {
            qlSV.docDanhSachSinhVien(sVList);
        } catch (SQLException ex) {
            Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
     public void btClear(ActionEvent evt){
        dem = 0;
        sVList.clear();    
        quanLySinhVien qlSV = new quanLySinhVien();
        quanLyLop qlLop = new quanLyLop();
        try {
             qlSV.docDanhSachSinhVien(sVList);
             qlLop.docDanhSachLop(lopList);
             } catch (SQLException ex) {
                 Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ParseException ex) {
                 Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
             }
          
            
            for(sinhVien sv : this.sVList){
                dem++;
            }
             maSV.setText(Integer.toString(dem+1));
         hoSV.setText("");
         tenSV.setText("");
         queQuan.setText("null");
         cbGioiTinh.setValue("null");
         datePick.setValue(LocalDate.of(1970, 01, 01));
         queQuan.setText("null");
         maLop.setText("null");
     }
     
    @FXML
    public void btSua(ActionEvent evt) throws SQLException{
         if(hoSV.getText().isEmpty() || tenSV.getText().isEmpty()){
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nhap thong tin!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
             return;
        }
        sVList.clear();    
             try {
                 quanLySinhVien qlSV = new quanLySinhVien();
                 qlSV.docDanhSachSinhVien(sVList);
             } catch (SQLException ex) {
                 Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ParseException ex) {
                 Logger.getLogger(ThemSinhVienController.class.getName()).log(Level.SEVERE, null, ex);
             }
        boolean checkSV = false; 
        for(sinhVien sv : this.sVList){
            if(maSV.getText().equals(Integer.toString(sv.getMaSV()))){
                    checkSV = true;
            }
        }
        if(checkSV == false){
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("MaSV ko ton tai!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
                  return;
        }
        
        try (Connection conn = com.homework.services.JdbcUtils.getConn()){
                String query = "update sinhvien set HoSV ='"
                        +hoSV.getText() 
                        + "',TenSV ='"
                        + tenSV.getText()
                        + "',GioiTinh ='"
                        + cbGioiTinh.getValue()
                        + "',NgaySinh ='"
                        + datePick.getValue()
                        + "',QueQuan ='"
                        + queQuan.getText()
                        + "',MaLop ='"
                        + maLop.getText()
                        + "' where MaSV ='" + maSV.getText() +"'";
                Statement stmt = null;
                Exception e = new Exception("An exception!!!!!!!");
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(query);
                alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
                alert.showAndWait();
                stmt = (Statement) conn.createStatement();
                stmt.execute(query);
                
        } 
     }
    
    @FXML
    public void btCheck(ActionEvent evt){
        boolean checkTonTai = false;
        dem = 0 ;
        for (sinhVien sv : ThemSinhVienController.this.sVList) {
            if(Integer.toString(sv.getMaSV()).equals(maSV.getText())){
                hoSV.setText(sv.getHoSV());
                tenSV.setText(sv.getTenSV());
                cbGioiTinh.setValue(sv.getGioiTinh());
                datePick.setValue(getDateFromString(sinhVien.F.format(sv.getNgaySinh()), formatter));
                queQuan.setText(sv.getQueQuan());
                maLop.setText(sv.getMaLop());
                checkTonTai = true;
            }    
        }
        if(checkTonTai == false){
            Exception e = new Exception("An exception!!!!!!!");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Khong co trong danh sach!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();
            for(sinhVien sv : this.sVList){
                dem++;
            }
             maSV.setText(Integer.toString(dem+1));
            hoSV.setText("");
            tenSV.setText("");
            queQuan.setText("null");
            cbGioiTinh.setValue("null");
            datePick.setValue(LocalDate.of(1970, 01, 01));
            queQuan.setText("null");
            maLop.setText("null");
        }

    }
     
}
