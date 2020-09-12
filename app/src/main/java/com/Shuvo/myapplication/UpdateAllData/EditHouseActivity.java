package com.Shuvo.myapplication.UpdateAllData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditHouseActivity extends AppCompatActivity {


    int id;
    String EDIT_HUS, currentUserID;
    FirebaseAuth auth;
    CircleImageView house_image;
    TextView Hus_userName1;
    EditText hus_number, hus_userPrice, hus_userFloor, hus_userRoom, hus_userBathRoom;
    Button hus_post_update_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_house);


        id = getIntent().getExtras().getInt("id");
        auth = FirebaseAuth.getInstance();
        currentUserID = auth.getCurrentUser().getUid();


        house_image = findViewById(R.id.house_image);

        Hus_userName1 = findViewById(R.id.Hus_userName1);
        hus_number = findViewById(R.id.hus_number);
        hus_userPrice = findViewById(R.id.hus_userPrice);
        hus_userFloor = findViewById(R.id.hus_userFloor);
        hus_userRoom = findViewById(R.id.hus_userRoom);
        hus_userBathRoom = findViewById(R.id.hus_userBathRoom);

        hus_post_update_Btn = findViewById(R.id.hus_post_update_Btn);


        hus_post_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dataupdate();

            }
        });


        dataShow();

    }

    private void Dataupdate() {


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

        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserImage.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                String  image = response;
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
}