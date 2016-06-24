package com.example.huangxiquan.pathbezier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BezierView bezierView = new BezierView(getApplicationContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600);
        linearLayout.addView(bezierView,layoutParams);

        Button button = new Button(getApplicationContext());
        button.setText("点击");
        LinearLayout.LayoutParams buttonparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(button,buttonparams);

        final BezierCircleView bezierCircleView = new BezierCircleView(getApplicationContext());
        LinearLayout.LayoutParams circleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        bezierCircleView.setLayoutParams(circleParams);
        linearLayout.addView(bezierCircleView,circleParams);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bezierCircleView.reStart();
            }
        });



        setContentView(linearLayout);
    }
}
