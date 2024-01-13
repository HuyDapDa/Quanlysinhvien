/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.doituong;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author HOME
 */
public class sinhVien {

    /**
     * @return the ngaySinh
     */
//    public String getNgaySinh() {
//        return ngaySinh;
//    }
//
//    /**
//     * @param ngaySinh the ngaySinh to set
//     */
//    public void setNgaySinh(String ngaySinh) {
//        this.ngaySinh = ngaySinh;
//    }
    
    private int maSV;
//    private static int dem = 0;
//    {
//        dem++;
//        this.maSV = dem;
//    }
//    public static void setDem() {
//        dem = 0;
//    }
    private String hoSV;
    private String tenSV;
    private String gioiTinh;
    private String queQuan;
    private String maLop;
    private Date ngaySinh;
    public static final SimpleDateFormat F = new SimpleDateFormat("yyyy-MM-dd");
    public static final Scanner sc = new Scanner(System.in);
    public static boolean isNumeric(String string) {
        // Checks if the provided string
        // is a numeric by applying a regular
        // expression on it.
        String regex = "[0-9]+[\\.]?[0-9]*";
        return Pattern.matches(regex, string);
    }
    
    public sinhVien(int maSV,String hoSV,String tenSV,String gioiTinh,String ngaySinh,String queQuan,String maLop) throws ParseException {
        this.maSV = maSV;
        this.hoSV = hoSV;
        this.tenSV = tenSV;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.maLop = maLop;
        this.ngaySinh = F.parse(ngaySinh);
    }

    public void hienThi(){
        System.out.printf("Ma SV: %s - Ma lop: %s\n", this.getMaSV(),this.maLop);
        System.out.printf("Ho va ten: %s %s - Gioi tinh: %s - Ngay sinh: %s\n",this.hoSV,this.tenSV,this.gioiTinh,sinhVien.F.format(this.ngaySinh));
        System.out.printf("Que quan: %s\n",this.queQuan);
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getHoSV() {
        return hoSV;
    }

    public void setHoSV(String hoSV) {
        this.hoSV = hoSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
