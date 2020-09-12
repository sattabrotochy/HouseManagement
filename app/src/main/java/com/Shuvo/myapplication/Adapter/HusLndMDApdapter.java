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

import com.Shuvo.myapplication.Class.HusLndMDModel;
import com.Shuvo.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HusLndMDApdapter  extends RecyclerView.Adapter<HusLndMDApdapter.MyView> {


    Context context;
    ArrayList<HusLndMDModel> husLndMDModelArrayList;


    public HusLndMDApdapter (Context context, ArrayList<HusLndMDModel> husLndMDModelArrayList)
    {
        this.context=context;
        this.husLndMDModelArrayList=husLndMDModelArrayList;

    }



    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.hus_lan_more_detls_lyout,parent,false);

        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position)
    {
        final HusLndMDModel model=husLndMDModelArrayList.get(position);


        holder.name.setText(model.getName());
        holder.number.setText(model.getPhn_number());
        holder.address.setText(model.getAddress());
        holder.houlan_type.setText(model.getHoulan_type());
        holder.houselan_floor.setText(model.getHouse_floor());
        holder.houlan_qunty.setText(model.getHoulan_qunty());
        holder.houLnd_Price.setText(model.getHouLnd_Price());
        holder.houlan_document.setText(model.getHoulan_document());
        holder.houLan_taxClr.setText(model.getHouLan_taxClr());
        holder.houlan_parking.setText(model.getHoulan_parking());
        holder.active_inactive.setText(model.getActive_inactive());
        Picasso.get().load(model.getImage()).into(holder.House_userImage);
        holder.husLand_User_call.setOnClickListener(new View.OnClickListener() {
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
        return husLndMDModelArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        TextView name,address,number,houlan_type,houselan_floor,houlan_qunty,houLnd_Price,houlan_document,houLan_taxClr,houlan_parking,active_inactive;


        CircleImageView husLand_User_call,House_userImage;

        public MyView(@NonNull View itemView) {
            super(itemView);



            name=itemView.findViewById(R.id.name);
            husLand_User_call=itemView.findViewById(R.id.husLand_User_call);
            number=itemView.findViewById(R.id.phn_number);
            address=itemView.findViewById(R.id.address);
            houlan_type=itemView.findViewById(R.id.houlan_type);
            houselan_floor=itemView.findViewById(R.id.houselan_floor);
            houlan_qunty=itemView.findViewById(R.id.houlan_qunty);
            houLnd_Price=itemView.findViewById(R.id.houLnd_Price);
            houlan_document=itemView.findViewById(R.id.houlan_document);
            houLan_taxClr=itemView.findViewById(R.id.houLan_taxClr);
            houlan_parking=itemView.findViewById(R.id.houlan_parking);
            active_inactive=itemView.findViewById(R.id.active_inactive);
            House_userImage=itemView.findViewById(R.id.House_userImage);


        }
    }
}
