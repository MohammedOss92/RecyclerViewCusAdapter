package com.example.myrecyclercusadapter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrecyclercusadapter.MsgActivity;
import com.example.myrecyclercusadapter.R;
import com.example.myrecyclercusadapter.model.MsgTypes;

import java.util.List;

public class MsgTypesAdapter extends RecyclerView.Adapter<MsgTypesAdapter.MyViewHolder> {
    private List<MsgTypes> msgTypes_list;
    Context con;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CardView card_view;
//        RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.msg_type);
            card_view = (CardView) view.findViewById(R.id.card_view_typea);
        }    }
    public MsgTypesAdapter(List<MsgTypes> msgTypes_list, Context context) {
        this.con = context;
        this.msgTypes_list = msgTypes_list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.msg_type_design, viewGroup, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(con);
        final MsgTypes m = msgTypes_list.get(position);
        holder.title.setText(m.getName());
//        holder.title.setTextColor(con.getResources().getColor(R.color.colorRed));

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgTypes m = msgTypes_list.get(position);
                Intent i = new Intent(con, MsgActivity.class);
                i.putExtra("titleID", m.getID());
                con.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
//        if(msgTypes_list==null || msgTypes_list.isEmpty()){
//            return 0;
//        }
//        else {
//            return msgTypes_list.size();
//        }
        return msgTypes_list.size();
    }
}


