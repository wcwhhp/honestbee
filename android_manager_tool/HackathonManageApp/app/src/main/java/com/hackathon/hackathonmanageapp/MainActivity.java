package com.hackathon.hackathonmanageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hackathon.hackathonmanageapp.api.BeeDataApiUtils;
import com.hackathon.hackathonmanageapp.task.GoodsDataAsyncTask;
import com.hackathon.hackathonmanageapp.task.RawEntryDataAsyncTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UpdatePriceCallback {

    private RecyclerView goodsListView;

    private BeeDataApiUtils beeDataApiUtils;
    private GoodsDataAdapter adapter;

    GoodsDataAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beeDataApiUtils = new BeeDataApiUtils();

        goodsListView = findViewById(R.id.main_page_recycler);
        goodsListView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        goodsListView.setLayoutManager(linearLayoutManager);

        adapter = new GoodsDataAdapter(this, this);

        goodsListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        beeDataApiUtils.queryQBeeGoodsDatas(adapter);

        RawEntryDataAsyncTask rawEntryDataAsyncTask = new RawEntryDataAsyncTask(adapter);
        rawEntryDataAsyncTask.execute((Void) null);

        task = new GoodsDataAsyncTask(adapter);
        task.execute((Void) null);
    }

    @Override
    public void onUpdatePrice() {
        task = new GoodsDataAsyncTask(adapter);
        task.execute((Void) null);
    }
}
