package dev.syafii.triabsensi.controller.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.syafii.triabsensi.R;
import dev.syafii.triabsensi.controller.home.MainActivity;
import dev.syafii.triabsensi.controller.login.LoginActivity;
import dev.syafii.triabsensi.model.OnBoarding;
import dev.syafii.triabsensi.utils.ActivityUtils;
import dev.syafii.triabsensi.utils.Constants;
import dev.syafii.triabsensi.utils.CustomProgressBar;
import dev.syafii.triabsensi.utils.PrefUtils;

public class BoardingActivity extends AppCompatActivity implements BoardingContract.View{

    @BindView(R.id.vp_boarding)
    ViewPager vpOnBoarding;
    @BindView(R.id.tb_boarding)
    TabLayout tbOnBoarding;
    @BindView(R.id.btn_action_boarding)
    Button btnAction;

    private BoardingContract.Presenter presenter;
    private BoardingAdapter adapter;
    private CustomProgressBar progressBar;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding);
        ButterKnife.bind(this);
        progressBar = new CustomProgressBar(this);
        presenter = new BoardingPresenter(this);
        presenter.start();
    }

    @Override
    public void initView() {
        final List<OnBoarding> list = new ArrayList<>();
        list.add(new OnBoarding(getString(R.string.boarding_title), null, R.drawable.logo_trid));
        list.add(new OnBoarding(getString(R.string.boarding_title1), getString(R.string.boarding_desc1), R.drawable.logo_trid));
        list.add(new OnBoarding(getString(R.string.boarding_title2), getString(R.string.boarding_desc2), R.drawable.logo_trid));

        adapter = new BoardingAdapter(this);
        adapter.setBoardingList(list);
        vpOnBoarding.setAdapter(adapter);
        tbOnBoarding.setupWithViewPager(vpOnBoarding);
        position = vpOnBoarding.getCurrentItem();

        btnAction.setText(getString(R.string.boarding_next));
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < list.size()){
                    position++;
                    vpOnBoarding.setCurrentItem(position);
                }

                if (position == list.size()-1 ){
                    btnAction.setText(getString(R.string.boarding_get_started));
                    btnAction.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PrefUtils.with(BoardingActivity.this).saveBoolean(Constants.KEY_IS_FIRST_INSTALL, true);
                            ActivityUtils.openActivity(BoardingActivity.this, LoginActivity.class);
                            finish();
                        }
                    });
                }
            }
        });

        ;

        tbOnBoarding.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size()-1){
                    btnAction.setText(getString(R.string.boarding_get_started));
                    btnAction.setOnClickListener(v -> {
                        PrefUtils.with(BoardingActivity.this).saveBoolean(Constants.KEY_IS_FIRST_INSTALL, true);
                        ActivityUtils.openActivity(BoardingActivity.this, LoginActivity.class);
                        finish();
                    });
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void showProgress() {
        progressBar.show();
    }

    @Override
    public void hideProgress() {
        progressBar.hide();
    }

    @Override
    public void showBoarding(List<OnBoarding> list) {

    }

    @Override
    public void setPresenter(BoardingContract.Presenter presenter) {
        this.presenter = presenter;
    }
}