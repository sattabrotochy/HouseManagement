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

import com.Shuvo.myapplication.Class.LandMrDtSWModel;
import com.Shuvo.myapplication.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LandMDSwAdapter extends RecyclerView.Adapter<LandMDSwAdapter.MyView> {


    Context context;
    ArrayList<LandMrDtSWModel> mrDtSWModelArrayList;


    public LandMDSwAdapter(Context context, ArrayList<LandMrDtSWModel> mrDtSWModelArrayList) {

        this.context = context;
        this.mrDtSWModelArrayList = mrDtSWModelArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.landmoredetails_show_item, parent, false);
        return new MyView(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {


        final LandMrDtSWModel model = mrDtSWModelArrayList.get(position);

        holder.phn_number.setText(model.getPhn_number());
        holder.Land_userName.setText(model.getName());
        holder.land_userAddress.setText(model.getAddress());
        holder.land_userqnty.setText(model.getLand_qty());
        holder.land_userPrice.setText(model.getLand_price());
        holder.land_user_per_Unit_price.setText(model.getPer_qty_price());
        holder.land_userMontlyPayDate.setText(model.getLand_py_Date());
        holder.land_userLevDate.setText(model.getMini_leave_date());
        holder.land_userAdnFee.setText(model.getMini_adv_date());



        holder.call_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + model.getPhn_number()));
               context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mrDtSWModelArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView Land_userName, land_userAddress, land_userqnty,phn_number ,land_userPrice, land_user_per_Unit_price, land_userMontlyPayDate, land_userLevDate, land_userAdnFee;

        CircleImageView call_user;
        public MyView(@NonNull View itemView) {
            super(itemView);



            Land_userName = itemView.findViewById(R.id.Land_userName1);
            call_user = itemView.findViewById(R.id.call_user);
            phn_number = itemView.findViewById(R.id.land_phone);
            land_userAddress = itemView.findViewById(R.id.land_userAddress1);
            land_userqnty = itemView.findViewById(R.id.land_userqnty1);
            land_userPrice = itemView.findViewById(R.id.land_userPrice1);
            land_user_per_Unit_price = itemView.findViewById(R.id.land_user_per_Unit_price1);
            land_userMontlyPayDate = itemView.findViewById(R.id.land_userMontlyPayDate1);
            land_userLevDate = itemView.findViewById(R.id.land_userLevDate1);
            land_userAdnFee = itemView.findViewById(R.id.land_userAdnFee1);
        }
    }
}
