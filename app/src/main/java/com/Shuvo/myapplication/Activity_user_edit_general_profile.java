package com.Shuvo.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.ProfileAdapter;
import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.ProfileModel;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class Activity_user_edit_general_profile extends AppCompatActivity {


    FirebaseAuth auth;
    String currentUserID, image;

    CircleImageView circleImageView;
    TextView prof_Name, prof_address;
    Context context = Activity_user_edit_general_profile.this;

    ProfileAdapter profileAdapter;
    ProgressDialog progressDialog;
    LinearLayoutManager layoutManager;
    String PROFILE_DATA, name, address;
    ArrayList<ProfileModel> profileModelArrayList;

    RecyclerView profileShow;
    ImageButton search_btn,change_image;
    CircleImageView ProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_general_profile);


        auth = FirebaseAuth.getInstance();
        //profileShow = findViewById(R.id.profileShow);

        currentUserID = auth.getCurrentUser().getUid();
        progressDialog = new ProgressDialog(this);


        prof_Name = findViewById(R.id.prof_Name);
        change_image = findViewById(R.id.change_image);

        prof_address = findViewById(R.id.prof_address);
        search_btn = findViewById(R.id.search_btn);
        ProfileImage = findViewById(R.id.ProfileImage);

        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_user_edit_general_profile.this,ImageUploadActivity.class));
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_user_edit_general_profile.this, SearchActivity.class));

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        progressDialog.setMessage("wait..........");
        progressDialog.show();
        dataLoad();
        nameLoad();
        imageLoad();


    }

    private void imageLoad() {


        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserImage.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                image = response;
                String url = "https://famousdb.000webhostapp.com/" + image;
                Picasso.get()
                        .load(url)
                        .into(ProfileImage);

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);

    }

    private void nameLoad() {
        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserId.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                prof_Name.setText(response);

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);

    }

    private void dataLoad() {
        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserAddress.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                prof_address.setText(response);
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);

    }

}

