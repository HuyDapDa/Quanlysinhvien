/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.doituong;
import com.homework.services.Menu;
import java.text.ParseException;
import java.util.Date;
/**
 *
 * @author HOME
 */
public class Hoc {
    private String maMH;
    private int maSV;
    private Date ngayDangKy;
    private double diem;
    private int submit;
    public Hoc(String maMH,int maSV,String ngayDangKy,double diem,int submit) throws ParseException{
        this.maMH = maMH;
        this.maSV = maSV;
        this.ngayDangKy = Menu.F.parse(ngayDangKy);
        this.diem = diem;
        this.submit = submit;
    }
    
    public void hienThi(){
        System.out.printf("Ma mon hoc: %s - Ma sinh vien: %s - Ngay dang ky: %s - Diem: %.2f - Submit: %s\n",this.maMH,this.maSV,Menu.F.format(this.ngayDangKy), this.diem,this.submit);
    }
    /**
     * @return the maMH
     */
    public String getMaMH() {
        return maMH;
    }

    /**
     * @param maMH the maMH to set
     */
    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    /**
     * @return the maSV
     */
    public int getMaSV() {
        return maSV;
    }

    /**
     * @param maSV the maSV to set
     */
    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    /**
     * @return the ngayDangKy
     */
    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    /**
     * @param ngayDangKy the ngayDangKy to set
     */
    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    /**
     * @return the submit
     */
    public int isSubmit() {
        return submit;
    }

    /**
     * @param submit the submit to set
     */
    public void setSubmit(int submit) {
        this.submit = submit;
    }

    /**
     * @return the diem
     */
    public double getDiem() {
        return diem;
    }

    /**
     * @param diem the diem to set
     */
    public void setDiem(double diem) {
        this.diem = diem;
    }
}
