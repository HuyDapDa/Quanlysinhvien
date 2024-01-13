/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.services;

import com.homework.doituong.Lop;
import com.homework.doituong.sinhVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author HOME
 */
public class quanLyLop {

    public void docDanhSachLop(ObservableList<Lop> ds) throws SQLException {
        ds.clear();
        try ( Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM lop");
            while (rs.next()) {
                String maLop = rs.getString("MaLop");
                String tenLop = rs.getString("TenLop");
                String gVCN = rs.getString("GiaoVien");
                Lop lop = new Lop(maLop, tenLop, gVCN);
                ds.add(lop);
            }
        }
    }

    public void hienThiDanhSachLop(List<Lop> ds) {
        for (Lop lp : ds) {
            lp.hienThi();
        }
    }

    public void timKiem(String a, List<Lop> ds) {
        for (Lop lop : ds) {
            if (Objects.isNull(lop.getGiaoVien()) == false) {
                if (lop.getTenLop().toLowerCase().contains(a.toLowerCase()) || lop.getMaLop().toLowerCase().contains(a.toLowerCase()) || lop.getTenLop().toLowerCase().contains(a.toLowerCase())) {
                    lop.hienThi();
                }
            } else if (a.toLowerCase().contains("NULL".toLowerCase())) {
                if (Objects.isNull(lop.getGiaoVien())) {
                    lop.hienThi();
                }
            }
        }
    }

    public void themLop(String maLop, String tenLop, String GVCN, List<Lop> ds) throws SQLException {
        if (maLop.isEmpty() || tenLop.isEmpty()) {
            System.out.println("maLop va TenLop khong dc de trong!");
            return;
        }
        if (GVCN.isEmpty()) {
            GVCN = "null";
        }

        for (Lop lop : ds) {
            if (lop.getMaLop().equals(maLop)) {
                System.out.println("maLop ton tai!");
                return;
            }
        }

        Statement stmt = null;
        try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
            String sql = "INSERT INTO lop (MaLop,TenLop,GiaoVien) VALUES ('" + maLop + "','" + tenLop + "','" + GVCN + "')";
            stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println(sql);

        }
    }

    public void fixLop(String maLop, String tenLop, String GVCN) {
        try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
            String query = "update lop set TenLop ='" + tenLop + "' where MaLop ='" + maLop + "'";
            Statement stmt = null;
            stmt = (Statement) conn.createStatement();
            stmt.execute(query);
            query = "update lop set GiaoVien ='" + GVCN + "' where MaLop ='" + maLop + "'";
            stmt = (Statement) conn.createStatement();
            stmt.execute(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(quanLyLop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void suaLop(String maLop, List<Lop> dsLop) {
        boolean checkLop = false;
        for (Lop lop : dsLop) {
            if (maLop.toLowerCase().equals(lop.getMaLop().toLowerCase())) {
                checkLop = true;
            }
        }
        if (checkLop == false) {
            System.out.println("MaLop ko ton tai!");
            return;
        }
        int choose;
        do {
            System.out.println("1.TenLop");
            System.out.println("2.GVCN");
            System.out.print("Ban chon: ");
            choose = sinhVien.sc.nextInt();
            sinhVien.sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.print("TenLop : ");
                    String tenLop = sinhVien.sc.nextLine();
                    try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
                        String query = "update lop set TenLop ='" + tenLop + "' where MaLop ='" + maLop + "'";
                        Statement stmt = null;
                        stmt = (Statement) conn.createStatement();
                        stmt.execute(query);
                        System.out.println(query);
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(quanLyLop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    System.out.print("GVCN : ");
                    String GVCN = sinhVien.sc.nextLine();
                    try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
                        String query = "update lop set GiaoVien ='" + GVCN + "' where MaLop ='" + maLop + "'";
                        Statement stmt = null;
                        stmt = (Statement) conn.createStatement();
                        stmt.execute(query);
                        System.out.println(query);
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(quanLyLop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

            }
        } while (choose != 0);
    }
}
