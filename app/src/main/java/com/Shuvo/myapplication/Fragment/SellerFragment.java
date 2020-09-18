package com.Shuvo.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.FlatAdapter;
import com.Shuvo.myapplication.Adapter.MyFlatAdapter;
import com.Shuvo.myapplication.Adapter.MyHouseAdapter;
import com.Shuvo.myapplication.Adapter.MyHusLndAdpater;
import com.Shuvo.myapplication.Adapter.MyLandPostAdapter;
import com.Shuvo.myapplication.Class.FlatFrontModel;
import com.Shuvo.myapplication.Class.HuslndModelFront;
import com.Shuvo.myapplication.Class.Landrnt;
import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.Class.frontModel;
import com.Shuvo.myapplication.HomeActivity;
import com.Shuvo.myapplication.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class SellerFragment extends Fragment {


    public static  ArrayList<frontModel> myHouseLists;
    FloatingActionButton saler_Flt_Atn_btn;
    String LAND_STRING_DATA, HOUSE_STRING_DATA, FLAT_STRING_DATA, HUS_LND_STRING_DATA, currentUserID;
    FirebaseAuth auth;
    MyLandPostAdapter landPostAdapter;

    MyFlatAdapter myFlatAdapter;
    MyHouseAdapter myHouseAdapter;
    public static ArrayList<Landrnt> arrayList;
    public static ArrayList<FlatFrontModel> flatFrontModelArrayList;
    LinearLayoutManager layoutManager;
    MyHusLndAdpater myHusLndAdpater;
    public static ArrayList<HuslndModelFront> huslndModelFronts;
    RecyclerView myLandList, myHouseList, MyFlatList, myHouseLandList;
    TextView Total_post_show;


    int count1;

    public SellerFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saler, container, false);
        saler_Flt_Atn_btn = view.findViewById(R.id.saler_Flt_Atn_btn);

        Total_post_show=view.findViewById(R.id.Total_post_show);

        myHouseList = view.findViewById(R.id.myHouseList);
        MyFlatList = view.findViewById(R.id.MyFlatList);
        myHouseLandList = view.findViewById(R.id.myHouseLandList);

        myLandList = view.findViewById(R.id.myLandList);
        auth = FirebaseAuth.getInstance();
        currentUserID = auth.getCurrentUser().getUid();

        arrayList = new ArrayList<>();
        flatFrontModelArrayList = new ArrayList<>();
        myHouseLists = new ArrayList<>();
        huslndModelFronts = new ArrayList<>();


        UserLandItemLoad();
        UserHouseItemLoad();
        UserFlatItemLoad();
        UserHusLandItemLoad();


        saler_Flt_Atn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHomeActivity();

            }
        });

        return view;
    }

    private void UserHusLandItemLoad() {

        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String shse) {
                super.onPostExecute(shse);
                HUS_LND_STRING_DATA = shse;
              //  Toast.makeText(getContext(), HUS_LND_STRING_DATA, Toast.LENGTH_SHORT).show();
              //  Log.d(TAG, "onPostExecute: " + HUS_LND_STRING_DATA);
                yourHouseLandPostList();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler1 = new RequestHandler();
                String shse = handler1.sendGetRequest("https://famousdb.000webhostapp.com/ownHusLndRetrieve.php?firebase_id=" + currentUserID);
                return shse;

            }
        }
        GetJSON gjdh = new GetJSON();
        gjdh.execute();

    }

    private void yourHouseLandPostList() {

        try {
            JSONObject jsonObject = new JSONObject(HUS_LND_STRING_DATA);
            JSONArray result = jsonObject.getJSONArray("output45");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                huslndModelFronts.add(new HuslndModelFront(

                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("address"),
                        object.getString("houlan_qunty"),
                        object.getString("houLnd_Price"),
                        object.getString("house_floor"),
                        object.getString("image"),
                        object.getString("husLnd_image")
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        myHusLndAdpater = new MyHusLndAdpater(getContext(), huslndModelFronts);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myHouseLandList.setHasFixedSize(true);
        myHouseLandList.setAdapter(myHusLndAdpater);
        myHouseLandList.setLayoutManager(layoutManager);

    }

    private void UserHouseItemLoad() {

        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String shse) {
                super.onPostExecute(shse);
                HOUSE_STRING_DATA = shse;
                //Toast.makeText(getContext(), HOUSE_STRING_DATA, Toast.LENGTH_SHORT).show();
                yourHousePostList();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler1 = new RequestHandler();
                String shse = handler1.sendGetRequest("https://famousdb.000webhostapp.com/HouseOwnDataRetrieve.php?firebase_id=" + currentUserID);
                return shse;

            }
        }
        GetJSON gjdh = new GetJSON();
        gjdh.execute();

    }

    private void yourHousePostList() {
        try {
            JSONObject jsonObject = new JSONObject(HOUSE_STRING_DATA);
            JSONArray result = jsonObject.getJSONArray("output5");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                myHouseLists.add(new frontModel(

                        object.getString("name"),
                        object.getString("location"),
                        object.getString("house_floor"),
                        object.getString("house_rnt_price"),
                        object.getString("house_room"),
                        object.getString("image"),
                        object.getString("house_image"),
                        object.getInt("id")
                ));
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


        myHouseAdapter = new MyHouseAdapter(getContext(), myHouseLists);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myHouseList.setHasFixedSize(true);
        myHouseList.setLayoutManager(layoutManager);
        myHouseList.setAdapter(myHouseAdapter);


    }


    private void UserLandItemLoad() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // progressbar2.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String sh) {
                super.onPostExecute(sh);
                LAND_STRING_DATA = sh;

                yourLandPostList();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler2 = new RequestHandler();
                String sh = handler2.sendGetRequest("https://famousdb.000webhostapp.com/UploadpostCU.php?firebase_id=" + currentUserID);
                return sh;

            }
        }
        GetJSON gjd = new GetJSON();
        gjd.execute();

    }

    private void yourLandPostList() {


        try {
            JSONObject jsonObject = new JSONObject(LAND_STRING_DATA);
            JSONArray result = jsonObject.getJSONArray("output45");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                arrayList.add(new Landrnt(

                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("address"),
                        object.getString("per_qty_price"),
                        object.getString("land_qty"),
                        object.getString("land_price"),
                        object.getString("active_ckeck"),
                        object.getString("image"),
                        object.getString("landimage")


                ));
            }


            landPostAdapter = new MyLandPostAdapter(getContext(), arrayList);
            layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            myLandList.setHasFixedSize(true);
            myLandList.setAdapter(landPostAdapter);
            myLandList.setLayoutManager(layoutManager);



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }


    private void UserFlatItemLoad() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String sh) {
                super.onPostExecute(sh);
                FLAT_STRING_DATA = sh;

             //   Toast.makeText(getContext(), FLAT_STRING_DATA+"lalala", Toast.LENGTH_SHORT).show();

                youHousePostList();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler2 = new RequestHandler();
                String sh = handler2.sendGetRequest("https://famousdb.000webhostapp.com/flatFrontDataRetrieve.php?firebase_id=" + currentUserID);
                return sh;

            }
        }
        GetJSON gjd = new GetJSON();
        gjd.execute();


    }

    private void youHousePostList() {

        try {
            JSONObject jsonObject = new JSONObject(FLAT_STRING_DATA);
            JSONArray result = jsonObject.getJSONArray("output");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                flatFrontModelArrayList.add(new FlatFrontModel(

                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("address"),
                        object.getString("floorId"),
                        object.getString("FlatBadRoom"),
                        object.getString("FlatPrice"),
                        object.getString("Active_Inactive"),
                        object.getString("image"),
                        object.getString("flat_image")



                ));
            }


            myFlatAdapter = new MyFlatAdapter(getContext(),flatFrontModelArrayList);
            layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            MyFlatList.setHasFixedSize(true);
            MyFlatList.setAdapter(myFlatAdapter);
            MyFlatList.setLayoutManager(layoutManager);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

    private void gotoHomeActivity() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(getContext());


    }

    @Override
    public void onStart() {
        super.onStart();

        totalPostLoad();

    }

    private void totalPostLoad() {


        StringRequest request=new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/CuntUsrTotalPostCount.php?firebase_id="+currentUserID, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response)
            {

                Total_post_show.setText("Total Post: "+response);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {


                Toast.makeText(getContext(), error.getMessage().toString()+"", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(request);


    }
}