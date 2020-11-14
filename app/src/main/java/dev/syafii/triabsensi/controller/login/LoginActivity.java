package dev.syafii.triabsensi.controller.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.syafii.triabsensi.R;
import dev.syafii.triabsensi.controller.home.MainActivity;
import dev.syafii.triabsensi.utils.ActivityUtils;
import dev.syafii.triabsensi.utils.Constants;
import dev.syafii.triabsensi.utils.CustomProgressBar;
import dev.syafii.triabsensi.utils.PrefUtils;
import dev.syafii.triabsensi.utils.ViewUtils;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.et_nik)
    EditText etNik;
    @BindView(R.id.mWarningNik)
    TextView mWarningNik;
    @BindView(R.id.btn_login)
    Button btnLogin;

    CustomProgressBar progressBar;
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        progressBar = new CustomProgressBar(this);
        presenter = new LoginPresenter(this, new LoginModel());
        presenter.start();

    }

    @Override
    public void initView() {
        mWarningNik.setVisibility(View.GONE);

        btnLogin.setOnClickListener(v -> {
            ActivityUtils.hideKeyboard(LoginActivity.this);
            String nik = etNik.getText().toString();
            presenter.loginAction(nik);
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
    public void showErrorNik(String message) {
        if (TextUtils.isEmpty(message)) {
            mWarningNik.setVisibility(View.GONE);
        } else {
            mWarningNik.setText(message);
            mWarningNik.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loginFailed(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String nik, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login Success");
        builder.setMessage("Nama " + name + " dengan NIK " + nik);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityUtils.openActivity(LoginActivity.this, MainActivity.class);
                PrefUtils.with(LoginActivity.this).saveBoolean(Constants.KEY_IS_LOGIN, true);
                PrefUtils.with(LoginActivity.this).saveString(Constants.KEY_DATA, nik);
                Toasty.success(LoginActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}