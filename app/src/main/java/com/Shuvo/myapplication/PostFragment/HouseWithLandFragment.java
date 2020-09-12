package com.Shuvo.myapplication.PostFragment;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.Shuvo.myapplication.Class.MySingleton;
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


public class HouseWithLandFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Spinner houseLandType, taxClrSpinner;
    String houseWithLandType, yes_no_tex_clr_output, taxClrSpinnerType,Imageurl;
    RadioGroup radioGroupTax, hlan_parking;
    RadioButton No_Radio_button_tax, Yes_Radio_button_tax, hlnd_parkingNo_Radio_button, hlnd_parking_Yes_Radio_button;
    LinearLayout taxclerLinerLayout;
    FirebaseAuth auth;
    String name, address, houlanType, houLndFloor, houLand_qnty, houLndPrice, husLndParking,number, currentUserId, activeInactive;
    EditText HLnd_address, HouseLand_floor, houLand_quantity, houLnd_Price;
    Button houLndPostBtn;
    ProgressDialog progressDialog;

    public HouseWithLandFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house_with_land, container, false);

        //spinner
        houseLandType = view.findViewById(R.id.houseLandType);
        taxClrSpinner = view.findViewById(R.id.taxClrSpinner);

        auth = FirebaseAuth.getInstance();
        currentUserId = auth.getCurrentUser().getUid();

        //radioGroup
        radioGroupTax = view.findViewById(R.id.radioGroupTax);
        hlan_parking = view.findViewById(R.id.hlan_parking);


        progressDialog=new ProgressDialog(getContext());
        //EditText
        HLnd_address = view.findViewById(R.id.HLnd_address);
        HouseLand_floor = view.findViewById(R.id.HouseLand_floor);
        houLand_quantity = view.findViewById(R.id.houLand_quantity);
        houLnd_Price = view.findViewById(R.id.houLnd_Price);
        houLndPostBtn = view.findViewById(R.id.houLndPostBtn);


        //radioButton
        No_Radio_button_tax = view.findViewById(R.id.No_Radio_button_tax);
        Yes_Radio_button_tax = view.findViewById(R.id.Yes_Radio_button_tax);
        hlnd_parkingNo_Radio_button = view.findViewById(R.id.hlnd_parkingNo_Radio_button);
        hlnd_parking_Yes_Radio_button = view.findViewById(R.id.hlnd_parking_Yes_Radio_button);


        //linerLayout
        taxclerLinerLayout = view.findViewById(R.id.taxclerLinerLayout);


        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(getContext(), R.array.house_type, android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseLandType.setAdapter(arrayAdapter1);
        houseLandType.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.tax_clr_input, android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taxClrSpinner.setAdapter(arrayAdapter2);
        taxClrSpinner.setOnItemSelectedListener(this);

        hlan_parking.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.hlnd_parkingNo_Radio_button:
                        husLndParking = hlnd_parkingNo_Radio_button.getText().toString();

                        yes_no_tex_clr_();
                        break;
                    case R.id.hlnd_parking_Yes_Radio_button:
                        husLndParking = hlnd_parking_Yes_Radio_button.getText().toString();
                        yes_no_tex_clr_();
                        break;
                    default:
                }
            }
        });

        radioGroupTax.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.Yes_Radio_button_tax:
                        yes_no_tex_clr_output = Yes_Radio_button_tax.getText().toString();

                        yes_no_tex_clr_();
                        break;
                    case R.id.No_Radio_button_tax:
                        yes_no_tex_clr_output = No_Radio_button_tax.getText().toString();
                        yes_no_tex_clr_();
                        break;
                    default:
                }


            }
        });


        houLndPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HouLndDataPost();

            }
        });

        return view;
    }

    private void HouLndDataPost() {

        progressDialog.setMessage("Wait..........");
        progressDialog.show();
        address = HLnd_address.getText().toString();
        houLndFloor = HouseLand_floor.getText().toString();
        houLand_qnty = houLand_quantity.getText().toString();
        houLndPrice = houLnd_Price.getText().toString();


        if (address.isEmpty() || houLndFloor.isEmpty() || houLand_qnty.isEmpty() || houLndPrice.isEmpty() || taxClrSpinnerType.isEmpty() ||
                houseWithLandType.isEmpty() || husLndParking.isEmpty()) {
            activeInactive = " Your post is Inactive ";
            Toast.makeText(getContext(), activeInactive, Toast.LENGTH_SHORT).show();
            HouseLandPostSave();

        } else {
            activeInactive = " Your post is active ";
            Toast.makeText(getContext(), activeInactive, Toast.LENGTH_SHORT).show();
            HouseLandPostSave();
        }


    }

    private void HouseLandPostSave() {


        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/HouseLandPost.php",
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
                params.put("firebase_id", currentUserId);
                params.put("name", name);
                params.put("phn_number", number);
                params.put("address", "বাসার ঠিকানা:" + address);
                params.put("houlan_type", houseWithLandType + "বাসা");
                params.put("house_floor", "বাসাটি" + houLndFloor + "তলা বিশিষ্ট");
                params.put("houlan_qunty", " বাসাটি সর্বমোট" + houLand_qnty + "বর্গ ফিট");
                params.put("houLnd_Price", " বাসাটি " + houLndPrice + "টাকায় বিক্রি হবে");
                params.put("houlan_document", yes_no_tex_clr_output + ",এই জায়গায় সব টেক্স পরিশোধ করা  হইছে");
                params.put("houLan_taxClr", taxClrSpinnerType + "কয় বছরের কর পরিশোধ করেন নি");
                params.put("houlan_parking", husLndParking + ",পার্কিং এর জন্য ব্যবস্থা আছে");
                params.put("image", Imageurl);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);


    }

    private void yes_no_tex_clr_() {

        if (yes_no_tex_clr_output.equals("হ্যাঁ")) {
            taxclerLinerLayout.setVisibility(View.GONE);
        } else if (yes_no_tex_clr_output.equals("না")) {
            taxclerLinerLayout.setVisibility(View.VISIBLE);
        } else {
            taxclerLinerLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        houseWithLandType = adapterView.getItemAtPosition(i).toString();
        taxClrSpinnerType = adapterView.getItemAtPosition(i).toString();
        houseWithLandTypeCall();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void houseWithLandTypeCall()
    {



    }

    @Override
    public void onStart() {
        super.onStart();


        numberLoad();
        imageLoad();
        StringRequest request=new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserId.php?firebase_id="+currentUserId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


                name=response;
                Log.d(TAG, "onResponse: "+name);

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
        StringRequest request=new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/userNumber.php?firebase_id="+currentUserId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


                number=response;
                Log.d(TAG, "onResponse: "+name);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(request);


    }
    private void imageLoad() {


        StringRequest request1 = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserImage.php?firebase_id=" + currentUserId, new Response.Listener<String>() {
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