package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.Landrnt;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.LandDatShowActivity;

import java.util.ArrayList;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.land_item_post, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {


        Landrnt model = landrntList.get(position);


        final Integer id = model.getId();
        holder.land_houseUserName.setText(model.getName());
        holder.land_houseUserLocation.setText(model.getAddress());
        holder.land_per_unit_price.setText(model.getPer_qty_price());
        holder.land_quinty.setText(model.getLand_qty());
        holder.land_Price.setText(model.getLand_price());

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

    @Override
    public int getItemCount() {
        return landrntList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        TextView land_houseUserName, land_houseUserLocation, land_quinty, land_Price, land_per_unit_price, showAll_dataFlt;

        public MyView(@NonNull View itemView) {
            super(itemView);

            land_houseUserName = itemView.findViewById(R.id.land_houseUserName);
            land_houseUserLocation = itemView.findViewById(R.id.land_houseUserLocation);
            land_quinty = itemView.findViewById(R.id.land_quinty);
            land_Price = itemView.findViewById(R.id.land_Price);
            land_per_unit_price = itemView.findViewById(R.id.land_per_unit_price);
            showAll_dataFlt = itemView.findViewById(R.id.showAll_dataFlt);

        }
    }

}
