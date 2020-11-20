package com.wgu.gigacamp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.wgu.gigacamp.R;
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

public class SignupActivity extends AppCompatActivity {

    //Declare Reusable Vars
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
        setContentView(R.layout.activity_signup);

        myAccountEmailTxt = findViewById(R.id.signupEmailTxt);
        myAccountPasswordTxt = findViewById(R.id.signupPasswordTxt);
        myAccountFNameTxt = findViewById(R.id.signupFNameTxt);
        myAccountLNameTxt = findViewById(R.id.signupLNameTxt);
        myAccountPhoneTxt = findViewById(R.id.signupPhoneTxt);
    }

    public void signup(View view) {

        //Set string vars with contents of text fields

        accountEmail = myAccountEmailTxt.getText().toString();
        accountPass = myAccountPasswordTxt.getText().toString();
        accountFName = myAccountFNameTxt.getText().toString();
        accountLName = myAccountLNameTxt.getText().toString();
        accountPhone = myAccountPhoneTxt.getText().toString();

        if((!(accountEmail.equals(""))) && (!(accountPass.equals("")))) {

            //Pass String vars to DataManager to update account info
            if(DataManager.insertAccount(SignupActivity.this, accountFName, accountLName, accountEmail, accountPass, accountPhone) != null){

            //Display success message and direct user to login

                AlertDialog.Builder accountSuccessBuilder = new AlertDialog.Builder(this);
                accountSuccessBuilder.setTitle("Account Created");
                accountSuccessBuilder.setMessage("Your account has been created successfully. \n\n" +
                        "Please login.");

                accountSuccessBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Launch Login Screen
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                });

                AlertDialog accountSucces = accountSuccessBuilder.create();
                accountSucces.show();
            }
        }else{
        //If either user name or password is empty display an error message.
            AlertDialog.Builder accountFailedBuilder = new AlertDialog.Builder(this);
            accountFailedBuilder.setTitle("Missing Required Information.");
            accountFailedBuilder.setMessage("The following fields are required: \n\n" +
                    "Email address \n" +
                    "Password\n\n");

            accountFailedBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog accountFailed = accountFailedBuilder.create();
            accountFailed.show();
        }
    }

    public void backButton(View view) {
        finish();
    }
}