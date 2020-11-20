package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wgu.gigacamp.Controller.DBOpenHelper;
import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Controller.DataProvider;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Address;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.Campsite;
import com.wgu.gigacamp.Model.City;
import com.wgu.gigacamp.Model.Reservations;
import com.wgu.gigacamp.Model.State;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declare vars to provide data access
    DBOpenHelper mainDBHelper;

    //Declare Form Vars
    EditText emailTxt;
    EditText passTxt;

    //Declare function vars
    Account userAccount;
    String email;
    String password;

    /**
     * ON CREATE - REQUIRED DEFAULT METHOD
     * When activity is launched preform the following actions
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Layout

        //Set Toolbar

        //Open or Create SQLite Database
        mainDBHelper = new DBOpenHelper(MainActivity.this);
        mainDBHelper.getWritableDatabase();
        mainDBHelper.createTable();
    }

    public void login(View view) {
        emailTxt = findViewById(R.id.loginEmailTxt);
        passTxt = findViewById(R.id.loginPasswordTxt);

        email = emailTxt.getText().toString();
        password = passTxt.getText().toString();
        Account accountLogin;

        try {
            accountLogin = DataManager.getAccount(MainActivity.this, email);

            if(accountLogin.getPassword().equals(password)){
                Intent intent = new Intent(MainActivity.this, AllCampgroundsActivity.class);
                intent.putExtra("accountID", accountLogin.getAccountID());
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "Invalid password. Please Try Again.", Toast.LENGTH_LONG).show();
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            Toast.makeText(MainActivity.this, "Invalid email address. Please try again.", Toast.LENGTH_LONG).show();
        }


    }

    public void signup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    //Create Test Data
        /*DataManager.insertAccount(MainActivity.this, "James", "Green", "jgre466@wgu.edu", "password", "555-555-5555");
        DataManager.insertAccount(MainActivity.this, "Jess", "Burgess", "jess@aol.com", "password", "555-555-5555");
        DataManager.insertAccount(MainActivity.this, "Corey", "Donada", "corey@aol.com", "password", "555-555-5555");

        DataManager.insertState(MainActivity.this, "Arizona");
        DataManager.insertState(MainActivity.this, "Texas");
        DataManager.insertState(MainActivity.this, "New York");

        DataManager.insertCity(MainActivity.this, 1, "Scottsdale", "85254");
        DataManager.insertCity(MainActivity.this, 1, "Phoenix", "85027");
        DataManager.insertCity(MainActivity.this, 2, "Houston", "77047");
        DataManager.insertCity(MainActivity.this, 2, "Austin", "77074");
        DataManager.insertCity(MainActivity.this, 3, "Buffalo", "12345");

        DataManager.insertAddress(MainActivity.this, 1, "14414 N 58th St", "NULL");
        DataManager.insertAddress(MainActivity.this, 1, "1536 W Rosemonte Dr", "NULL");
        DataManager.insertAddress(MainActivity.this, 2, "2850 E Sam Houston Pkwy", "NULL");
        DataManager.insertAddress(MainActivity.this, 2, "10650 SW Plaza Ct", "NULL");
        DataManager.insertAddress(MainActivity.this, 3, "123 Fake St", "NULL");

        DataManager.insertCampground(MainActivity.this, 1, 1, "James'Campground",
                "This is James' campground.", "contact@jamescampground.com", "555-666-7788", "8:00 AM", "7:00PM");
        DataManager.insertCampground(MainActivity.this, 1, 2, "Danger Noodle Campground",
                "This is a Dangerous campground.", "contact@dangnood.com", "555-666-7788", "6:00 AM", "7:00PM");
        DataManager.insertCampground(MainActivity.this, 1, 3, "Not Houston Campground",
                "This campground is not in houston.", "contact@nothouston.com", "555-666-7788", "7:00 AM", "7:00PM");
        DataManager.insertCampground(MainActivity.this, 2, 4, "Jess'Campground",
                "This is Jess' campground.", "contact@Jesscampground.com", "555-666-7788", "9:30 AM", "6:00PM");
        DataManager.insertCampground(MainActivity.this, 3, 5, "Corey'sCampground",
                "This is Corey's campground.", "contact@Coreyscampground.com", "555-666-7788", "9:00 AM", "9:00PM");

        DataManager.insertCampsite(MainActivity.this, 1, "C1", 25, 4);
        DataManager.insertCampsite(MainActivity.this, 1, "C2", 25, 4);
        DataManager.insertCampsite(MainActivity.this, 1, "C3", 25, 4);
        DataManager.insertCampsite(MainActivity.this, 2, "E1", 20, 4);
        DataManager.insertCampsite(MainActivity.this, 2, "E2", 20, 4);
        DataManager.insertCampsite(MainActivity.this, 2, "E3", 20, 4);
        DataManager.insertCampsite(MainActivity.this, 3, "D1", 30, 4);
        DataManager.insertCampsite(MainActivity.this, 3, "D2", 30, 4);
        DataManager.insertCampsite(MainActivity.this, 3, "D3", 30, 4);
        DataManager.insertCampsite(MainActivity.this, 4, "B1", 50, 4);
        DataManager.insertCampsite(MainActivity.this, 4, "B2", 50, 4);
        DataManager.insertCampsite(MainActivity.this, 4, "B3", 50, 4);
        DataManager.insertCampsite(MainActivity.this, 5, "A1", 35, 4);
        DataManager.insertCampsite(MainActivity.this, 5, "A2", 35, 4);
        DataManager.insertCampsite(MainActivity.this, 5, "A3", 35, 4);

        DataManager.insertReservation(MainActivity.this, 1, 2, "01/01/2020",
                "01/07/2020", "James", "111-222-3344", "james@aol.com");
        DataManager.insertReservation(MainActivity.this, 4, 3, "01/01/2020",
                "01/07/2020", "James", "111-222-3344", "james@aol.com");
        DataManager.insertReservation(MainActivity.this, 7, 1, "01/01/2020",
                "01/07/2020", "James", "111-222-3344", "james@aol.com");*/

    //Read Test Data
        /*Account testAccount = DataManager.getAccount(MainActivity.this, "jgre466@wgu.edu");
        Campground testCampground = DataManager.getCampground(MainActivity.this, 1);
        Campsite testCampsite = DataManager.getCampsite(MainActivity.this, 1);
        Address testAddress = DataManager.getAddress(MainActivity.this, 1);
        City testCity = DataManager.getCity(MainActivity.this, 1);
        City testCity1 = DataManager.getCity(MainActivity.this, "Houston");
        State testState = DataManager.getState(MainActivity.this, 1);
        State testState1 = DataManager.getState(MainActivity.this, "Texas");
        Reservations testRes = DataManager.getReservation(MainActivity.this, 1);
        Reservations testRes1 = DataManager.getReservationByCSID(MainActivity.this, 1);
        Reservations testRes2 = DataManager.getReservationByAccountID(MainActivity.this, 1);

        System.out.println(testAccount.getAccountID() + " " + testAccount.getEmail()  + " " + testAccount.getfName() + " " + testAccount.getlName()  + " " + testAccount.getPassword()  + " " + testAccount.getPhoneNum());
        System.out.println(testCampground.getCgID() + " " + testCampground.getAccountID() + " " + testCampground.getAddressID() + " " + testCampground.getCgName() + " " + testCampground.getCgDesc() + " " + testCampground.getCgEmail() + " " + testCampground.getCgPhone() + " " + testCampground.getCgOpenTime() + " " + testCampground.getCgCloseTime());
        System.out.println(testCampsite.getCsID() + " " + testCampsite.getCgID()  + " " + testCampsite.getCampsiteNum()  + " " + testCampsite.getPrice()  + " " + testCampsite.getMaxGuests());
        System.out.println(testAddress.getAddressID() + " " + testAddress.getCityID() + " " + testAddress.getAddressLine1() + " " + testAddress.getAddressLine2());
        System.out.println(testCity.getCityID() + " " + testCity.getStateID() + " " + testCity.getCityName() + " " + testCity.getZipcode());
        System.out.println(testCity1.getCityID() + " " + testCity1.getStateID() + " " + testCity1.getCityName() + " " + testCity1.getZipcode());
        System.out.println(testState.getStateID() + " " + testState.getStateName());
        System.out.println(testState1.getStateID() + " " + testState1.getStateName());
        System.out.println(testRes.getResID() + " " + testRes.getCsID() + " " + testRes.getTravelerAccountID() + " " + testRes.getArrivalDate() + " " + testRes.getDepartureDate() + " " + testRes.getResEmail() + " " + testRes.getResName() + " " + testRes.getResPhone());
        System.out.println(testRes1.getResID() + " " + testRes1.getCsID() + " " + testRes1.getTravelerAccountID() + " " + testRes1.getArrivalDate() + " " + testRes1.getDepartureDate() + " " + testRes1.getResEmail() + " " + testRes1.getResName() + " " + testRes1.getResPhone());
        System.out.println(testRes2.getResID() + " " + testRes2.getCsID() + " " + testRes2.getTravelerAccountID() + " " + testRes2.getArrivalDate() + " " + testRes2.getDepartureDate() + " " + testRes2.getResEmail() + " " + testRes2.getResName() + " " + testRes2.getResPhone());*/

    //Read Test Data (Arrays)
        /*ArrayList<City> allStateCities = DataManager.getAllStateCities(MainActivity.this, "Arizona");
        ArrayList<Address> allCityAddresses = DataManager.getAllCityAddresses(MainActivity.this, "Scottsdale");
        ArrayList<Campground> allCampgrounds = DataManager.getAllCampgrounds(MainActivity.this);
        ArrayList<Campground> allCampgroundName = DataManager.lookupCampgroundName(MainActivity.this, "James'Campground");
        ArrayList<Campground> allCampgroundState = DataManager.lookupCampgroundState(MainActivity.this, "New York");
        ArrayList<Campground> allCampgroundCity = DataManager.lookupCampgroundCity(MainActivity.this, "Houston");
        ArrayList<Campground> myCampgrounds = DataManager.getMyCampgrounds(MainActivity.this, 1);
        ArrayList<Campsite> allCampsites = DataManager.getAllCampsites(MainActivity.this, 2);
        ArrayList<Reservations> allTravelReservations = DataManager.getAllTravelReservations(MainActivity.this, 1);
        ArrayList<Reservations> allCamphostReservations = DataManager.getAllCamphostReservations(MainActivity.this, 1);

        //Print contents of AllStateCities List
        for(int i = 0; i < allStateCities.size(); i++){
            System.out.println("AllStateCities List: " + allStateCities.get(i).getCityID() + ", " + allStateCities.get(i).getStateID() + ", " + allStateCities.get(i).getCityName() + ", " + allStateCities.get(i).getZipcode());
        }
        //Print contents of allCityAddresses List
        for(int i = 0; i < allCityAddresses.size(); i++){
            System.out.println("AllCityAddresses List: " + allCityAddresses.get(i).getAddressID() + " " + allCityAddresses.get(i).getCityID() + " " + allCityAddresses.get(i).getAddressLine1() + " " + allCityAddresses.get(i).getAddressLine2());
        }
        //Print contents of allCampgrounds List
        for(int i = 0; i < allCampgrounds.size(); i++){
            System.out.println("allCampgrounds list: " + allCampgrounds.get(i).getCgID() + ", " + allCampgrounds.get(i).getAccountID() + ", " + allCampgrounds.get(i).getAddressID() + ", " + allCampgrounds.get(i).getCgName() + ", " + allCampgrounds.get(i).getCgDesc() + ", " + allCampgrounds.get(i).getCgPhone() + ", " + allCampgrounds.get(i).getCgEmail() + ", " + allCampgrounds.get(i).getCgOpenTime() + ", " + allCampgrounds.get(i).getCgCloseTime());
        }
        //Print contents of allCampgroundName List
        for(int i = 0; i < allCampgroundName.size(); i++){
            System.out.println("allCampgroundName list: " + allCampgroundName.get(i).getCgID() + ", " + allCampgroundName.get(i).getAccountID() + ", " + allCampgroundName.get(i).getAddressID() + ", " + allCampgroundName.get(i).getCgName() + ", " + allCampgroundName.get(i).getCgDesc() + ", " + allCampgroundName.get(i).getCgPhone() + ", " + allCampgroundName.get(i).getCgEmail() + ", " + allCampgroundName.get(i).getCgOpenTime() + ", " + allCampgroundName.get(i).getCgCloseTime());
        }
        //Print contents of allCampgroundState List
        for(int i = 0; i < allCampgroundState.size(); i++){
            System.out.println("allCampgroundState list: " + allCampgroundState.get(i).getCgID() + ", " + allCampgroundState.get(i).getAccountID() + ", " + allCampgroundState.get(i).getAddressID() + ", " + allCampgroundState.get(i).getCgName() + ", " + allCampgroundState.get(i).getCgDesc() + ", " + allCampgroundState.get(i).getCgPhone() + ", " + allCampgroundState.get(i).getCgEmail() + ", " + allCampgroundState.get(i).getCgOpenTime() + ", " + allCampgroundState.get(i).getCgCloseTime());
        }
        //Print contents of allCampgroundCity List
        for(int i = 0; i < allCampgroundCity.size(); i++){
            System.out.println("allCampgroundCity list: " + allCampgroundCity.get(i).getCgID() + ", " + allCampgroundCity.get(i).getAccountID() + ", " + allCampgroundCity.get(i).getAddressID() + ", " + allCampgroundCity.get(i).getCgName() + ", " + allCampgroundCity.get(i).getCgDesc() + ", " + allCampgroundCity.get(i).getCgPhone() + ", " + allCampgroundCity.get(i).getCgEmail() + ", " + allCampgroundCity.get(i).getCgOpenTime() + ", " + allCampgroundCity.get(i).getCgCloseTime());
        }
        //Print contents of myCampgrounds List
        for(int i = 0; i < myCampgrounds.size(); i++){
            System.out.println("myCampgrounds list: " + myCampgrounds.get(i).getCgID() + ", " + myCampgrounds.get(i).getAccountID() + ", " + myCampgrounds.get(i).getAddressID() + ", " + myCampgrounds.get(i).getCgName() + ", " + myCampgrounds.get(i).getCgDesc() + ", " + myCampgrounds.get(i).getCgPhone() + ", " + myCampgrounds.get(i).getCgEmail() + ", " + myCampgrounds.get(i).getCgOpenTime() + ", " + myCampgrounds.get(i).getCgCloseTime());
        }
        //Print contents of allCampsites List
        for(int i = 0; i < allCampsites.size(); i++){
            System.out.println("allCampsites List: " + allCampsites.get(i).getCsID() + ", " + allCampsites.get(i).getCgID() + ", " + allCampsites.get(i).getCampsiteNum() + ", " + allCampsites.get(i).getPrice() + ", " + allCampsites.get(i).getMaxGuests());
        }
        //Print contents of allTravelReservations List
        for(int i = 0; i < allTravelReservations.size(); i++){
            System.out.println("allTravelReservations List: " + allTravelReservations.get(i).getResID() + ", " + allTravelReservations.get(i).getCsID() + ", " + allTravelReservations.get(i).getTravelerAccountID() + ", " + allTravelReservations.get(i).getArrivalDate() + ", " + allTravelReservations.get(i).getDepartureDate() + ", " + allTravelReservations.get(i).getResName() + ", " + allTravelReservations.get(i).getResEmail() + ", " + allTravelReservations.get(i).getResPhone());
        }
        //Print contents of allCamphostReservations List
        for(int i = 0; i < allCamphostReservations.size(); i++){
            System.out.println("allCamphostReservations List: " + allCamphostReservations.get(i).getResID() + ", " + allCamphostReservations.get(i).getCsID() + ", " + allCamphostReservations.get(i).getTravelerAccountID() + ", " + allCamphostReservations.get(i).getArrivalDate() + ", " + allCamphostReservations.get(i).getDepartureDate() + ", " + allCamphostReservations.get(i).getResName() + ", " + allCamphostReservations.get(i).getResEmail() + ", " + allCamphostReservations.get(i).getResPhone());
        }*/

    //Update Test Data
        /*DataManager.updateAccount(MainActivity.this, 1, "Danger", "Noodle", "james@dangernoodle.com", "password", "555-555-5555");
        DataManager.updateCampground(MainActivity.this, 1, 1, 1, "Danger Noodle Campground", "DangNood Desc", "contact@dangnood.com", "555-555-5555", "8:00 AM", "7:00 AM");
        DataManager.updateCampsite(MainActivity.this, 1, 1, "D1", 35, 2);
        DataManager.updateAddress(MainActivity.this, 1, 2, "100 Wookie Ave", "NULL");
        DataManager.updateCity(MainActivity.this, 1, 1, "Phoenix", "85027");
        DataManager.updateState(MainActivity.this,1, "arizona");
        DataManager.updateReservation(MainActivity.this, 1, 1, 3, "1/1/2021", "1/7/2021", "Jess", "999-888-7766", "jess@aol.com");*/

    //Delete Test data
        /*DataManager.deleteAddress(MainActivity.this, 1);
        DataManager.deleteReservation(MainActivity.this, 1);
        DataManager.deleteCampsite(MainActivity.this, 1);
        DataManager.deleteCampground(MainActivity.this, 1);
        DataManager.deleteAccount(MainActivity.this, 1);*/

}