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

import com.Shuvo.myapplication.Class.FlatFrontModel;
import com.Shuvo.myapplication.Class.FlatMoreDetls;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.FlatShowActivity;
import com.Shuvo.myapplication.UpdateAllData.EditFlatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyFlatAdapter extends RecyclerView.Adapter<MyFlatAdapter.MyView> {


    Context context;
    ArrayList<FlatFrontModel> frontModelArrayList;

    public MyFlatAdapter(Context context, ArrayList<FlatFrontModel> frontModelArrayList) {
        this.context = context;
        this.frontModelArrayList = frontModelArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.active_inactive_post_layout_item, parent, false);
        return new MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        FlatFrontModel model = frontModelArrayList.get(position);

        final Integer idd = model.getId();
        holder.Flat_houseUserName.setText(model.getName());
        holder.Flat_houseUserLocation.setText(model.getAddress());
        holder.Flat_houseUserRoom.setText(model.getFlatBadRoom());
        holder.Flat_houseUserPrice.setText(model.getFlatPrice());
        holder.Flat_houseUserFloor.setText(model.getFloorId());



        final FlatMoreDetls moreDetls=new FlatMoreDetls();

        if (model.getActive_Inactive().equals(" Your post is active")) {
            holder.act_chk.setVisibility(View.VISIBLE);
        } else if (model.getActive_Inactive().equals(" Your post is Inactive ")) {
            holder.intcv_chk.setVisibility(View.VISIBLE);
        }
        else {
            holder.intcv_chk.setVisibility(View.VISIBLE);
        }

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
                                Intent intent = new Intent(context, FlatShowActivity.class);
                                intent.putExtra("id", idd);

                                Toast.makeText(context, idd + "", Toast.LENGTH_SHORT).show();
                                context.startActivity(intent);
                                break;
                            case 1:
                                Intent intent1=new Intent(context, EditFlatActivity.class);
                               intent1.putExtra("id",idd);
                              // intent1.putExtra("model", (Serializable) moreDetls);
                                Toast.makeText(context, idd+"", Toast.LENGTH_SHORT).show();
                               context.startActivity(intent1);
                                break;
                            case 2:
                                FlatDataDelete(idd);
                            default:
                                break;
                        }

                    }
                });

                builder.create().show();

            }
        });

    }

    private void FlatDataDelete(final int idd) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/myflatdelete.php",
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

                params.put("id", String.valueOf(idd));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);


    }

    @Override
    public int getItemCount() {
        return frontModelArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView Flat_houseUserName, Flat_houseUserLocation, Flat_houseUserRoom, Flat_houseUserPrice, Flat_houseUserFloor, showAllData,act_intc_chk,act_chk,intcv_chk;


        public MyView(@NonNull View itemView) {
            super(itemView);


            Flat_houseUserName = itemView.findViewById(R.id.landUserName);
            Flat_houseUserLocation = itemView.findViewById(R.id.land_location);
            Flat_houseUserRoom = itemView.findViewById(R.id.land_qunty);
            Flat_houseUserPrice = itemView.findViewById(R.id.land_price);
            Flat_houseUserFloor = itemView.findViewById(R.id.per_qty_price);
            act_intc_chk = itemView.findViewById(R.id.act_intc_chk);
            act_chk = itemView.findViewById(R.id.act_chk);
            intcv_chk = itemView.findViewById(R.id.intcv_chk);
        }
    }
}
