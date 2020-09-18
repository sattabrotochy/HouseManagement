package com.Shuvo.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.FlatAdapter;
import com.Shuvo.myapplication.Adapter.HouseAdapter;
import com.Shuvo.myapplication.Adapter.HusLanFntAdapter;
import com.Shuvo.myapplication.Adapter.LandAdapter;
import com.Shuvo.myapplication.Class.FlatBuyerModel;
import com.Shuvo.myapplication.Class.FlatFrontModel;
import com.Shuvo.myapplication.Class.HuslndModelFront;
import com.Shuvo.myapplication.Class.Landrnt;
import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.Class.frontModel;
import com.Shuvo.myapplication.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BuyerFragment extends Fragment {


    public static ArrayList<frontModel> list;
    public static ArrayList<Landrnt> landrntList;
    Spinner allItemSpinner;
    String allItemSpinnerType, currentUserID, flatCount, LAND_STRING_DATA, HusLandCount, HOUSE_Land_STRING_DATA;
    EditText Main_search_Id;
    TextView userTextView;
    RecyclerView listView, houseRecyclerView, Flat_Sale, husLan_sale;
    LandAdapter adapter;
    HouseAdapter houseAdapter;
    TextView landPostCount, housePostCount, flat_Count, husLan_count;
    ArrayList<FlatBuyerModel> flatFrontModelArrayList;
    FlatAdapter flatAdapter;
    ArrayList<HuslndModelFront> huslndModelFrontArrayList;
    HusLanFntAdapter husLanFntAdapter;

    ProgressDialog progressbar2;
    LinearLayoutManager layoutManager;
    LinearLayout linerlayoit123, houseRent_linerlayOut;
    FirebaseAuth mAuth;
    String Imageurl;
    private String JSON_STRING_CAT, LAND_STRING, LandPostCount, houseCount, FLAT_STRING_DATA;

    public BuyerFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buyer, container, false);


        //linerLayout
        linerlayoit123 = view.findViewById(R.id.linerlayoit123);
        houseRent_linerlayOut = view.findViewById(R.id.houseRent_linerlayOut);

        progressbar2 = new ProgressDialog(getContext());
        progressbar2.setCanceledOnTouchOutside(false);
        landPostCount = view.findViewById(R.id.LandPostCount);
        housePostCount = view.findViewById(R.id.housePostCount);


        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        listView = view.findViewById(R.id.listview);


        huslndModelFrontArrayList = new ArrayList<>();
        husLan_sale = view.findViewById(R.id.husLan_sale);
        husLan_count = view.findViewById(R.id.husLan_count);


        Flat_Sale = view.findViewById(R.id.Flat_Sale);
        flat_Count = view.findViewById(R.id.flat_Count);


        list = new ArrayList<>();
        landrntList = new ArrayList<>();
        houseRecyclerView = view.findViewById(R.id.houseRecyclerView);
        houseRecyclerView.setHasFixedSize(true);
        houseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        flatFrontModelArrayList = new ArrayList<>();


        DataLoad();
        landDataLoad();
        flatDataLoad();
        huslandDataLoad();
        return view;


    }


    //all house & land post show
    ///////////////////////////////////////////////////////
    private void huslandDataLoad() {

        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shf) {
                super.onPostExecute(shf);
                HOUSE_Land_STRING_DATA = shf;
                progressbar2.dismiss();
                // Toast.makeText(getContext(), HOUSE_Land_STRING_DATA, Toast.LENGTH_SHORT).show();
                husLandPost();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler = new RequestHandler();
                String shf = handler.sendGetRequest("https://famousdb.000webhostapp.com/houseLandRetrieve.php");
                return shf;
            }

        }
        GetJSON gjfh = new GetJSON();
        gjfh.execute();
    }

    private void husLandPost() {
        try {
            JSONObject jsonObject = new JSONObject(HOUSE_Land_STRING_DATA);
            JSONArray result = jsonObject.getJSONArray("flatresult2");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                huslndModelFrontArrayList.add(new HuslndModelFront(

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
            Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        husLanFntAdapter = new HusLanFntAdapter(getContext(), huslndModelFrontArrayList);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        husLan_sale.setHasFixedSize(true);
        husLan_sale.setAdapter(husLanFntAdapter);
        husLan_sale.setLayoutManager(layoutManager);

    }

    //all flat post show
    ////////////////////////////////////////////////
    private void flatDataLoad() {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String sfd) {
                super.onPostExecute(sfd);
                FLAT_STRING_DATA = sfd;
                progressbar2.dismiss();
               // Toast.makeText(getActivity(), FLAT_STRING_DATA, Toast.LENGTH_SHORT).show();
                FlatLoadPost();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handlerd = new RequestHandler();
                String sfd = handlerd.sendGetRequest("https://famousdb.000webhostapp.com/flatPostRetrieve.php");
                return sfd;
            }
        }
        GetJSON gjf = new GetJSON();
        gjf.execute();

    }
    private void FlatLoadPost() {
        try {
            JSONObject jsonObject = new JSONObject(FLAT_STRING_DATA);
            JSONArray result = jsonObject.getJSONArray("flatresult33");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                flatFrontModelArrayList.add(new FlatBuyerModel(

                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("address"),
                        object.getString("floorId"),
                        object.getString("flatBadRoom"),
                        object.getString("flatPrice"),
                        object.getString("Active_Inactive"),
                        object.getString("image"),
                        object.getString("flat_image")

                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        flatAdapter = new FlatAdapter(getContext(), flatFrontModelArrayList);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Flat_Sale.setHasFixedSize(true);
        Flat_Sale.setAdapter(flatAdapter);
        Flat_Sale.setLayoutManager(layoutManager);
    }


    ////////////////////////////

    //all house post show

///////////////////////////////////////////////////////////////////////
    private void DataLoad() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSON_STRING_CAT = s;
                progressbar2.dismiss();
               // Toast.makeText(getActivity(), JSON_STRING_CAT, Toast.LENGTH_SHORT).show();
                houseDataLoad();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler = new RequestHandler();
                String s = handler.sendGetRequest("https://famousdb.000webhostapp.com/retrieveHouseList.php");
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();


    }

    private void houseDataLoad() {
        try {
            JSONObject jsonObject = new JSONObject(JSON_STRING_CAT);
            JSONArray result = jsonObject.getJSONArray("result23");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                list.add(new frontModel(


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
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        houseAdapter = new HouseAdapter(getContext(), list);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        houseRecyclerView.setHasFixedSize(true);
        houseRecyclerView.setAdapter(houseAdapter);
        houseRecyclerView.setLayoutManager(layoutManager);
    }
    //////////////////////////////////////////////////////

    //all land post show
    //////////////////////////////////////////////////
    private void landDataLoad() {
        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // progressbar2.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String sh) {
                super.onPostExecute(sh);
                LAND_STRING = sh;
                //  progressbar2.setVisibility(View.INVISIBLE);
                // Toast.makeText(getContext(), JSON_STRING_CAT, Toast.LENGTH_SHORT).show();
                landDataList();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler2 = new RequestHandler();
                String sh = handler2.sendGetRequest("https://famousdb.000webhostapp.com/retrieve.php");
                return sh;
            }
        }
        GetJSON gjd = new GetJSON();
        gjd.execute();
    }

    private void landDataList() {
        try {
            JSONObject jsonObject = new JSONObject(LAND_STRING);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                landrntList.add(new Landrnt(
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
            adapter = new LandAdapter(getContext(), landrntList);
            layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            listView.setHasFixedSize(true);
            listView.setAdapter(adapter);
            listView.setLayoutManager(layoutManager);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    ////////////////////////////////////////////////////
    @Override
    public void onStart() {
        super.onStart();



        countland();
        countHouse();
        countflat();
        countHusLand();

    }







    //house & land post count
    private void countHusLand() {
        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shf) {
                super.onPostExecute(shf);
                HusLandCount = shf;
                progressbar2.dismiss();
                huslanPostCount();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler4 = new RequestHandler();
                String shf = handler4.sendGetRequest("https://famousdb.000webhostapp.com/husLandCount.php");
                return shf;
            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();
    }

    @SuppressLint("SetTextI18n")
    private void huslanPostCount() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(HusLandCount);
            JSONArray result = jsonObject.getJSONArray("output6");

            husLan_count.setText("Total House & land Post" + result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //flat post count
    private void countflat() {
        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shf) {
                super.onPostExecute(shf);
                flatCount = shf;
                progressbar2.dismiss();
                countFlatPost();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler handler4 = new RequestHandler();
                String shf = handler4.sendGetRequest("https://famousdb.000webhostapp.com/flatcount.php");
                return shf;
            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();
    }

    @SuppressLint("SetTextI18n")
    private void countFlatPost() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(flatCount);
            JSONArray result = jsonObject.getJSONArray("output4");

            flat_Count.setText("Total Flat Post" + result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //house post count
    private void countHouse() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                houseCount = shl;
                progressbar2.dismiss();
                // Toast.makeText(getContext(), JSON_STRING_CAT, Toast.LENGTH_SHORT).show();
                HousePostCountShow();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/housepostCount.php");
                return shl;
            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();
    }

    @SuppressLint("SetTextI18n")
    private void HousePostCountShow() {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(houseCount);
            JSONArray result = jsonObject.getJSONArray("output3");

            housePostCount.setText("Total House Post" + result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //land post count
    private void countland() {
        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressbar2.show();
            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                LandPostCount = shl;
                landPostCountShow();
                progressbar2.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/countlandPost.php");
                return shl;
            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();
    }

    @SuppressLint("SetTextI18n")
    private void landPostCountShow() {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(LandPostCount);
            JSONArray result = jsonObject.getJSONArray("output2");
            landPostCount.setText("Total Land Post" + result.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

      

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}