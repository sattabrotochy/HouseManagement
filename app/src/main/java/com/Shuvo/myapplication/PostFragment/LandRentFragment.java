package com.Shuvo.myapplication.PostFragment;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.ImageUploadActivity;
import com.Shuvo.myapplication.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class LandRentFragment extends Fragment {


    LinearLayout advanceFeeVisibility;
    EditText monthlyPaymentLand, minimumTimeSpendLand, advanceFee, land_quantity, land_rent, per_quantity_rent, land_address;
    RadioGroup radioGroupAdvanceMoney;
    String advanceMoneyYesNo,number, land_qty, land_rnt, per_qty_rnt, monthlyPaymentLandType, minimumTimeSpendLandType, advanceFeeType, currentUserID, address;
    Button submitLandPost;
    String activeInactivesystem, name,Imageurl;
    CheckBox land_check_Box;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    Button image_select;
    ImageView land_Image;
    private RadioButton Yes_Radio_button_advance, No_Radio_button_advance;
    String url;

    public LandRentFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_land_rent, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();




        progressDialog = new ProgressDialog(getContext());



        //EditText
        monthlyPaymentLand = view.findViewById(R.id.monthlyPaymentLand);
        minimumTimeSpendLand = view.findViewById(R.id.minimumTimeSpendLand);
        advanceFee = view.findViewById(R.id.advanceFee);
        land_quantity = view.findViewById(R.id.land_quantity);
        land_rent = view.findViewById(R.id.land_rent);
        per_quantity_rent = view.findViewById(R.id.per_quantity_rent);
        land_address = view.findViewById(R.id.land_address);
        url="https://famousdb.000webhostapp.com/LandImageUpload.php";






        //radioGroup
        radioGroupAdvanceMoney = view.findViewById(R.id.radioGroupAdvanceMoney);

        //radioButton
        Yes_Radio_button_advance = view.findViewById(R.id.Yes_Radio_button_advance);
        No_Radio_button_advance = view.findViewById(R.id.No_Radio_button_advance);


        //button
        submitLandPost = view.findViewById(R.id.submitLandPost);

        //linerLayout
        advanceFeeVisibility = view.findViewById(R.id.advanceFeeVisibility);


        //checkBox
        land_check_Box = view.findViewById(R.id.land_check_Box);


        submitLandPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                landPostDataSaveToServer();

            }
        });

        radioGroupAdvanceMoney.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.Yes_Radio_button_advance:
                        advanceMoneyYesNo = Yes_Radio_button_advance.getText().toString();
                        feeVisibility();
                        break;
                    case R.id.No_Radio_button_advance:
                        advanceMoneyYesNo = No_Radio_button_advance.getText().toString();
                        feeVisibility();
                        break;
                    default:
                }

                Log.d(TAG, "onCheckedChanged: " + advanceMoneyYesNo);
            }
        });


        return view;
    }

    private void landPostDataSaveToServer() {

        progressDialog.setMessage("Please wait.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        land_qty = land_quantity.getText().toString().trim();
        land_rnt = land_rent.getText().toString().trim();
        per_qty_rnt = per_quantity_rent.getText().toString().trim();
        monthlyPaymentLandType = monthlyPaymentLand.getText().toString().trim();
        minimumTimeSpendLandType = minimumTimeSpendLand.getText().toString().trim();
        advanceFeeType = advanceFee.getText().toString().trim();
        address = land_address.getText().toString().trim();


        if (land_rnt.isEmpty() || land_qty.isEmpty() || per_qty_rnt.isEmpty() || monthlyPaymentLandType.isEmpty() ||
                minimumTimeSpendLandType.isEmpty() || advanceFeeType.isEmpty()) {

            activeInactivesystem = " Your post is Inactive ";
            landpostSave();
            Toast.makeText(getContext(), activeInactivesystem, Toast.LENGTH_SHORT).show();
        } else {
            activeInactivesystem = " Your post is active";
            landpostSave();

            Toast.makeText(getContext(), activeInactivesystem, Toast.LENGTH_SHORT).show();
        }


    }

    private void landpostSave() {


        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/landpost.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "post successful", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Log.d(TAG, "getParams: " + currentUserID);
                params.put("firebase_id", currentUserID);
                params.put("name", name);
                params.put("phn_number", number);
                params.put("address", "জমির ঠিকানা: " + address);
                params.put("land_qty", "জমির পরিমাণটা :" + land_qty + "একক");
                params.put("land_price", "জমির ভাড়া :" + land_rnt+"টাকা");
                params.put("land_py_Date", "জমির ভাড়া " + monthlyPaymentLandType + "তারিখের মধ্যে দিতে হবে");
                params.put("mini_leave_date", "সর্বনিম্ন" + minimumTimeSpendLandType + "দিন থাকার পর জমি ছাড়তে পারবে");
                params.put("per_qty_price", "প্রতি একক জমির ভাড়া" + per_qty_rnt+"টাকা");
                params.put("mini_adv_date", "সর্বনিম্ন " + advanceFeeType + "মাসের অগ্রিম ভাড়া দিতে হবে");
                params.put("active_ckeck", activeInactivesystem);
                params.put("image", Imageurl);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }

    private void feeVisibility() {

        if (advanceMoneyYesNo.equals("হ্যাঁ")) {
            advanceFeeVisibility.setVisibility(View.VISIBLE);
        } else if (advanceMoneyYesNo.equals("না")) {
            advanceFeeVisibility.setVisibility(View.GONE);
        } else {
            advanceFeeVisibility.setVisibility(View.GONE);
        }


    }


    @Override
    public void onStart() {
        super.onStart();

        numberLoad();
        imageLoad();
        StringRequest request=new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserId.php?firebase_id="+currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                name=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(request);

    }

    private void numberLoad()
    {
        StringRequest request=new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/userNumber.php?firebase_id="+currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();

                number=response;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(request);


    }

    private void imageLoad() {


        StringRequest request1 = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserImage.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                String image = response;
                Log.d(ContentValues.TAG, "onResponse: " + image);
                Imageurl = "https://famousdb.000webhostapp.com/" + image;




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(request1);

    }


}