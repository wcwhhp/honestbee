package com.hackathon.hackathonmanageapp;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
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

public class GoodsDataAdapter extends RecyclerView.Adapter<GoodsDataAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private ArrayList<GetGoodsListResponse.Item> dataModelList;
    private HashMap<String, HashMap<String, Integer>> rawMaps;
    private HashMap<View, Boolean> isPrices;
    private HashMap<View, Boolean> isChart;
    private HashMap<View, String> ids;
    private final Context context;
    private UpdatePriceCallback updatePriceCallback;

    public GoodsDataAdapter(Context context, UpdatePriceCallback updatePriceCallback){
        this.context = context;
        dataModelList = new ArrayList<>();
        isPrices = new HashMap<>();
        isChart = new HashMap<>();
        rawMaps = new HashMap<>();
        ids = new HashMap<>();
        this.updatePriceCallback = updatePriceCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout cardItem = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_item, parent, false);

        ViewHolder vh = new ViewHolder(cardItem);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GetGoodsListResponse.Item product = dataModelList.get(position);

        LinearLayout cardItem = holder.cardView;

        CardView cardView = cardItem.findViewById(R.id.card_item_card);

        cardView.setOnClickListener(this);
        cardView.setOnLongClickListener(this);

        ImageView goodsImg = cardItem.findViewById(R.id.card_item_img);
        if(product.getImageUrl() == null){
            goodsImg.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Picasso.get().load(Uri.parse(product.getImageUrl())).into(goodsImg);
        }

        TextView nameTxt = cardItem.findViewById(R.id.card_item_name_txt);
        nameTxt.setText(product.getName());

        TextView priceTxt = cardItem.findViewById(R.id.card_item_price_txt);
        priceTxt.setText(product.getPrice());

        TextView chgPriceTxt = cardItem.findViewById(R.id.card_item_chg_price_txt);
        chgPriceTxt.setText(product.getPrice());

        ids.put(cardView, product.getId());

        Button updateButton = cardItem.findViewById(R.id.card_item_chg_price_btn);
        UpdatePriceClickListener updatePriceClickListener = new UpdatePriceClickListener(product.getId(), cardItem, updatePriceCallback);
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
    public void onClick(final View view) {

        AnimatorSet anim1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_out);
        AnimatorSet anim2 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in);
        AnimatorSet s = new AnimatorSet();

        anim1.setTarget(view.findViewById(R.id.card_item_card));
        anim2.setTarget(view.findViewById(R.id.card_item_card));

        s.play(anim1).before(anim2);
        s.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if(isPrices.get(view) != null && isPrices.get(view)){
                    view.findViewById(R.id.card_item_chg_price).setVisibility(View.GONE);
                }
                else{
                    view.findViewById(R.id.card_item_default).setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(isPrices.get(view) != null && isPrices.get(view)){
                    view.findViewById(R.id.card_item_default).setVisibility(View.VISIBLE);
                    isPrices.put(view, false);
                }
                else{
                    view.findViewById(R.id.card_item_chg_price).setVisibility(View.VISIBLE);
                    isPrices.put(view, true);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        s.start();
    }

    @Override
    public boolean onLongClick(final View view) {

        PieChart pieChart = view.findViewById(R.id.card_item_piechart);
        int width = 0;
        int height = 0;
        view.measure(width, height);
        ViewGroup.LayoutParams params = view.getLayoutParams();

        if(isChart.get(view) != null && isChart.get(view)){
            pieChart.setVisibility(View.GONE);
            params.height = params.height/2;
            isChart.put(view, false);
        }
        else{
            pieChart.setVisibility(View.VISIBLE);
            params.height = params.height*2;
            isChart.put(view, true);

            view.setLayoutParams(params);
            view.postInvalidate();

            String id = ids.get(view);
            setPieChart(pieChart, id);
        }

        this.notifyDataSetChanged();
        return true;
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

        LinearLayout cardView;

        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            this.cardView = itemView;
        }
    }

}
