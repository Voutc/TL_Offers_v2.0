package com.example.kamran.logingreentheme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signup extends AppCompatActivity implements View.OnClickListener
{
    EditText mail,mophone,pswd,usrusr;
    TextView lin;
    Button sup;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        sup = (Button) findViewById(R.id.sup); //Είσοδος με μορφή textview αντί button
        lin = (TextView) findViewById(R.id.lin); //Εάν έχεις ήδη λογαριασμό
        usrusr = (EditText) findViewById(R.id.usrusr); //Username
        pswd = (EditText) findViewById(R.id.pswrdd); //Password
        mail = (EditText) findViewById(R.id.mail); //Email
        mophone = (EditText) findViewById(R.id.mobphone); //Telephone

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        mophone.setTypeface(custom_font);
        sup.setTypeface(custom_font1);
        pswd.setTypeface(custom_font);
        lin.setTypeface(custom_font);
        usrusr.setTypeface(custom_font);
        mail.setTypeface(custom_font);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(signup.this,login.class);
                startActivity(it);
            }
        });



    }
    private void registerUser()
    {
        String email = mail.getText().toString().trim();
        String password = pswd.getText().toString().trim();

        if(email.isEmpty())
        {
            mail.setError("Email is required");
            mail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mail.setError("Invaild Email");
            mail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            pswd.setError("Password is required");
            pswd.requestFocus();
            return;
        }
        if(password.length()<5){
            pswd.setError("Minimum length of password is 5 numbers");
            pswd.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Register Succesfull",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signup.this,login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registerd",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View view) {

    }
}
