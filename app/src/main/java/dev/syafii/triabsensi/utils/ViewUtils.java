package dev.syafii.triabsensi.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import dev.syafii.triabsensi.R;

public class ViewUtils {
    @SuppressLint("ClickableViewAccessibility")
    public static void showHidePassword(final Context context, final EditText editText) {
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_visibility_off), null);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_RIGHT = 2;

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (editText.getTransformationMethod() == null) {
                            editText.setTransformationMethod(new PasswordTransformationMethod());
                            editText.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_visibility_off), null);
                        } else {
                            editText.setTransformationMethod(null);
                            editText.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_visibility), null);
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
