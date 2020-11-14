package dev.syafii.triabsensi.controller.history;

import java.util.List;

import dev.syafii.triabsensi.base.BaseModel;
import dev.syafii.triabsensi.base.BasePresenter;
import dev.syafii.triabsensi.base.BaseView;
import dev.syafii.triabsensi.model.DataRequest;
import dev.syafii.triabsensi.model.DataResponse;

public interface HistoryContract {
    interface View extends BaseView<Presenter>{
        void initView();
        void showProgress();
        void hideProgress();
        void showSuccessDataAbsent(List<DataResponse> list);
        void showFailedDataAbsent(String message);

    }
    interface Presenter extends BasePresenter{
        void getDataAbsent(String nik);
    }

    interface Model extends BaseModel{
        void retrieveDataAbsent(String nik, OnFinishedListener<DataRequest> listener);
    }
}
