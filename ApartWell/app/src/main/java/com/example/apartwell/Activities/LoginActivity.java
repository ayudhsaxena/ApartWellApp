package com.example.apartwell.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.User;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static Snackbar signUpSnackBar,logoutSnackBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        String s = "Account created successfully!";
        String s2 = "Logged out successfully!";
        CoordinatorLayout layout = findViewById(R.id.loginCoordinatorLayout);
        signUpSnackBar = Snackbar.make(layout,s, BaseTransientBottomBar.LENGTH_LONG);
        logoutSnackBar = Snackbar.make(layout,s2, BaseTransientBottomBar.LENGTH_LONG);
        if( intent.getStringExtra("from") != null && intent.getStringExtra("from").equals("signUp")){
            signUpSnackBar.show();
        }
        else if( intent.getStringExtra("from") != null && intent.getStringExtra("from").equals("logout")){
            logoutSnackBar.show();
        }
    }

    public void userLogin(final View view){
        EditText unameText =  findViewById(R.id.unameTextId);
        EditText pwdText =  findViewById(R.id.pwdTextId);
        final TextView errorText = findViewById(R.id.errorTextView);
        errorText.setText(null);
        String uname = unameText.getText().toString();
        final String pwd = pwdText.getText().toString();

        DatabaseHelper db = DatabaseHelper.getInstance(this);
        List<User> ul = db.getUser(uname);

        if(ul.isEmpty()){
            errorText.setText(R.string.missingUsername);
        }
        else{
            User u = ul.get(0);
            UserHomePage.myUser = u;
            if(u.getPassword().equals(pwd)){
                Intent intent = new Intent(view.getContext(),UserHomePage.class);
                intent.putExtra("from","loginPage");
                startActivity(intent);
            }
            else{
                errorText.setText(R.string.incorrectPassword);
            }
        }
    }

    public void initiateSignUp(View view){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

}