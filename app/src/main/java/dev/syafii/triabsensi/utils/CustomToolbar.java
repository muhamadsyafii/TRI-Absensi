package dev.syafii.triabsensi.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.syafii.triabsensi.R;

public class CustomToolbar {
    public static void setupToolbar(final Activity activity){
        View view = activity.getWindow().getDecorView();
        ImageView mToolbarBack = view.findViewById(R.id.mToolbarBack);
        mToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.closeActivity(activity);
            }
        });

        TextView mToolbarTitle = view.findViewById(R.id.mToolbarTitle);
        mToolbarTitle.setVisibility(View.GONE);

    }
    public static void setupToolbar(final Activity activity, String title){
        View view = activity.getWindow().getDecorView();
        ImageView mToolbarBack = view.findViewById(R.id.mToolbarBack);
        mToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.closeActivity(activity);
            }
        });

        TextView mToolbarTitle = view.findViewById(R.id.mToolbarTitle);
        mToolbarTitle.setVisibility(View.VISIBLE);
        mToolbarTitle.setText(title);
    }
}
