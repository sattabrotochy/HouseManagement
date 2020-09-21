package com.Shuvo.myapplication.UpdateAllData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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


public class EditHusLandActivity extends AppCompatActivity {


    String HUS_LAND_EDIT, currentUserID;
    EditText hus_land_userAddress, hus_land_number;
    TextView hus_Land_userName1;
    Button hus_land_post_update_Btn,hus_land_image_update_Btn;
    CircleImageView hulannEd_iamge;
    FirebaseAuth auth;
    int id;
    String url,url3,active_inactive;
    ProgressDialog progressDialog;
    ImageView image_husLan;
    CheckBox hus_lan_active,hus_lan_inactive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hus_land);


        id = getIntent().getExtras().getInt("id");

        auth = FirebaseAuth.getInstance();
        currentUserID = auth.getCurrentUser().getUid();
        progressDialog=new ProgressDialog(this);
        
        
        hus_Land_userName1 = findViewById(R.id.hus_Land_userName1);
        hus_land_userAddress = findViewById(R.id.hus_land_userAddress);
        hus_land_number = findViewById(R.id.hus_land_number);
        hus_land_post_update_Btn = findViewById(R.id.hus_land_post_update_Btn);
        hulannEd_iamge = findViewById(R.id.hulannEd_iamge);
        hus_land_image_update_Btn = findViewById(R.id.hus_land_image_update_Btn);
        image_husLan = findViewById(R.id.image_husLan);
        hus_lan_active = findViewById(R.id.hus_lan_active);
        hus_lan_inactive = findViewById(R.id.hus_lan_inactive);

        hus_lan_active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (hus_lan_active.isChecked())
                {
                    active_inactive="active";
                    Toast.makeText(EditHusLandActivity.this, "post is active click update ", Toast.LENGTH_SHORT).show();

                }
                
            }
        });
        hus_lan_inactive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (hus_lan_inactive.isChecked())
                {
                    active_inactive="inactive";
                    Toast.makeText(EditHusLandActivity.this, "post is inactive click update", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        

        dataLoad();
        url="https://famousdb.000webhostapp.com/huslndimageUpload.php";

        hus_land_image_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(EditHusLandActivity.this, PostImageUploadActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("id",id);
                startActivity(intent);



            }
        });

        hus_land_post_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataEdit();
            }
        });
    }

    private void dataEdit() {

        progressDialog.setMessage("Wait............");
        progressDialog.show();
        final String phn_number, houLnd_Price;

        phn_number = hus_land_number.getText().toString();
        houLnd_Price = hus_land_userAddress.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/updateHusLandData.php?id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditHusLandActivity.this, "data Upload successful", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                startActivity(new Intent(EditHusLandActivity.this, MainActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditHusLandActivity.this, error.getMessage() + "Data not Upload", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> data = new HashMap<>();
                data.put("phn_number", phn_number);
                data.put("houLnd_Price", houLnd_Price);
                data.put("husLnd_image", url3);
                data.put("active_ckeck", active_inactive);
                return data;

            }
        };


        MySingleton.getInstance(this).addToRequestQueue(request);

    }

    private void dataLoad() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                HUS_LAND_EDIT = shl;
                progressDialog.dismiss();
                huslandMDtShow();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showhuslndMDetls.php?id=" + id);
                return shl;

            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();

    }

    private void huslandMDtShow() {

        try {
            JSONObject jsonObject = new JSONObject(HUS_LAND_EDIT);
            JSONArray result = jsonObject.getJSONArray("outputMore2");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
//                husLndMDModelArrayList.add(new HusLndMDModel(
//
//                        object.getString("name"),
//                        object.getString("phn_number"),
//                        object.getString("address"),
//                        object.getString("houlan_type"),
//                        object.getString("house_floor"),
//                        object.getString("houlan_qunty"),
//                        object.getString("houLnd_Price"),
//                        object.getString("houlan_document"),
//                        object.getString("houLan_taxClr"),
//                        object.getString("houlan_parking"),
//                        object.getString("active_inactive")
//
//
//                ));

                hus_Land_userName1.setText(object.getString("name"));
                hus_land_number.setText(object.getString("phn_number"));
                hus_land_userAddress.setText(object.getString("houLnd_Price"));

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
                        .into(hulannEd_iamge);


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
        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/huslanImageRetrieve.php?id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                String image2 = response;
                url3 = "https://famousdb.000webhostapp.com/" + image2;
                Picasso.get()
                        .load(url3)
                        .into(image_husLan);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);



    }
}