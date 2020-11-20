package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.Campsite;

public class DetailsCampsiteActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    Campsite selectedCampsite;

    //Campsite Textview Vars
    TextView detailCampsiteCGName;
    TextView detailCampsiteNumberTxt;
    TextView detailCampsiteMaxGuestsTimeTxt;
    TextView detailCampsitePriceTimeTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_campsite);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.detailCampsiteToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        getIncomingIntent();
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
            userAccount = DataManager.getAccount(DetailsCampsiteActivity.this, accountID);
            System.out.println("Campsite account details: " + userAccount.getEmail());
        }
        if(getIntent().hasExtra("campsiteID")){
            int campsiteID = getIntent().getIntExtra("campsiteID", 1);
            selectedCampsite = DataManager.getCampsite(DetailsCampsiteActivity.this, campsiteID);

            setContents(selectedCampsite);

        }
    }

    public void setContents(Campsite selectedCampsite){
        //Get Campground for campsite
        Campground csCampground = DataManager.getCampground(DetailsCampsiteActivity.this, selectedCampsite.getCgID());

        //Initialize text view vars with corresponding view in activity
        detailCampsiteCGName = findViewById(R.id.detailCampsiteCGName);
        detailCampsiteNumberTxt = findViewById(R.id.detailCampsiteNumberTxt);
        detailCampsiteMaxGuestsTimeTxt = findViewById(R.id.detailCampsiteMaxGuestsTimeTxt);
        detailCampsitePriceTimeTxt = findViewById(R.id.detailCampsitePriceTimeTxt);

        detailCampsiteCGName.setText(csCampground.getCgName());
        detailCampsiteNumberTxt.setText(selectedCampsite.getCampsiteNum());
        detailCampsiteMaxGuestsTimeTxt.setText(Integer.toString(selectedCampsite.getMaxGuests()));
        detailCampsitePriceTimeTxt.setText(Integer.toString(selectedCampsite.getPrice()));
    }

    public void makeReservation(View view) {
    }

    public void backButton(View view) {
        finish();
    }
}