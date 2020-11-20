package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;

public class AddCampgroundActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_campground);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.addCampgroundToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        //Get Incoming Intent
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
            userAccount = DataManager.getAccount(AddCampgroundActivity.this, accountID);
        }
    }

    public void backButton(View view) {
        finish();
    }

    public void addCampgroundStep2(View view) {
    }
}