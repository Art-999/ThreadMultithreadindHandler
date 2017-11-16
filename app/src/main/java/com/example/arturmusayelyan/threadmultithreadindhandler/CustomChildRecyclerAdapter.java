package com.example.arturmusayelyan.threadmultithreadindhandler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by artur.musayelyan on 16/11/2017.
 */
public class CustomChildRecyclerAdapter extends RecyclerView.Adapter<CustomChildRecyclerAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    //private List<ChildrenCats> dataList = Collections.emptyList();
    private ArrayList<ChildrenCats> dataList = new ArrayList<>();
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public CustomChildRecyclerAdapter(Context context, ArrayList<ChildrenCats> list) {
        this.context = context;
        this.dataList = list;
        inflater = LayoutInflater.from(context);
    }

    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv1.setText(dataList.get(position).getId());
        holder.tv2.setText(dataList.get(position).getName());
        holder.tv3.setText(dataList.get(position).getCount() + "");
        // holder.tv1.setTag(dataList.get(position).getChildrenCatsList());
        //holder.bigView.setTag(dataList.get(position).getChildrenCatsList());
    }


    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView tv1, tv2, tv3;
        View bigView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1_customRow);
            tv2 = itemView.findViewById(R.id.tv2_customRow);
            tv3 = itemView.findViewById(R.id.tv3_customRow);
            bigView = itemView.findViewById(R.id.customRow);

        }


    }

    public void delete(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }
}

