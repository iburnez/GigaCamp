package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Address;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.City;
import com.wgu.gigacamp.Model.State;

public class EditCampgroundActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    Campground selectedCampground;
    Address cgAddress;
    City cgCity;
    State cgState;

    //EditText Form Field Vars
    TextView editCampgroundHeader;
    EditText editCampgroundNameTxt;
    EditText editCampgroundEmailTxt;
    EditText editCampgroundPhoneTxt;
    EditText editCampgroundAddressLine1Txt;
    EditText editCampgroundAddressLine2Txt;
    EditText editCampgroundCityTxt;
    EditText editCampgroundStateTxt;
    EditText editCampgroundZipcodeTxt;
    EditText editCampgroundOpenTimeTxt;
    EditText editCampgroundCloseTimeTxt;
    EditText editCampgroundDescTimeTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_campground);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.editCampgroundToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        getIncomingIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();

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
            userAccount = DataManager.getAccount(EditCampgroundActivity.this, accountID);
        }

        if(getIntent().hasExtra("campgroundID")){
            int campgroundID = getIntent().getIntExtra("campgroundID", 1);
            selectedCampground = DataManager.getCampground(EditCampgroundActivity.this, campgroundID);
        }

        setContent(selectedCampground);
    }

    public void setContent(Campground selectedCampground){
        cgAddress = DataManager.getAddress(EditCampgroundActivity.this, selectedCampground.getAddressID());
        cgCity = DataManager.getCity(EditCampgroundActivity.this, cgAddress.getAddressID());
        cgState = DataManager.getState(EditCampgroundActivity.this, cgCity.getStateID());

        //Initialize form fields with corresponding activity
        editCampgroundHeader = findViewById(R.id.editCampgroundHeader);
        editCampgroundNameTxt = findViewById(R.id.editCampgroundNameTxt);
        editCampgroundEmailTxt = findViewById(R.id.editCampgroundEmailTxt);
        editCampgroundPhoneTxt = findViewById(R.id.editCampgroundPhoneTxt);
        editCampgroundAddressLine1Txt = findViewById(R.id.editCampgroundAddressLine1Txt);
        editCampgroundAddressLine2Txt = findViewById(R.id.editCampgroundAddressLine2Txt);
        editCampgroundCityTxt = findViewById(R.id.editCampgroundCityTxt);
        editCampgroundStateTxt = findViewById(R.id.editCampgroundStateTxt);
        editCampgroundZipcodeTxt = findViewById(R.id.editCampgroundZipcodeTxt);
        editCampgroundOpenTimeTxt = findViewById(R.id.editCampgroundOpenTimeTxt);
        editCampgroundCloseTimeTxt = findViewById(R.id.editCampgroundCloseTimeTxt);
        editCampgroundDescTimeTxt = findViewById(R.id.editCampgroundDescTimeTxt);

        //Set Text for form field vars
        editCampgroundHeader.setText(selectedCampground.getCgName());
        editCampgroundNameTxt.setText(selectedCampground.getCgName());
        editCampgroundEmailTxt.setText(selectedCampground.getCgEmail());
        editCampgroundPhoneTxt.setText(selectedCampground.getCgPhone());
        editCampgroundAddressLine1Txt.setText(cgAddress.getAddressLine1());
        editCampgroundCityTxt.setText(cgCity.getCityName());
        editCampgroundStateTxt.setText(cgState.getStateName());
        editCampgroundZipcodeTxt.setText(cgCity.getZipcode());
        editCampgroundOpenTimeTxt.setText(selectedCampground.getCgOpenTime());
        editCampgroundCloseTimeTxt.setText(selectedCampground.getCgCloseTime());
        editCampgroundDescTimeTxt.setText(selectedCampground.getCgDesc());

        //If AddressLine2 is not empty set contents otherwise leave blank.
        if(!(cgAddress.getAddressLine2().equalsIgnoreCase("null"))){
            editCampgroundAddressLine2Txt.setText(cgAddress.getAddressLine2());
        }
    }

    public void backButton(View view) {
        finish();
    }

    public void updateCampgroundBtn(View view) {
        //Vars to use to update SQL
        int cgID = selectedCampground.getCgID();
        int accountID = selectedCampground.getAccountID();
        int addressID = cgAddress.getAddressID();
        int cityID = cgCity.getCityID();
        int stateID = cgState.getStateID();
        String cgName = editCampgroundNameTxt.getText().toString();
        String cgDesc = editCampgroundDescTimeTxt.getText().toString();
        String cgEmail = editCampgroundEmailTxt.getText().toString();
        String cgPhone = editCampgroundPhoneTxt.getText().toString();
        String cgOpenTime = editCampgroundOpenTimeTxt.getText().toString();
        String cgCloseTime = editCampgroundCloseTimeTxt.getText().toString();
        String addressLine1 = editCampgroundAddressLine1Txt.getText().toString();
        String cityName = editCampgroundCityTxt.getText().toString();
        String zipcode = editCampgroundZipcodeTxt.getText().toString();
        String stateName = editCampgroundStateTxt.getText().toString();

        //Check if AddressLine2 is empty
        //If yes set addressLine2 to "NULL"
        String addressLine2;
        if(editCampgroundAddressLine2Txt.getText().toString().equals("")){
            addressLine2 = "NULL";
        }
        //If no set addressLine2 to getText value
        else{
            addressLine2 = editCampgroundAddressLine2Txt.getText().toString();
        }

        //Check all required fields have data if not display error
        if(validateCampground()){

            //Check if State has changed
                // If Yes - create a new address then Update Campground with new addressID
                // If No - Create new State in SQL and get state obj
            if(!(stateName.equalsIgnoreCase(cgState.getStateName()))){
                State newState;
                City newCity;

                //Check if stateName exists in SQLdb. If yes get state ID
                if(DataManager.getState(EditCampgroundActivity.this, stateName) != null){
                    newState = DataManager.getState(EditCampgroundActivity.this, stateName);
                }
                //If State Does not exist create a new state and get obj
                else{
                    DataManager.insertState(EditCampgroundActivity.this, stateName);
                    newState = DataManager.getState(EditCampgroundActivity.this, stateName);
                }

                //Check to see if city already exists in state
                //If yes - get existing city
                if(DataManager.getCity(EditCampgroundActivity.this, cityName, newState.getStateID()) != null){
                    newCity = DataManager.getCity(EditCampgroundActivity.this, cityName, newState.getStateID());
                }
                //If no - Create new City in SQL and get city obj
                else{
                    DataManager.insertCity(EditCampgroundActivity.this, newState.getStateID(), cityName, zipcode);
                    newCity = DataManager.getCity(EditCampgroundActivity.this, cityName, newState.getStateID());
                }

                //Create new Address in SQL and get Address obj
                DataManager.insertAddress(EditCampgroundActivity.this, newCity.getCityID(), addressLine1, addressLine2);
                Address newAddress = DataManager.getAddress(EditCampgroundActivity.this, addressLine1, newCity.getCityID());

                //Update Campground
                DataManager.updateCampground(EditCampgroundActivity.this, cgID, accountID, newAddress.getAddressID(), cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime);

                //Launch activity and pass intents
                Intent intent = new Intent(EditCampgroundActivity.this, DetailsMyCampgroundActivity.class);
                intent.putExtra("accountID", userAccount.getAccountID());
                intent.putExtra("campgroundID", selectedCampground.getCgID());
                finish();
                startActivity(intent);

            }

            // Check if City has changed but state is the same.
            // If yes create a new address then Update Campground with new addressID
            if(!(cityName.equalsIgnoreCase(cgCity.getCityName()))
                    && (stateName.equalsIgnoreCase(cgState.getStateName()))){
                City newCity;

                //Check to see if city already exists in state
                //If yes - get existing city
                if(DataManager.getCity(EditCampgroundActivity.this, cityName, stateID) != null){
                    newCity = DataManager.getCity(EditCampgroundActivity.this, cityName, stateID);
                }
                //If no - Create new City in SQL and get city obj
                else{
                    DataManager.insertCity(EditCampgroundActivity.this, stateID, cityName, zipcode);
                    newCity = DataManager.getCity(EditCampgroundActivity.this, cityName, stateID);
                }

                //Create new Address in SQL and get Address obj
                DataManager.insertAddress(EditCampgroundActivity.this, newCity.getCityID(), addressLine1, addressLine2);
                Address newAddress = DataManager.getAddress(EditCampgroundActivity.this, addressLine1, newCity.getCityID());

                //Update Campground
                DataManager.updateCampground(EditCampgroundActivity.this, cgID, accountID, newAddress.getAddressID(), cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime);

                //Launch activity and pass intents
                Intent intent = new Intent(EditCampgroundActivity.this, DetailsMyCampgroundActivity.class);
                intent.putExtra("accountID", userAccount.getAccountID());
                intent.putExtra("campgroundID", selectedCampground.getCgID());
                finish();
                startActivity(intent);


            }

            // Check if AddressLine1 or AddressLine2 has changed and city and state are the same.
            // If yes update the existing address then Update Campground
            if((!(addressLine1.equalsIgnoreCase(cgAddress.getAddressLine1()))
                    || !(addressLine2.equalsIgnoreCase(cgAddress.getAddressLine2())))
                    && (cityName.equalsIgnoreCase(cgCity.getCityName()))
                    && (stateName.equalsIgnoreCase(cgState.getStateName()))){

                //Update new Address in SQL and get Address obj
                DataManager.updateAddress(EditCampgroundActivity.this, addressID, cityID, addressLine1, addressLine2);

                //Update Campground
                DataManager.updateCampground(EditCampgroundActivity.this, cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime);

                //Launch activity and pass intents
                Intent intent = new Intent(EditCampgroundActivity.this, DetailsMyCampgroundActivity.class);
                intent.putExtra("accountID", userAccount.getAccountID());
                intent.putExtra("campgroundID", selectedCampground.getCgID());
                finish();
                startActivity(intent);
            }

            // Check if city, state and address are the same.
            // Update Campground
            if(stateName.equalsIgnoreCase(DataManager.getState(EditCampgroundActivity.this, stateID).getStateName()) &&
                    (addressLine1.equalsIgnoreCase(cgAddress.getAddressLine1())) && (cityName.equalsIgnoreCase(cgCity.getCityName()))
                    && (stateName.equalsIgnoreCase(cgState.getStateName()))){

                //Update Campground
                DataManager.updateCampground(EditCampgroundActivity.this, cgID, accountID, addressID, cgName, cgDesc, cgEmail, cgPhone, cgOpenTime, cgCloseTime);

                //Launch activity and pass intents
                Intent intent = new Intent(EditCampgroundActivity.this, DetailsMyCampgroundActivity.class);
                intent.putExtra("accountID", userAccount.getAccountID());
                intent.putExtra("campgroundID", selectedCampground.getCgID());
                finish();
                startActivity(intent);
            }
        }

    }

    public boolean validateCampground(){

        //Vars to use to update SQL
        int cgID = selectedCampground.getCgID();
        int accountID = selectedCampground.getAccountID();
        int addressID = cgAddress.getAddressID();
        int cityID = cgCity.getCityID();
        int stateID = cgState.getStateID();
        String cgName = editCampgroundNameTxt.getText().toString();
        String cgDesc = editCampgroundDescTimeTxt.getText().toString();
        String cgEmail = editCampgroundEmailTxt.getText().toString();
        String cgPhone = editCampgroundPhoneTxt.getText().toString();
        String cgOpenTime = editCampgroundOpenTimeTxt.getText().toString();
        String cgCloseTime = editCampgroundCloseTimeTxt.getText().toString();
        String addressLine1 = editCampgroundAddressLine1Txt.getText().toString();
        String addressLine2 = editCampgroundAddressLine2Txt.getText().toString();
        String cityName = editCampgroundCityTxt.getText().toString();
        String zipcode = editCampgroundStateTxt.getText().toString();
        String stateName = editCampgroundZipcodeTxt.getText().toString();

        //Validate all required fields have data, if no data display error message
        if((!(cgName.equalsIgnoreCase("")))){
            if((!(cgEmail.equalsIgnoreCase("")))){
                if((!(cgPhone.equalsIgnoreCase("")))){
                    if((!(addressLine1.equalsIgnoreCase("")))){
                        if((!(cityName.equalsIgnoreCase("")))){
                            if((!(stateName.equalsIgnoreCase("")))){
                                if((!(zipcode.equalsIgnoreCase("")))){
                                    if((!(cgOpenTime.equalsIgnoreCase("")))){
                                        if((!(cgCloseTime.equalsIgnoreCase("")))){
                                            if((!(cgDesc.equalsIgnoreCase("")))){

                                                return true;

                                            }
                                            else{
                                                AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                                                updateFailedBuilder.setTitle("Missing Information!");
                                                updateFailedBuilder.setMessage("Campground Description is a required field. Please enter a Campground Description.");
                                                updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                });
                                                AlertDialog updateFailedAlert = updateFailedBuilder.create();
                                                updateFailedAlert.show();
                                                return false;
                                            }
                                        }
                                        else{
                                            AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                                            updateFailedBuilder.setTitle("Missing Information!");
                                            updateFailedBuilder.setMessage("Campground Close Time is a required field. Please enter a Campground Close Time.");
                                            updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });
                                            AlertDialog updateFailedAlert = updateFailedBuilder.create();
                                            updateFailedAlert.show();
                                            return false;
                                        }
                                    }
                                    else{
                                        AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                                        updateFailedBuilder.setTitle("Missing Information!");
                                        updateFailedBuilder.setMessage("Campground Open Time is a required field. Please enter a Campground Open Time.");
                                        updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        AlertDialog updateFailedAlert = updateFailedBuilder.create();
                                        updateFailedAlert.show();
                                        return false;
                                    }
                                }
                                else{
                                    AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                                    updateFailedBuilder.setTitle("Missing Information!");
                                    updateFailedBuilder.setMessage("Campground Zipcode is a required field. Please enter a Campground Zipcode.");
                                    updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    AlertDialog updateFailedAlert = updateFailedBuilder.create();
                                    updateFailedAlert.show();
                                    return false;
                                }
                            }
                            else{
                                AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                                updateFailedBuilder.setTitle("Missing Information!");
                                updateFailedBuilder.setMessage("Campground State is a required field. Please enter a Campground State.");
                                updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                AlertDialog updateFailedAlert = updateFailedBuilder.create();
                                updateFailedAlert.show();
                                return false;
                            }
                        }
                        else{
                            AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                            updateFailedBuilder.setTitle("Missing Information!");
                            updateFailedBuilder.setMessage("Campground State is a required field. Please enter a Campground State.");
                            updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog updateFailedAlert = updateFailedBuilder.create();
                            updateFailedAlert.show();
                            return false;
                        }
                    }
                    else{
                        AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                        updateFailedBuilder.setTitle("Missing Information!");
                        updateFailedBuilder.setMessage("Campground Address Line 1 is a required field. Please enter a Campground Address Line 1.");
                        updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog updateFailedAlert = updateFailedBuilder.create();
                        updateFailedAlert.show();
                        return false;
                    }
                }
                else{
                    AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                    updateFailedBuilder.setTitle("Missing Information!");
                    updateFailedBuilder.setMessage("Campground Phone Number is a required field. Please enter a Campground Phone Number.");
                    updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog updateFailedAlert = updateFailedBuilder.create();
                    updateFailedAlert.show();
                    return false;
                }
            }
            else{
                AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                updateFailedBuilder.setTitle("Missing Information!");
                updateFailedBuilder.setMessage("Campground Email is a required field. Please enter a Campground Email.");
                updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog updateFailedAlert = updateFailedBuilder.create();
                updateFailedAlert.show();
                return false;
            }
        }
        else{
            AlertDialog.Builder updateFailedBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
            updateFailedBuilder.setTitle("Missing Information!");
            updateFailedBuilder.setMessage("Campground Name is a required field. Please enter a campground name.");
            updateFailedBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog updateFailedAlert = updateFailedBuilder.create();
            updateFailedAlert.show();
            return false;
        }
    }

    public void deleteCampgroundBtn(View view) {
        AlertDialog.Builder deleteCampgroundBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
        deleteCampgroundBuilder.setTitle("Are you sure you want to delete this campground? ");
        deleteCampgroundBuilder.setMessage("Deleting a campground will also delete all associated campsites and reservations. \n\n" +
                "This action is not reversible.");

        deleteCampgroundBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataManager.deleteCampground(EditCampgroundActivity.this, selectedCampground.getCgID());

                AlertDialog.Builder deleteCampgroundSuccessBuilder = new AlertDialog.Builder(EditCampgroundActivity.this);
                deleteCampgroundSuccessBuilder.setTitle("Campground Deleted");
                deleteCampgroundSuccessBuilder.setMessage("Your campground and all associated campsites and reservations have been deleted.");

                deleteCampgroundSuccessBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(EditCampgroundActivity.this, MyCampgroundsActivity.class);
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
}