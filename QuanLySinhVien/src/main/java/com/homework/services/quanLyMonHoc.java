/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.services;

import com.homework.doituong.MonHoc;
import static com.homework.jframeQLSinhVien.danhSachHoc.isNumeric;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HOME
 */
public class quanLyMonHoc {
    public void docDanhSachMonHoc(List<MonHoc> ds) throws SQLException{
        ds.clear();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM monhoc");
            while (rs.next()) {
                String maMH = rs.getString("MaMH");
                String tenMH = rs.getString("TenMH");
                int soTinChi = rs.getInt("SoTinChi");
                MonHoc mh = new MonHoc(maMH,tenMH,soTinChi);
                ds.add(mh);
            }
        }
    }
    public void hienThiDanhSachMonHoc(List<MonHoc> ds){
        for(MonHoc mh : ds){
            mh.hienThi();
        }
    }
    public void timKiem(String a,List<MonHoc> ds){
        for(MonHoc mh : ds){
            if(mh.getMaMH().toLowerCase().contains(a.toLowerCase()) || mh.getTenMH().toLowerCase().contains(a.toLowerCase())){
                mh.hienThi();
            }
        }
    }
    
    public void themMonHoc(String maMH,String tenMH,String soTinChi,List<MonHoc> ds) throws SQLException{
        if(maMH.isEmpty() || tenMH.isEmpty() || soTinChi.isEmpty()){
            System.out.println("MaMh , TenMH, SoTinChi khong dc de trong!");
            return;
        }
        if(!isNumeric(soTinChi)){
            System.out.println("So tin chi phai la chu so!");
            return;
        }
        if(Integer.parseInt(soTinChi) > 10 || Integer.parseInt(soTinChi) < 0) {
            System.out.println("So tin chi 0 < So tin chi < 10!");
            return;
        }
        
        for(MonHoc mh : ds){
            if(maMH.toLowerCase().equals(mh.getMaMH().toLowerCase())){
                System.out.println("Trung ma Mon Hoc!");
                return;
            }
        }
        Statement stmt = null;
        try (Connection conn = com.homework.services.JdbcUtils.getConn()){
                stmt = (Statement) conn.createStatement();
                String query = "INSERT INTO monhoc " + "VALUES ('" + maMH + "','" + tenMH + "'," + Integer.parseInt(soTinChi) + ")";
                stmt.executeUpdate(query);
                System.out.println(query);
        }
       
    }
    public void suaMonHoc(String maMH , List<MonHoc> dsMH){
        boolean checkMH = false;
        for(MonHoc mh : dsMH){
            if(maMH.toLowerCase().equals(mh.getMaMH().toLowerCase()))
            {
                checkMH = true;
            }
        }
        
        if(checkMH == false){
            System.out.println("MaMH ko ton tai!");
            return;
        }
        
        int choose;
        do{
            System.out.println("1.TenMonHoc");
            System.out.println("2.SoTinChi");
            System.out.print("Ban chon: "); choose = Menu.sc.nextInt();
            Menu.sc.nextLine();
            switch(choose){
                case 1:
                    System.out.print("TenMH : "); String tenMH = Menu.sc.nextLine();
                    try (Connection conn = com.homework.services.JdbcUtils.getConn()){
                    String query = "update monhoc set TenMH ='"+ tenMH +  "' where MaMH ='" + maMH +"'";
                    Statement stmt = null;
                    stmt = (Statement) conn.createStatement();
                    stmt.execute(query);
                    System.out.println(query);
                    } catch (SQLException ex) {
                    Logger.getLogger(quanLyMonHoc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 2:
                    System.out.print("SoTinChi : "); String soTinChi = Menu.sc.nextLine();
                    if(!isNumeric(soTinChi)){
                        System.out.print("soTinChi phai la chu so!");
                        return;
                    }
                    try (Connection conn = com.homework.services.JdbcUtils.getConn()){
                    String query = "update monhoc set SoTinChi ='"+ soTinChi +  "' where MaMH ='" + maMH +"'";
                    Statement stmt = null;
                    stmt = (Statement) conn.createStatement();
                    stmt.execute(query);
                    System.out.println(query);
                    } catch (SQLException ex) {
                    Logger.getLogger(quanLyMonHoc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;


            }
        }while(choose != 0);
    }
}
