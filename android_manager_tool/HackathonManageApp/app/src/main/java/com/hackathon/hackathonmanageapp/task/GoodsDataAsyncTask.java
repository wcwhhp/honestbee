package com.hackathon.hackathonmanageapp.task;

import android.os.AsyncTask;

import com.hackathon.hackathonmanageapp.GoodsDataAdapter;
import com.hackathon.hackathonmanageapp.api.BeeDataApiUtils;
import com.hackathon.hackathonmanageapp.api.response.GetGoodsListResponse;

import java.util.ArrayList;

public class GoodsDataAsyncTask extends AsyncTask<Void, Integer, GetGoodsListResponse> {

    private GoodsDataAdapter adapter;

    public GoodsDataAsyncTask(GoodsDataAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected GetGoodsListResponse doInBackground(Void... voids) {

        BeeDataApiUtils dataApiUtils = new BeeDataApiUtils();
        GetGoodsListResponse goodsResponse = dataApiUtils.getGoodsList();

        return goodsResponse;
    }

    @Override
    protected void onPostExecute(GetGoodsListResponse getBeeGoodsResponse) {
        super.onPostExecute(getBeeGoodsResponse);
        if(getBeeGoodsResponse != null){
            adapter.updateDataModels((ArrayList< GetGoodsListResponse.Item>) getBeeGoodsResponse.getItems());
            adapter.notifyDataSetChanged();
        }
    }
}
