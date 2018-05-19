package com.hackathon.hackathonmanageapp.task;

import android.os.AsyncTask;
import android.util.Log;

import com.hackathon.hackathonmanageapp.GoodsDataAdapter;
import com.hackathon.hackathonmanageapp.api.BeeDataApiUtils;
import com.hackathon.hackathonmanageapp.api.response.RawDatasResponse;
import com.hackathon.hackathonmanageapp.model.RawEntryDataModel;

import java.util.HashMap;
import java.util.List;

public class RawEntryDataAsyncTask extends AsyncTask<Void, Integer, RawDatasResponse> {

    private GoodsDataAdapter adapter;

    public RawEntryDataAsyncTask(GoodsDataAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected RawDatasResponse doInBackground(Void... voids) {

        BeeDataApiUtils apiUtils = new BeeDataApiUtils();

        RawDatasResponse rawDatasResponse = apiUtils.getRawDatas();

        return rawDatasResponse;
    }

    @Override
    protected void onPostExecute(RawDatasResponse rawDatasResponse) {
        super.onPostExecute(rawDatasResponse);
        HashMap<String, HashMap<String, Integer>> dataMap = new HashMap<>();

        for(int i=0;i<rawDatasResponse.getItems().size();i++){
            RawDatasResponse.Item item = rawDatasResponse.getItems().get(i);

            if(dataMap.get(item.getId()) == null){
                HashMap<String, Integer> subDatas = new HashMap<>();
                subDatas.put(item.getSource(), 1);
                dataMap.put(item.getId(), subDatas);
            }
            else{
                HashMap<String, Integer> subData = dataMap.get(item.getId());

                if(subData.get(item.getSource()) == null){
                    subData.put(item.getSource(), 1);
                }
                else{
                    int cnt = subData.get(item.getSource());
                    cnt++;
                    subData.put(item.getSource(), cnt);
                }

            }

        }

        Log.d("test", dataMap.toString());

        adapter.updateRawDatas(dataMap);
    }
}
