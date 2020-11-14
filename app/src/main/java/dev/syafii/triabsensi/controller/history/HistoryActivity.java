package dev.syafii.triabsensi.controller.history;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.syafii.triabsensi.R;
import dev.syafii.triabsensi.model.DataResponse;
import dev.syafii.triabsensi.utils.Constants;
import dev.syafii.triabsensi.utils.CustomToolbar;

public class HistoryActivity extends AppCompatActivity implements HistoryContract.View{

    @BindView(R.id.swr_layout)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.rlEmptyData)
    RelativeLayout rlEmptyData;
    private HistoryContract.Presenter presenter;
    private HistoryAdapter adapter;
    String nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        CustomToolbar.setupToolbar(this, "History");
        nik = getIntent().getStringExtra(Constants.KEY_DATA);
        presenter = new HistoryPresenter(this, new HistoryModel());
        presenter.start();
    }


    @Override
    public void initView() {
        rlEmptyData.setVisibility(View.GONE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataAbsent(nik);
            }
        });
        adapter = new HistoryAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHistory.setLayoutManager(manager);
        presenter.getDataAbsent(nik);
    }

    @Override
    public void showProgress() {
        if (swipeRefresh != null) swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        if (swipeRefresh != null) swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showSuccessDataAbsent(List<DataResponse> list) {
        if (list.size() > 0){
            rlEmptyData.setVisibility(View.GONE);
            adapter.setDataList(list);
            rvHistory.setAdapter(adapter);
        }else{
            rlEmptyData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showFailedDataAbsent(String message) {
        rlEmptyData.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }
}