/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.services;

import com.homework.doituong.Hoc;
import com.homework.doituong.MonHoc;

import com.homework.doituong.sinhVien;
import static com.homework.doituong.sinhVien.isNumeric;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author HOME
 */
public class quanLyHoc {

    public void docDanhSachHoc(ObservableList<Hoc> ds) throws SQLException, ParseException {
        ds.clear();
        try ( Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM hoc");
            while (rs.next()) {
                String maMH = rs.getString("MaMH");
                int maSV = rs.getInt("MaSV");
                String ngayDangKy = rs.getString("NgayDangKy");
                double diem = rs.getDouble("Diem");
                int submit = rs.getInt("submit");
                Hoc hoc = new Hoc(maMH, maSV, ngayDangKy, diem, submit);
                ds.add(hoc);
            }
        }
    }

    public void hienThiDanhSachHoc(List<Hoc> ds) {
        for (Hoc hc : ds) {
            hc.hienThi();
        }
    }

    public void timKiem(String a, List<Hoc> ds) {
        for (Hoc hoc : ds) {
            if (isNumeric(a)) {
                if (Integer.toString(hoc.getMaSV()).equals(a)) {
                    hoc.hienThi();
                }
            } else {
                if (hoc.getMaMH().toLowerCase().contains(a)) {
                    hoc.hienThi();
                }
            }

        }
    }

