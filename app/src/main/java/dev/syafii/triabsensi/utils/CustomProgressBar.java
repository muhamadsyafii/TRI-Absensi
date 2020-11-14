package dev.syafii.triabsensi.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import dev.syafii.triabsensi.R;

public class CustomProgressBar {
    private RelativeLayout layout;
    private Context mContext;

    public CustomProgressBar(Context context) {
        mContext = context;

        ViewGroup viewGroup = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();

        ProgressBar mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        layout = new RelativeLayout(context);
        layout.setBackgroundColor(context.getResources().getColor(R.color.backgroundTransparent));
        layout.setGravity(Gravity.CENTER);
        layout.addView(mProgressBar);
        layout.setClickable(true);
        layout.setFocusable(true);

        viewGroup.addView(layout, params);

        hide();
    }

    public void show() {
        if (layout != null) {
            if (layout.getVisibility() != View.VISIBLE) {
                layout.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hide();
                    }
                }, Constants.REQUEST_TIME_OUT * 1000);
            }
        }
    }

    public void hide() {
        if (layout != null) layout.setVisibility(View.INVISIBLE);
    }
}
