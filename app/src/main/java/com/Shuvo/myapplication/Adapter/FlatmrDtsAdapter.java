package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.FlatMoreDetls;
import com.Shuvo.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FlatmrDtsAdapter extends RecyclerView.Adapter<FlatmrDtsAdapter.MyView> {

    Context context;
    ArrayList<FlatMoreDetls> flatMoreDetlsArrayList;

    public FlatmrDtsAdapter(Context context, ArrayList<FlatMoreDetls> flatMoreDetlsArrayList) {
        this.context = context;
        this.flatMoreDetlsArrayList = flatMoreDetlsArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flatdoredetailslayout, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {

        final FlatMoreDetls model = flatMoreDetlsArrayList.get(position);


        holder.name.setText(model.getName());
        holder.phn_number_flat.setText(model.getPhn_number());
        holder.address.setText(model.getAddress());
        holder.floorId.setText(model.getFloorId());
        holder.FlatBadRoom.setText(model.getFlatBadRoom());
        holder.FlatBathroom.setText(model.getFlatBathroom());
        holder.FlatQunty.setText(model.getFlatQunty());
        holder.FlatPrice.setText(model.getFlatPrice());
        holder.parkingYesNo.setText(model.getParkingYesNo());
        holder.Active_Inactive.setText(model.getActive_Inactive());
        holder.image.setText(model.getImage());
        Picasso.get().load(model.getImage()).into(holder.flat_u_prf);


        holder.flat_call_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + model.getPhn_number()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return flatMoreDetlsArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        TextView name, address,phn_number_flat,floorId, FlatBadRoom, FlatBathroom, FlatQunty, FlatPrice, parkingYesNo, Active_Inactive, image;

        CircleImageView flat_call_user,flat_u_prf;

        public MyView(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.flat_userName12);
            flat_call_user = itemView.findViewById(R.id.flat_call_user);
            phn_number_flat = itemView.findViewById(R.id.phn_number_flat);
            address = itemView.findViewById(R.id.flat_userAddress12);
            floorId = itemView.findViewById(R.id.flat_per_Unit_price12);
            FlatPrice = itemView.findViewById(R.id.flat_userPrice12);
            FlatBadRoom = itemView.findViewById(R.id.flat_userMontlyPayDate12);
            FlatBathroom = itemView.findViewById(R.id.flat_userLevDate12);
            FlatQunty = itemView.findViewById(R.id.flat_userqnty12);
            parkingYesNo = itemView.findViewById(R.id.flat_userAdnFee12);
            Active_Inactive = itemView.findViewById(R.id.flat_activity);
            image = itemView.findViewById(R.id.image);
            flat_u_prf = itemView.findViewById(R.id.flat_u_prf);




        }
    }
}
