package com.Shuvo.myapplication.ShowActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.HouseMDSAdapter;
import com.Shuvo.myapplication.Class.HouseMDModel;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HouseMoreDtlActivity extends AppCompatActivity {

    Context context = HouseMoreDtlActivity.this;

    HouseMDSAdapter mdsAdapter;
    ArrayList<HouseMDModel> mdModelArrayList;


    RecyclerView house_more_details;

    ProgressDialog progressbar2;
    LinearLayoutManager layoutManager;

    Integer id;
    String HousePostMoreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_more_dtl);


        house_more_details = findViewById(R.id.house_more_details);



        id = getIntent().getIntExtra("id", 0);


        progressbar2 = new ProgressDialog(this);
        mdModelArrayList = new ArrayList<>();

        dataShow();

    }

    private void dataShow() {


        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                HousePostMoreDetails = shl;
                progressbar2.dismiss();
                housePostShow();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showhouseRentMdtls.php?id="+id);
                return shl;

            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();

    }

    private void housePostShow() {
        try {
            JSONObject jsonObject = new JSONObject(HousePostMoreDetails);
            JSONArray result = jsonObject.getJSONArray("outputMore");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                mdModelArrayList.add(new HouseMDModel(

                        object.getString("name"),
                        object.getString("phn_number"),
                        object.getString("location"),
                        object.getString("house_type"),
                        object.getString("house_floor"),
                        object.getString("house_rnt_type"),
                        object.getString("house_rnt_price"),
                        object.getString("house_room"),
                        object.getString("house_bathrm"),
                        object.getString("house_rnt_date"),
                        object.getString("house_lv_date"),
                        object.getString("house_gs_type"),
                        object.getString("house_gs_bill"),
                        object.getString("house_advc_fee"),
                        object.getString("house_parking"),
                        object.getString("house_lift")


                ));

            }


            mdsAdapter = new HouseMDSAdapter(context, mdModelArrayList);
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            house_more_details.setHasFixedSize(true);
            house_more_details.setAdapter(mdsAdapter);
            house_more_details.setLayoutManager(layoutManager);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

}
