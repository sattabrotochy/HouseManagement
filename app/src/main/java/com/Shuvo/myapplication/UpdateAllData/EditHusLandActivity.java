package com.Shuvo.myapplication.UpdateAllData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Shuvo.myapplication.Adapter.HusLndMDApdapter;
import com.Shuvo.myapplication.Class.HusLndMDModel;
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


public class EditHusLandActivity extends AppCompatActivity {


    String HUS_LAND_EDIT,currentUserID;
    EditText hus_land_userAddress,hus_land_number;
    TextView hus_Land_userName1;
    Button hus_land_post_update_Btn;
    CircleImageView hulannEd_iamge;
    FirebaseAuth auth;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hus_land);


        id=getIntent().getExtras().getInt("id");

        auth=FirebaseAuth.getInstance();
        currentUserID=auth.getCurrentUser().getUid();

        hus_Land_userName1=findViewById(R.id.hus_Land_userName1);
        hus_land_userAddress=findViewById(R.id.hus_land_userAddress);
        hus_land_number=findViewById(R.id.hus_land_number);
        hus_land_post_update_Btn=findViewById(R.id.hus_land_post_update_Btn);
        hulannEd_iamge=findViewById(R.id.hulannEd_iamge);



        dataLoad();


        hus_land_post_update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataEdit();
            }
        });
    }

    private void dataEdit()
    {

        String phn_number, houLnd_Price;

        phn_number=hus_land_number.getText().toString();
        houLnd_Price=hus_land_userAddress.getText().toString();



    }

    private void dataLoad() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                HUS_LAND_EDIT = shl;

                huslandMDtShow();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/showhuslndMDetls.php?id="+id);
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

        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserImage.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


              String  image = response;
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
}