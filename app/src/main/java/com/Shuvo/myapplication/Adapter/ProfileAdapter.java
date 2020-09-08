package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.ProfileModel;
import com.Shuvo.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyView> {



    Context context;
    ArrayList<ProfileModel> profileModelArrayList;
    public  ProfileAdapter ( Context context,ArrayList<ProfileModel> profileModelArrayList)
    {
        this.context=context;
        this.profileModelArrayList=profileModelArrayList;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.my_profile_layout,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position)
    {
        ProfileModel model=profileModelArrayList.get(position);
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        Picasso.get().load(model.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return profileModelArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView name,address;
        CircleImageView imageView;

        public MyView(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.ProfileImage);
            name=itemView.findViewById(R.id.prof_Name);
            address=itemView.findViewById(R.id.prof_address);



        }
    }
}
