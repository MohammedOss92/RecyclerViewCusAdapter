package com.example.myrecyclercusadapter.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrecyclercusadapter.R;
import com.example.myrecyclercusadapter.database.DataBase;
import com.example.myrecyclercusadapter.model.Msg;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MyViewHolder> {
    private List<Msg> msg_list;
    Context con;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title,tv_Name;
        ImageView img_fav,img_share,img_copy,img_shared;
        RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tvTitle);
            tv_Name = (TextView) view.findViewById(R.id.txt_msg);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.card_view);
            img_fav =(ImageView) view.findViewById(R.id.img_fav);
        }    }
    public MsgAdapter(List<Msg> msg_list, Context context) {
        this.con = context;
        this.msg_list = msg_list;
    }
    @NonNull
    @Override
    public MsgAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.msg_design, viewGroup, false);
        return new MsgAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MsgAdapter.MyViewHolder holder, final int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(con);
        final Msg m = msg_list.get(position);
        DataBase s = new DataBase(con);
        int titleId = m.getID_Categry();
        String titleDesc = s.getMsgTitleByTitleID(titleId);
        holder.tv_title.setText(titleDesc);
        holder.tv_Name.setText(m.getCon_Name());

        final  DataBase d=new DataBase(con);
        if (d.getIFMsgIsFav(m) == 0) {
            holder.img_fav.setImageResource(R.mipmap.nf);

        } else {
            holder.img_fav.setImageResource(R.mipmap.f);
        }

        holder.img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (d.getIFMsgIsFav(m) == 0) {
                    holder.img_fav.setImageResource(R.mipmap.f);
                    d.changeFav(m, 1);
                    Toast.makeText(con, "تم الإضافة إلى المفضلة", Toast.LENGTH_LONG).show();
                } else {
                    holder.img_fav.setImageResource(R.mipmap.nf);
                    d.changeFav(m, 0);
                    Toast.makeText(con, "تم الإزالة من المفضلة", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
//        if(msg_list==null || msg_list.isEmpty()){
//            return 0;
//        }
//        else {
//            return msg_list.size();
//        }
        return msg_list.size();
    }
}
