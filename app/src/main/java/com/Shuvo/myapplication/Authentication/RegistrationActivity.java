package com.Shuvo.myapplication.Authentication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {

    String TAG = "RegistrationActivity";
    TextView textView;
    Context context = RegistrationActivity.this;
    Button userRegistrationBtn;
    Bitmap bitmap;
    Uri selectedImage;
    String image;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String name, email, password, address, profession, currentUserID, number;
    String id;
    private CircleImageView userProfileImage;
    private EditText userName, userEmail, userPassword, userAddress, userProfession, userPhn_number, IdUser;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textView = findViewById(R.id.signInActivity);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userAddress = findViewById(R.id.userAddress);
        userProfession = findViewById(R.id.userProfession);
        userRegistrationBtn = findViewById(R.id.userRegistrationBtn);
        userProfileImage = findViewById(R.id.userProfileImage);
        userPhn_number = findViewById(R.id.userPhn_number);

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("Image");
        db = FirebaseFirestore.getInstance();


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignInActivity();
            }
        });

        userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(RegistrationActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                                permissionToken.continuePermissionRequest();
                            }
                        }).check();


            }
        });

        userRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationSystem();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImage = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
                userProfileImage.setImageBitmap(bitmap);
                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageByte = stream.toByteArray();
        image = android.util.Base64.encodeToString(imageByte, Base64.DEFAULT);

        Toast.makeText(context, image, Toast.LENGTH_SHORT).show();

    }

    private void registrationSystem() {
        name = userName.getText().toString().trim();
        Log.d(TAG, "registrationSystem: " + name);
        email = userEmail.getText().toString().trim();
        password = userPassword.getText().toString().trim();
        address = userAddress.getText().toString().trim();
        profession = userProfession.getText().toString().trim();
        number = userPhn_number.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);

        if (name.isEmpty()) {
            userName.setError("Enter your name");
        }
        if (email.isEmpty()) {
            userEmail.setError("Enter your email");

        }
        if (password.isEmpty()) {
            userPassword.setError("Enter your password");
        }
        if (address.isEmpty()) {
            userAddress.setError("Enter your address");
        }
        if (profession.isEmpty()) {
            userProfession.setError("Enter your profession");
        }
        if (number.isEmpty()) {
            userPhn_number.setError("Enter your number");
        } else {
            if (selectedImage != null) {

                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUserID = mAuth.getCurrentUser().getUid();

                            if (currentUserID.equals("")) {
                                Toast.makeText(context, "Please try again.", Toast.LENGTH_SHORT).show();
                            } else {
                                sendDataToServer();
                                imageUpload();

                                Log.d(TAG, "onComplete: " + id);
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(context, "Select your image", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void imageUpload()
    {


        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/imageUpload.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> data=new HashMap<>();


                data.put("image",image);


                return data;
            }
        };


        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    private void sendDataToServer() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        Toast.makeText(context, "Data created to firebase.", Toast.LENGTH_SHORT).show();


        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/createuser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "registration successful", Toast.LENGTH_SHORT).show();
                        gotoSignInActivity();

                        Log.d(TAG, "onResponse: " + currentUserID);
                        progressDialog.dismiss();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> params = new HashMap<String, String>();


                Log.d(TAG, "getParams: " + currentUserID);
                params.put("firebase_id", currentUserID);
                params.put("name", name);
                params.put("username", email);
                params.put("password", password);
                params.put("address", address);
                params.put("phn_number", number);
                params.put("profession", profession);
                // params.put("image",image);
                return params;
            }
        };


        MySingleton.getInstance(this).addToRequestQueue(request);


    }

    private void gotoSignInActivity() {
        Intent intent = new Intent(context, SIngInActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideRight(this);
        finish();
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
}


