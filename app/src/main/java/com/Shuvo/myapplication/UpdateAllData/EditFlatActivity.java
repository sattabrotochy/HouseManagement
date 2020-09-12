package com.Shuvo.myapplication.UpdateAllData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Shuvo.myapplication.Adapter.FlatmrDtsAdapter;
import com.Shuvo.myapplication.Adapter.LandMDSwAdapter;
import com.Shuvo.myapplication.Class.FlatMoreDetls;
import com.Shuvo.myapplication.Class.LandMrDtSWModel;
import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.FlatShowActivity;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class EditFlatActivity extends AppCompatActivity {


    private static final String TAG = "EdotFlatActivity";
    EditText flat_userAddressEdit,flat_phne_numberEdit,flat_userqntyEdit,flat_userPriceEdit,flat_user_per_Unit_priceEdit,flat_userMontlyPayDateEdit,flat_userLevDateEdit,flat_userAdnFeeEdit;

    TextView flat_userName;
     private int id;
     String landPostEdit,currentUserID;
     FirebaseAuth auth;
     ArrayList<LandMrDtSWModel> mrDtSWModelArrayList;
     Button flat_update_Btn;
     CircleImageView flat_userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edot_flat);


        id=getIntent().getExtras().getInt("id");

        auth=FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();
        //FlatMoreDetls model= (FlatMoreDetls) getIntent().getSerializableExtra("model");


        flat_userAddressEdit=findViewById(R.id.flat_floor_id);
        flat_userName=findViewById(R.id.flat_userName);
        flat_userqntyEdit=findViewById(R.id.flat_user_price);
        flat_phne_numberEdit=findViewById(R.id.flat_phne_numberEdit);
        flat_update_Btn=findViewById(R.id.flat_update_Btn);

        flat_userImage=findViewById(R.id.flat_userImage);


        dataEdit();

        flat_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                flatUserEdit();

            }
        });


    }

    private void flatUserEdit()
    {
        String phn_number,floorId,FlatPrice;

        phn_number=flat_phne_numberEdit.getText().toString().trim();
        floorId=flat_userAddressEdit.getText().toString().trim();
        FlatPrice=flat_userqntyEdit.getText().toString().trim();













    }

    private void dataEdit() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                landPostEdit= shl;

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
            JSONObject jsonObject = new JSONObject(landPostEdit);
            JSONArray result = jsonObject.getJSONArray("outputMore2");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
//                moreDetlsArrayList.add(new FlatMoreDetls(
//
//                        object.getString("name"),
//                        object.getString("phn_number"),
//                        object.getString("address"),
//                        object.getString("floorId"),
//                        object.getString("FlatBadRoom"),
//                        object.getString("FlatBathroom"),
//                        object.getString("FlatQunty"),
//                        object.getString("FlatPrice"),
//                        object.getString("parkingYesNo"),
//                        object.getString("Active_Inactive"),
//                        object.getString("image")
//
//
//                ));]

                flat_userName.setText(object.getString("name"));
                flat_userAddressEdit.setText(object.getString("floorId"));
                flat_phne_numberEdit.setText(object.getString("phn_number"));
                flat_userqntyEdit.setText(object.getString("FlatPrice"));


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
                        .into(flat_userImage);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);

    }
}