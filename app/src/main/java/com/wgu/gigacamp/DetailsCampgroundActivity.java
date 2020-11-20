package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wgu.gigacamp.Controller.Adapter_Campsite;
import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Address;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.Campsite;
import com.wgu.gigacamp.Model.City;
import com.wgu.gigacamp.Model.State;

import java.util.ArrayList;

public class DetailsCampgroundActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    Campground selectedCampground;
    ArrayList<Campsite> cgCampsites;
    Adapter_Campsite campsiteAdapter;

    //Campground Info Vars
    TextView detailCampgroundName;
    TextView detailCampgroundOpenTime;
    TextView detailCampgroundCloseTime;
    TextView detailCampgroundPhone;
    TextView detailCampgroundEmail;
    TextView detailCampgroundAddressLine1;
    TextView detailCampgroundAddressLine2;
    TextView detailCampgroundCity;
    TextView detailCampgroundState;
    TextView detailCampgroundZipcode;
    TextView detailCampgroundDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_campground);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.detailCampgroundToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        getIncomingIntent();


        //Create Recycler View for Campsites
        RecyclerView campsiteRecView = findViewById(R.id.detailCampgroundCampsiteRecyclerView);
        campsiteRecView.setLayoutManager(new LinearLayoutManager(DetailsCampgroundActivity.this));

        //Build List of all campsites
        cgCampsites = DataManager.getAllCampsites(DetailsCampgroundActivity.this, selectedCampground.getCgID());

        //If cgCampsites list not null build RV View
        if(cgCampsites != null){
            campsiteAdapter = new Adapter_Campsite(DetailsCampgroundActivity.this, cgCampsites, userAccount);
            campsiteRecView.setAdapter(campsiteAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.main_menu_allCampgrounds:
                Intent allCampgroundsIntent = new Intent(this, AllCampgroundsActivity.class);
                allCampgroundsIntent.putExtra("accountID", userAccount.getAccountID());
                startActivity(allCampgroundsIntent);
                return true;
            case R.id.main_menu_myCampgrounds:
                Intent myCampgroundIntent = new Intent(this, MyCampgroundsActivity.class);
                myCampgroundIntent.putExtra("accountID", userAccount.getAccountID());
                startActivity(myCampgroundIntent);
                return true;
            case R.id.main_menu_myReservations:
                Intent myReservationIntent = new Intent(this, MyReservationsActivity.class);
                myReservationIntent.putExtra("accountID", userAccount.getAccountID());
                startActivity(myReservationIntent);
                return true;
            case R.id.main_menu_myAccount:
                Intent myAccountIntent= new Intent(this, MyAccountActivity.class);
                myAccountIntent.putExtra("accountID", userAccount.getAccountID());
                startActivity(myAccountIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void getIncomingIntent(){
        if(getIntent().hasExtra("accountID")){
            int accountID = getIntent().getIntExtra("accountID", 1);
            userAccount = DataManager.getAccount(DetailsCampgroundActivity.this, accountID);
        }
        if(getIntent().hasExtra("campgroundID")){
            int campgroundID = getIntent().getIntExtra("campgroundID", 1);
            selectedCampground = DataManager.getCampground(DetailsCampgroundActivity.this, campgroundID);

            setContents(selectedCampground);
        }
    }

    public void setContents(Campground selectedCampground){
        Address cgAddress = DataManager.getAddress(DetailsCampgroundActivity.this, selectedCampground.getAddressID());
        City cgCity = DataManager.getCity(DetailsCampgroundActivity.this, cgAddress.getCityID());
        State cgState = DataManager.getState(DetailsCampgroundActivity.this, cgCity.getStateID());

        //Initialize Text View Vars
        detailCampgroundName = findViewById(R.id.detailCampgroundName);
        detailCampgroundOpenTime = findViewById(R.id.detailCampgroundOpenTime);
        detailCampgroundCloseTime = findViewById(R.id.detailCampgroundCloseTime);
        detailCampgroundPhone = findViewById(R.id.detailCampgroundPhone);
        detailCampgroundEmail = findViewById(R.id.detailCampgroundEmail);
        detailCampgroundAddressLine1 = findViewById(R.id.detailCampgroundAddressLine1);
        detailCampgroundAddressLine2 = findViewById(R.id.detailCampgroundAddressLine2);
        detailCampgroundCity = findViewById(R.id.detailCampgroundCity);
        detailCampgroundState = findViewById(R.id.detailCampgroundState);
        detailCampgroundZipcode = findViewById(R.id.detailCampgroundZipcode);
        detailCampgroundDesc = findViewById(R.id.detailCampgroundDesc);

        //Set Textview text to display appropriate campground info
        detailCampgroundName.setText(selectedCampground.getCgName());
        detailCampgroundOpenTime.setText(selectedCampground.getCgOpenTime());
        detailCampgroundCloseTime.setText(selectedCampground.getCgCloseTime());
        detailCampgroundPhone.setText(selectedCampground.getCgPhone());
        detailCampgroundEmail.setText(selectedCampground.getCgEmail());
        detailCampgroundAddressLine1.setText(cgAddress.getAddressLine1());
        detailCampgroundAddressLine2.setText(cgAddress.getAddressLine2());
        detailCampgroundCity.setText(cgCity.getCityName());
        detailCampgroundState.setText(cgState.getStateName());
        detailCampgroundZipcode.setText(cgCity.getZipcode());
        detailCampgroundDesc.setText(selectedCampground.getCgDesc());

        //If addresssLine2 is empty hide the field
        if(cgAddress.getAddressLine2().equalsIgnoreCase("null")){
        detailCampgroundAddressLine2.setVisibility(View.INVISIBLE);
        detailCampgroundAddressLine2.setHeight(0);
        }



    }

    public void backButton(View view) {
        finish();
    }
}