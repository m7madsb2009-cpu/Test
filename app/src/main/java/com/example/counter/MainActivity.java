package com.example.counter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private SharedPreferences prefs;
    private TextView countView;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // نسترجع العدد المحفوظ (يبقى حتى لو سكرت التطبيق)
        prefs = getSharedPreferences("counter", MODE_PRIVATE);
        count = prefs.getInt("count", 0);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(48, 48, 48, 48);
        root.setBackgroundColor(Color.WHITE);

        countView = new TextView(this);
        countView.setTextSize(96);
        countView.setTextColor(Color.parseColor("#1B5E20"));
        countView.setGravity(Gravity.CENTER);

        Button plus = new Button(this);
        plus.setText("+1");
        plus.setTextSize(40);
        LinearLayout.LayoutParams plusParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
        plusParams.topMargin = 48;

        Button reset = new Button(this);
        reset.setText("تصفير");
        LinearLayout.LayoutParams resetParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        resetParams.topMargin = 24;

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                saveAndShow();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                saveAndShow();
            }
        });

        root.addView(countView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        root.addView(plus, plusParams);
        root.addView(reset, resetParams);

        setContentView(root);
        saveAndShow();
    }

    private void saveAndShow() {
        prefs.edit().putInt("count", count).apply();
        countView.setText(String.valueOf(count));
    }
  }
