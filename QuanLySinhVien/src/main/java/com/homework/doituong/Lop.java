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
    private String gVCN;
    public Lop(String maLop,String tenLop,String gVCN){
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.gVCN = gVCN;
    }
    public void hienThi(){
        System.out.printf("Ma lop: %s - Ten lop: %s - GVCN: %s\n",this.maLop,this.tenLop,this.gVCN);
    }
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getgVCN() {
        return gVCN;
    }

    public void setgVCN(String gVCN) {
        this.gVCN = gVCN;
    }
}
