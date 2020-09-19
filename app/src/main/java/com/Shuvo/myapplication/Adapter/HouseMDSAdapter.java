package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.HouseMDModel;
import com.Shuvo.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class                                                                                                                                                                                                                                    HouseMDSAdapter extends RecyclerView.Adapter<HouseMDSAdapter.Myview> {



    Context context;
    ArrayList<HouseMDModel> houseMDModelArrayList;
    public  HouseMDSAdapter( Context context,ArrayList<HouseMDModel> houseMDModelArrayList)
    {
        this.context=context;
        this.houseMDModelArrayList=houseMDModelArrayList;
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.house_more_details_show,parent,false);
        return new Myview(view);

    }
    @Override
    public void onBindViewHolder(@NonNull Myview holder, int position)
    {
        final HouseMDModel mdModel=houseMDModelArrayList.get(position);
        holder.phn_number_hs.setText(mdModel.getPhn_number());
        holder.userName.setText(mdModel.getName());
        holder.location.setText(mdModel.getLocation());
        holder.house_type.setText(mdModel.getHouse_type());
        holder.house_floor.setText(mdModel.getHouse_floor());
        holder.house_rnt_type.setText(mdModel.getHouse_rnt_type());
        holder.house_rnt_price.setText(mdModel.getHouse_rnt_price());
        holder.house_room.setText(mdModel.getHouse_room());
        holder.house_bathrm.setText(mdModel.getHouse_bathrm());
        holder.house_rnt_date.setText(mdModel.getHouse_rnt_date());
        holder.house_lv_date.setText(mdModel.getHouse_lv_date());
        holder.house_gs_type.setText(mdModel.getHouse_gs_type());
        holder.house_gs_bill.setText(mdModel.getHouse_gs_bill());
        holder.house_advc_fee.setText(mdModel.getHouse_advc_fee());
        holder.house_parking.setText(mdModel.getHouse_parking());
        holder.house_lift.setText(mdModel.getHouse_lift());

        Picasso.get().load(mdModel.getImage()).into(holder.House_userImage);
        Picasso.get().load(mdModel.getHouse_image()).into(holder.house_image5);

        holder.house_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mdModel.getPhn_number()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return houseMDModelArrayList.size();
    }

    public class Myview extends RecyclerView.ViewHolder {



        TextView userName,location,house_type,house_floor,house_rnt_type,house_rnt_price,house_room,house_bathrm,house_rnt_date,house_lv_date,house_gs_type,house_gs_bill,house_advc_fee,
                house_parking,house_lift,phn_number_hs;

        CircleImageView House_userImage;
        CircleImageView house_call;
        ImageView house_image5;
        public Myview(@NonNull View itemView) {
            super(itemView);

            userName=itemView.findViewById(R.id.userName);
            house_call=itemView.findViewById(R.id.house_call_user);
            phn_number_hs=itemView.findViewById(R.id.phn_number_hs);
            location=itemView.findViewById(R.id.location);
            house_type=itemView.findViewById(R.id.house_type);
            house_floor=itemView.findViewById(R.id.house_floor);
            house_rnt_type=itemView.findViewById(R.id.house_rnt_type);
            house_rnt_price=itemView.findViewById(R.id.house_rnt_price);
            house_room=itemView.findViewById(R.id.house_room);
            house_bathrm=itemView.findViewById(R.id.house_bathrm);
            house_rnt_date=itemView.findViewById(R.id.house_rnt_date);
            house_lv_date=itemView.findViewById(R.id.house_lv_date);
            house_gs_type=itemView.findViewById(R.id.house_gs_type);
            house_gs_bill=itemView.findViewById(R.id.house_gs_bill);
            house_advc_fee=itemView.findViewById(R.id.house_advc_fee);
            house_parking=itemView.findViewById(R.id.house_parking);
            house_lift=itemView.findViewById(R.id.house_lift);
            House_userImage=itemView.findViewById(R.id.House_userImage);
            house_image5=itemView.findViewById(R.id.house_image5);




        }
    }
}
