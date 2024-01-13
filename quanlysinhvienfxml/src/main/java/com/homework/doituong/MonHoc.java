/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.doituong;

/**
 *
 * @author HOME
 */
public class MonHoc {
    private String maMH;
    private String tenMH;
    private int soTinChi;
    
    public MonHoc(String maMH,String tenMH,int soTinChi){
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTinChi = soTinChi;
    }
    public void hienThi(){
        System.out.printf("Ma mon hoc: %s - Ten mon hoc: %s - So tin chi: %s\n",this.maMH,this.tenMH,this.soTinChi);
    }
    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }
}
