package dev.syafii.triabsensi.controller.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import dev.syafii.triabsensi.R;
import dev.syafii.triabsensi.model.OnBoarding;
import dev.syafii.triabsensi.utils.StringUtils;

public class BoardingAdapter extends PagerAdapter {
    private Context context;
    private List<OnBoarding> boardingList;

    public void setBoardingList(List<OnBoarding> boardingList){
        this.boardingList = boardingList;
        notifyDataSetChanged();
    }

    public BoardingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = new View(context);
        if (inflater !=null){
            OnBoarding data = boardingList.get(position);
            view = inflater.inflate(R.layout.item_boarding, null);
            ImageView mBoardingIcon = view.findViewById(R.id.mBoardingIcon);
            TextView mBoardingTitle = view.findViewById(R.id.mBoardingTitle);
            TextView mBoardingDescription = view.findViewById(R.id.mBoardingDescription);
            mBoardingIcon.setImageResource(data.getImage());
//            ImageUtils.loadImageNoError(context, String.valueOf(data.getImage()), mBoardingIcon);
            mBoardingTitle.setText(data.getTittle());
            mBoardingDescription.setText(StringUtils.clearHTML(data.getDescription()));

        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return boardingList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
