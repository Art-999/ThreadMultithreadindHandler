package com.example.arturmusayelyan.threadmultithreadindhandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            String id, name;
            int count;


            for (int i = 0; i < jsonArray.length(); i++) {
                List<Object> list = new ArrayList<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString("category_id");
                name = jsonObject.getString("category_name");
                count = jsonObject.getInt("category_count");

                JSONArray jArray = jsonObject.getJSONArray("children_cats");

                if (jArray != null) {
                    for (int j = 0; j < jArray.length(); j++) {
                        JSONObject jsonObject1 = jArray.getJSONObject(j);
                        ChildrenCats childrenCats = new ChildrenCats(jsonObject1.getString("category_id"), jsonObject1.getString("category_name"), jsonObject1.getInt("category_count"));
                        list.add(childrenCats);
                    }
                }
                System.out.println("LIST: " + list);
                Contacts contacts = new Contacts(id, name, count, list);
                //  Contacts contacts = new Contacts(id, name, Integer.parseInt(count));
//                childrenList=jsonObject.getString("children_cats");
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
        // adapter.delete(position);


        // ArrayList<ChildrenCats> list=new ArrayList<>();
        // ArrayList<ChildrenCats> list = (ArrayList<ChildrenCats>) view.getTag();
        // System.out.println("LIST: " + list);
        //  intent.putExtra("child_recycler", new Gson().toJson(list));

        Intent intent = new Intent(this, ChildRecyclerView.class);
//        intent.putExtra("child_recycler", listFromRequest.get(position));
        intent.putExtra("json_data", jsonArray.optJSONObject(position).toString());
        startActivity(intent);
//        intent1(position);
//        intent2(position);
    }

//    public void intent1(int position) {
//        Intent intent = new Intent(this, ChildRecyclerView.class);
//        intent.putExtra("child_recycler", listFromRequest.get(position).getId());
//        startActivity(intent);
//    }
//
//    public void intent2(int position) {
//        Intent intent2 = new Intent(this, ChildRecyclerView.class);
//        intent2.putExtra("json_data", valueFromRequest);
//        startActivity(intent2);
//    }
}
