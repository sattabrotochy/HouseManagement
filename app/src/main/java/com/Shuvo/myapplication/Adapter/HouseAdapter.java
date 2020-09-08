package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.frontModel;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.HouseMoreDtlActivity;

import java.util.ArrayList;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    private Context context;
    private ArrayList<frontModel> list;

    public HouseAdapter( Context context ,ArrayList<frontModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.all_post_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        frontModel model = list.get(position);
        final int id=model.getId();
        holder.houseUserName.setText(model.getName());
        holder.houseUserLocation.setText(model.getLocation());
        holder.houseUserPrice.setText(model.getHouse_rnt_price());
        holder.houseUserFloor.setText(model.getHouse_floor());
        holder.houseUserRoom.setText(model.getHouse_room());


        holder.houseMoreSHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(context, HouseMoreDtlActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView houseUserName, houseUserLocation, houseUserRoom, houseUserPrice, houseUserFloor,houseMoreSHow;


        public ViewHolder(View itemView) {
            super(itemView);

            houseUserName = itemView.findViewById(R.id.houseUserName);
            houseUserLocation = itemView.findViewById(R.id.houseUserLocation);
            houseUserRoom = itemView.findViewById(R.id.houseUserRoom);
            houseUserPrice = itemView.findViewById(R.id.houseUserPrice);
            houseUserFloor = itemView.findViewById(R.id.houseUserFloor);
            houseMoreSHow = itemView.findViewById(R.id.houseMoreSHow);


        }
    }
}
