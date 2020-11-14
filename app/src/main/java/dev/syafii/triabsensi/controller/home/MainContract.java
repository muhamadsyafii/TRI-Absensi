package dev.syafii.triabsensi.controller.home;

import java.util.List;

import dev.syafii.triabsensi.base.BaseModel;
import dev.syafii.triabsensi.base.BasePresenter;
import dev.syafii.triabsensi.base.BaseView;
import dev.syafii.triabsensi.model.AbsentResponse;
import dev.syafii.triabsensi.model.UserRequest;
import dev.syafii.triabsensi.model.UserResponse;

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void initView();
        void showProgress();
        void hideProgress();
        void showSuccessAbsentIn(String message);
        void showFailedAbsentIn(String message);
        void showSuccessAbsentOut(String message);
        void showFailedAbsentOut(String message);
        void showSuccessProfile(List<UserResponse> data);
        void showFailedProfile(String message);
        void setupLogout();
        void showProfile(String value);
        void setupShowCase(int viewId, String title, String description, int type);

    }
    interface Presenter extends BasePresenter{
        void absentIn(String tgl_absensi, String nik, String waktu_in);
        void absentOut(String waktu_out, String nik);
        void getUserProfile(String nik);


    }

    interface Model extends BaseModel{
        void authAbsentIn(String tgl_absensi, String nik, String waktu_in, OnFinishedListener<AbsentResponse> listener);
        void authAbsentOut(String waktu_out, String nik, OnFinishedListener<AbsentResponse> listener);
        void retrieveUser(String nik, OnFinishedListener<UserRequest> listener);
        //TODO: Still Development
    }
}
