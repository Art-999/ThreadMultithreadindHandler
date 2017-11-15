package com.example.arturmusayelyan.threadmultithreadindhandler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by artur.musayelyan on 15/11/2017.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Contacts> dataList = Collections.emptyList();
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public CustomRecyclerAdapter(Context context, List<Contacts> list) {
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
        holder.tv3.setText(dataList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv1, tv2, tv3;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1_customRow);
            tv2 = itemView.findViewById(R.id.tv2_customRow);
            tv3 = itemView.findViewById(R.id.tv3_customRow);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onItemClict(v, getAdapterPosition());
        }
    }

    public void delete(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }
}
