package com.hackathon.hackathonmanageapp.task;

import android.os.AsyncTask;

import com.hackathon.hackathonmanageapp.UpdatePriceCallback;
import com.hackathon.hackathonmanageapp.api.BeeDataApiUtils;
import com.hackathon.hackathonmanageapp.api.request.UpdatePriceRequest;
import com.hackathon.hackathonmanageapp.api.response.UpdatePriceResponse;

public class UpdatePriceAsyncTask extends AsyncTask<String, Integer, UpdatePriceResponse> {

    private UpdatePriceCallback updatePriceCallback;

    @Override
    protected UpdatePriceResponse doInBackground(String... strings) {

        String id = strings[0];
        String price = strings[1];

        UpdatePriceRequest request = new UpdatePriceRequest();
        request.setId(id);
        request.setPrice(price);

        BeeDataApiUtils apiUtils = new BeeDataApiUtils();
        UpdatePriceResponse response = apiUtils.updatePrice(request);

        return response;
    }

    @Override
    protected void onPostExecute(UpdatePriceResponse updatePriceResponse) {
        super.onPostExecute(updatePriceResponse);
        if(updatePriceResponse != null && updatePriceResponse.getAttributes() != null){
            updatePriceCallback.onUpdatePrice();
        }
    }

    public void setUpdatePriceCallback(UpdatePriceCallback updatePriceCallback) {
        this.updatePriceCallback = updatePriceCallback;
    }
}
