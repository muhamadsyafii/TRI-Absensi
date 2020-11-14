package dev.syafii.triabsensi.controller.intro;

public class BoardingPresenter implements BoardingContract.Presenter {
    private final BoardingContract.View view;

    public BoardingPresenter(BoardingContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void getOnBoarding() {
        //TODO : Still Development
    }

    @Override
    public void start() {
        view.initView();
//        getOnBoarding();
    }
}
