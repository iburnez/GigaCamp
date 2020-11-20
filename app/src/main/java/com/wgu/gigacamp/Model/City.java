package com.wgu.gigacamp.Model;

public class City {
    int cityID;
    int stateID;
    String cityName;
    String zipcode;

    public City(int cityID, int stateID, String cityName, String zipcode) {
        setCityID(cityID);
        setStateID(stateID);
        setCityName(cityName);
        setZipcode(zipcode);
    }

    /**
     * _______________________________________________________________________
     *                                  SETTERS
     * -----------------------------------------------------------------------
     */

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * _______________________________________________________________________
     *                                  GETTERS
     * -----------------------------------------------------------------------
     */

    public int getCityID() {
        return cityID;
    }

    public int getStateID() {
        return stateID;
    }

    public String getCityName() {
        return cityName;
    }

    public String getZipcode() {
        return zipcode;
    }

    /**
     * _______________________________________________________________________
     *                                  METHODS
     * -----------------------------------------------------------------------
     */


    public void lookupState(String stateName){
        //FIXME - return int stateID (remember to change method return type from void to int)
    }

}
