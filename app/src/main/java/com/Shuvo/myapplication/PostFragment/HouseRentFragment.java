package com.Shuvo.myapplication.PostFragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.Shuvo.myapplication.Class.MySingleton;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.HomeActivity;
import com.Shuvo.myapplication.MainActivity;
import com.Shuvo.myapplication.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class HouseRentFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Spinner houseTypeSpinner, rentType, monthlyPayment, minimumTimeSpend, gasSystem, advanceFee_house;
    String houseType="", houseType2="", rentItemType="", monthlyPaymentType="", minimumTimeSpendType="", gasSystemType="", advanceYesNoreturnString="";
    String advanceFeeHouseStringSpinner="",number, currentUserID, parking="", lift="", CurrentUser;

    EditText floorId, houseRent, HouseRoom, houseBathroom, user_house_location;
    LinearLayout advanceFeeVisibility_house, gasSystemLinerId, floorNumberId, houseLiftLinerLayout;

    RadioGroup radioGroupGas, House_radioGroupAdvanceMoney, radioGroupParking_House, radioGroupLift_house;


    RadioButton No_Radio_button_advanceFee_House, Yes_Radio_button_advanceFree_house, House_lift_No_Radio_button, House_lift_Yes_Radio_button, house_parking_No_Radio_button, house_parking_Yes_Radio_button, Gas_bil_Yes_Radio_button, Gas_bil_No_Radio_button;


    ImageView Back_To_Home;
    ImageView HouseRentImageType, HouseRentImageType1, HouseRentImageType2, HouseRentImageType3;

    String name="", location="", floorNumberHouse = "", housePrice="", houseRoom="", houseBathRoom="", gasBil="";
    Button videoUpload, houseRentPost, house_rent_image;
    Uri videoUri;


    FirebaseAuth mAuth;
    int PICK_REQUEST_CODE = 1;
    ProgressDialog progressDialog;


    public HouseRentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house_rent, container, false);


        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();


        progressDialog = new ProgressDialog(getContext());
        //Image view
        HouseRentImageType = view.findViewById(R.id.HouseRentImageType);
        HouseRentImageType1 = view.findViewById(R.id.HouseRentImageType1);
        HouseRentImageType2 = view.findViewById(R.id.HouseRentImageType2);
        HouseRentImageType3 = view.findViewById(R.id.HouseRentImageType3);


        //GroupRadioButton
        House_radioGroupAdvanceMoney = view.findViewById(R.id.House_radioGroupAdvanceMoney);
        radioGroupParking_House = view.findViewById(R.id.radioGroupParking_House);

        radioGroupLift_house = view.findViewById(R.id.radioGroupLift_house);


        //radioButton
        No_Radio_button_advanceFee_House = view.findViewById(R.id.No_Radio_button_advanceFee_House);
        Yes_Radio_button_advanceFree_house = view.findViewById(R.id.Yes_Radio_button_advanceFree_house);

        House_lift_No_Radio_button = view.findViewById(R.id.House_lift_No_Radio_button);
        House_lift_Yes_Radio_button = view.findViewById(R.id.House_lift_Yes_Radio_button);

        house_parking_No_Radio_button = view.findViewById(R.id.house_parking_No_Radio_button);
        house_parking_Yes_Radio_button = view.findViewById(R.id.house_parking_Yes_Radio_button);


        //button
