package com.Shuvo.myapplication.UpdateAllData;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.EditLandApadter;
import com.Shuvo.myapplication.Adapter.FlatmrDtsAdapter;
import com.Shuvo.myapplication.Adapter.LandMDSwAdapter;
import com.Shuvo.myapplication.Class.FlatMoreDetls;
import com.Shuvo.myapplication.Class.LandMrDtSWModel;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.LandDatShowActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class EditlandPostActivity extends AppCompatActivity {


    int id;
    TextView Land_userName, land_userAddress, land_userqnty, land_userPrice, land_user_per_Unit_price, land_userMontlyPayDate, land_userLevDate, land_userAdnFee;


    String TAG="EditlandPostActivity";
    Context context=EditlandPostActivity.this;
    FirebaseAuth    auth;
    String DATA_EDIT_LAND,currentUserID;
    LinearLayoutManager layoutManager;
    EditLandApadter editLandApadter;
    RecyclerView landEditItem;
    public static ArrayList<LandMrDtSWModel> mrDtSWModelArrayList;

    ProgressDialog progressbar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editland_post);


        id =getIntent().getIntExtra("id",0);
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();


        landEditItem=findViewById(R.id.landEditItem);
        mrDtSWModelArrayList = new ArrayList<>();
        auth=FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();


        progressbar2 = new ProgressDialog(this);
        progressbar2.setMessage("please Wait..........");
        progressbar2.setCanceledOnTouchOutside(false);


        Land_userName = findViewById(R.id.Land_userName1);
        land_userAddress = findViewById(R.id.land_userAddress1);
        land_userqnty = findViewById(R.id.land_userqnty1);
        land_userPrice = findViewById(R.id.land_userPrice1);
        land_user_per_Unit_price = findViewById(R.id.land_user_per_Unit_price1);
        land_userMontlyPayDate = findViewById(R.id.land_userMontlyPayDate1);
        land_userLevDate = findViewById(R.id.land_userLevDate1);
        land_userAdnFee = findViewById(R.id.land_userAdnFee1);





        dataSHow();

    }

    private void dataSHow()
    {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }
            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                DATA_EDIT_LAND=shl;
                Toast.makeText(EditlandPostActivity.this, DATA_EDIT_LAND, Toast.LENGTH_SHORT).show();
                DataLoad();
            }
            @Override
            protected String doInBackground(Void... params)
            {
                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showlandPostMoreDetails.php?id="+id);
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
                mrDtSWModelArrayList.add(new LandMrDtSWModel(

                        object.getString("name"),
                        object.getString("phn_number"),
                        object.getString("address"),
                        object.getString("per_qty_price"),
                        object.getString("land_qty"),
                        object.getString("land_price"),
                        object.getString("land_py_Date"),
                        object.getString("mini_leave_date"),
                        object.getString("mini_adv_date")


                ));
            }

            editLandApadter = new EditLandApadter(context, mrDtSWModelArrayList);
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            landEditItem.setHasFixedSize(true);
            landEditItem.setAdapter(editLandApadter);
            landEditItem.setLayoutManager(layoutManager);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}


