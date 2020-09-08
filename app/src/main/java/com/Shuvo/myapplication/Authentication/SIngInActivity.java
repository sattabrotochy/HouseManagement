package com.Shuvo.myapplication.Authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.Shuvo.myapplication.MainActivity;
import com.Shuvo.myapplication.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SIngInActivity extends AppCompatActivity {


    Context context = SIngInActivity.this;

    String TAG = "SIngInActivity";
    TextView textView;
    String url = "https://famousdb.000webhostapp.com/currentUserId.php";
    String url2;
    Button singInBtn;
    String uEmail, uPassword, currentUserID;
    EditText UserEmail, UserPass;
    FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_ing_in);

        // url2="https://famousdb.000webhostapp.com/currentUserId.php?firebase_id=\" + currentUserID;

        textView = findViewById(R.id.registration);
        singInBtn = findViewById(R.id.singInBtn);
        UserEmail = findViewById(R.id.UserEmail);
        UserPass = findViewById(R.id.UserPass);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegistrationActivity();
            }
        });
        singInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoMainActivity();
            }
        });
    }

    private void gotoMainActivity() {


//
////                            Intent intent=new Intent(context, MainActivity.class);
////                            startActivity(intent);
////                            Animatoo.animateSlideLeft(context);

        progressDialog.setMessage("Please wait.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        uEmail = UserEmail.getText().toString().trim();
        uPassword = UserPass.getText().toString().trim();


        if (uEmail.isEmpty()) {
            UserEmail.setError("Enter your email");
            progressDialog.dismiss();
            return;
        }
        if (uPassword.isEmpty()) {
            UserPass.setError("Enter your password");
            progressDialog.dismiss();
        } else {
            mAuth.signInWithEmailAndPassword(uEmail, uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();

                        currentUserID = mAuth.getCurrentUser().getUid();
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        Animatoo.animateSlideLeft(context);
                    } else {
                        Toast.makeText(context, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });

        }


    }


    private void gotoRegistrationActivity() {
        Intent intent = new Intent(context, RegistrationActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(context);

    }
}