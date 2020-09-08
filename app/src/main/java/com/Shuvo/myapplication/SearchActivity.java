package com.Shuvo.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;

import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Shuvo.myapplication.Adapter.FlatmrDtsAdapter;
import com.Shuvo.myapplication.Adapter.SearchAdapter;
import com.Shuvo.myapplication.Class.FlatMoreDetls;
import com.Shuvo.myapplication.Class.RequestHandler;
import com.Shuvo.myapplication.Class.SrchModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {





    SearchView searchView;
    String SEARCH_ITEM;
    SearchAdapter adapter;
    RecyclerView search_item;
    ArrayList<SrchModel> srchModels;
    Context context=SearchActivity.this;
    ProgressDialog progressDialog;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        searchView=findViewById(R.id.search_name);
        search_item=findViewById(R.id.search_item);


        progressDialog=new ProgressDialog(this);

        srchModels=new ArrayList<>();

        DataLoadSearch();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


    }

    private void DataLoadSearch()
    {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("wait..........");
                progressDialog.show();

            }

            @Override
            protected void onPostExecute(String shl) {
                super.onPostExecute(shl);
                 SEARCH_ITEM= shl;
                progressDialog.dismiss();

                FlatPostShow();
            }

            @Override
            protected String doInBackground(Void... params) {

                RequestHandler handler3 = new RequestHandler();
                String shl = handler3.sendGetRequest("https://famousdb.000webhostapp.com/search.php");
                return shl;

            }
        }
        GetJSON gjdl = new GetJSON();
        gjdl.execute();

    }

    private void FlatPostShow() {

        try {
            JSONObject jsonObject = new JSONObject(SEARCH_ITEM);
            JSONArray result = jsonObject.getJSONArray("output5");

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                srchModels.add(new SrchModel(

                        object.getInt("id"),
                        object.getString("name"),
                        object.getString("address"),
                        object.getString("number")
                ));
            }


            adapter = new SearchAdapter(context,srchModels);
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            search_item.setHasFixedSize(true);
            search_item.setLayoutManager(layoutManager);
            search_item.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }

}

