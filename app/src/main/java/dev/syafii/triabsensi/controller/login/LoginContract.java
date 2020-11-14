package dev.syafii.triabsensi.controller.login;

import dev.syafii.triabsensi.base.BaseModel;
import dev.syafii.triabsensi.base.BasePresenter;
import dev.syafii.triabsensi.base.BaseView;
import dev.syafii.triabsensi.model.UserRequest;

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void initView();
        void showProgress();
        void hideProgress();
        void showErrorNik(String message);
        void loginFailed(String message);
        void loginSuccess(String nik, String name);

    }

    interface Presenter extends BasePresenter {
        boolean validateNik(String nik);
        void loginAction(String nik);

    }

    interface Model extends BaseModel {
        void authLogin(String nik,OnFinishedListener<UserRequest> listener);
    }
}
