package dev.syafii.triabsensi.controller.history;

import java.util.List;

import dev.syafii.triabsensi.base.BaseModel;
import dev.syafii.triabsensi.model.DataRequest;
import dev.syafii.triabsensi.model.DataResponse;

public class HistoryPresenter implements HistoryContract.Presenter {
    private final HistoryContract.View view;
    private final HistoryContract.Model model;

    public HistoryPresenter(HistoryContract.View view, HistoryContract.Model model) {
        this.view = view;
        this.model = model;
        this.view.setPresenter(this);
    }

    @Override
    public void getDataAbsent(String nik) {
        view.showProgress();
        model.retrieveDataAbsent(nik, new BaseModel.OnFinishedListener<DataRequest>() {
            @Override
            public void onSuccess(DataRequest data) {
                view.hideProgress();
                List<DataResponse> list = data.getResult();
                view.showSuccessDataAbsent(list);
            }

            @Override
            public void onFailure(String message) {
                view.hideProgress();
                view.showFailedDataAbsent(message);
            }
        });
    }

    @Override
    public void start() {
        view.initView();
    }
}
