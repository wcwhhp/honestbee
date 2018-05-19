package com.hackathon.hackathonmanageapp;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;

import java.util.HashSet;

public class ChangePriceClickListener implements View.OnClickListener {

    private final Context context;
    private HashSet<Integer> chgSets;

    public ChangePriceClickListener(Context context, HashSet<Integer> chgSets){
        this.context = context;
        this.chgSets = chgSets;
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
                if(chgSets.contains(view.getTag())){
                    view.findViewById(R.id.card_item_chg_price).setVisibility(View.GONE);
                }
                else{
                    view.findViewById(R.id.card_item_default).setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(chgSets.contains(view.getTag())){
                    view.findViewById(R.id.card_item_default).setVisibility(View.VISIBLE);
                    chgSets.remove(view.getTag());
                }
                else{
                    view.findViewById(R.id.card_item_chg_price).setVisibility(View.VISIBLE);
                    chgSets.add((Integer) view.getTag());
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        s.start();
    }

}
