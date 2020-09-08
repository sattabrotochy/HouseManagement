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

import com.Shuvo.myapplication.Class.FlatBuyerModel;
import com.Shuvo.myapplication.Class.FlatFrontModel;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.FlatShowActivity;

import java.util.ArrayList;

public class FlatAdapter extends RecyclerView.Adapter<FlatAdapter.ViewHolder> {


    Context context;
    ArrayList<FlatBuyerModel> frontModelArrayList;

    public FlatAdapter(Context context, ArrayList<FlatBuyerModel> frontModelArrayList) {
        this.context = context;
        this.frontModelArrayList = frontModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.flat_front_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FlatBuyerModel model = frontModelArrayList.get(position);

        final Integer idd = model.getId();
        holder.Flat_houseUserName.setText(model.getName());
        holder.Flat_houseUserLocation.setText(model.getAddress());
        holder.Flat_houseUserRoom.setText(model.getFlatBadRoom());
        holder.Flat_houseUserPrice.setText(model.getFlatPrice());
        holder.Flat_houseUserFloor.setText(model.getFloorId());



        holder.showAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, FlatShowActivity.class);
                intent.putExtra("id", idd);
                Toast.makeText(context, idd+"", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return frontModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Flat_houseUserName, Flat_houseUserLocation, Flat_houseUserRoom, Flat_houseUserPrice, Flat_houseUserFloor, showAllData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Flat_houseUserName = itemView.findViewById(R.id.Flat_houseUserName);
            Flat_houseUserLocation = itemView.findViewById(R.id.Flat_houseUserLocation);
            Flat_houseUserRoom = itemView.findViewById(R.id.Flat_houseUserRoom);
            Flat_houseUserPrice = itemView.findViewById(R.id.Flat_houseUserPrice);
            Flat_houseUserFloor = itemView.findViewById(R.id.Flat_houseUserFloor);
            showAllData = itemView.findViewById(R.id.showAllData);

        }
    }
}
