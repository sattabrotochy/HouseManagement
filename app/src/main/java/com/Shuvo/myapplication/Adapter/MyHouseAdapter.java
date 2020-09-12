package com.Shuvo.myapplication.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.frontModel;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.HouseMoreDtlActivity;
import com.Shuvo.myapplication.UpdateAllData.EditHouseActivity;
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

public class MyHouseAdapter extends RecyclerView.Adapter<MyHouseAdapter.MYView> {

    Context context;
    private ArrayList<frontModel> myHouseLists;

    public MyHouseAdapter(Context context, ArrayList<frontModel> myHouseLists) {
        this.context = context;
        this.myHouseLists = myHouseLists;

    }

    @NonNull
    @Override
    public MYView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.active_inactive_post_layout_item, parent, false);

        return new MYView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MYView holder, int position) {

        frontModel model = myHouseLists.get(position);

        final int id = model.getId();
        holder.houseUserName.setText(model.getName());
        holder.houseUserLocation.setText(model.getLocation());
        holder.houseUserPrice.setText(model.getHouse_rnt_price());
        holder.houseUserFloor.setText(model.getHouse_floor());
        holder.houseUserRoom.setText(model.getHouse_room());
        Picasso.get().load(model.getImage()).into(holder.ActiveProfileImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
                                Intent intent = new Intent(context, HouseMoreDtlActivity.class);
                                intent.putExtra("id", id);
                                Toast.makeText(context, id + "", Toast.LENGTH_SHORT).show();
                                context.startActivity(intent);
                                break;
                            case 1:
                                Intent intent1=new Intent(context, EditHouseActivity.class);
                                intent1.putExtra("id",id);
                                context.startActivity(intent1);
                                break;
                            case 2:
                                HouseDataDelete(id);
                            default:
                                break;
                        }

                    }
                });

                builder.create().show();

            }
        });


    }

    private void HouseDataDelete(final int id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/myHouseRdelete.php",
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
        return myHouseLists.size();
    }

    public class MYView extends RecyclerView.ViewHolder {

        TextView houseUserName, houseUserLocation, houseUserRoom, houseUserPrice, houseUserFloor;
        CircleImageView ActiveProfileImage;

        public MYView(@NonNull View itemView) {
            super(itemView);

            houseUserName = itemView.findViewById(R.id.landUserName);
            houseUserLocation = itemView.findViewById(R.id.land_location);
            houseUserRoom = itemView.findViewById(R.id.per_qty_price);
            houseUserPrice = itemView.findViewById(R.id.land_price);
            houseUserFloor = itemView.findViewById(R.id.land_qunty);
            ActiveProfileImage = itemView.findViewById(R.id.ActiveProfileImage);
        }
    }
}
