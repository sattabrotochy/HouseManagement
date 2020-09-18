package com.Shuvo.myapplication.UpdateAllData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.MainActivity;
import com.Shuvo.myapplication.PostImageUploadActivity;
import com.Shuvo.myapplication.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditHouseActivity extends AppCompatActivity {


    int id;
    String EDIT_HUS, currentUserID;
    FirebaseAuth auth;
    CircleImageView house_image;
    TextView Hus_userName1;
    EditText hus_number, hus_userPrice, hus_userFloor, hus_userRoom, hus_userBathRoom;
    Button hus_post_update_Btn,hus_image_update_Btn;
    String url,url3;
    ProgressDialog progressDialog;
    ImageView image_house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_house);


        id = getIntent().getExtras().getInt("id");
        auth = FirebaseAuth.getInstance();
        currentUserID = auth.getCurrentUser().getUid();

        progressDialog=new ProgressDialog(this);

        house_image = findViewById(R.id.house_image);

        Hus_userName1 = findViewById(R.id.Hus_userName1);
        hus_number = findViewById(R.id.hus_number);
        hus_userPrice = findViewById(R.id.hus_userPrice);
        hus_userFloor = findViewById(R.id.hus_userFloor);
        hus_userRoom = findViewById(R.id.hus_userRoom);
        hus_userBathRoom = findViewById(R.id.hus_userBathRoom);
        hus_post_update_Btn = findViewById(R.id.hus_post_update_Btn);
        hus_image_update_Btn = findViewById(R.id.hus_image_update_Btn);
        image_house = findViewById(R.id.image_house);



        hus_post_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dataupdate();

            }
        });


        url="https://famousdb.000webhostapp.com/houseImageUpload.php";

        hus_image_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(EditHouseActivity.this, PostImageUploadActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("id",id);
                startActivity(intent);


            }
        });


        dataShow();

    }

    private void Dataupdate()
    {
        progressDialog.setMessage("wait.........");
        progressDialog.show();
        final  String phn_number,house_rnt_price,house_floor,house_room,house_bathrm ;

        phn_number=hus_number.getText().toString().trim();
        house_rnt_price=hus_userPrice.getText().toString().trim();
        house_floor=hus_userFloor.getText().toString().trim();
        house_room=hus_userRoom.getText().toString().trim();
        house_bathrm=hus_userBathRoom.getText().toString().trim();


        StringRequest request=new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/UpdateHouseData.php?id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(EditHouseActivity.this, "Data upload successful", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                startActivity(new Intent(EditHouseActivity.this, MainActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(EditHouseActivity.this, error.getMessage()+"data not upload", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> data=new HashMap<>();

                data.put("phn_number",phn_number);
                data.put("house_rnt_price",house_rnt_price);
                data.put("house_floor",house_floor);
                data.put("house_room",house_room);
                data.put("house_bathrm",house_bathrm);
                data.put("$house_image",url3);

                return data;

            }
        };

        MySingleton.getInstance(this).addToRequestQueue(request);

    }

    private void dataShow() {


        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                EDIT_HUS = shl;

                housePostShow();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showhouseRentMdtls.php?id=" + id);
                return shl;

            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();

    }

    private void housePostShow() {
        try {
            JSONObject jsonObject = new JSONObject(EDIT_HUS);
            JSONArray result = jsonObject.getJSONArray("outputMore");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
//                mdModelArrayList.add(new HouseMDModel(
//
//                        object.getString("name"),
//                        object.getString("phn_number"),
//                        object.getString("location"),
//                        object.getString("house_type"),
//                        object.getString("house_floor"),
//                        object.getString("house_rnt_type"),
//                        object.getString("house_rnt_price"),
//                        object.getString("house_room"),
//                        object.getString("house_bathrm"),
//                        object.getString("house_rnt_date"),
//                        object.getString("house_lv_date"),
//                        object.getString("house_gs_type"),
//                        object.getString("house_gs_bill"),
//                        object.getString("house_advc_fee"),
//                        object.getString("house_parking"),
//                        object.getString("house_lift")
//
//
//                ));

                Hus_userName1.setText(object.getString("name"));
                hus_number.setText(object.getString("phn_number"));
                hus_userPrice.setText(object.getString("house_rnt_price"));
                hus_userFloor.setText(object.getString("house_floor"));
                hus_userRoom.setText(object.getString("house_room"));
                hus_userBathRoom.setText(object.getString("house_bathrm"));


            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        postImageLoad();
        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserImage.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                String image = response;
                String url = "https://famousdb.000webhostapp.com/" + image;
                Picasso.get()
                        .load(url)
                        .into(house_image);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);

    }
        private void postImageLoad()
        {
            StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/houseImageReitrieve.php?id=" + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    String image2 = response;
                    url3 = "https://famousdb.000webhostapp.com/" + image2;
                    Picasso.get()
                            .load(url3)
                            .into(image_house);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            MySingleton.getInstance(this).addToRequestQueue(request);



        }


    }
