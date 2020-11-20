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
import android.widget.Toast;

import com.wgu.gigacamp.Controller.Adapter_Campgrounds;
import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Campground;

import java.util.ArrayList;

public class AllCampgroundsActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    ArrayList<Campground> allCampgroundList;
    Adapter_Campgrounds campgroundAdapter;

    /**
     * ON CREATE _ REQUIRED DEFAULT METHOD
     * When activity is launched execute the following code
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_campgrounds);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.allCampgroundsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        //Get incoming intent if applicable
        getIncomingIntent();

        //Set Activity RV for All Assessment View
        RecyclerView campgroundRecView = findViewById(R.id.allCampgroundsRecView);
        campgroundRecView.setLayoutManager(new LinearLayoutManager(AllCampgroundsActivity.this));

        //Get List of all campgrounds
        allCampgroundList = DataManager.getAllCampgrounds(AllCampgroundsActivity.this);

        //If list has campgrounds display build and display in recycler view
        if(allCampgroundList != null){
            campgroundAdapter = new Adapter_Campgrounds(AllCampgroundsActivity.this, allCampgroundList, userAccount);
            campgroundRecView.setAdapter(campgroundAdapter);
        }else{
            Toast.makeText(AllCampgroundsActivity.this, "No Campgrounds to display.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.allCampgroundsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        //Get incoming intent if applicable
        getIncomingIntent();

        //Set Activity RV for All Assessment View
        RecyclerView campgroundRecView = findViewById(R.id.allCampgroundsRecView);
        campgroundRecView.setLayoutManager(new LinearLayoutManager(AllCampgroundsActivity.this));

        //Get List of all campgrounds
        allCampgroundList = DataManager.getAllCampgrounds(AllCampgroundsActivity.this);

        //If list has campgrounds display build and display in recycler view
        if(allCampgroundList != null){
            campgroundAdapter = new Adapter_Campgrounds(AllCampgroundsActivity.this, allCampgroundList, userAccount);
            campgroundRecView.setAdapter(campgroundAdapter);
        }else{
            Toast.makeText(AllCampgroundsActivity.this, "No Campgrounds to display.", Toast.LENGTH_LONG).show();
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
            userAccount = DataManager.getAccount(AllCampgroundsActivity.this, accountID);
        }
    }

    public void backButton(View view) {
        finish();
    }

    public void searchCampgrounds(View view) {
    }
}