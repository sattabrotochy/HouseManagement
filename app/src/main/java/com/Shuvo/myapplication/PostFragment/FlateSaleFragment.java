package com.Shuvo.myapplication.PostFragment;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FlateSaleFragment extends Fragment {


    RadioGroup radioGroupLiftFlat, flat_parking_groupButton;
    RadioButton flat_No_Radio_button, flat_Yes_Radio_button, parking_Flat_No_Radio_button, parking_Flat_Yes_Radio_button;
    String shu,Imageurl;
    FirebaseAuth auth;
    EditText flat_address, FloorID, flat_badRoom, flat_bathroom, flat_quantity, flat_price;
    String name, address, floorId, FlatBadRoom, FlatBathroom, FlatQunty, FlatPrice, number, currentUserID, parkingYesNo, activeInactive = "";
    Button flat_post;
    ProgressDialog progressDialog;

    public FlateSaleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_flate_sale, container, false);


        //radio group
        auth = FirebaseAuth.getInstance();
        currentUserID = auth.getCurrentUser().getUid();
        radioGroupLiftFlat = view.findViewById(R.id.radioGroupLiftFlat);
        flat_parking_groupButton = view.findViewById(R.id.flat_parking_groupButton);

        flat_address = view.findViewById(R.id.flat_address);
        FloorID = view.findViewById(R.id.FloorID);
        flat_badRoom = view.findViewById(R.id.flat_badRoom);
        flat_bathroom = view.findViewById(R.id.flat_bathroom);
        flat_quantity = view.findViewById(R.id.flat_quantity);
        flat_price = view.findViewById(R.id.flat_price);

        progressDialog = new ProgressDialog(getContext());


        flat_post = view.findViewById(R.id.flat_post);

        flat_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLoad();

            }
        });

        //radio button
        flat_No_Radio_button = view.findViewById(R.id.flat_lift_No_Radio_button);
        flat_Yes_Radio_button = view.findViewById(R.id.flat_lift_Yes_Radio_button);

        flat_parking_groupButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.flat_lift_No_Radio_button:
                        parkingYesNo = flat_No_Radio_button.getText().toString();
                        Toast.makeText(getContext(), parkingYesNo.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onCheckedChanged222: "+parkingYesNo);
                        break;
                    case R.id.flat_lift_Yes_Radio_button:
                        parkingYesNo = flat_Yes_Radio_button.getText().toString();
                        Toast.makeText(getContext(), parkingYesNo.toString(), Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }

            }
        });

        radioGroupLiftFlat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.flat_lift_No_Radio_button:
                        shu = flat_No_Radio_button.getText().toString();
                        break;
                    case R.id.flat_lift_Yes_Radio_button:
                        shu = flat_Yes_Radio_button.getText().toString();

                        break;
                    default:
                }
                Log.d(TAG, "onCheckedChanged: " + shu);


            }
        });

        return view;
    }

    private void postLoad() {


        progressDialog.setMessage("wait .......");
        progressDialog.show();
        address = flat_address.getText().toString();
        floorId = FloorID.getText().toString();
        FlatBadRoom = flat_badRoom.getText().toString();
        FlatBathroom = flat_bathroom.getText().toString();
        FlatQunty = flat_quantity.getText().toString();
        FlatPrice = flat_price.getText().toString();


        if (address.isEmpty() || floorId.isEmpty() || FlatBadRoom.isEmpty() || FlatBathroom.isEmpty() || FlatQunty.isEmpty() || FlatPrice.isEmpty()) {

            activeInactive = "Your post is Inactive";
            DataSaveToServer();
            Toast.makeText(getContext(), activeInactive, Toast.LENGTH_SHORT).show();


        } else {
            activeInactive = " Your post is active";
            Toast.makeText(getContext(), activeInactive, Toast.LENGTH_SHORT).show();
            DataSaveToServer();
        }


    }

    private void DataSaveToServer() {


        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/FlatPost.php",
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
                params.put("firebase_id", currentUserID);
                params.put("name", name);
                params.put("phn_number", number);
                params.put("address", "বাসার ঠিকানাা:" + address);
                params.put("floorId", "ফ্ল্যাট নম্বর" + floorId);
                params.put("FlatBadRoom", "বেড রুম" + FlatBadRoom + "টি");
                params.put("FlatBathroom", "গোসলখানা" + FlatBathroom + "টি");
                params.put("FlatQunty", "ফ্ল্যাটটি" + FlatQunty + "বর্গ ফিট");
                params.put("FlatPrice", "ফ্ল্যাটটির দাম" + FlatPrice + "টাকা");
                params.put("parkingYesNo", parkingYesNo + "পার্কিং এর জন্য ব্যবস্থা আছে");
                params.put("Active_Inactive", activeInactive);
                params.put("image", Imageurl);
                params.put("$flat_image", " ");
                return params;
            }
        };

        MySingleton.getInstance(getContext()).addToRequestQueue(request);

    }

    @Override
    public void onStart() {
        super.onStart();
        currentUserIDLoad();
        cuurentNumbwe();
        imageLoad();

    }

    private void cuurentNumbwe() {
        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/userNumber.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                number = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(request);


    }

    private void currentUserIDLoad() {


        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserId.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                name = response;


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