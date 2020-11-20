package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wgu.gigacamp.Controller.Adapter_MyCampsite;
import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Address;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.Campsite;
import com.wgu.gigacamp.Model.City;
import com.wgu.gigacamp.Model.State;

import java.util.ArrayList;

public class DetailsMyCampgroundActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    Campground selectedCampground;
    ArrayList<Campsite> myCampsitesList;
    Adapter_MyCampsite myCampsiteAdapter;
    Address cgAddress;
    City cgCity;
    State cgState;

    //Vars for Campground Info
    TextView detailMyCampgroundName;
    TextView detailMyCampgroundOpenTime;
    TextView detailMyCampgroundCloseTime;
    TextView detailMyCampgroundPhone;
    TextView detailMyCampgroundEmail;
    TextView detailMyCampgroundAddressLine1;
    TextView detailMyCampgroundAddressLine2;
    TextView detailMyCampgroundCity;
    TextView detailMyCampgroundState;
    TextView detailMyCampgroundZipcode;
    TextView detailMyCampgroundDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_my_campground);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.detailMyCampgroundToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        getIncomingIntent();

        //Create RecyclerView
        RecyclerView myCampgroundRecView = findViewById(R.id.detailMyCampgroundCampsiteRecyclerView);
        myCampgroundRecView.setLayoutManager(new LinearLayoutManager(DetailsMyCampgroundActivity.this));

        //Get list of all campsites
        myCampsitesList = DataManager.getAllCampsites(DetailsMyCampgroundActivity.this, selectedCampground.getCgID());

        //If campsites Exist display in RecView
        if(myCampsitesList != null){
            myCampsiteAdapter = new Adapter_MyCampsite(DetailsMyCampgroundActivity.this, myCampsitesList, userAccount);
            myCampgroundRecView.setAdapter(myCampsiteAdapter);
        }else{
            Toast.makeText(DetailsMyCampgroundActivity.this, "No Campsites to display. Click Add Campsites to create campsites.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        getIncomingIntent();

        //Create RecyclerView
        RecyclerView myCampgroundRecView = findViewById(R.id.detailMyCampgroundCampsiteRecyclerView);
        myCampgroundRecView.setLayoutManager(new LinearLayoutManager(DetailsMyCampgroundActivity.this));

        //Get list of all campsites
        myCampsitesList = DataManager.getAllCampsites(DetailsMyCampgroundActivity.this, selectedCampground.getCgID());

        //If campsites Exist display in RecView
        if(myCampsitesList != null){
            myCampsiteAdapter = new Adapter_MyCampsite(DetailsMyCampgroundActivity.this, myCampsitesList, userAccount);
            myCampgroundRecView.setAdapter(myCampsiteAdapter);
        }else{
            Toast.makeText(DetailsMyCampgroundActivity.this, "No Campsites to display. Click Add Campsites to create campsites.", Toast.LENGTH_LONG).show();
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
            userAccount = DataManager.getAccount(DetailsMyCampgroundActivity.this, accountID);
        }

        if(getIntent().hasExtra("campgroundID")){
            int campgroundID = getIntent().getIntExtra("campgroundID", 1);
            selectedCampground = DataManager.getCampground(DetailsMyCampgroundActivity.this, campgroundID);

            setContents(selectedCampground);
        }
    }

    public void setContents(Campground selectedCampground) {
        //Get campground address city and state
        cgAddress = DataManager.getAddress(DetailsMyCampgroundActivity.this, selectedCampground.getAddressID());
        cgCity = DataManager.getCity(DetailsMyCampgroundActivity.this, cgAddress.getCityID());
        cgState = DataManager.getState(DetailsMyCampgroundActivity.this, cgCity.getStateID());

        //Initialize campground TextView vars with associated activity
        detailMyCampgroundName = findViewById(R.id.detailMyCampgroundName);
        detailMyCampgroundOpenTime = findViewById(R.id.detailMyCampgroundOpenTime);
        detailMyCampgroundCloseTime = findViewById(R.id.detailMyCampgroundCloseTime);
        detailMyCampgroundPhone = findViewById(R.id.detailMyCampgroundPhone);
        detailMyCampgroundEmail = findViewById(R.id.detailMyCampgroundEmail);
        detailMyCampgroundAddressLine1 = findViewById(R.id.detailMyCampgroundAddressLine1);
        detailMyCampgroundAddressLine2 = findViewById(R.id.detailMyCampgroundAddressLine2);
        detailMyCampgroundCity = findViewById(R.id.detailMyCampgroundCity);
        detailMyCampgroundState = findViewById(R.id.detailMyCampgroundState);
        detailMyCampgroundZipcode = findViewById(R.id.detailMyCampgroundZipcode);
        detailMyCampgroundDesc = findViewById(R.id.detailMyCampgroundDesc);

        //Set text to display appropriate campground info TextViews
        detailMyCampgroundName.setText(selectedCampground.getCgName());
        detailMyCampgroundOpenTime.setText(selectedCampground.getCgOpenTime());
        detailMyCampgroundCloseTime.setText(selectedCampground.getCgCloseTime());
        detailMyCampgroundPhone.setText(selectedCampground.getCgPhone());
        detailMyCampgroundEmail.setText(selectedCampground.getCgEmail());
        detailMyCampgroundAddressLine1.setText(cgAddress.getAddressLine1());
        detailMyCampgroundAddressLine2.setText(cgAddress.getAddressLine2());
        detailMyCampgroundCity.setText(cgCity.getCityName());
        detailMyCampgroundState.setText(cgState.getStateName());
        detailMyCampgroundZipcode.setText(cgCity.getZipcode());
        detailMyCampgroundDesc.setText(selectedCampground.getCgDesc());

        //If AddressLine2 is empty hide the line
        if(cgAddress.getAddressLine2().equalsIgnoreCase("null")){
            detailMyCampgroundAddressLine2.setVisibility(View.INVISIBLE);
            detailMyCampgroundAddressLine2.setHeight(0);
        }
        else{
            detailMyCampgroundAddressLine2.setVisibility(View.VISIBLE);
        }

    }

    public void addCampsites(View view) {
        Intent intent = new Intent(DetailsMyCampgroundActivity.this, AddCampsitesActivity.class);
        intent.putExtra("accountID", userAccount.getAccountID());
        intent.putExtra("campgroundID", selectedCampground.getCgID());
        startActivity(intent);
    }

    public void editCampground(View view) {
        Intent intent = new Intent(DetailsMyCampgroundActivity.this, EditCampgroundActivity.class);
        intent.putExtra("accountID", userAccount.getAccountID());
        intent.putExtra("campgroundID", selectedCampground.getCgID());
        startActivity(intent);
    }

    public void deleteCampground(View view) {
        AlertDialog.Builder deleteCampgroundBuilder = new AlertDialog.Builder(DetailsMyCampgroundActivity.this);
        deleteCampgroundBuilder.setTitle("Are you sure you want to delete this campground? ");
        deleteCampgroundBuilder.setMessage("Deleting a campground will also delete all associated campsites and reservations. \n\n" +
                "This action is not reversible.");

        deleteCampgroundBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataManager.deleteCampground(DetailsMyCampgroundActivity.this, selectedCampground.getCgID());

                AlertDialog.Builder deleteCampgroundSuccessBuilder = new AlertDialog.Builder(DetailsMyCampgroundActivity.this);
                deleteCampgroundSuccessBuilder.setTitle("Campground Deleted");
                deleteCampgroundSuccessBuilder.setMessage("Your campground and all associated campsites and reservations have been deleted.");

                deleteCampgroundSuccessBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DetailsMyCampgroundActivity.this, MyCampgroundsActivity.class);
                        intent.putExtra("accountID", userAccount.getAccountID());
                        finish();
                        startActivity(intent);
                    }
                });
                AlertDialog deleteSuccessAlert = deleteCampgroundSuccessBuilder.create();
                deleteSuccessAlert.show();
            }
        });

        deleteCampgroundBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog deleteCampgroundAlert = deleteCampgroundBuilder.create();
        deleteCampgroundAlert.show();

    }

    public void backButton(View view) {
        finish();
    }
}