package com.Shuvo.myapplication.UpdateAllData;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.EditLandApadter;
import com.Shuvo.myapplication.Class.LandMrDtSWModel;
import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.MainActivity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditlandPostActivity extends AppCompatActivity {


    public static ArrayList<LandMrDtSWModel> mrDtSWModelArrayList;
    int id;
    TextView Land_userName, land_number, land_userAddress, land_userqnty, land_userPrice, land_user_per_Unit_price, land_userMontlyPayDate, land_userLevDate, land_userAdnFee;
    Button land_post_update_Btn;
    String name;
    String TAG = "EditlandPostActivity";
    Context context = EditlandPostActivity.this;
    FirebaseAuth auth;
    String DATA_EDIT_LAND, currentUserID;
    LinearLayoutManager layoutManager;
    EditLandApadter editLandApadter;
    RecyclerView landEditItem;
    CircleImageView landEd_userImage;
    ProgressDialog progressbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editland_post);


        id = getIntent().getIntExtra("id", 0);
        Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();


        landEditItem = findViewById(R.id.landEditItem);
        mrDtSWModelArrayList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        currentUserID = auth.getCurrentUser().getUid();


        progressbar2 = new ProgressDialog(this);
        progressbar2.setMessage("please Wait..........");
        progressbar2.setCanceledOnTouchOutside(false);


        Land_userName = findViewById(R.id.Land_userName1);
        landEd_userImage = findViewById(R.id.landEd_userImage);
        land_userAddress = findViewById(R.id.land_userAddress);
        land_number = findViewById(R.id.land_number);
        land_userqnty = findViewById(R.id.land_userqnty1);
        land_userPrice = findViewById(R.id.land_userPrice1);
        land_user_per_Unit_price = findViewById(R.id.land_user_per_Unit_price1);
        land_userMontlyPayDate = findViewById(R.id.land_userMontlyPayDate1);
        land_userLevDate = findViewById(R.id.land_userLevDate1);
        land_userAdnFee = findViewById(R.id.land_userAdnFee1);
        land_post_update_Btn = findViewById(R.id.land_post_update_Btn);


        dataSHow();


        land_post_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataUpdate();
            }
        });
    }

    private void dataUpdate() {
        progressbar2.setMessage("please Wait..........");
        progressbar2.show();
        progressbar2.setCanceledOnTouchOutside(false);
        final String phn_number = land_number.getText().toString().trim();
        final String land_price = land_userPrice.getText().toString().trim();
        final String per_qty_price = land_user_per_Unit_price.getText().toString().trim();
        final String land_py_Date = land_userMontlyPayDate.getText().toString().trim();
        final String mini_leave_date = land_userLevDate.getText().toString().trim();
        final String mini_adv_date = land_userAdnFee.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/Updatelanddata.php?id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Data Upload successful", Toast.LENGTH_SHORT).show();
                progressbar2.dismiss();
                startActivity(new Intent(EditlandPostActivity.this, MainActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage().toString() + "data not upload", Toast.LENGTH_SHORT).show();
                progressbar2.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("phn_number", phn_number);
                params.put("land_price", land_price);
                params.put("per_qty_price", per_qty_price);
                params.put("land_py_Date", land_py_Date);
                params.put("mini_leave_date", mini_leave_date);
                params.put("mini_adv_date", mini_adv_date);


                return params;
            }
        };


        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    private void dataSHow() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();

            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                DATA_EDIT_LAND = shl;
               // Toast.makeText(EditlandPostActivity.this, DATA_EDIT_LAND, Toast.LENGTH_SHORT).show();
                progressbar2.dismiss();
                DataLoad();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showlandPostMoreDetails.php?id=" + id);
                return shl;

            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();

    }

    private void DataLoad() {
        try {
            JSONObject jsonObject = new JSONObject(DATA_EDIT_LAND);
            JSONArray result = jsonObject.getJSONArray("outputMore");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
//                mrDtSWModelArrayList.add(new LandMrDtSWModel(
//
//                        object.getString("name"),
//                        object.getString("phn_number"),
//                        object.getString("address"),
//                        object.getString("per_qty_price"),
//                        object.getString("land_qty"),
//                        object.getString("land_price"),
//                        object.getString("land_py_Date"),
//                        object.getString("mini_leave_date"),
//                        object.getString("mini_adv_date")
//
//
//                ));


                Land_userName.setText(object.getString("name"));
                land_number.setText(object.getString("phn_number"));
                land_userAddress.setText(object.getString("address"));
                land_user_per_Unit_price.setText(object.getString("per_qty_price"));
                land_userqnty.setText(object.getString("land_qty"));
                land_userPrice.setText(object.getString("land_price"));
                land_userMontlyPayDate.setText(object.getString("land_py_Date"));
                land_userLevDate.setText(object.getString("mini_leave_date"));
                land_userAdnFee.setText(object.getString("mini_adv_date"));

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


                String image = response;
                String url = "https://famousdb.000webhostapp.com/" + image;
                Picasso.get()
                        .load(url)
                        .into(landEd_userImage);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);


    }

}


