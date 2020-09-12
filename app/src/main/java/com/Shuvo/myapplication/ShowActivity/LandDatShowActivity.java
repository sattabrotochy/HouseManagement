package com.Shuvo.myapplication.ShowActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.LandMDSwAdapter;
import com.Shuvo.myapplication.Class.LandMrDtSWModel;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LandDatShowActivity extends AppCompatActivity {


    String TAG = "LandDatShowActivity";
    Context context = LandDatShowActivity.this;

    String LandPostMoreDetrails,FlatPostMoreDetails;
    LinearLayoutManager layoutManager;
    LandMDSwAdapter swAdapter;
   public static ArrayList<LandMrDtSWModel> mrDtSWModelArrayList;




    RecyclerView moreDetailsSHow,flat_moreDetailsSHow;

    ProgressDialog progressbar2;
    int id;

    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_dat_show);


        id = getIntent().getIntExtra("id", 0);



        Log.d(TAG, "onCreate: " + id);

        moreDetailsSHow = findViewById(R.id.moreDetailsSHow);
       // flat_moreDetailsSHow = findViewById(R.id.flat_moreDetailsSHow);


        mrDtSWModelArrayList = new ArrayList<>();



        progressbar2 = new ProgressDialog(this);
        progressbar2.setMessage("please Wait..........");
        progressbar2.setCanceledOnTouchOutside(false);




            dataShow();

    }


    private void dataShow() {



        mrDtSWModelArrayList.clear();
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                LandPostMoreDetrails = shl;
                progressbar2.dismiss();
                landPostShow();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showlandPostMoreDetails.php?id="+id);
                return shl;

            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();

    }

    private void landPostShow() {


        try {
            JSONObject jsonObject = new JSONObject(LandPostMoreDetrails);
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
                        object.getString("mini_adv_date"),
                        object.getString("image")





                ));
            }


            swAdapter = new LandMDSwAdapter(context, mrDtSWModelArrayList);
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            moreDetailsSHow.setHasFixedSize(true);
            moreDetailsSHow.setLayoutManager(layoutManager);
            moreDetailsSHow.setAdapter(swAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

}