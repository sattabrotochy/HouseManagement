package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LandAdapter extends RecyclerView.Adapter<LandAdapter.MyView> {


    Context context;
    ArrayList<Landrnt> landrntList;

    public LandAdapter(Context context, ArrayList<Landrnt> landrntList) {
        this.context = context;
        this.landrntList = landrntList;
    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.front_land_layout, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {


        Landrnt model = landrntList.get(position);


        final Integer id = model.getId();
        holder.land_houseUserName.setText(model.getName());
        holder.land_houseUserLocation.setText(model.getAddress());
        //holder.land_per_unit_price.setText(model.getPer_qty_price());
       // holder.land_quinty.setText(model.getLand_qty());
        holder.land_Price.setText(model.getLand_price());
        Picasso.get().load(model.getImage()).into(holder.land_user_image);
        Picasso.get().load(model.getLandimage()).into(holder.landImage1);

        if (model.getActive_ckeck().equals("active"))
        {
            holder.showAll_dataFlt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, LandDatShowActivity.class);
                    intent.putExtra("id", id);
                    Toast.makeText(context, id + "", Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);


                }
            });
        }
        else
        {
            holder.showAll_dataFlt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "This post is inactive", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return landrntList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        TextView land_houseUserName, land_houseUserLocation, land_quinty, land_Price, land_per_unit_price, showAll_dataFlt;
        CircleImageView land_user_image;
        ImageView landImage1;

        public MyView(@NonNull View itemView) {
            super(itemView);

            land_houseUserName = itemView.findViewById(R.id.land_houseUserName);
            land_houseUserLocation = itemView.findViewById(R.id.land_houseUserLocation);
          //  land_quinty = itemView.findViewById(R.id.land_quinty);
            land_Price = itemView.findViewById(R.id.land_Price);
            //land_per_unit_price = itemView.findViewById(R.id.land_per_unit_price);
            showAll_dataFlt = itemView.findViewById(R.id.showAll_dataFlt);
            land_user_image = itemView.findViewById(R.id.land_user_image);
            landImage1 = itemView.findViewById(R.id.landImage1);


        }
    }

}