    public void themSV_MH(String maMH, String maSV, List<sinhVien> dsSV, List<MonHoc> dsMH) {
        Date d = new Date();
        if (maMH.isEmpty() || maSV.isEmpty()) {
            System.out.println("maMH va maSV khong de trong!");
            return;
        }
        if (!isNumeric(maSV)) {
            System.out.println("maSV phai la so!");
            return;
        }
        boolean checkSV = false;
        boolean checkMH = false;
        for (sinhVien sv : dsSV) {
            if (maSV.equals(Integer.toString(sv.getMaSV()))) {
                checkSV = true;
            }
        }
        for (MonHoc mh : dsMH) {
            if (mh.getMaMH().toLowerCase().equals(maMH.toLowerCase())) {
                checkMH = true;
            }
        }

        if (checkSV == true && checkMH == true) {
            Statement stmt = null;
            Date date = new Date();
            try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
                String sql = "INSERT INTO hoc (MaMH,MaSV,NgayDangKy,Diem,submit,time) VALUES ('"
                        + maMH + "','"
                        + maSV + "','"
                        + sinhVien.F.format(date) + "','"
                        + 0 + "','"
                        + 0 + "','"
                        + String.valueOf(d.getTime()) + "')";
                System.out.println("");
                stmt = (Statement) conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Ok!" + checkSV + checkMH);

            } catch (SQLException ex) {
                Logger.getLogger(quanLyHoc.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (checkSV == false) {
            System.out.println("MaSV ko ton tai!");
        } else if (checkMH == false) {
            System.out.println("MaMH ko ton tai!");
        }

    }

    public void inputDiem(String maMH, String maSV, String diem, int submit,String date ,List<MonHoc> dsMH, List<sinhVien> dsSV, List<Hoc> dsHoc) {
        boolean checkMH = false;
        boolean checkSV = false;
        for (MonHoc mh : dsMH) {
            if (maMH.toLowerCase().equals(mh.getMaMH().toLowerCase())) {
                checkMH = true;
            }
        }
        for (sinhVien sv : dsSV) {
            if (maSV.equals(Integer.toString(sv.getMaSV()))) {
                checkSV = true;
            }
        }

        if (checkMH == false) {
            System.out.println("MaMonHoc ko ton tai!");
            return;
        }
        if (checkSV == false) {
            System.out.println("MaSV ko ton tai!");
            return;
        }

        boolean checkHc = false;
        for (Hoc hc : dsHoc) {
            if (maMH.toLowerCase().equals(hc.getMaMH().toLowerCase()) && maSV.equals(Integer.toString(hc.getMaSV()))) {
                checkHc = true;
            }
        }
        if (checkHc == false) {
            System.out.println("Sinh vien chua dang ky mon!");
            return;
        }
        if (!isNumeric(diem)) {
            System.out.print("Diem phai la so");
            return;
        }
        if (Double.parseDouble(diem) < 0 || Double.parseDouble(diem) > 10) {
            System.out.print("-1 < Diem < 11");
            return;
        }
        try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
            String query = "update hoc set Diem ='"
                    + Double.parseDouble(diem)
                    + "' where MaSV ='" + maSV + "' and MaMH ='"
                    + maMH + "' and NgayDangKy ='"
                    + date + "'";
            Statement stmt = null;
            stmt = (Statement) conn.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(quanLyHoc.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean check = false;
        for (Hoc hc : dsHoc) {
            if (maMH.toLowerCase().equals(hc.getMaMH().toLowerCase()) && maSV.equals(Integer.toString(hc.getMaSV()))) {
                if (hc.isSubmit() == 0) {
                    check = true;
                }
            }
        }

        if (check == false) {
            System.out.println("Da submit roi!");
            return;
        }
        try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
            String query = "update hoc set submit ='"
                    + submit
                    + "' where MaSV ='" + maSV + "' and MaMH ='"
                    + maMH + "' and NgayDangKy ='"
                    + date + "'";
            Statement stmt = null;
            stmt = (Statement) conn.createStatement();
            stmt.execute(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(quanLyHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nhapDiem(String maMH, String maSV, List<MonHoc> dsMH, List<sinhVien> dsSV, List<Hoc> dsHoc) {
        boolean checkMH = false;
        boolean checkSV = false;
        for (MonHoc mh : dsMH) {
            if (maMH.toLowerCase().equals(mh.getMaMH().toLowerCase())) {
                checkMH = true;
            }
        }
        for (sinhVien sv : dsSV) {
            if (maSV.equals(Integer.toString(sv.getMaSV()))) {
                checkSV = true;
            }
        }

        if (checkMH == false) {
            System.out.println("MaMonHoc ko ton tai!");
            return;
        }
        if (checkSV == false) {
            System.out.println("MaSV ko ton tai!");
            return;
        }

        boolean checkHc = false;
        for (Hoc hc : dsHoc) {
            if (maMH.toLowerCase().equals(hc.getMaMH().toLowerCase()) && maSV.equals(Integer.toString(hc.getMaSV()))) {
                checkHc = true;
            }
        }
        if (checkHc == false) {
            System.out.println("Sinh vien chua dang ky mon!");
            return;
        }

        int choose;
        do {
            System.out.println("1.Diem");
            System.out.println("2.Submit");
            System.out.print("Ban chon: ");
            choose = sinhVien.sc.nextInt();
            sinhVien.sc.nextLine();

            switch (choose) {
                case 1:
                    System.out.print("Nhap diem: ");
                    String diem = sinhVien.sc.nextLine();
                    if (!isNumeric(diem)) {
                        System.out.print("Diem phai la so");
                        return;
                    }
                    if (Double.parseDouble(diem) < 0 || Double.parseDouble(diem) > 10) {
                        System.out.print("-1 < Diem < 11");
                        return;
                    }
                    try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
                        String query = "update hoc set Diem ='"
                                + Double.parseDouble(diem)
                                + "' where MaSV ='" + maSV + "' and MaMH ='"
                                + maMH + "'";
                        Statement stmt = null;
                        stmt = (Statement) conn.createStatement();
                        stmt.execute(query);
                        System.out.print(query);
                    } catch (SQLException ex) {
                        Logger.getLogger(quanLyHoc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    for (Hoc hc : dsHoc) {
                        if (maMH.toLowerCase().equals(hc.getMaMH().toLowerCase()) && maSV.equals(Integer.toString(hc.getMaSV()))) {
                            if (hc.isSubmit() == 1) {
                                System.out.println("Da submit roi!");
                                return;
                            }
                        }
                    }
                    System.out.print("Y/N: ");
                    String yesno = sinhVien.sc.nextLine();
                    if (yesno.toLowerCase().equals("N".toLowerCase())) {
                        System.out.println("HUY submit diem");
                    } else if (yesno.toLowerCase().equals("Y".toLowerCase())) {
                        try ( Connection conn = com.homework.services.JdbcUtils.getConn()) {
                            String query = "update hoc set submit ='"
                                    + 1
                                    + "' where MaSV ='" + maSV + "' and MaMH ='"
                                    + maMH + "'";
                            Statement stmt = null;
                            stmt = (Statement) conn.createStatement();
                            stmt.execute(query);
                            System.out.println(query);
                        } catch (SQLException ex) {
                            Logger.getLogger(quanLyHoc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("Nhap Y hoac N");
                    }
                    break;

            }
        } while (choose != 0);
    }
}
