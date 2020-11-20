package com.wgu.gigacamp.Model;

import java.util.ArrayList;

public class Campground {

    int cgID;
    int accountID;
    int addressID;
    String cgName;
    String cgDesc;
    String cgEmail;
    String cgPhone;
    String cgOpenTime;
    String cgCloseTime;

    public Campground(int cgID, int accountID, int addressID, String cgName, String cgDesc, String cgEmail, String cgPhone, String cgOpenTime, String cgCloseTime) {
        setCgID(cgID);
        setAccountID(accountID);
        setAddressID(addressID);
        setCgName(cgName);
        setCgDesc(cgDesc);
        setCgEmail(cgEmail);
        setCgPhone(cgPhone);
        setCgOpenTime(cgOpenTime);
        setCgCloseTime(cgCloseTime);
    }

    /**
     * _______________________________________________________________________
     *                                  SETTERS
     * -----------------------------------------------------------------------
     */

    public void setCgID(int cgID) {
        this.cgID = cgID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setCgName(String cgName) {
        this.cgName = cgName;
    }

    public void setCgDesc(String cgDesc) {
        this.cgDesc = cgDesc;
    }

    public void setCgEmail(String cgEmail) {
        this.cgEmail = cgEmail;
    }

    public void setCgPhone(String cgPhone) {
        this.cgPhone = cgPhone;
    }

    public void setCgOpenTime(String cgOpenTime) {
        this.cgOpenTime = cgOpenTime;
    }

    public void setCgCloseTime(String cgCloseTime) {
        this.cgCloseTime = cgCloseTime;
    }

    /**
     * _______________________________________________________________________
     *                                  GETTERS
     * -----------------------------------------------------------------------
     */

    public int getCgID() {
        return cgID;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getAddressID() {
        return addressID;
    }

    public String getCgName() {
        return cgName;
    }

    public String getCgDesc() {
        return cgDesc;
    }

    public String getCgEmail() {
        return cgEmail;
    }

    public String getCgPhone() {
        return cgPhone;
    }

    public String getCgOpenTime() {
        return cgOpenTime;
    }

    public String getCgCloseTime() {
        return cgCloseTime;
    }

}
