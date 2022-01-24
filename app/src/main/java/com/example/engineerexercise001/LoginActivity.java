package com.example.engineerexercise001;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    private EditText et_Email;
    private EditText et_Password;
    EditText et_Confirm_Password;
    ImageButton btn_Back_Main_Activity;
    Button btn_Create_Account;
    LinearLayout error_Email_message;
    LinearLayout msg_password;
    private boolean emailValid;
    private boolean passwordValid;
    private boolean passwordsMatch;
    private ArrayList<String> emails_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ImageButton button = findViewById(R.id.btn_back_main_activity);
        initViews();
        emails_list = new ArrayList<String>();
        button.setOnClickListener(v -> {
            // your handler code here
            Intent moveToActivity = new Intent();
            moveToActivity.setClass(LoginActivity.this,
                    MainActivity.class);
            startActivity(moveToActivity);
        });


        et_Email.setOnFocusChangeListener((view, b) -> {
            if(!b){
                String myString = et_Email.getText().toString();
                if(isValidEmail(myString)) {
                    if(emails_list.contains(myString)){
                        error_Email_message.setVisibility(View.VISIBLE);
                    }else{
                        error_Email_message.setVisibility(View.GONE);
                        emailValid = true;
                        et_Email.setForeground(LoginActivity.this.getDrawable(R.drawable.editview_border_green));
                        et_Email.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.tick,0);
                    }
                }else{
                    emailValid = false;
                    et_Email.setForeground(LoginActivity.this.getDrawable(R.drawable.editview_border_red));
                    et_Email.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.cross,0);
                }
                createAccountButtonChecking();
            }
        });

        et_Password.setOnFocusChangeListener((view, b) -> {
            if(!b){
                String myString = et_Password.getText().toString();
                if(isValidPassword(myString)) {
                    passwordValid = true;
                    et_Password.setForeground(LoginActivity.this.getDrawable(R.drawable.editview_border_green));
                    et_Password.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.tick,0);
                }else{
                    passwordValid = false;
                    et_Password.setForeground(LoginActivity.this.getDrawable(R.drawable.editview_border_red));
                    et_Password.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.cross,0);
                }
                createAccountButtonChecking();
            }
        });

        et_Confirm_Password.setOnFocusChangeListener((view, b) -> {
            if(!b){
                String myString = et_Confirm_Password.getText().toString();
                if(!myString.isEmpty() && et_Password.getText().toString().equals(myString)) {
                    passwordsMatch = true;
                    et_Confirm_Password.setForeground(LoginActivity.this.getDrawable(R.drawable.editview_border_green));
                    et_Confirm_Password.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.tick,0);
                    msg_password.setVisibility(View.GONE);
                }else{
                    passwordsMatch = false;
                    et_Confirm_Password.setForeground(LoginActivity.this.getDrawable(R.drawable.editview_border_red));
                    et_Confirm_Password.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.cross,0);
                    msg_password.setVisibility(View.VISIBLE);
                }
                createAccountButtonChecking();
            }
        });

        btn_Create_Account.setOnClickListener(view -> {
            emails_list.add(et_Email.getText().toString());
            Toast.makeText(LoginActivity.this, "Account created", Toast.LENGTH_SHORT).show();
        });
    }

    private void initViews() {
        emailValid = false;
        passwordValid = false;
        passwordsMatch = false;
        et_Email = findViewById(R.id.et_email);
        msg_password = findViewById(R.id.et_invalid_password);
        et_Password = findViewById(R.id.et_password);
        et_Confirm_Password = findViewById(R.id.et_confirm_password);
        btn_Back_Main_Activity = findViewById(R.id.btn_back_main_activity);
        btn_Create_Account = findViewById(R.id.btn_create_account);
        error_Email_message = findViewById(R.id.et_email_already_exist);
    }

    private boolean isValidEmail(String myString) {
        return (!myString.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(myString).matches());
    }

    private boolean isValidPassword(String myString) {
        Pattern pattern = Pattern.compile(passwordRegex);
        return (!TextUtils.isEmpty(myString) && pattern.matcher(myString).matches());
    }

    private void createAccountButtonChecking(){
        if (emailValid && passwordValid && passwordsMatch){
            btn_Create_Account.setClickable(true);
            btn_Create_Account.setAlpha(1);
        }else{
            btn_Create_Account.setClickable(false);
            btn_Create_Account.setAlpha(0.5f);
        }

    }




}
