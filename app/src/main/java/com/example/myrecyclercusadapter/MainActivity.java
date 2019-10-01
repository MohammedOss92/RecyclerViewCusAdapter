package com.example.myrecyclercusadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myrecyclercusadapter.adapters.MsgTypesAdapter;
import com.example.myrecyclercusadapter.database.DataBase;
import com.example.myrecyclercusadapter.model.MsgTypes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MsgTypesAdapter msgTypesAdapter;
    List<MsgTypes> msgTypes_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =findViewById(R.id.recycler_frag_one);

        msgTypesAdapter = new MsgTypesAdapter(msgTypes_list, this);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(msgTypesAdapter);

        List();
    }

    private void List(){

        DataBase.getInstance(this).addNewRow("1", "حكم و أقوال عن الناس");
        DataBase.getInstance(this).addNewRow("2", "حكم و أقوال عن الحكمة");
        DataBase.getInstance(this).addNewRow("3","حكم و أقوال عن العلم" );
        DataBase.getInstance(this).addNewRow("4",  "حكم و أقوال عن الصحة");
        DataBase.getInstance(this).addNewRow("5",  "حكم و أقوال عن السلام");
        DataBase.getInstance(this).addNewRow("6",  "حكم و أقوال عن العمل");
        DataBase.getInstance(this).addNewRow("7",  "حكم و أقوال عن الحياة");
        DataBase.getInstance(this).addNewRow("8",  "حكم و أقوال عن الرجل");


        ArrayList<MsgTypes> contactList = DataBase.getInstance(this).getAllPrayer();
        Log.e("contactList", contactList.size() + " size");

        DataBase db = new DataBase(this);
        msgTypes_list.addAll(DataBase.getInstance(this).getAllPrayer());
        msgTypesAdapter.notifyDataSetChanged();
    }
}
