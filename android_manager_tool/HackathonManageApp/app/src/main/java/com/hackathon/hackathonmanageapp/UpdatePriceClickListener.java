package com.hackathon.hackathonmanageapp;

import android.view.View;
import android.widget.EditText;

import com.hackathon.hackathonmanageapp.task.UpdatePriceAsyncTask;

public class UpdatePriceClickListener implements View.OnClickListener {

    private String id;
    private UpdatePriceCallback updatePriceCallback;
    private View parentView;

    public UpdatePriceClickListener(String id, View parentView, UpdatePriceCallback updatePriceCallback) {
        this.id = id;
        this.updatePriceCallback = updatePriceCallback;
        this.parentView = parentView;
    }

    @Override
    public void onClick(View view) {
        EditText editText = parentView.findViewById(R.id.card_item_chg_price_txt);
        String newPrice = editText.getText().toString();

        UpdatePriceAsyncTask task = new UpdatePriceAsyncTask();
        task.setUpdatePriceCallback(updatePriceCallback);
        task.execute(id, newPrice);
    }
}
