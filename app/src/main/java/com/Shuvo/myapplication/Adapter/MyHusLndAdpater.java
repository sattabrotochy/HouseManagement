package com.Shuvo.myapplication.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.HuslndModelFront;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.HusLandMDActivity;
import com.Shuvo.myapplication.UpdateAllData.EditHusLandActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyHusLndAdpater extends RecyclerView.Adapter<MyHusLndAdpater.MyView> {

    Context context;
    ArrayList<HuslndModelFront> modelFronts;

    public MyHusLndAdpater(Context context, ArrayList<HuslndModelFront> modelFronts) {
        this.context = context;
        this.modelFronts = modelFronts;
    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.my_front_layout_new, parent, false);
        return new MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {


        HuslndModelFront modelFront = modelFronts.get(position);
        final int id = modelFront.getId();
        holder.Flat_houseUserName.setText(modelFront.getName());
        holder.Flat_houseUserLocation.setText(modelFront.getAddress());
       // holder.Flat_houseUserRoom.setText(modelFront.getHoulan_qunty());
        holder.Flat_houseUserPrice.setText(modelFront.getHouLnd_Price());
       // holder.Flat_houseUserFloor.setText(modelFront.getHouse_floor());
        Picasso.get().load(modelFront.getImage()).into(holder.ActiveProfileImage);
        Picasso.get().load(modelFront.getHusLnd_image()).into(holder.landImage);

        if (modelFront.getActive_inactive().equals("active"))
        {

            holder.act_chk.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.intcv_chk.setVisibility(View.VISIBLE);
        }



        holder.itemShow_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dilofItem = {"More Details", "Edit Data", "Delete Data"};
                builder.setItems(dilofItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(context, HusLandMDActivity.class);
                                intent.putExtra("id", id);
                                Toast.makeText(context, id + "", Toast.LENGTH_SHORT).show();
                                context.startActivity(intent);
                                break;
                            case 1:
                                Intent intent1=new Intent(context, EditHusLandActivity.class);
                                intent1.putExtra("id",id);
                                context.startActivity(intent1);

                                break;
                            case 2:
                                HouselndDataDelete(id);
                            default:
                                break;
                        }

                    }
                });

                builder.create().show();

            }
        });


    }

    private void HouselndDataDelete(final int id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/myHusLndDelete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Post delete successful", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error + "Post not delete", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", String.valueOf(id));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);


    }

    @Override
    public int getItemCount() {
        return modelFronts.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView Flat_houseUserName, Flat_houseUserLocation, Flat_houseUserRoom, Flat_houseUserPrice, Flat_houseUserFloor, showAllData;

        CircleImageView ActiveProfileImage;
        TextView act_chk,intcv_chk;
        ImageView landImage;
        TextView itemShow_data;

        public MyView(@NonNull View itemView) {
            super(itemView);


            Flat_houseUserName = itemView.findViewById(R.id.landUserName);
            Flat_houseUserLocation = itemView.findViewById(R.id.land_location);
            Flat_houseUserRoom = itemView.findViewById(R.id.per_qty_price);
            Flat_houseUserPrice = itemView.findViewById(R.id.land_price);
            Flat_houseUserFloor = itemView.findViewById(R.id.land_qunty);
            showAllData = itemView.findViewById(R.id.showAllData);
            ActiveProfileImage = itemView.findViewById(R.id.ActiveProfileImage);
            landImage = itemView.findViewById(R.id.landImage);
            itemShow_data = itemView.findViewById(R.id.itemShow_data);


            act_chk = itemView.findViewById(R.id.act_chk1);
            intcv_chk = itemView.findViewById(R.id.intcv_chk1);



        }
    }
}
