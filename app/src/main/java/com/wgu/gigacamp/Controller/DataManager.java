package com.wgu.gigacamp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Address;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.Campsite;
import com.wgu.gigacamp.Model.City;
import com.wgu.gigacamp.Model.Reservations;
import com.wgu.gigacamp.Model.State;

import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DataManager {


    /**
     * _______________________________________________________________________
     *                            INSERT POJO TO DB
     * -----------------------------------------------------------------------
     */

    /**
     * INSERT NEW ACCOUNT
     * Declare new ContentValues
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context activity context
     * @param fName user first name
     * @param lName user last name
     * @param email user email
     * @param password user password
     * @param phoneNum user phone number
     * @return uri of inserted account
     */
    public static Uri insertAccount(Context context, String fName, String lName, String email, String password, String phoneNum){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.ACCOUNT_FNAME, fName);
        values.put(DBOpenHelper.ACCOUNT_LNAME, lName);
        values.put(DBOpenHelper.ACCOUNT_EMAIL, email);
        values.put(DBOpenHelper.ACCOUNT_PASSWORD, password);
        values.put(DBOpenHelper.ACCOUNT_PHONE_NUM, phoneNum);
        Uri accountUri = context.getContentResolver().insert(DataProvider.ACCOUNT_URI, values);
        return accountUri;
    }

    /**
     * INSERT NEW CAMPGROUND
     * Declare new ContentValues
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context activity context
     * @param accountID user accountID
     * @param addressID campground addressID
     * @param cgName campground name
     * @param cgDesc campground description
     * @param cgEmail campground email
     * @param cgPhone campground phone number
     * @param cgOpenTime campground open time
     * @param cgCloseTime campground close time
     * @return uri of inserted campground
     */
    public static Uri insertCampground(Context context, int accountID, int addressID, String cgName,
                                       String cgDesc, String cgEmail, String cgPhone, String cgOpenTime, String cgCloseTime){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CAMPGROUND_ACCOUNT_ID, accountID);
        values.put(DBOpenHelper.CAMPGROUND_ADDRESS_ID, addressID);
        values.put(DBOpenHelper.CAMPGROUND_NAME, cgName);
        values.put(DBOpenHelper.CAMPGROUND_DESC, cgDesc);
        values.put(DBOpenHelper.CAMPGROUND_EMAIL, cgEmail);
        values.put(DBOpenHelper.CAMPGROUND_PHONE, cgPhone);
        values.put(DBOpenHelper.CAMPGROUND_OPEN_TIME, cgOpenTime);
        values.put(DBOpenHelper.CAMPGROUND_CLOSE_TIME, cgCloseTime);
        Uri campgroundUri = context.getContentResolver().insert(DataProvider.CAMPGROUND_URI, values);
        return campgroundUri;
    }

    /**
     * INSERT NEW CAMPSITE
     * Declare new ContentValues
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context activity context
     * @param cgID campsite campgroundID
     * @param campsiteNum campsite number
     * @param price campsite price
     * @param maxGuests campsite max # of guests
     * @return uri of inserted campsite
     */
    public static Uri insertCampsite(Context context, int cgID, String campsiteNum, int price, int maxGuests){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CAMPSITE_CAMPGROUND_ID, cgID);
        values.put(DBOpenHelper.CAMPSITE_SITE_NUM, campsiteNum);
        values.put(DBOpenHelper.CAMPSITE_PRICE, price);
        values.put(DBOpenHelper.CAMPSITE_MAX_GUESTS, maxGuests);
        Uri campsiteUri = context.getContentResolver().insert(DataProvider.CAMPSITE_URI, values);
        return campsiteUri;
    }

    /**
     * INSERT NEW ADDRESS
     * Declare new ContentValues
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context activity context
     * @param cityID address cityID
     * @param addressLine1 address line one
     * @param addressLine2 address line two
     * @return uri of inserted address
     */
    public static Uri insertAddress(Context context, int cityID, String addressLine1, String addressLine2){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.ADDRESS_CITY_ID, cityID);
        values.put(DBOpenHelper.ADDRESS_LINE_1, addressLine1);
        values.put(DBOpenHelper.ADDRESS_LINE_2, addressLine2);
        Uri addressUri = context.getContentResolver().insert(DataProvider.ADDRESS_URI, values);
        return addressUri;
    }

    /**
     * INSERT NEW CITY
     * Declare new ContentValues
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context activity context
     * @param stateID stateID of city
     * @param cityName name of city
     * @param zipcode city zipcode
     * @return uri of inserted city
     */
    public static Uri insertCity(Context context, int stateID, String cityName, String zipcode){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CITY_STATE_ID, stateID);
        values.put(DBOpenHelper.CITY_NAME, cityName);
        values.put(DBOpenHelper.CITY_ZIPCODE, zipcode);
        Uri cityUri = context.getContentResolver().insert(DataProvider.CITY_URI, values);
        return cityUri;
    }

    /**
     * INSERT NEW STATE
     * Declare new ContentValues
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context activity context
     * @param stateName name of state
     * @return uri of inserted state
     */
    public static Uri insertState(Context context, String stateName){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.STATE_NAME, stateName);
        Uri stateUri = context.getContentResolver().insert(DataProvider.STATE_URI, values);
        return stateUri;
    }

    /**
     * INSERT NEW RESERVATION
     * Declare new ContentValues
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context activity context
     * @param csID reservation campsiteID
     * @param travelerAccountID reservation traveler accountID
     * @param arrivalDate reservation arrival date
     * @param departureDate reservation departure date
     * @param resName reservation name
     * @param resPhone reservation phone number
     * @param resEmail reservation email
     * @return uri of inserted reservation
     */
    public static Uri insertReservation(Context context, int csID, int travelerAccountID, String arrivalDate,
                                        String departureDate, String resName, String resPhone, String resEmail){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.RESERVATIONS_CAMPSITE_ID, csID);
        values.put(DBOpenHelper.RESERVATIONS_TRAVELER_ID, travelerAccountID);
        values.put(DBOpenHelper.RESERVATIONS_ARRIVAL, arrivalDate);
        values.put(DBOpenHelper.RESERVATIONS_DEPARTURE, departureDate);
        values.put(DBOpenHelper.RESERVATIONS_NAME, resName);
        values.put(DBOpenHelper.RESERVATIONS_PHONE, resPhone);
        values.put(DBOpenHelper.RESERVATIONS_EMAIL, resEmail);
        Uri reservationUri = context.getContentResolver().insert(DataProvider.RESERVATIONS_URI, values);
        return reservationUri;
    }



    /**
     * _______________________________________________________________________
     *                            UPDATE POJO IN DB
     * -----------------------------------------------------------------------
     */

    /**
     * UPDATE EXISTING ACCOUNT
     * Declare new ContentValues
     * Search for URI of term based on termID param
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context context from calling activity
     * @param accountID ID of account to update
     * @param fName user first name
     * @param lName user last name
     * @param email user email
     * @param password user password
     * @param phoneNum user phone number
     * @return number of records updated
     */
    public static int updateAccount(Context context, int accountID, String fName, String lName, String email, String password, String phoneNum){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.ACCOUNT_FNAME, fName);
        values.put(DBOpenHelper.ACCOUNT_LNAME, lName);
        values.put(DBOpenHelper.ACCOUNT_EMAIL, email);
        values.put(DBOpenHelper.ACCOUNT_PASSWORD, password);
        values.put(DBOpenHelper.ACCOUNT_PHONE_NUM, phoneNum);
        return context.getContentResolver().update(DataProvider.ACCOUNT_URI, values,  DBOpenHelper.ACCOUNT_TABLE_ID + " = " + accountID, null);
    }

    /**
     * UPDATE EXISTING CAMPGROUND
     * Declare new ContentValues
     * Search for URI of term based on termID param
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context context from calling activity
     * @param cgID existing campgroundID
     * @param accountID user accountID
     * @param addressID campground addressID
     * @param cgName campground name
     * @param cgDesc campground description
     * @param cgEmail campground email
     * @param cgPhone campground phone number
     * @param cgOpenTime campground open time
     * @param cgCloseTime campground close time
     * @return number of records updated
     */
    public static int updateCampground(Context context,int cgID, int accountID, int addressID, String cgName,
                                    String cgDesc, String cgEmail, String cgPhone, String cgOpenTime, String cgCloseTime){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CAMPGROUND_ACCOUNT_ID, accountID);
        values.put(DBOpenHelper.CAMPGROUND_ADDRESS_ID, addressID);
        values.put(DBOpenHelper.CAMPGROUND_NAME, cgName);
        values.put(DBOpenHelper.CAMPGROUND_DESC, cgDesc);
        values.put(DBOpenHelper.CAMPGROUND_EMAIL, cgEmail);
        values.put(DBOpenHelper.CAMPGROUND_PHONE, cgPhone);
        values.put(DBOpenHelper.CAMPGROUND_OPEN_TIME, cgOpenTime);
        values.put(DBOpenHelper.CAMPGROUND_CLOSE_TIME, cgCloseTime);
        return context.getContentResolver().update(DataProvider.CAMPGROUND_URI, values,  DBOpenHelper.CAMPGROUND_TABLE_ID + " = " + cgID, null);
    }

    /**
     * UPDATE EXISTING CAMPSITE
     * Declare new ContentValues
     * Search for URI of term based on termID param
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context context from calling activity
     * @param csID existing campsiteID
     * @param cgID campsite campgroundID
     * @param campsiteNum campsite number
     * @param price campsite price
     * @param maxGuests campsite max # of guests
     * @return number of records updated
     */
    public static int updateCampsite(Context context,int csID, int cgID, String campsiteNum, int price, int maxGuests){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CAMPSITE_CAMPGROUND_ID, cgID);
        values.put(DBOpenHelper.CAMPSITE_SITE_NUM, campsiteNum);
        values.put(DBOpenHelper.CAMPSITE_PRICE, price);
        values.put(DBOpenHelper.CAMPSITE_MAX_GUESTS, maxGuests);
        return context.getContentResolver().update(DataProvider.CAMPSITE_URI, values,  DBOpenHelper.CAMPSITE_TABLE_ID + " = " + csID, null);
    }

    /**
     * UPDATE EXISTING ADDRESS
     * Declare new ContentValues
     * Search for URI of term based on termID param
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context context from calling activity
     * @param addressID existing addressID
     * @param cityID address cityID
     * @param addressLine1 address line one
     * @param addressLine2 address line two
     * @return number of records updated
     */
    public static int updateAddress(Context context,int addressID, int cityID, String addressLine1, String addressLine2){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.ADDRESS_CITY_ID, cityID);
        values.put(DBOpenHelper.ADDRESS_LINE_1, addressLine1);
        values.put(DBOpenHelper.ADDRESS_LINE_2, addressLine2);
        return context.getContentResolver().update(DataProvider.ADDRESS_URI, values,  DBOpenHelper.ADDRESS_TABLE_ID + " = " + addressID, null);
    }

    /**
     * UPDATE EXISTING CITY
     * Declare new ContentValues
     * Search for URI of term based on termID param
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context context from calling activity
     * @param cityID existing cityID
     * @param stateID stateID of city
     * @param cityName name of city
     * @param zipcode city zipcode
     * @return number of records updated
     */
    public static int updateCity(Context context,int cityID, int stateID, String cityName, String zipcode){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CITY_STATE_ID, stateID);
        values.put(DBOpenHelper.CITY_NAME, cityName);
        values.put(DBOpenHelper.CITY_ZIPCODE, zipcode);
        return context.getContentResolver().update(DataProvider.CITY_URI, values,  DBOpenHelper.CITY_TABLE_ID + " = " + cityID, null);
    }

    /**
     * UPDATE EXISTING STATE
     * Declare new ContentValues
     * Search for URI of term based on termID param
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context context from calling activity
     * @param stateID existing stateID
     * @param stateName name of state
     * @return number of records updated
     */
    public static int updateState(Context context,int stateID, String stateName){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.STATE_NAME, stateName);
        return context.getContentResolver().update(DataProvider.STATE_URI, values,  DBOpenHelper.STATE_TABLE_ID + " = " + stateID, null);
    }

    /**
     * UPDATE EXISTING RESERVATION
     * Declare new ContentValues
     * Search for URI of term based on termID param
     * add params to content values
     * call DataProvider Insert Method and pass table and values to insert
     * @param context context from calling activity
     * @param resID existing reservationID
     * @param csID reservation campsiteID
     * @param travelerAccountID reservation traveler accountID
     * @param arrivalDate reservation arrival date
     * @param departureDate reservation departure date
     * @param resName reservation name
     * @param resPhone reservation phone number
     * @param resEmail reservation email
     * @return number of records updated
     */
    public static int updateReservation(Context context,int resID, int csID, int travelerAccountID, String arrivalDate,
                                       String departureDate, String resName, String resPhone, String resEmail){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.RESERVATIONS_CAMPSITE_ID, csID);
        values.put(DBOpenHelper.RESERVATIONS_TRAVELER_ID, travelerAccountID);
        values.put(DBOpenHelper.RESERVATIONS_ARRIVAL, arrivalDate);
        values.put(DBOpenHelper.RESERVATIONS_DEPARTURE, departureDate);
        values.put(DBOpenHelper.RESERVATIONS_NAME, resName);
        values.put(DBOpenHelper.RESERVATIONS_PHONE, resPhone);
        values.put(DBOpenHelper.RESERVATIONS_EMAIL, resEmail);
        return context.getContentResolver().update(DataProvider.RESERVATIONS_URI, values,  DBOpenHelper.RESERVATIONS_TABLE_ID + " = " + resID, null);
    }


    /**
     * _______________________________________________________________________
     *                            GET POJO FROM DB
     * -----------------------------------------------------------------------
     */

    /**
     * GET ACCOUNT BY EMAIL
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param accountEmail primary key ID used as search param
     * @return Term Object
     */
    public static Account getAccount(Context context, String accountEmail) throws NullPointerException{
        Cursor cursor = context.getContentResolver().query(DataProvider.ACCOUNT_URI, DBOpenHelper.ACCOUNT_COLS,
                DBOpenHelper.ACCOUNT_EMAIL + " = " + "\"" + accountEmail + "\"", null, null );


        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int accountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_TABLE_ID));
            String fName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_FNAME));
            String lName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_LNAME));
            String password = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_PASSWORD));
            String phoneNum = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_PHONE_NUM));

            Account account = new Account(accountID, fName, lName, accountEmail, password, phoneNum);
            cursor.close();
            return account;

        }
        return null;
    }

    /**
     * GET ACCOUNT BY EMAIL
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param accountID primary key ID used as search param
     * @return Term Object
     */
    public static Account getAccount(Context context, int accountID) throws NullPointerException{
        Cursor cursor = context.getContentResolver().query(DataProvider.ACCOUNT_URI, DBOpenHelper.ACCOUNT_COLS,
                DBOpenHelper.ACCOUNT_TABLE_ID + " = " + accountID, null, null );


        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            String accountEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_EMAIL));
            String fName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_FNAME));
            String lName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_LNAME));
            String password = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_PASSWORD));
            String phoneNum = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ACCOUNT_PHONE_NUM));

            Account account = new Account(accountID, fName, lName, accountEmail, password, phoneNum);
            cursor.close();
            return account;
        }
        return null;
    }

    /**
     * GET CAMPGROUND BY ID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param cgID primary key ID used as search param
     * @return Term Object
     */
    public static Campground getCampground(Context context, int cgID) throws NullPointerException{
        Cursor cursor = context.getContentResolver().query(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_COLS,
                DBOpenHelper.CAMPGROUND_TABLE_ID + " = " + cgID, null, null );

        if((cursor != null) && (cursor.getCount() > 0) ){
        cursor.moveToFirst();
            int accountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ACCOUNT_ID));
            int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ADDRESS_ID));
            String cgName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_NAME));
            String cgDesc = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_DESC));
            String cgEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_EMAIL));
            String cgPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_PHONE));
            String cgOpenTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_OPEN_TIME));
            String cgCloseTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_CLOSE_TIME));

            Campground campground = new Campground(cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime);
            cursor.close();
            return campground;

        }
        return null;

    }

    /**
     * GET CAMPSITE BY ID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param csID primary key ID used as search param
     * @return Term Object
     */
    public static Campsite getCampsite(Context context, int csID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.CAMPSITE_URI, DBOpenHelper.CAMPSITE_COLS,
                DBOpenHelper.CAMPSITE_TABLE_ID + " = " + csID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int cgID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_CAMPGROUND_ID));
            String campsiteNum = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_SITE_NUM));
            int price = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_PRICE));
            int maxGuests = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_MAX_GUESTS));

            Campsite campsite = new Campsite(csID, cgID, campsiteNum, price, maxGuests);
            cursor.close();
            return campsite;
        }
        return null;
    }

    /**
     * GET ADDRESS BY ID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param addressID primary key ID used as search param
     * @return Term Object
     */
    public static Address getAddress(Context context, int addressID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.ADDRESS_URI, DBOpenHelper.ADDRESS_COLS,
                DBOpenHelper.ADDRESS_TABLE_ID + " = " + addressID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int cityID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.ADDRESS_CITY_ID));
            String addressLine1 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ADDRESS_LINE_1));
            String addressLine2 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ADDRESS_LINE_2));

            Address address = new Address(addressID, cityID, addressLine1, addressLine2);
            cursor.close();
            return address;
        }
        return null;
    }

    /**
     * GET ADDRESS BY ID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param addressLine1 search term used as search param
     * @param cityID search term used as search param
     * @return Term Object
     */
    public static Address getAddress(Context context, String addressLine1, int cityID) throws NullPointerException{
        Cursor cursor = context.getContentResolver().query(DataProvider.ADDRESS_URI, DBOpenHelper.ADDRESS_COLS,
                DBOpenHelper.ADDRESS_LINE_1 + " = " + "\"" + addressLine1 + "\"" + " AND " +
                        DBOpenHelper.ADDRESS_CITY_ID + " = " + cityID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.ADDRESS_TABLE_ID));
            String addressLine2 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ADDRESS_LINE_2));

            Address address = new Address(addressID, cityID, addressLine1, addressLine2);
            cursor.close();
            return address;
        }
        return null;
    }

    /**
     * GET CITY BY ID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param cityID primary key ID used as search param
     * @return Term Object
     */
    public static City getCity(Context context, int cityID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.CITY_URI, DBOpenHelper.CITY_COLS,
                DBOpenHelper.CITY_TABLE_ID + " = " + cityID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int stateID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CITY_STATE_ID));
            String cityName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CITY_NAME));
            String zipcode = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CITY_ZIPCODE));

            City city = new City(cityID, stateID, cityName, zipcode);
            cursor.close();
            return city;
        }
        return null;
    }

    /**
     * GET CITY BY NAME
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param cityName search term ID used as search param
     * @param stateID search term ID used as search param
     * @return Term Object
     */
    public static City getCity(Context context, String cityName, int stateID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.CITY_URI, DBOpenHelper.CITY_COLS,
                DBOpenHelper.CITY_NAME + " = " + "\"" + cityName + "\"" + " AND " +
                        DBOpenHelper.CITY_STATE_ID + " = " + stateID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int cityID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CITY_TABLE_ID));
            String zipcode = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CITY_ZIPCODE));

            City city = new City(cityID, stateID, cityName, zipcode);
            cursor.close();
            return city;
        }
        return null;
    }

    /**
     * GET STATE BY ID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param stateID primary key ID used as search param
     * @return Term Object
     */
    public static State getState(Context context, int stateID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.STATE_URI, DBOpenHelper.STATE_COLS,
                DBOpenHelper.STATE_TABLE_ID + " = " + stateID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            String stateName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.STATE_NAME));

            State state = new State(stateID, stateName);
            cursor.close();
            return state;
        }
        return null;
    }

    /**
     * GET STATE BY NAME
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param stateName primary key ID used as search param
     * @return Term Object
     */
    public static State getState(Context context, String stateName) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.STATE_URI, DBOpenHelper.STATE_COLS,
                DBOpenHelper.STATE_NAME + " = " + "\"" + stateName + "\"", null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int stateID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.STATE_TABLE_ID));
            cursor.close();

            State state = new State(stateID, stateName);
            cursor.close();
            return state;
        }
        return null;
    }

    /**
     * GET RESERVATION BY ID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param resID primary key ID used as search param
     * @return Term Object
     */
    public static Reservations getReservation(Context context, int resID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.RESERVATIONS_URI, DBOpenHelper.RESERVATIONS_COLS,
                DBOpenHelper.RESERVATIONS_TABLE_ID + " = " + resID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int csID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_CAMPSITE_ID));
            int travelerAccountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TRAVELER_ID));
            String arrivalDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_ARRIVAL));
            String departureDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_DEPARTURE));
            String resName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_NAME));
            String resEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_EMAIL));
            String resPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_PHONE));

            Reservations reservation = new Reservations(resID, csID, travelerAccountID, arrivalDate, departureDate, resName, resEmail, resPhone);
            cursor.close();
            return reservation;
        }
        return null;
    }

    /**
     * GET RESERVATION BY CAMPSITEID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param csID primary key ID used as search param
     * @return Term Object
     */
    public static Reservations getReservationByCSID(Context context, int csID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.RESERVATIONS_URI, DBOpenHelper.RESERVATIONS_COLS,
                DBOpenHelper.RESERVATIONS_CAMPSITE_ID + " = " + csID, null, null );

        if((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            int resID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TABLE_ID));
            int travelerAccountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TRAVELER_ID));
            String arrivalDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_ARRIVAL));
            String departureDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_DEPARTURE));
            String resName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_NAME));
            String resEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_EMAIL));
            String resPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_PHONE));

            Reservations reservation = new Reservations(resID, csID, travelerAccountID, arrivalDate, departureDate, resName, resEmail, resPhone);
            cursor.close();
            return reservation;
        }
        return null;
    }

    /**
     * GET RESERVATION BY TRAVELER ACCOUNTID
     * Call DataProvider Query Method and pass search param
     * Save values from each column to variable
     * Call constructor method passing variables
     * Return Term Object
     * @param context context from calling activity
     * @param travelerAccountID primary key ID used as search param
     * @return Term Object
     */
    public static Reservations getReservationByAccountID(Context context, int travelerAccountID) throws NullPointerException {
        Cursor cursor = context.getContentResolver().query(DataProvider.RESERVATIONS_URI, DBOpenHelper.RESERVATIONS_COLS,
                DBOpenHelper.RESERVATIONS_TRAVELER_ID + " = " + travelerAccountID, null, null );
        if((cursor != null) && (cursor.getCount() > 0)){
            cursor.moveToFirst();
            int csID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_CAMPSITE_ID));
            int resID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TABLE_ID));
            String arrivalDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_ARRIVAL));
            String departureDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_DEPARTURE));
            String resName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_NAME));
            String resEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_EMAIL));
            String resPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_PHONE));

            Reservations reservation = new Reservations(resID, csID, travelerAccountID, arrivalDate, departureDate, resName, resEmail, resPhone);
            cursor.close();
            return reservation;
        }
        return null;
    }


    /**
     * _______________________________________________________________________
     *                            GET POJO ARRAY FROM DB
     * -----------------------------------------------------------------------
     */

    /**
     * GET ALL CITIES IN A STATE
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for all objects in table
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param stateName search param
     * @return Return array list of objects
     */
    public static ArrayList<City> getAllStateCities(Context context, String stateName) throws NullPointerException {
        State state = getState(context, stateName);

        ArrayList<City> allStateCities = new ArrayList<>();

        try{
        Cursor cursor = context.getContentResolver().query(DataProvider.CITY_URI, DBOpenHelper.CITY_COLS,
                DBOpenHelper.CITY_STATE_ID + " = " + state.getStateID(), null, null );

            while(cursor.moveToNext()){
                int cityID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CITY_TABLE_ID));
                int stateID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CITY_STATE_ID));
                String cityName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CITY_NAME));
                String zipcode = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CITY_ZIPCODE));

                //Create new Campground and Add to Array list
                allStateCities.add(new City(cityID, stateID, cityName, zipcode));
            }
            cursor.close();
            return allStateCities;
        }catch (NullPointerException e){
            System.out.println("getAllStateCities method error: " + e.getMessage());
            return null;
        }
    }

    /**
     * GET ALL ADDRESSES IN A CITY
     * Get City Obj from cityName string
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for all objects in table
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param cityName search param
     * @return Return array list of objects
     */
    public static ArrayList<Address> getAllCityAddresses(Context context, String cityName, int stateID) throws NullPointerException {
        City city = getCity(context, cityName, stateID);

        ArrayList<Address> allCityAddresses = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(DataProvider.ADDRESS_URI, DBOpenHelper.ADDRESS_COLS,
                DBOpenHelper.ADDRESS_CITY_ID + " = " + city.getCityID(), null, null );

        while(cursor.moveToNext()){
            int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.ADDRESS_TABLE_ID));
            int cityID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.ADDRESS_CITY_ID));
            String addressLine1 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ADDRESS_LINE_1));
            String addressLine2 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ADDRESS_LINE_2));

            //Create new Campground and Add to Array list
            allCityAddresses.add(new Address(addressID, cityID, addressLine1, addressLine2));
        }
        cursor.close();

        return allCityAddresses;
    }

    /**
     * GET ALL CAMPGROUNDS
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for all objects in table
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @return Return array list of objects
     */
    public static ArrayList<Campground> getAllCampgrounds(Context context) throws NullPointerException {
        ArrayList<Campground> allCampgrounds = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_COLS,
                DBOpenHelper.CAMPGROUND_TABLE_ID + " > 0 ", null, null );

        while(cursor.moveToNext()){
            int cgID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_TABLE_ID));
            int accountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ACCOUNT_ID));
            int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ADDRESS_ID));
            String cgName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_NAME));
            String cgDesc = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_DESC));
            String cgEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_EMAIL));
            String cgPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_PHONE));
            String cgOpenTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_OPEN_TIME));
            String cgCloseTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_CLOSE_TIME));

        //Create new Campground and Add to Array list
            allCampgrounds.add(new Campground(cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime));
        }
        cursor.close();

        return allCampgrounds;
    }

    /**
     * LOOKUP ALL CAMPGROUNDS BY CAMPGROUND NAME
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for all objects in table
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param campgroundName search param
     * @return Return array list of objects
     */
    public static ArrayList<Campground> lookupCampgroundName(Context context, String campgroundName) throws NullPointerException {
        ArrayList<Campground> allCampgrounds = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_COLS,
                DBOpenHelper.CAMPGROUND_NAME + " = " + "\"" + campgroundName + "\"", null, null );

        while(cursor.moveToNext()){
            int cgID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_TABLE_ID));
            int accountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ACCOUNT_ID));
            int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ADDRESS_ID));
            String cgName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_NAME));
            String cgDesc = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_DESC));
            String cgEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_EMAIL));
            String cgPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_PHONE));
            String cgOpenTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_OPEN_TIME));
            String cgCloseTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_CLOSE_TIME));

            //Create new Campground and Add to Array list
            allCampgrounds.add(new Campground(cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime));
        }
        cursor.close();

        return allCampgrounds;
    }

    /**
     * LOOKUP ALL CAMPGROUNDS BY CAMPGROUND STATE
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for all objects in table
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param campgroundState search param
     * @return Return array list of objects
     */
    public static ArrayList<Campground> lookupCampgroundState(Context context, String campgroundState) throws NullPointerException {
        //Get State object from state name
        State state = getState(context, campgroundState);

        //Get all cities in state
        ArrayList<City> allCities = getAllStateCities(context, state.getStateName());

        //Get all addresses for all cities in state
        ArrayList<Address> allAddresses = new ArrayList<>();

        //Loop through allCities list and create list of all addresses in cities
        for(int i = 0; i < allCities.size(); i++) {
            allAddresses.addAll(getAllCityAddresses(context, allCities.get(i).getCityName(), state.getStateID()));
        }

        //Create list to hold all campgrounds
        ArrayList<Campground> allCampgrounds = new ArrayList<>();

        for(int j = 0; j < allAddresses.size(); j++ ){
            Cursor cursor = context.getContentResolver().query(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_COLS,
                    DBOpenHelper.CAMPGROUND_ADDRESS_ID + " = " + allAddresses.get(j).getAddressID(), null, null );

            while(cursor.moveToNext()){
                int cgID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_TABLE_ID));
                int accountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ACCOUNT_ID));
                int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ADDRESS_ID));
                String cgName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_NAME));
                String cgDesc = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_DESC));
                String cgEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_EMAIL));
                String cgPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_PHONE));
                String cgOpenTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_OPEN_TIME));
                String cgCloseTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_CLOSE_TIME));

                //Create new Campground and Add to Array list
                allCampgrounds.add(new Campground(cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime));
            }
            cursor.close();
        }



        return allCampgrounds;
    }

    /**
     * LOOKUP ALL CAMPGROUNDS BY CAMPGROUND CITY
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for all objects in table
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param campgroundCity search param
     * @return Return array list of objects
     */
    public static ArrayList<Campground> lookupCampgroundCity(Context context, String campgroundCity, int stateID) throws NullPointerException {

        //Get all addresses for all cities in state
        ArrayList<Address> allAddresses = getAllCityAddresses(context, campgroundCity, stateID);

        //Create list to hold all campgrounds
        ArrayList<Campground> allCampgrounds = new ArrayList<>();

        for(int j = 0; j < allAddresses.size(); j++ ){
            Cursor cursor = context.getContentResolver().query(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_COLS,
                    DBOpenHelper.CAMPGROUND_ADDRESS_ID + " = " + allAddresses.get(j).getAddressID(), null, null );

            while(cursor.moveToNext()){
                int cgID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_TABLE_ID));
                int accountID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ACCOUNT_ID));
                int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ADDRESS_ID));
                String cgName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_NAME));
                String cgDesc = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_DESC));
                String cgEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_EMAIL));
                String cgPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_PHONE));
                String cgOpenTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_OPEN_TIME));
                String cgCloseTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_CLOSE_TIME));

                //Create new Campground and Add to Array list
                allCampgrounds.add(new Campground(cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime));
            }
            cursor.close();
        }


        return allCampgrounds;
    }

    /**
     * GET MY CAMPGROUNDS
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for objects in table matching search params
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param accountID primary key ID used as search param
     * @return Return array list of objects
     */
    public static ArrayList<Campground> getMyCampgrounds(Context context, int accountID) throws NullPointerException {
        ArrayList<Campground> myCampgrounds = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_COLS,
                DBOpenHelper.CAMPGROUND_ACCOUNT_ID + " = " + accountID, null, null );

        while(cursor.moveToNext()){
            int cgID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_TABLE_ID));
            int addressID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_ADDRESS_ID));
            String cgName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_NAME));
            String cgDesc = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_DESC));
            String cgEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_EMAIL));
            String cgPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_PHONE));
            String cgOpenTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_OPEN_TIME));
            String cgCloseTime = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPGROUND_CLOSE_TIME));

            //Create new Campground and Add to Array list
            myCampgrounds.add(new Campground(cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime));
        }
        cursor.close();

        return myCampgrounds;
    }

    /**
     * GET ALL CAMPSITES
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for objects in table matching search params
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param cgID primary key ID used as search param
     * @return Return array list of objects
     */
    public static ArrayList<Campsite> getAllCampsites(Context context, int cgID) throws NullPointerException {
        ArrayList<Campsite> allCampsites = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(DataProvider.CAMPSITE_URI, DBOpenHelper.CAMPSITE_COLS,
                DBOpenHelper.CAMPSITE_CAMPGROUND_ID + " = " + cgID, null, null );

        while(cursor.moveToNext()){
            int csID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_TABLE_ID));
            String campsiteNum = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_SITE_NUM));
            int price = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_PRICE));
            int maxGuests = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CAMPSITE_MAX_GUESTS));

            //Create new Campground and Add to Array list
            allCampsites.add(new Campsite(csID, cgID, campsiteNum, price, maxGuests));
        }
        cursor.close();

        return allCampsites;
    }

    /**
     * GET ALL TRAVEL RESERVATIONS
     * Declare array list of corresponding data type to hold objects
     * Call DataProvider query method and get SQL data for objects in table matching search params
     * Loop the following
     * * Save row data to vars
     * * Call default constructor and pass vars
     * * Add object to array list
     * Return array list of objects
     * @param context context from calling activity
     * @param travelAccountID primary key ID used as search param
     * @return Return array list of objects
     */
    public static ArrayList<Reservations> getAllTravelReservations(Context context, int travelAccountID) throws NullPointerException {
        ArrayList<Reservations> allReservations = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(DataProvider.RESERVATIONS_URI, DBOpenHelper.RESERVATIONS_COLS,
                DBOpenHelper.RESERVATIONS_TRAVELER_ID + " = " + travelAccountID, null, null );

        while(cursor.moveToNext()){
            int resID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TABLE_ID));
            int csID = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_CAMPSITE_ID));
            String arrivalDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_ARRIVAL));
            String departureDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_DEPARTURE));
            String resName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_NAME));
            String resEmail = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_EMAIL));
            String resPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.RESERVATIONS_PHONE));

            //Create new Campground and Add to Array list
            allReservations.add(new Reservations(resID, csID, travelAccountID, arrivalDate, departureDate, resName, resEmail, resPhone));
        }
        cursor.close();

        return allReservations;
    }

    /**
     * GET ALL CAMPHOST RESERVATIONS
     * Create an array list of all campgrounds owned with the users accountID
     * Loop through the list of campgrounds owned by the user account and create a list of all campsites owned by the users account
     * Loop through the list of campsites owned by the user and create a list of all reservations associated with those campsites
     * Return array list of reservations
     * @param context context from calling activity
     * @param accountID primary key ID used as search param
     * @return Return array list of objects
     */
    public static ArrayList<Reservations> getAllCamphostReservations(Context context, int accountID) throws NullPointerException {
        //Get a list of all campgrounds owned by the accountID
        ArrayList<Campground> myCampgrounds = getMyCampgrounds(context, accountID);

        //Create an array list to hold all campsites owned by the accountID
        ArrayList<Campsite> myCampsites = new ArrayList<>();

        //Loop through all campgrounds owned by the accountID and add campsites to myCampsites ArrayList
        for(int i = 0; i < myCampgrounds.size(); i++) {

            myCampsites.addAll(getAllCampsites(context, myCampgrounds.get(i).getCgID()));

        }

        //Get reservations associated with campsites owned by the user account
        ArrayList<Reservations> allReservations = new ArrayList<>();

        //Loop through all reservations and add all reservations including a campsite owned by the accountID
        for(int i = 0; i < myCampsites.size(); i++){
            Cursor reservationCursor = context.getContentResolver().query(DataProvider.RESERVATIONS_URI, DBOpenHelper.RESERVATIONS_COLS,
                    DBOpenHelper.RESERVATIONS_CAMPSITE_ID + " = " + myCampsites.get(i).getCsID(), null, null );

            while(reservationCursor.moveToNext()){
                int resID = reservationCursor.getInt(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TABLE_ID));
                int travelerAccountID = reservationCursor.getInt(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TRAVELER_ID));
                String arrivalDate = reservationCursor.getString(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_ARRIVAL));
                String departureDate = reservationCursor.getString(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_DEPARTURE));
                String resName = reservationCursor.getString(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_NAME));
                String resEmail = reservationCursor.getString(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_EMAIL));
                String resPhone = reservationCursor.getString(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_PHONE));

                //Create new Campground and Add to Array list
                allReservations.add(new Reservations(resID, myCampsites.get(i).getCsID(), travelerAccountID, arrivalDate, departureDate, resName, resEmail, resPhone));
            }
            reservationCursor.close();
        }

        return allReservations;
    }



    /**
     * _______________________________________________________________________
     *                            DELETE POJO FROM DB
     * -----------------------------------------------------------------------
     */


    /**
     * DELETE ADDRESS FROM DB
     * Call DataProvider delete method and pass primary key of record to delete
     * @param context context from calling activity
     * @param addressID used in delete statement to identify record to delete
     * @return true/false delete successful
     */
    public static boolean deleteAddress(Context context, int addressID){
        context.getContentResolver().delete(DataProvider.ADDRESS_URI, DBOpenHelper.ADDRESS_TABLE_ID + " = " + addressID, null);
        return true;
    }

    /**
     * DELETE RESERVATION FROM DB
     * Call DataProvider delete method and pass primary key of record to delete
     * @param context context from calling activity
     * @param resID used in delete statement to identify record to delete
     * @return true/false delete successful
     */
    public static boolean deleteReservation(Context context, int resID){
        context.getContentResolver().delete(DataProvider.RESERVATIONS_URI, DBOpenHelper.RESERVATIONS_TABLE_ID + " = " + resID, null);
        return true;
    }

    /**
     * DELETE CAMPSITE FROM DB
     * Call Delete reservations method to delete all reservations associated with the campsite
     * Call DataProvider delete method and pass primary key of record to delete
     * @param context context from calling activity
     * @param csID used in delete statement to identify record to delete
     * @return true/false delete successful
     */
    public static boolean deleteCampsite(Context context, int csID){
        //delete all reservations associated with the campsite
        Cursor reservationCursor = context.getContentResolver().query(DataProvider.RESERVATIONS_URI, DBOpenHelper.RESERVATIONS_COLS,
                DBOpenHelper.RESERVATIONS_CAMPSITE_ID + " = " + csID, null, null);
        while(reservationCursor.moveToNext()){
            deleteReservation(context, reservationCursor.getInt(reservationCursor.getColumnIndex(DBOpenHelper.RESERVATIONS_TABLE_ID)));
        }
        reservationCursor.close();

        //Delete the campsite from campsite table
        context.getContentResolver().delete(DataProvider.CAMPSITE_URI, DBOpenHelper.CAMPSITE_TABLE_ID + " = " + csID, null);
        return true;
    }

    /**
     * DELETE CAMPGROUND FROM DB
     * Call deleteCampsite method to delete all campsites associated with the campground
     * Call DataProvider delete method and pass primary key of record to delete
     * @param context context from calling activity
     * @param cgID used in delete statement to identify record to delete
     * @return true/false delete successful
     */
    public static boolean deleteCampground(Context context, int cgID){
        //delete all campsites associated with the campground
        Cursor campsiteCursor = context.getContentResolver().query(DataProvider.CAMPSITE_URI, DBOpenHelper.CAMPSITE_COLS,
                DBOpenHelper.CAMPSITE_CAMPGROUND_ID + " = " + cgID, null, null);
        while(campsiteCursor.moveToNext()){
            deleteCampsite(context, campsiteCursor.getInt(campsiteCursor.getColumnIndex(DBOpenHelper.CAMPSITE_TABLE_ID)));
        }
        campsiteCursor.close();

        //Delete the campground from campground table
        context.getContentResolver().delete(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_TABLE_ID + " = " + cgID, null);
        return true;
    }

    /**
     * DELETE ACCOUNT FROM DB
     * Call deleteCampground method to delete all campgrounds associated with the account
     * Call DataProvider delete method and pass primary key of record to delete
     * @param context context from calling activity
     * @param accountID used in delete statement to identify record to delete
     * @return true/false delete successful
     */
    public static boolean deleteAccount(Context context, int accountID){
        //delete all campsites associated with the campground
        Cursor campgroundCursor = context.getContentResolver().query(DataProvider.CAMPGROUND_URI, DBOpenHelper.CAMPGROUND_COLS,
                DBOpenHelper.CAMPGROUND_ACCOUNT_ID + " = " + accountID, null, null);
        while(campgroundCursor.moveToNext()){
            deleteCampground(context, campgroundCursor.getInt(campgroundCursor.getColumnIndex(DBOpenHelper.CAMPGROUND_TABLE_ID)));
        }
        campgroundCursor.close();

        //Delete the campground from campground table
        context.getContentResolver().delete(DataProvider.ACCOUNT_URI, DBOpenHelper.ACCOUNT_TABLE_ID + " = " + accountID, null);
        return true;
    }

}
