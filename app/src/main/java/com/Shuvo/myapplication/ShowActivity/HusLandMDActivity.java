package com.Shuvo.myapplication.ShowActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.HusLndMDApdapter;
import com.Shuvo.myapplication.Adapter.MyHusLndAdpater;
import com.Shuvo.myapplication.Class.HusLndMDModel;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HusLandMDActivity extends AppCompatActivity {


    RecyclerView hou_lan_more_dtls;
    Context context = HusLandMDActivity.this;
    HusLndMDApdapter husLndMDApdapter;
    ArrayList<HusLndMDModel> husLndMDModelArrayList;
    LinearLayoutManager layoutManager;
    ProgressDialog progressbar2;
    String HUS_LAND_MD;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hus_land_m_d);


        hou_lan_more_dtls = findViewById(R.id.hou_lan_more_dtls);

        husLndMDModelArrayList = new ArrayList<>();

        id = getIntent().getIntExtra("id", 0);


        progressbar2=new ProgressDialog(this);
        dataload();


    }

    private void dataload() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                HUS_LAND_MD = shl;
                progressbar2.dismiss();
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
            JSONObject jsonObject = new JSONObject(HUS_LAND_MD);
            JSONArray result = jsonObject.getJSONArray("outputMore2");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                husLndMDModelArrayList.add(new HusLndMDModel(

                        object.getString("name"),
                        object.getString("phn_number"),
                        object.getString("address"),
                        object.getString("houlan_type"),
                        object.getString("house_floor"),
                        object.getString("houlan_qunty"),
                        object.getString("houLnd_Price"),
                        object.getString("houlan_document"),
                        object.getString("houLan_taxClr"),
                        object.getString("houlan_parking"),
                        object.getString("active_inactive"),
                        object.getString("image")


                ));
            }


            husLndMDApdapter = new HusLndMDApdapter(context, husLndMDModelArrayList);
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            hou_lan_more_dtls.setHasFixedSize(true);
            hou_lan_more_dtls.setAdapter(husLndMDApdapter);
            hou_lan_more_dtls.setLayoutManager(layoutManager);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

}
