package com.Shuvo.myapplication.UpdateAllData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.Shuvo.myapplication.Adapter.LandMDSwAdapter;
import com.Shuvo.myapplication.Class.FlatMoreDetls;
import com.Shuvo.myapplication.Class.LandMrDtSWModel;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.FlatShowActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EditFlatActivity extends AppCompatActivity {


    private static final String TAG = "EdotFlatActivity";
    EditText flat_userAddressEdit,flat_userqntyEdit,flat_userPriceEdit,flat_user_per_Unit_priceEdit,flat_userMontlyPayDateEdit,flat_userLevDateEdit,flat_userAdnFeeEdit;

     private int id;
     String landPostEdit;
     ArrayList<LandMrDtSWModel> mrDtSWModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edot_flat);


        id=getIntent().getExtras().getInt("id");

        //FlatMoreDetls model= (FlatMoreDetls) getIntent().getSerializableExtra("model");


        flat_userAddressEdit=findViewById(R.id.flat_userAddressEdit);
        flat_userqntyEdit=findViewById(R.id.flat_userqntyEdit);
        flat_userPriceEdit=findViewById(R.id.flat_userPriceEdit);
        flat_user_per_Unit_priceEdit=findViewById(R.id.flat_user_per_Unit_priceEdit);
        flat_userMontlyPayDateEdit=findViewById(R.id.flat_userMontlyPayDateEdit);
        flat_userLevDateEdit=findViewById(R.id.flat_userLevDateEdit);
        flat_userAdnFeeEdit=findViewById(R.id.flat_userAdnFeeEdit);




        dataEdit();



    }

    private void dataEdit() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                landPostEdit = shl;
             //   progressbar2.dismiss();
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
            JSONObject jsonObject = new JSONObject(landPostEdit);
            JSONArray result = jsonObject.getJSONArray("outputMore");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);


                String name=object.getString("name");
                Log.d(TAG, "landPostShow: "+name);
//                mrDtSWModelArrayList.add(new LandMrDtSWModel(
//
////                        object.getString("name"),
////                        object.getString("phn_number"),
////                        object.getString("address"),
////                        object.getString("per_qty_price"),
////                        object.getString("land_qty"),
////                        object.getString("land_price"),
////                        object.getString("land_py_Date"),
////                        object.getString("mini_leave_date"),
////                        object.getString("mini_adv_date")
////
//
//                        String address =flat_userAddressEdit.setText(object.getString("address")),
//
//
//
//
//                ));
            }





        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

}