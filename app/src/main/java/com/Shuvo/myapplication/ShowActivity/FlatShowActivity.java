package com.Shuvo.myapplication.ShowActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.FlatmrDtsAdapter;
import com.Shuvo.myapplication.Class.FlatMoreDetls;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlatShowActivity extends AppCompatActivity {


    RecyclerView flat_list_show;

    FlatmrDtsAdapter dtsAdapter;
    Context context = FlatShowActivity.this;
   public static   ArrayList<FlatMoreDetls> moreDetlsArrayList;
    LinearLayoutManager layoutManager;
    ProgressDialog progressbar2;
    int id;
    String data, FlatPostMoreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_show);


        flat_list_show = findViewById(R.id.flat_list_show);
        moreDetlsArrayList = new ArrayList<>();

        id = getIntent().getIntExtra("id", 0);

        progressbar2 = new ProgressDialog(this);


            flatShow();



    }

    private void flatShow() {


              class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                FlatPostMoreDetails = shl;
                progressbar2.dismiss();
                FlatPostShow();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showflatRentMdtls.php?id="+id);
                return shl;

            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();

    }

    private void FlatPostShow() {

        try {
            JSONObject jsonObject = new JSONObject(FlatPostMoreDetails);
            JSONArray result = jsonObject.getJSONArray("outputMore2");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                moreDetlsArrayList.add(new FlatMoreDetls(

                        object.getString("name"),
                        object.getString("phn_number"),
                        object.getString("address"),
                        object.getString("floorId"),
                        object.getString("FlatBadRoom"),
                        object.getString("FlatBathroom"),
                        object.getString("FlatQunty"),
                        object.getString("FlatPrice"),
                        object.getString("parkingYesNo"),
                        object.getString("Active_Inactive"),
                        object.getString("image"),
                        object.getString("flat_image")


                ));
            }


            dtsAdapter = new FlatmrDtsAdapter(context, moreDetlsArrayList);
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            flat_list_show.setHasFixedSize(true);
            flat_list_show.setLayoutManager(layoutManager);
            flat_list_show.setAdapter(dtsAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }
}