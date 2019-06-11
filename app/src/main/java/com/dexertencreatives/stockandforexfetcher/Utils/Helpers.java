package com.dexertencreatives.stockandforexfetcher.Utils;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexertencreatives.stockandforexfetcher.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by USER on 5/12/2019.
 */

public class Helpers {
    public static String round(double number) {
        NumberFormat numberFormat = new DecimalFormat("##.##");
        return (numberFormat.format(number));
    }

    public static void playListItemTransitionAnimation(Context context, int colorFrom, final LinearLayout listItemLayout) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
                ContextCompat.getColor(context, colorFrom),
                ContextCompat.getColor(context, android.R.color.white));
        colorAnimation.setDuration(5000); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                listItemLayout.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    public static void playTextTransitionAnimation(Context context, int colorFrom, final TextView textView) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
                ContextCompat.getColor(context, colorFrom),
                ContextCompat.getColor(context, R.color.text));
        colorAnimation.setDuration(20000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setTextColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    /**
     * Animation with
     * Exit current Activity- SlideOut to Left
     * Entry new Activity- SlideIn from Right
     *
     * @param context
     */
    public static void finishEntryAnimation(Context context, Intent intent) {
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_slide_in_from_right, R.anim.anim_slide_out_to_left);

    }

}
