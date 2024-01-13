/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.homework.doituong;

/**
 *
 * @author HOME
 */
public class userPassword {
     private String userName;
    private String passWord;
    private boolean checkPassWord;
    private boolean checkUserName;
    private boolean validationPassWord(String passWord) {
        if(passWord.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}") && passWord.matches(".*[@#$]{1,}.*") && passWord.length() >=4 && passWord.length()<=20){
            return checkPassWord = true;
        }
        else
        {
            return checkPassWord = false;
        }
    }
    private boolean validationUserName(String userName) {
        if(userName.contains("@ou.edu.vn")){
            return checkUserName = true;
        }
        else
        {
            return checkUserName = false;
        }
    }
    public boolean check(String userName,String passWord){
        if(validationPassWord(passWord) && validationUserName(userName)){
            return true;
        }
        else {
            return false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isCheckPassWord() {
        return checkPassWord;
    }

    public void setCheckPassWord(boolean checkPassWord) {
        this.checkPassWord = checkPassWord;
    }

    public boolean isCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(boolean checkUserName) {
        this.checkUserName = checkUserName;
    }
}
