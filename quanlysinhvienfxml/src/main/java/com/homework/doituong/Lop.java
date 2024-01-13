/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.doituong;

/**
 *
 * @author HOME
 */
public class Lop {
    private String maLop;
    private String tenLop;
    private String giaoVien;

    public Lop(String maLop, String tenLop, String giaoVien) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.giaoVien = giaoVien;
    }
    public void hienThi(){
        System.out.printf("Ma lop: %s - Ten lop: %s - GVCN: %s\n", this.getMaLop(), this.getTenLop(), this.getGiaoVien());
    }
    /**
     * @return the maLop
     */
    public String getMaLop() {
        return maLop;
    }

    /**
     * @param maLop the maLop to set
     */
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    /**
     * @return the tenLop
     */
    public String getTenLop() {
        return tenLop;
    }

    /**
     * @param tenLop the tenLop to set
     */
    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    /**
     * @return the giaoVien
     */
    public String getGiaoVien() {
        return giaoVien;
    }

    /**
     * @param giaoVien the giaoVien to set
     */
    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }
    
}
