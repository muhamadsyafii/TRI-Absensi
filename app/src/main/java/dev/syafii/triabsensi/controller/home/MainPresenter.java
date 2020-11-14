package dev.syafii.triabsensi.controller.home;

import java.util.List;

import dev.syafii.triabsensi.base.BaseModel;
import dev.syafii.triabsensi.model.AbsentResponse;
import dev.syafii.triabsensi.model.UserRequest;
import dev.syafii.triabsensi.model.UserResponse;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View view;
    private final MainContract.Model model;

    public MainPresenter(MainContract.View view, MainContract.Model model) {
        this.view = view;
        this.model = model;
        this.view.setPresenter(this);
    }


    @Override
    public void absentIn(String tgl_absensi, String nik, String waktu_in) {
        view.showProgress();
        model.authAbsentIn(tgl_absensi,nik,waktu_in, new BaseModel.OnFinishedListener<AbsentResponse>() {
            @Override
            public void onSuccess(AbsentResponse data) {
                view.hideProgress();
                view.showSuccessAbsentIn(data.getResult());
            }

            @Override
            public void onFailure(String message) {
                view.hideProgress();
                view.showFailedAbsentIn(message);
            }
        });
    }

    @Override
    public void absentOut(String waktu_out, String nik) {
        view.showProgress();
        model.authAbsentOut(waktu_out,nik, new BaseModel.OnFinishedListener<AbsentResponse>() {
            @Override
            public void onSuccess(AbsentResponse data) {
                view.hideProgress();
                view.showSuccessAbsentOut(data.getResult());
            }

            @Override
            public void onFailure(String message) {
                view.hideProgress();
                view.showFailedAbsentOut(message);
            }
        });
    }

    @Override
    public void getUserProfile(String nik) {
        view.showProgress();
        model.retrieveUser(nik, new BaseModel.OnFinishedListener<UserRequest>() {
            @Override
            public void onSuccess(UserRequest data) {
                view.hideProgress();
                List<UserResponse> list = data.getResult();
                view.showSuccessProfile(list);
            }

            @Override
            public void onFailure(String message) {
                view.showFailedProfile(message);
            }
        });
    }

    @Override
    public void start() {
        view.initView();
    }
}
