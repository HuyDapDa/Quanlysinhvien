/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.services;


import com.homework.doituong.Hoc;
import com.homework.doituong.Lop;
import com.homework.doituong.MonHoc;
import com.homework.doituong.sinhVien;
import com.homework.doituong.userPassword;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HOME
 */
public class Menu {
    public static Scanner sc = new Scanner(System.in);
    public static final SimpleDateFormat F = new SimpleDateFormat("yyyy-MM-dd");
    public static final DecimalFormat dF = new DecimalFormat("#.00");
    private ObservableList<sinhVien> dsSinhVien = FXCollections.observableArrayList();
    private ObservableList<MonHoc> dsMonHoc = FXCollections.observableArrayList();
    private ObservableList<Hoc> dsHoc = FXCollections.observableArrayList();
    private ObservableList<Lop> dsLop = FXCollections.observableArrayList();
    public void Menu() throws  SQLException, ParseException {
        
       userPassword userPassword = new userPassword();
       System.out.print("Nhap UserName: ");
       String userName = sc.nextLine();
       System.out.print("Nhap PassWord: ");
       String passWord = sc.nextLine();
       if (userPassword.check(userName, passWord)) {
           int choose;
           do {
               quanLySinhVien quanLySinhVien = new quanLySinhVien();
               
               quanLyLop quanLyLop = new quanLyLop();
               quanLyMonHoc quanLyMonHoc = new quanLyMonHoc();
               quanLyHoc qlHoc = new quanLyHoc();
               System.out.println("1.Danh sach sinh vien");
               System.out.println("2.Danh sach Lop");
               System.out.println("3.Danh sach mon hoc");
               System.out.println("4.Danh sach Hoc ");
               System.out.println("======================================");
               System.out.println("5.Tim kiem sinh vien theo MaSV hoac MaLop ");
               System.out.println("6.Tim kiem Lop theo maLop hoac tenLop");
               System.out.println("7.Tim kiem Mon hoc theo MaMH hoac TenMH ");
               System.out.println("8.Tim kiem Hoc theo MaMH hoac MaSV ");
               System.out.println("======================================");
               System.out.println("9.Them 1 sinh vien");
               System.out.println("10.Them 1 lop");
               System.out.println("11.Them 1 mon hoc");
               System.out.println("12.Them 1 SinhVien_MonHoc");
               System.out.println("======================================");
               System.out.println("13.Sua 1 sinh vien");
               System.out.println("14.Sua 1 lop");
               System.out.println("15.Sua 1 mon hoc");
               System.out.println("16.Sua 1 SinhVien_MonHoc");
               System.out.print("Ban chon: ");
               choose = sc.nextInt();
               sc.nextLine();
               switch (choose) {
                   case 1:
                       
                       quanLySinhVien.docDanhSachSinhVien(dsSinhVien);
                       quanLySinhVien.hienThiDanhSach(dsSinhVien);
                       break;
                   case 2:
                       quanLyLop.docDanhSachLop(dsLop);
                       quanLyLop.hienThiDanhSachLop(dsLop);
                       break;
                   case 3:
                       quanLyMonHoc.docDanhSachMonHoc(dsMonHoc);
                       quanLyMonHoc.hienThiDanhSachMonHoc(dsMonHoc);
                       break;
                   case 4:
                       qlHoc.docDanhSachHoc(dsHoc);
                       qlHoc.hienThiDanhSachHoc(dsHoc);
                       break;
                    case 5:
                        quanLySinhVien.docDanhSachSinhVien(dsSinhVien);
                        System.out.print("Nhap MaSV || MaLop: "); String tmp = Menu.sc.nextLine();
                        quanLySinhVien.timKiem(tmp, dsSinhVien);
                        break;
                    case 6:
                        quanLyLop.docDanhSachLop(dsLop);
                        System.out.print("Nhap MaLop || TenLop: "); String tmpLop = Menu.sc.nextLine();
                        quanLyLop.timKiem(tmpLop, dsLop);
                        break;
                    case 7:
                        quanLyMonHoc.docDanhSachMonHoc(dsMonHoc);
                        System.out.print("Nhap MaMH || TenMH: "); String tmpMH = Menu.sc.nextLine();
                        quanLyMonHoc.timKiem(tmpMH, dsMonHoc);
                        break;
                    case 8:
                        qlHoc.docDanhSachHoc(dsHoc);
                        System.out.print("Nhap MaMH || maSV: "); String tmpHoc = Menu.sc.nextLine();
                        qlHoc.timKiem(tmpHoc, dsHoc);
                        break;
                    case 9:
                        quanLySinhVien.docDanhSachSinhVien(dsSinhVien);
                        System.out.print("Ho: "); String ho = Menu.sc.nextLine();
                        System.out.print("Ten: "); String ten = Menu.sc.nextLine();
                        System.out.print("gioiTinh: "); String gioiTinh = Menu.sc.nextLine();
                        System.out.print("ngaySinh (yyyy-MM-dd): "); String ngaySinh = Menu.sc.nextLine();
                        System.out.print("queQuan: "); String queQuan = Menu.sc.nextLine();
                        System.out.print("maLop: "); String maLop = Menu.sc.nextLine();
                        quanLySinhVien.themSinhVien(ho,ten,gioiTinh,ngaySinh,queQuan,maLop);
                        break;
                    case 10:
                        quanLyLop.docDanhSachLop(dsLop);
                        System.out.print("maLop: "); String maLopCs10 = Menu.sc.nextLine();
                        System.out.print("TenLop: "); String tenLop = Menu.sc.nextLine();
                        System.out.print("GVCN: "); String GVCN = Menu.sc.nextLine();
                        quanLyLop.themLop(maLopCs10, tenLop, GVCN, dsLop);
                        break;
                    case 11:
                        quanLyMonHoc.docDanhSachMonHoc(dsMonHoc);
                        System.out.print("maMH: "); String maMH = Menu.sc.nextLine();
                        System.out.print("tenMH: "); String tenMH = Menu.sc.nextLine();
                        System.out.print("soTinChi: "); String soTinChi = Menu.sc.nextLine();
                        quanLyMonHoc.themMonHoc(maMH, tenMH, soTinChi, dsMonHoc);
                        break;
                    case 12:
                        quanLySinhVien.docDanhSachSinhVien(dsSinhVien);
                        quanLyMonHoc.docDanhSachMonHoc(dsMonHoc);
                        System.out.print("maMH: "); String maMHCs12 = Menu.sc.nextLine();
                        System.out.print("maSV: ");  String maSVCs12 = Menu.sc.nextLine();
                        qlHoc.themSV_MH(maMHCs12, maSVCs12, dsSinhVien, dsMonHoc);  
                        break;
                    case 13:
                        quanLySinhVien.docDanhSachSinhVien(dsSinhVien);
                        quanLyLop.docDanhSachLop(dsLop);
                        System.out.print("maSV: ");  String maSVCs13 = Menu.sc.nextLine();
                        quanLySinhVien.updateSinhVien(maSVCs13, dsSinhVien,dsLop);
                        break;
                    case 14:
                        quanLyLop.docDanhSachLop(dsLop);
                        System.out.print("maLop: ");  String maLopCs14 = Menu.sc.nextLine();
                        quanLyLop.suaLop(maLopCs14, dsLop);
                        break;
                    case 15:
                        quanLyMonHoc.docDanhSachMonHoc(dsMonHoc);
                        System.out.print("maMH: ");  String maMHCs15 = Menu.sc.nextLine();
                        quanLyMonHoc.suaMonHoc(maMHCs15, dsMonHoc);
                        break;
                    case 16:
                        quanLyMonHoc.docDanhSachMonHoc(dsMonHoc);
                        quanLySinhVien.docDanhSachSinhVien(dsSinhVien);
                        qlHoc.docDanhSachHoc(dsHoc);
                        System.out.print("maMH: "); String maMHCs16 = Menu.sc.nextLine();
                        System.out.print("maSV: ");  String maSVCs16 = Menu.sc.nextLine();
                        qlHoc.nhapDiem(maMHCs16, maSVCs16, dsMonHoc, dsSinhVien, dsHoc);
                        
                        break;

               }
           } while (choose != 0);
       }
       else {
           System.out.print("Sai UserName hoac PassWord\n");
       }
    }
}
