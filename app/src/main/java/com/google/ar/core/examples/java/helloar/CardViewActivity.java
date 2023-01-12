package com.google.ar.core.examples.java.helloar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import android.os.Bundle;

public class CardViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
//        for (int index = 0; index < 20; index++) {
//            DataObject obj = new DataObject("Some Primary Text " + index,
//                    "Secondary " + index);
//            results.add(index, obj);
//        }
        DataObject obj=new DataObject("Alfa Designer","alfadesigner.com","Haider Ali","Lahore","03316837883",R.drawable.designer);
        results.add(0, obj);
        DataObject obj1=new DataObject("Interior Architects","interiorarchitects.com","Ahmad Khan","Karachi","03316837883",R.drawable.art);
        results.add(1, obj1);
        DataObject obj2=new DataObject("The Interior","theinterior.com","Huzaifa Khalid","Lahore","03018477839",R.drawable.interior);
        results.add(2, obj2);
        return results;
    }
}