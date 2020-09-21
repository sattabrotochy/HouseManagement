package com.Shuvo.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.Landrnt;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.LandDatShowActivity;
import com.Shuvo.myapplication.UpdateAllData.EditlandPostActivity;
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

import static android.content.ContentValues.TAG;

public class MyLandPostAdapter extends RecyclerView.Adapter<MyLandPostAdapter.MyView> {

    String LAND_POST_DELETE;
    Context context;
    ArrayList<Landrnt> landPostList;

    public MyLandPostAdapter(Context context, ArrayList<Landrnt> landPostList) {
        this.context = context;
        this.landPostList = landPostList;

    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_front_layout_new, parent, false);
        return new MyView(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {

        Landrnt model = landPostList.get(position);

        final Integer id = model.getId();
      //  holder.land_qunty.setText(model.getLand_qty());
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
      //  holder.per_qty_price.setText(model.getPer_qty_price());
        holder.land_price.setText(model.getLand_price());

        Picasso.get().load(model.getImage()).into(holder.userActiveProfileImage);
        Picasso.get().load(model.getLandimage()).into(holder.landImage);



        if (model.getActive_ckeck().equals("active")) {
            holder.act_chk.setVisibility(View.VISIBLE);
        } else if (model.getActive_ckeck().equals("inactive")) {
            holder.intcv_chk.setVisibility(View.VISIBLE);
        }

        Log.d(TAG, "onBindViewHolder: " + model.getActive_ckeck());

        final String data = "data";

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
                                Intent intent = new Intent(context, LandDatShowActivity.class);
                                intent.putExtra("id", id);
                                Toast.makeText(context, id + "", Toast.LENGTH_SHORT).show();
                                context.startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = new Intent(context, EditlandPostActivity.class);
                                intent1.putExtra("id", id);
                                Toast.makeText(context, id + "", Toast.LENGTH_SHORT).show();
                                context.startActivity(intent1);
                                break;
                            case 2:
                                DataDelete(id);
                            default:
                                break;
                        }

                    }
                });

                builder.create().show();

            }
        });


    }

    private void DataDelete(final Integer id) {


        StringRequest request = new StringRequest(Request.Method.POST, "https://famousdb.000webhostapp.com/mylandPostDelete.php",
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
        return landPostList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView name, address, land_qunty, land_price, per_qty_price, act_intc_chk,act_chk,intcv_chk;

        CircleImageView userActiveProfileImage;
        ImageView landImage;
        TextView itemShow_data;

        public MyView(@NonNull View itemView) {
            super(itemView);

            land_qunty = itemView.findViewById(R.id.land_qunty);
            name = itemView.findViewById(R.id.landUserName);
            address = itemView.findViewById(R.id.land_location);
            land_price = itemView.findViewById(R.id.land_price);
            per_qty_price = itemView.findViewById(R.id.per_qty_price);
            act_intc_chk = itemView.findViewById(R.id.act_intc_chk);
            act_chk = itemView.findViewById(R.id.act_chk1);
            intcv_chk = itemView.findViewById(R.id.intcv_chk1);
            userActiveProfileImage = itemView.findViewById(R.id.ActiveProfileImage);
            landImage = itemView.findViewById(R.id.landImage);
            itemShow_data = itemView.findViewById(R.id.itemShow_data);

        }
    }
}
