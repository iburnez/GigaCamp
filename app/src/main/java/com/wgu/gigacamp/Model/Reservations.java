package com.wgu.gigacamp.Model;

import java.sql.Date;

public class Reservations {
    int resID;
    int csID;
    int travelerAccountID;
    String arrivalDate;
    String departureDate;
    String resName;
    String resEmail;
    String resPhone;

    public Reservations(int resID, int csID, int travelerAccountID, String arrivalDate, String departureDate, String resName, String resEmail, String resPhone) {
        setResID(resID);
        setCsID(csID);
        setTravelerAccountID(travelerAccountID);
        setArrivalDate(arrivalDate);
        setDepartureDate(departureDate);
        setResName(resName);
        setResEmail(resEmail);
        setResPhone(resPhone);
    }

    /**
     * _______________________________________________________________________
     *                                  SETTERS
     * -----------------------------------------------------------------------
     */

    public void setResID(int resID) {
        this.resID = resID;
    }

    public void setCsID(int csID) {
        this.csID = csID;
    }

    public void setTravelerAccountID(int travelerAccountID) {
        this.travelerAccountID = travelerAccountID;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public void setResEmail(String resEmail) {
        this.resEmail = resEmail;
    }

    public void setResPhone(String resPhone) {
        this.resPhone = resPhone;
    }

    /**
     * _______________________________________________________________________
     *                                  GETTERS
     * -----------------------------------------------------------------------
     */

    public int getResID() {
        return resID;
    }

    public int getCsID() {
        return csID;
    }

    public int getTravelerAccountID() {
        return travelerAccountID;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getResName() {
        return resName;
    }

    public String getResEmail() {
        return resEmail;
    }

    public String getResPhone() {
        return resPhone;
    }
}
