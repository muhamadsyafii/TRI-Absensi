package dev.syafii.triabsensi.controller.login;

import android.util.Log;

import java.util.List;

import dev.syafii.triabsensi.base.BaseModel;
import dev.syafii.triabsensi.model.UserRequest;
import dev.syafii.triabsensi.model.UserResponse;
import dev.syafii.triabsensi.utils.Constants;
import dev.syafii.triabsensi.utils.ValidationUtils;

public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View view;
    private final LoginContract.Model model;

    public LoginPresenter(LoginContract.View view, LoginContract.Model model) {
        this.view = view;
        this.model = model;
        this.view.setPresenter(this);
    }

    @Override
    public boolean validateNik(String nik) {
        boolean isValid = false;
        if (ValidationUtils.isEmpty(nik)) {
            view.showErrorNik(Constants.FIELD_REQUIRED);
        } else {
            if (!ValidationUtils.isValidNik(nik)) {
                view.showErrorNik(Constants.FIELD_REQUIRED_NIK);
            } else {
                view.showErrorNik("");
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public void loginAction(String nik) {
        boolean isValidNik = validateNik(nik);
        if (isValidNik) {
            view.showProgress();
            model.authLogin(nik, new BaseModel.OnFinishedListener<UserRequest>() {
                @Override
                public void onSuccess(UserRequest data) {
                    view.hideProgress();
                    List<UserResponse> list = data.getResult();
                    for (UserResponse result : list) {
                        view.loginSuccess(result.getnIK(), result.getNama());
                    }
                }

                @Override
                public void onFailure(String message) {
                    view.loginFailed(message);
                    view.hideProgress();
                }
            });

        } else {
            view.loginFailed(Constants.LOGIN_ERROR_MESSAGE);
        }
    }

    @Override
    public void start() {
        view.initView();
    }
}
