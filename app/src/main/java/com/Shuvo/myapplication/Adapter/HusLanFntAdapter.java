package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.HuslndModelFront;
import com.Shuvo.myapplication.R;
import com.Shuvo.myapplication.ShowActivity.HusLandMDActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HusLanFntAdapter extends RecyclerView.Adapter<HusLanFntAdapter.MyView> {


    Context context;
    ArrayList<HuslndModelFront> husLndMDModelArrayList;

    public  HusLanFntAdapter( Context context, ArrayList<HuslndModelFront> husLndMDModelArrayList)
    {
        this.context=context;
        this.husLndMDModelArrayList=husLndMDModelArrayList;
    }



    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.front_husland_layout_new, parent, false);
        return new MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position)
    {
        HuslndModelFront modelFront=husLndMDModelArrayList.get(position);

       final int id=modelFront.getId();
        holder.Flat_houseUserName.setText(modelFront.getName());
        holder.Flat_houseUserLocation.setText(modelFront.getAddress());
      //  holder.Flat_houseUserRoom.setText(modelFront.getHoulan_qunty());
        holder.Flat_houseUserPrice.setText(modelFront.getHouLnd_Price());
     //   holder.Flat_houseUserFloor.setText(modelFront.getHouse_floor());
        Picasso.get().load(modelFront.getImage()).into(holder.profImage);
        Picasso.get().load(modelFront.getHusLnd_image()).into(holder.flat_image);




        holder.showAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(context, HusLandMDActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return husLndMDModelArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        TextView Flat_houseUserName, Flat_houseUserLocation, Flat_houseUserRoom, Flat_houseUserPrice, Flat_houseUserFloor, showAllData;

        CircleImageView profImage;

        ImageView flat_image;
        public MyView(@NonNull View itemView) {
            super(itemView);



            Flat_houseUserName = itemView.findViewById(R.id.Flat_houseUserName);
            Flat_houseUserLocation = itemView.findViewById(R.id.Flat_houseUserLocation);
            Flat_houseUserRoom = itemView.findViewById(R.id.Flat_houseUserRoom);
            Flat_houseUserPrice = itemView.findViewById(R.id.Flat_houseUserPrice);
            Flat_houseUserFloor = itemView.findViewById(R.id.Flat_houseUserFloor);
            showAllData = itemView.findViewById(R.id.showAllData);
            profImage = itemView.findViewById(R.id.profImage);
            flat_image = itemView.findViewById(R.id.flat_image);
        }
    }
}
