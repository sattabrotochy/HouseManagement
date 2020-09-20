package com.Shuvo.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.Shuvo.myapplication.Adapter.MainPageAdapter;
import com.Shuvo.myapplication.Class.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {


    TabLayout main_tab_layout;
    ViewPager main_Viewpager;

    CircleImageView profile_image_user;
    MainPageAdapter adapter;
    FirebaseAuth auth;
    String currentUserID,image;
    TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        profile_image_user = findViewById(R.id.profile_image_user);
        main_tab_layout = findViewById(R.id.main_tab_layout);
        main_Viewpager = findViewById(R.id.main_Viewpager);
        userName = findViewById(R.id.MainUsername);

        auth=FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();
        Toast.makeText(this, currentUserID, Toast.LENGTH_SHORT).show();

        adapter = new MainPageAdapter(getSupportFragmentManager());
        main_Viewpager.setAdapter(adapter);
        main_tab_layout.setupWithViewPager(main_Viewpager);

        nameLoad();
        imageLoad();

        profile_image_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_user_edit_general_profile.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

       AlertDialog.Builder builder=new AlertDialog.Builder(this);
       builder.setMessage("Are you sure You want to exit");
       builder.setCancelable(false);
       builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i)
           {
             
               finish();

           }
       });
       builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i)
           {
               Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();


           }
       });
       AlertDialog alertDialog=builder.create();
       alertDialog.show();

//        super.onBackPressed();
//
//
//        finish();


    }
    private void imageLoad() {


        StringRequest request1 = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserImage.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                image = response;
                Log.d(TAG, "onResponse: " + image);
                String url = "https://famousdb.000webhostapp.com/" + image;
                Picasso.get()
                        .load(url)
                        .into(profile_image_user);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request1);

    }

    private void nameLoad() {
        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserId.php?firebase_id="+currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                userName.setText(response);

                Log.d(TAG, "onResponse222: "+userName);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);

    }


}