//        videoUpload = view.findViewById(R.id.videoUpload);
        houseRentPost = view.findViewById(R.id.houseRentTypePost);
        house_rent_image = view.findViewById(R.id.house_rent_image);


        //editText
        houseRent = view.findViewById(R.id.houseRent);
        HouseRoom = view.findViewById(R.id.HouseRoom);
        houseBathroom = view.findViewById(R.id.houseBathroom);
        user_house_location = view.findViewById(R.id.user_house_location);


        floorId = view.findViewById(R.id.FloorID);
        monthlyPayment = view.findViewById(R.id.monthlyPayment);


        //spinner
        rentType = view.findViewById(R.id.rent_Type_Spinner);
        houseTypeSpinner = view.findViewById(R.id.houseTypeSpinner);
        minimumTimeSpend = view.findViewById(R.id.House_minimumTimeSpend);
        gasSystem = view.findViewById(R.id.House_gasSystem);
        advanceFee_house = view.findViewById(R.id.advanceFee_house);


        //LinerLayoutId
        floorNumberId = view.findViewById(R.id.floorNumberId);
        houseLiftLinerLayout = view.findViewById(R.id.houseLiftLinerLayout);

        houseLiftLinerLayout = view.findViewById(R.id.houseLiftLinerLayout);
        advanceFeeVisibility_house = view.findViewById(R.id.advanceFeeVisibility_house);


        Back_To_Home = view.findViewById(R.id.Back_To_Home);


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.house_type, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseTypeSpinner.setAdapter(arrayAdapter);
        houseTypeSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Rent_Type, android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rentType.setAdapter(arrayAdapter2);
        rentType.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter3 = ArrayAdapter.createFromResource(getContext(), R.array.monthly_payment, android.R.layout.simple_spinner_item);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthlyPayment.setAdapter(arrayAdapter3);
        monthlyPayment.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter4 = ArrayAdapter.createFromResource(getContext(), R.array.minimumTime_Spend, android.R.layout.simple_spinner_item);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minimumTimeSpend.setAdapter(arrayAdapter4);
        minimumTimeSpend.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> arrayAdapter5 = ArrayAdapter.createFromResource(getContext(), R.array.Gas_System, android.R.layout.simple_spinner_item);
        arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gasSystem.setAdapter(arrayAdapter5);
        gasSystem.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> arrayAdapter6 = ArrayAdapter.createFromResource(getContext(), R.array.minimum_advance_fee, android.R.layout.simple_spinner_item);
        arrayAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        advanceFee_house.setAdapter(arrayAdapter6);
        advanceFee_house.setOnItemSelectedListener(this);


        radioGroupLift_house.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.House_lift_Yes_Radio_button:
                        lift = "লিফ্ট এর  ব্যবস্থা আছে, "+House_lift_Yes_Radio_button.getText().toString();
                        break;
                    case R.id.House_lift_No_Radio_button:
                        lift = "লিফ্ট এর  ব্যবস্থা নেই, "+House_lift_No_Radio_button.getText().toString();
                    default:
                        break;
                }

            }
        });


        radioGroupParking_House.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.house_parking_Yes_Radio_button:
                        parking ="পার্কিং এর জন্য ব্যবস্থা আছে, "+ house_parking_Yes_Radio_button.getText().toString();

                    case R.id.house_parking_No_Radio_button:
                        parking = "পার্কিং এর জন্য ব্যবস্থা নেই, "+house_parking_No_Radio_button.getText().toString();

                    default:
                        break;
                }

            }
        });
        Back_To_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();

            }
        });


        house_rent_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        House_radioGroupAdvanceMoney.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.No_Radio_button_advanceFee_House:
                        advanceYesNoreturnString = No_Radio_button_advanceFee_House.getText().toString();
                        houseAdvanceFeeYesNOStringCheck();
                        break;
                    case R.id.Yes_Radio_button_advanceFree_house:
                        advanceYesNoreturnString = Yes_Radio_button_advanceFree_house.getText().toString();
                        houseAdvanceFeeYesNOStringCheck();
                        break;
                    default:

                }


            }
        });


        houseRentPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadHouseRentPost();


            }
        });

        return view;


    }


    private void houseAdvanceFeeYesNOStringCheck() {
        if (advanceYesNoreturnString.equals("হ্যাঁ")) {
            advanceFeeVisibility_house.setVisibility(View.VISIBLE);
        } else if (advanceYesNoreturnString.equals("না")) {
            advanceFeeVisibility_house.setVisibility(View.GONE);
        } else {
            advanceFeeVisibility_house.setVisibility(View.GONE);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        switch (adapterView.getId()) {
            case R.id.houseTypeSpinner:
                houseType = adapterView.getItemAtPosition(i).toString();
                houseTypeCaLl();
                break;
            case  R.id.rent_Type_Spinner:
                rentItemType = adapterView.getItemAtPosition(i).toString();
                break;
            case  R.id.monthlyPayment:
                monthlyPaymentType = adapterView.getItemAtPosition(i).toString();
                break;
            case  R.id.House_minimumTimeSpend:
                minimumTimeSpendType = adapterView.getItemAtPosition(i).toString();
                monthlyPaymentTypeCall();
                break;
            case  R.id.House_gasSystem:
                gasSystemType = adapterView.getItemAtPosition(i).toString();
                break;
            case  R.id.advanceFee_house:
                advanceFeeHouseStringSpinner = adapterView.getItemAtPosition(i).toString();
                break;
            default:
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void houseTypeCaLl() {
        if (houseType.equals(" ")) {

            Toast.makeText(getContext(), "  ", Toast.LENGTH_SHORT).show();
        } else if (houseType.equals("ভবন বাসা")) {
            floorNumberId.setVisibility(View.VISIBLE);
        } else if (houseType.equals("টিন সেট বাসা")) {
            floorNumberId.setVisibility(View.GONE);
        }


    }


    private void monthlyPaymentTypeCall() {

        if (monthlyPaymentType.equals("বাসা ভাড়া কত তারিখের মধ্যে দিতে হবে নির্বাচন করুন?")) {
            Toast.makeText(getContext(), "বাসা ভাড়া কত তারিখের মধ্যে দিতে হবে নির্বাচন করুন?", Toast.LENGTH_SHORT).show();
        }

    }


    private void uploadHouseRentPost() {


        progressDialog.setMessage("wait.........");
        progressDialog.show();
        location = user_house_location.getText().toString().trim();
        floorNumberHouse = floorId.getText().toString().trim();

        housePrice = houseRent.getText().toString().trim();
        houseRoom = HouseRoom.getText().toString().trim();
        houseBathRoom = houseBathroom.getText().toString().trim();


        houseType2 = houseType;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/house.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getContext(), "post is successful", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Intent intent=new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<String, String>();
                data.put("firebase_id", currentUserID);
                data.put("name", name);
                data.put("phn_number", number);
                data.put("location", "বাসার ঠিকানা :" + location);
                data.put("house_type", houseType2+"নম্বর তলাটি ভাড়া দেওয়া হবে");
                data.put("house_floor", floorNumberHouse);
                data.put("house_rnt_type", rentItemType+ " জন্য বাসা ভাড়া দেওয়া হবে");
                data.put("house_rnt_price", "বাসা ভাড়াটা "+housePrice+ "টাকা");
                data.put("house_room", houseRoom+"টি রুম");
                data.put("house_bathrm", houseBathRoom+"টি বাথরুম");
                data.put("house_rnt_date", monthlyPaymentType+"ভাড়া দিতে হবে");
                data.put("house_lv_date", minimumTimeSpendType+"পর্যন্ত থাকতে হবে ");
                   data.put("house_gs_type", gasSystemType+"");
                data.put("house_advc_fee", advanceFeeHouseStringSpinner+"অগ্রিম ভাড়া দিতে হবে");
                data.put("house_parking", parking);
                data.put("house_lift", lift);


                return data;

            }
        };

        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }


    @Override
    public void onStart() {
        super.onStart();


        curruserget();
        curreNumber();


    }

    private void curreNumber() {
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

    private void curruserget() {

        StringRequest request = new StringRequest(Request.Method.GET, "https://famousdb.000webhostapp.com/currentUserId.php?firebase_id=" + currentUserID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


                name = response;


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

}