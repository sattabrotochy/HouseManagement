package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.LandMrDtSWModel;
import com.Shuvo.myapplication.R;

import java.util.ArrayList;

public class EditLandApadter extends RecyclerView.Adapter<EditLandApadter.MyView>
{


    Context context;
    ArrayList<LandMrDtSWModel> mrDtSWModelArrayList;


    public EditLandApadter(Context context, ArrayList<LandMrDtSWModel> mrDtSWModelArrayList) {

        this.context = context;
        this.mrDtSWModelArrayList = mrDtSWModelArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.edit_land_rent_layout, parent, false);
        return new MyView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position)
    {

        LandMrDtSWModel model = mrDtSWModelArrayList.get(position);
        holder.Land_userName.setText(model.getName());
        holder.land_userAddress.setText(model.getAddress());
        holder.land_userqnty.setText(model.getLand_qty());
        holder.land_userPrice.setText(model.getLand_price());
        holder.land_user_per_Unit_price.setText(model.getPer_qty_price());
        holder.land_userMontlyPayDate.setText(model.getLand_py_Date());
        holder.land_userLevDate.setText(model.getMini_leave_date());
        holder.land_userAdnFee.setText(model.getMini_adv_date());

    }

    @Override
    public int getItemCount() {
        return mrDtSWModelArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView Land_userName, land_userAddress, land_userqnty, land_userPrice, land_user_per_Unit_price, land_userMontlyPayDate, land_userLevDate, land_userAdnFee;

        public MyView(@NonNull View itemView) {
            super(itemView);



            Land_userName = itemView.findViewById(R.id.Land_userName1);
            land_userAddress = itemView.findViewById(R.id.land_userAddressEdit);
            land_userqnty = itemView.findViewById(R.id.land_userqntyEdit);
            land_userPrice = itemView.findViewById(R.id.land_userPriceEdit);
            land_user_per_Unit_price = itemView.findViewById(R.id.land_user_per_Unit_priceEdit);
            land_userMontlyPayDate = itemView.findViewById(R.id.land_userMontlyPayDateEdit);
            land_userLevDate = itemView.findViewById(R.id.land_userLevDateEdit);
            land_userAdnFee = itemView.findViewById(R.id.land_userAdnFeeEdit);
        }
    }
}
