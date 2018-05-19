package com.hackathon.hackathonmanageapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hackathon.hackathonmanageapp.api.response.GetGoodsListResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GoodsDataAdapter extends RecyclerView.Adapter<GoodsDataAdapter.ViewHolder> implements View.OnLongClickListener {

    private int orgCardHeight;

    private ArrayList<GetGoodsListResponse.Item> dataModelList;
    private HashMap<String, HashMap<String, Integer>> rawMaps;
    private HashMap<View, String> ids;
    private HashSet<Integer> chgSets;
    private HashSet<Integer> chartSets;
    private final Context context;
    private UpdatePriceCallback updatePriceCallback;

    public GoodsDataAdapter(Context context, UpdatePriceCallback updatePriceCallback){
        this.context = context;
        dataModelList = new ArrayList<>();
        rawMaps = new HashMap<>();
        ids = new HashMap<>();
        chgSets = new HashSet<>();
        chartSets = new HashSet<>();
        this.updatePriceCallback = updatePriceCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout cardItem = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_item, parent, false);

        ChangePriceClickListener changePriceClickListener = new ChangePriceClickListener(parent.getContext(), chgSets);

        CardView cardView = cardItem.findViewById(R.id.card_item_card);
        cardView.setOnClickListener(changePriceClickListener);

        int w = 0;
        int h = 0;
        cardView.measure(w, h);
        ViewGroup.LayoutParams params = cardView.getLayoutParams();
        orgCardHeight = params.height;

        ViewHolder vh = new ViewHolder(cardItem);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GetGoodsListResponse.Item product = dataModelList.get(position);

        CardView cardView = holder.cardView;
        cardView.setTag(position);

        cardView.setOnLongClickListener(this);

        ImageView goodsImg = cardView.findViewById(R.id.card_item_img);
        if(product.getImageUrl() == null){
            goodsImg.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Picasso.get().load(Uri.parse(product.getImageUrl())).into(goodsImg);
        }

        if(chgSets.contains(position)){
            cardView.findViewById(R.id.card_item_chg_price).setVisibility(View.VISIBLE);
            cardView.findViewById(R.id.card_item_default).setVisibility(View.GONE);
        }
        else{
            cardView.findViewById(R.id.card_item_default).setVisibility(View.VISIBLE);
            cardView.findViewById(R.id.card_item_chg_price).setVisibility(View.GONE);
        }

        if(chartSets.contains(position)){
            showChart(cardView);
        }
        else{
            dismissChart(cardView);
        }

        TextView nameTxt = cardView.findViewById(R.id.card_item_name_txt);
        nameTxt.setText(product.getName());

        TextView priceTxt = cardView.findViewById(R.id.card_item_price_txt);
        priceTxt.setText(product.getPrice());

        TextView chgPriceTxt = cardView.findViewById(R.id.card_item_chg_price_txt);
        chgPriceTxt.setText(product.getPrice());

        ids.put(cardView, product.getId());

        Button updateButton = cardView.findViewById(R.id.card_item_chg_price_btn);
        UpdatePriceClickListener updatePriceClickListener = new UpdatePriceClickListener(product.getId(), cardView, updatePriceCallback);
        updateButton.setOnClickListener(updatePriceClickListener);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public void updateDataModels(ArrayList<GetGoodsListResponse.Item> models){
        this.dataModelList = models;
    }

    public void updateRawDatas(HashMap<String, HashMap<String, Integer>> datas){
        this.rawMaps = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public boolean onLongClick(View view) {

        if(chartSets.contains(view.getTag())){
            dismissChart(view);
            chartSets.remove(view.getTag());
        }
        else{
            chartSets.add((Integer) view.getTag());
            showChart(view);
        }

        return true;
    }

    private void showChart(View view){

        PieChart pieChart = view.findViewById(R.id.card_item_piechart);
        pieChart.setVisibility(View.VISIBLE);

        ViewGroup.LayoutParams params = view.getLayoutParams();

        params.height = orgCardHeight*2;
        chartSets.add((Integer) view.getTag());

        view.setLayoutParams(params);
        view.postInvalidate();

        setPieChart(pieChart, dataModelList.get((Integer) view.getTag()).getId());
    }

    private void dismissChart(View view){

        PieChart pieChart = view.findViewById(R.id.card_item_piechart);
        ViewGroup.LayoutParams params = view.getLayoutParams();

        pieChart.setVisibility(View.GONE);
        params.height = orgCardHeight;
        view.setLayoutParams(params);
        view.postInvalidate();
    }

    private void setPieChart(PieChart pieChart, String id){
        pieChart.setUsePercentValues(true);
        pieChart.animateY(800, Easing.EasingOption.EaseInOutQuad);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setEntryLabelColor(Color.BLACK);
        Description description = new Description();
        description.setText(context.getString(R.string.goods_count));
        pieChart.setDescription(description);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        PieDataSet dataSet = new PieDataSet(getPietEntries(id), null);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }

    private ArrayList<PieEntry> getPietEntries(String id){
        ArrayList<PieEntry> entries = new ArrayList<>();

        HashMap<String, Integer> subMap = rawMaps.get(id);
        if(subMap == null){
            PieEntry pieEntry = new PieEntry(1);
            entries.add(pieEntry);
        }
        else{
            ArrayList<String> subKeys = new ArrayList<>(subMap.keySet());
            for(int i=0;i<subKeys.size();i++){
                String key = subKeys.get(i);
                PieEntry pieEntry = new PieEntry(subMap.get(key).intValue());
                pieEntry.setLabel(subKeys.get(i));
                entries.add(pieEntry);
            }
        }

        return entries;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.card_item_card);
            this.cardView.setTag(false);
        }
    }

}
