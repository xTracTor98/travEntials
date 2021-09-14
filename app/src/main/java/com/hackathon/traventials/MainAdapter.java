package com.hackathon.traventials;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    //Initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //Create constructor
    public MainAdapter(Activity context, List<MainData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //INITIALIZE VIEW
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //Initialize main data
        MainData data = dataList.get(position);
        //Initialize Database
        database = RoomDB.getInstance(context);
        //Set text on text view
        holder.textView.setText(data.getItem());

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //INITIALIZE MAIN DATA
                MainData d = dataList.get(holder.getAdapterPosition());
                /* DELETE FROM DATABASE */
                database.mainDao().delete(d);
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //INITILIZE VARIABLE
        TextView textView;
        ImageView btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //ASSIGN VARIABLE
            textView = itemView.findViewById(R.id.text_view);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
