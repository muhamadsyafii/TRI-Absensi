package dev.syafii.triabsensi.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.squareup.picasso.Picasso;

import dev.syafii.triabsensi.R;

public class ImageUtils {
    public static void loadAvatar(Context context, String imageUrl, ImageView imageView) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
        circularProgressDrawable.setColorFilter(0xFF007141, PorterDuff.Mode.ADD);
        circularProgressDrawable.start();
        if (!imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.ic_default_profile)
                    .into(imageView);
        }
    }

    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
        circularProgressDrawable.setColorFilter(0xFF007141, PorterDuff.Mode.MULTIPLY);
        circularProgressDrawable.start();
        if (!imageUrl.isEmpty()){
            Picasso.get().load(imageUrl).placeholder(circularProgressDrawable).error(R.drawable.ic_default_profile).into(imageView);
        }
    }
}
