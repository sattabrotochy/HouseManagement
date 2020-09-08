package com.Shuvo.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Class.SrchModel;
import com.Shuvo.myapplication.R;

import java.util.ArrayList;
import java.util.Collection;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {


    Context context;
    ArrayList<SrchModel> srchModels;
    ArrayList<SrchModel> srchModelArrayListFull;


    public  SearchAdapter ( Context context, ArrayList<SrchModel> srchModels)
    {
        this.context=context;
        this.srchModels=srchModels;
        srchModelArrayListFull=new ArrayList<>(srchModels);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(context).inflate(R.layout.search_item_list,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        SrchModel model=srchModels.get(position);

        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.number.setText(model.getNumber());


    }

    @Override
    public int getItemCount() {
        return srchModels.size();
    }

    @Override
    public Filter getFilter() {
        return exampleSrch;
    }
    private Filter exampleSrch=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<SrchModel> filteredList=new ArrayList<>();

            if (charSequence==null || charSequence.length()==0)
            {

                filteredList.addAll(srchModelArrayListFull);

            }
            else
            {
                String filterPatten=charSequence.toString().toLowerCase().trim();
                for (SrchModel item:srchModelArrayListFull)
                {
                    if (item.getName().toLowerCase().contains(filterPatten))
                    {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
             srchModels.clear();
             srchModels.addAll((ArrayList) filterResults.values);
             notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,address,number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            name=itemView.findViewById(R.id.Srch_name);
            address=itemView.findViewById(R.id.Srch_address);
            number=itemView.findViewById(R.id.Srch_number);

        }
    }
}
