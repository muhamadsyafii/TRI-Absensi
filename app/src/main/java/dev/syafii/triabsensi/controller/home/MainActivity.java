package dev.syafii.triabsensi.controller.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import dev.syafii.triabsensi.R;
import dev.syafii.triabsensi.controller.history.HistoryActivity;
import dev.syafii.triabsensi.controller.login.LoginActivity;
import dev.syafii.triabsensi.model.UserResponse;
import dev.syafii.triabsensi.utils.ActivityUtils;
import dev.syafii.triabsensi.utils.Constants;
import dev.syafii.triabsensi.utils.CustomProgressBar;
import dev.syafii.triabsensi.utils.DateUtils;
import dev.syafii.triabsensi.utils.ImageUtils;
import dev.syafii.triabsensi.utils.PrefUtils;
import es.dmoral.toasty.Toasty;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.swfLayout)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.sv_layout)
    ScrollView svLayout;
    @BindView(R.id.ci_avatar)
    CircleImageView ciAvatar;
    @BindView(R.id.tv_employee_name)
    TextView tvName;
    @BindView(R.id.tv_nik)
    TextView tvNik;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_dob)
    TextView tvDob;
    @BindView(R.id.btn_absent_in)
    Button btnAbsentIn;
    @BindView(R.id.btn_absent_out)
    Button btnAbsentOut;
    @BindView(R.id.btnLogout)
    Button btnLogout;
    @BindView(R.id.im_history)
    ImageView btnHistory;

    boolean isExit = false;
    private MainContract.Presenter presenter;
    CustomProgressBar progressBar;
    String nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        nik = PrefUtils.with(this).getString(Constants.KEY_DATA);
        progressBar = new CustomProgressBar(this);
        presenter = new MainPresenter(this, new MainModel());
        presenter.start();

    }

    @Override
    public void initView() {

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getUserProfile(nik);
            }
        });

        btnAbsentIn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            ActivityUtils.hideKeyboard(this);

            String date = DateUtils.formatDateToString(calendar.getTime(), DateUtils.RAW_DATE_PATTERN);
            String time = DateUtils.formatDateToString(calendar.getTime(), DateUtils.RAW_TIME_DATE_PATTERN);

            presenter.absentIn(date, nik, time);
        });

        btnAbsentOut.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            ActivityUtils.hideKeyboard(this);
            presenter.absentOut(DateUtils.formatDateToString(calendar.getTime(), DateUtils.RAW_TIME_DATE_PATTERN), nik);
        });
        Log.e("TAG", "initView: " + nik);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupLogout();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putExtra(Constants.KEY_DATA, nik);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_left_to_right);
            }
        });
        presenter.getUserProfile(nik);
    }

    @Override
    public void showProgress() {
        if (swipeRefresh != null) swipeRefresh.setRefreshing(true);
        progressBar.show();
    }

    @Override
    public void hideProgress() {
        if (swipeRefresh != null) swipeRefresh.setRefreshing(false);
        progressBar.hide();
    }

    @Override
    public void showSuccessAbsentIn(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedAbsentIn(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessAbsentOut(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedAbsentOut(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessProfile(List<UserResponse> data) {
        UserResponse response = new UserResponse();
        for (UserResponse list : data) {
            response.setNik(list.getNik());
            response.setNama(list.getNama());
            response.setJeKel(list.getJeKel());
            response.setCategory(list.getCategory());
            response.setFoto(list.getFoto());
        }

        //this is for show field profile
        svLayout.setVisibility(View.VISIBLE);
        tvNik.setText(response.getNik());
        tvName.setText(response.getNama());
        tvDob.setText(response.getJeKel());
        tvCategory.setText(response.getCategory());
        ImageUtils.loadAvatar(this, response.getFoto(), ciAvatar);

        //this dialog for show foto profile
        ciAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProfile(response.getFoto());
            }
        });
        setupShowCase(R.id.im_history, "Menu History", "Klik disin untuk melihat history absen", 1);
    }

    @Override
    public void showFailedProfile(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to logout application ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PrefUtils.with(MainActivity.this).clear();
                PrefUtils.with(MainActivity.this).saveBoolean(Constants.KEY_IS_FIRST_INSTALL, true);
                PrefUtils.with(MainActivity.this).saveBoolean(Constants.KEY_SHOW_CASE, true);
                ActivityUtils.openActivity(MainActivity.this, LoginActivity.class);
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void showProfile(String value) {
        final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
        dialog.setContentView(R.layout.dialog_show_profile);
        ImageView imageView = dialog.findViewById(R.id.mMemberQrCode);
        Button mDialogAction = dialog.findViewById(R.id.mDialogAction);
        Glide.with(this).load(value).into(imageView);
        mDialogAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void setupShowCase(int viewId, String title, String description, int type) {
        boolean check = PrefUtils.with(this).getBoolean(Constants.KEY_SHOW_CASE);
        if (!check) {
            new GuideView.Builder(this)
                    .setTitle(title)
                    .setContentText(description)
                    .setTargetView(findViewById(viewId))
                    .setDismissType(DismissType.outside) //optional - default dismissible by TargetView
                    .setGuideListener(new GuideListener() {
                        @Override
                        public void onDismiss(View view) {
                            if (type == 1) {
                                setupShowCase(R.id.ci_avatar, "Foto Profile", "Klik disini untuk memperbesar profile anda", 2);
                            } else {
                                if (type == 2) {
                                    setupShowCase(R.id.btn_absent_in, "Button Absen Masuk", "Klik disini untuk Absen Masuk", 3);
                                } else {
                                    if (type == 3) {
                                        setupShowCase(R.id.btn_absent_out, "Button Absen Keluar", "Klik disini untuk Absen Keluar", 4);
                                    } else {
                                        if (type == 4) {
                                            setupShowCase(R.id.btnLogout, "Button Logout", "Klik disini untuk logout akun", 5);
                                        } else {
                                            if (type == 5)
                                                PrefUtils.with(MainActivity.this).saveBoolean(Constants.KEY_SHOW_CASE, true);
                                        }
                                    }
                                }
                            }
                        }
                    })
                    .build()
                    .show();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onBackPressed() {
        if (isExit) {
            super.onBackPressed();
            finishAffinity();
        } else {
            isExit = true;
            Toast.makeText(this, "Press once again to close the app..", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(() -> isExit = false, 2000);
    }
}