package com.wgu.gigacamp.Model;

public class Campsite {
    int csID;
    int cgID;
    String campsiteNum;
    int price;
    int maxGuests;

    public Campsite(int csID, int cgID, String campsiteNum, int price, int maxGuests){
        setCsID(csID);
        setCgID(cgID);
        setCampsiteNum(campsiteNum);
        setPrice(price);
        setMaxGuests(maxGuests);
    }

    /**
     * _______________________________________________________________________
     *                                  SETTERS
     * -----------------------------------------------------------------------
     */

    public void setCsID(int csID) {
        this.csID = csID;
    }

    public void setCgID(int cgID) {
        this.cgID = cgID;
    }

    public void setCampsiteNum(String campsiteNum) {
        this.campsiteNum = campsiteNum;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    /**
     * _______________________________________________________________________
     *                                  GETTERS
     * -----------------------------------------------------------------------
     */

    public int getCsID() {
        return csID;
    }

    public int getCgID() {
        return cgID;
    }

    public String getCampsiteNum() {
        return campsiteNum;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

}
