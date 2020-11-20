package com.wgu.gigacamp.Model;

public class Address {
    int addressID;
    int cityID;
    String addressLine1;
    String addressLine2;

    public Address(int addressID, int cityID, String addressLine1, String addressLine2) {
     setAddressID(addressID);
     setCityID(cityID);
     setAddressLine1(addressLine1);
     setAddressLine2(addressLine2);
    }

    public Address(int addressID, int cityID, String addressLine1) {
        setAddressID(addressID);
        setCityID(cityID);
        setAddressLine1(addressLine1);
    }

    /**
     * _______________________________________________________________________
     *                                  SETTERS
     * -----------------------------------------------------------------------
     */

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * _______________________________________________________________________
     *                                  GETTERS
     * -----------------------------------------------------------------------
     */

    public int getAddressID() {
        return addressID;
    }

    public int getCityID() {
        return cityID;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * _______________________________________________________________________
     *                                  METHODS
     * -----------------------------------------------------------------------
     */


    public void lookupCity(String cityName, String stateName){
        //FIXME - return int cityID (remember to change method return type from void to int)
    }
}
