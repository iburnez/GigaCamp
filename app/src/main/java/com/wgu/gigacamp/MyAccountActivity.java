package com.wgu.gigacamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wgu.gigacamp.Controller.DataManager;
import com.wgu.gigacamp.Model.Account;

public class MyAccountActivity extends AppCompatActivity {

    //Declare Reusable Vars
    Account userAccount;
    EditText myAccountEmailTxt;
    EditText myAccountPasswordTxt;
    EditText myAccountFNameTxt;
    EditText myAccountLNameTxt;
    EditText myAccountPhoneTxt;

    String accountEmail;
    String accountPass;
    String accountFName;
    String accountLName;
    String accountPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        //Set Toolbar & Remove App Name from toolbar
        Toolbar toolbar = findViewById(R.id.myAccountToolbar);
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
            userAccount = DataManager.getAccount(MyAccountActivity.this, accountID);

            setContents(userAccount);
        }
    }

    public void setContents(Account account){

        myAccountEmailTxt = findViewById(R.id.myAccountEmailTxt);
        myAccountPasswordTxt = findViewById(R.id.myAccountPasswordTxt);
        myAccountFNameTxt = findViewById(R.id.myAccountFNameTxt);
        myAccountLNameTxt = findViewById(R.id.myAccountLNameTxt);
        myAccountPhoneTxt = findViewById(R.id.myAccountPhoneTxt);

        myAccountEmailTxt.setText(account.getEmail());
        myAccountPasswordTxt.setText(account.getPassword());
        myAccountFNameTxt.setText(account.getfName());
        myAccountLNameTxt.setText(account.getlName());
        myAccountPhoneTxt.setText(account.getPhoneNum());

    }

    public void backButton(View view) {
        finish();
    }

    public void updateAccountBtn(View view){

        //Set string vars with contents of text fields
        accountEmail = myAccountEmailTxt.getText().toString();
        accountPass = myAccountPasswordTxt.getText().toString();
        accountFName = myAccountFNameTxt.getText().toString();
        accountLName = myAccountLNameTxt.getText().toString();
        accountPhone = myAccountPhoneTxt.getText().toString();

        if((accountEmail != null) && (accountPass != null)){

            //Pass String vars to DataManager to update account info
            DataManager.updateAccount(MyAccountActivity.this, userAccount.getAccountID(), accountFName, accountLName, accountEmail, accountPass, accountPhone);

            Intent intent = new Intent(MyAccountActivity.this, AllCampgroundsActivity.class);
            intent.putExtra("accountID", userAccount.getAccountID());
            startActivity(intent);
        }
    }

    public void deleteAccountBtn(View view) {

        //Display Confirmation Dialog to User prior to delete action
        AlertDialog.Builder deleteAccountBuilder = new AlertDialog.Builder(this);
        deleteAccountBuilder.setTitle("Are you sure you want to delete your account?");
        deleteAccountBuilder.setMessage("Deleting your account will delete all campgrounds, campsites, and reservations" +
                "you have made. " + "\n\nThis action is not reversible." );

        deleteAccountBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataManager.deleteAccount(MyAccountActivity.this, userAccount.getAccountID());
                        Intent intent = new Intent(MyAccountActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
        deleteAccountBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog deleteAccountAlert = deleteAccountBuilder.create();
        deleteAccountAlert.show();

    }
}