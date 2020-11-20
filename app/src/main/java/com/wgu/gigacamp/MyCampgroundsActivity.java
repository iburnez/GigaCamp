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
import com.wgu.gigacamp.Controller.Adapter_Campsite;
import com.wgu.gigacamp.Controller.Adapter_MyCampgrounds;
import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.Campsite;

import java.util.ArrayList;

public class MyCampgroundsActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    ArrayList<Campground> myCampgroundsList;
    Adapter_MyCampgrounds myCampgroundsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_campgrounds);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.myCampgroundsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        getIncomingIntent();

        //Set Recycler View
        RecyclerView myCampgroundRecView = findViewById(R.id.myCampgroundsRecView);
        myCampgroundRecView.setLayoutManager(new LinearLayoutManager(MyCampgroundsActivity.this));

        //Get List of all account campgrounds
        myCampgroundsList = DataManager.getMyCampgrounds(MyCampgroundsActivity.this, userAccount.getAccountID());

        //If myCampgroundsList is not null display campgrounds in RecView
        if(myCampgroundsList != null){
            myCampgroundsAdapter = new Adapter_MyCampgrounds(MyCampgroundsActivity.this, myCampgroundsList, userAccount);
            myCampgroundRecView.setAdapter(myCampgroundsAdapter);
        }else{
            Toast.makeText(MyCampgroundsActivity.this, "No campgrounds to display. Try createing a campground and become a camphost!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        getIncomingIntent();

        //Set Recycler View
        RecyclerView myCampgroundRecView = findViewById(R.id.myCampgroundsRecView);
        myCampgroundRecView.setLayoutManager(new LinearLayoutManager(MyCampgroundsActivity.this));

        //Get List of all account campgrounds
        myCampgroundsList = DataManager.getMyCampgrounds(MyCampgroundsActivity.this, userAccount.getAccountID());

        //If myCampgroundsList is not null display campgrounds in RecView
        if(myCampgroundsList != null){
            myCampgroundsAdapter = new Adapter_MyCampgrounds(MyCampgroundsActivity.this, myCampgroundsList, userAccount);
            myCampgroundRecView.setAdapter(myCampgroundsAdapter);
        }else{
            Toast.makeText(MyCampgroundsActivity.this, "No campgrounds to display. Try createing a campground and become a camphost!", Toast.LENGTH_LONG).show();
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
            userAccount = DataManager.getAccount(MyCampgroundsActivity.this, accountID);
        }
    }

    public void backButton(View view) {
        finish();
    }

    public void createNewCampground(View view) {
        Intent intent = new Intent(MyCampgroundsActivity.this, AddCampgroundActivity.class);
        startActivity(intent);
    }
}