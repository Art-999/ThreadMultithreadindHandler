package com.example.arturmusayelyan.threadmultithreadindhandler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChildRecyclerView extends AppCompatActivity {
    private RecyclerView childRecyclerView;
    private DisplayRecyclerView data;
    private JSONObject childJson;
    private String valueFromRequest;
    private JSONArray jsonArray;
    private ArrayList<ChildrenCats> listFromRequest;
    private CustomChildRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_recycler_view);
        childRecyclerView = findViewById(R.id.childRecyclerView);
        //data=new Gson().fromJson( getIntent().getStringExtra("category_count"),DisplayRecyclerView.class);
        listFromRequest = new ArrayList<>();
        try {
            childJson = new JSONObject(getIntent().getExtras().getString("json_data"));
            jsonArray = childJson.getJSONArray("children_cats");
            String id, name;
            int count;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString("category_id");
                name = jsonObject.getString("category_name");
                count = jsonObject.getInt("category_count");

                ChildrenCats childrenCats = new ChildrenCats(id, name, count);
                listFromRequest.add(childrenCats);
            }
            System.out.println("Lists: " + listFromRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.println("LIST:  " + childJson);

//        valueFromRequest = getIntent().getExtras().getString("json_data");
//        System.out.println("LIST:  " + valueFromRequest);

//        try {
//            jsonArray = new JSONArray(valueFromRequest);
//            String id, name;
//            int count;
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                if (jsonObject.getString("category_id") == parentId) {
//                    id = jsonObject.getString("category_id");
//                    name = jsonObject.getString("category_name");
//                    count = jsonObject.getInt("category_count");
//
//
//                    ChildrenCats childrenCats = new ChildrenCats(id, name, count);
//                    listFromRequest.add(childrenCats);
//                    System.out.println("LIST: " + listFromRequest);
//                    break;
//                }
//
//                //  Contacts contacts = new Contacts(id, name, Integer.parseInt(count));
////                childrenList=jsonObject.getString("children_cats");
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        adapter = new CustomChildRecyclerAdapter(this, listFromRequest);
        childRecyclerView.setAdapter(adapter);
        childRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
