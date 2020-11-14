package dev.syafii.triabsensi.controller.intro;

import java.util.List;

import dev.syafii.triabsensi.base.BaseModel;
import dev.syafii.triabsensi.base.BasePresenter;
import dev.syafii.triabsensi.base.BaseView;
import dev.syafii.triabsensi.model.OnBoarding;

public interface BoardingContract {
    interface View extends BaseView<Presenter> {
        void initView();
        void showProgress();
        void hideProgress();
        void showBoarding(List<OnBoarding> list);

    }

    interface Presenter extends BasePresenter {
        void getOnBoarding();

    }
    interface Model extends BaseModel {
        void RetrieveOnBoarding(OnFinishedListener<OnBoarding> listener);
    }
}
