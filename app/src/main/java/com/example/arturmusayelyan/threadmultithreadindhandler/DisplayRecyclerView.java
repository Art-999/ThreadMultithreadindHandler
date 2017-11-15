package com.example.arturmusayelyan.threadmultithreadindhandler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayRecyclerView extends AppCompatActivity implements RecyclerViewOnItemClickListener {
    private RecyclerView recyclerView;
    private JSONArray jsonArray;
    private String valueFromRequest;
    private List<Contacts> listFromRequest;
    private CustomRecyclerAdapter adapter;
    JSONObject jsonArray1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        listFromRequest = new ArrayList<>();

        valueFromRequest = getIntent().getExtras().getString("json_data");
        try {
            jsonArray = new JSONArray(valueFromRequest);
            // jsonArray1=new JSONArray(jsonArray.getJSONObject(0));
            String id, name, count,childrenList;
            List<ChildrenCats> list;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString("category_id");
                name = jsonObject.getString("category_name");
                count = jsonObject.getString("category_count");
//                list= (List<ChildrenCats>) jsonObject.getJSONArray("children_cats");
//                Contacts contacts = new Contacts(id, name, Integer.parseInt(count),list);
                Contacts contacts = new Contacts(id, name, Integer.parseInt(count));
                childrenList=jsonObject.getString("children_cats");
                listFromRequest.add(contacts);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new CustomRecyclerAdapter(this, listFromRequest);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setRecyclerViewOnItemClickListener(this);
    }

    public void delete(int position) {
        listFromRequest.remove(position);

    }

    @Override
    public void onItemClict(View view, int position) {
        Toast.makeText(this, "position " + position, Toast.LENGTH_SHORT).show();
        adapter.delete(position);
    }
}
