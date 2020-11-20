package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wgu.gigacamp.Controller.Adapter_Reservations;
import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Reservations;

import java.util.ArrayList;

public class MyReservationsActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    ArrayList<Reservations> allTravelReservations;
    ArrayList<Reservations> allHostReservations;
    Adapter_Reservations reservationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.myReservationsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        getIncomingIntent();

        //Set Activity RV for All Assessment View
        RecyclerView reservationsRecyclerView = findViewById(R.id.myReservationsRecView);
        reservationsRecyclerView.setLayoutManager(new LinearLayoutManager(MyReservationsActivity.this));

        //Get List of all Reservations
        if(userAccount != null){
        allTravelReservations = DataManager.getAllTravelReservations(MyReservationsActivity.this, userAccount.getAccountID());
        }

        //If list has campgrounds display build and display in recycler view
        if(allTravelReservations != null){
            reservationsAdapter = new Adapter_Reservations(MyReservationsActivity.this, allTravelReservations, userAccount);
            reservationsRecyclerView.setAdapter(reservationsAdapter);
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

    public void getIncomingIntent() {
        if(getIntent().hasExtra("accountID")){
            int accountID = getIntent().getIntExtra("accountID", 1);
            userAccount = DataManager.getAccount(MyReservationsActivity.this, accountID);
        }
    }

    public void backButton(View view) {
        finish();
    }

    public void searchReservations(View view) {
    }
}