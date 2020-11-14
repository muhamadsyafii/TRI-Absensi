package dev.syafii.triabsensi.controller.history;

import dev.syafii.triabsensi.model.DataRequest;
import dev.syafii.triabsensi.model.repository.Repository;
import dev.syafii.triabsensi.network.ApiClient;
import dev.syafii.triabsensi.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryModel implements HistoryContract.Model {
    @Override
    public void retrieveDataAbsent(String nik, OnFinishedListener<DataRequest> listener) {
        Repository service = ApiClient.getClient().create(Repository.class);
        Call<DataRequest> call = service.RetrieveDataAbsent(nik);
        call.enqueue(new Callback<DataRequest>() {
            @Override
            public void onResponse(Call<DataRequest> call, Response<DataRequest> response) {
                if (response.isSuccessful() && response.body() !=null){
                    if (response.body().isStatus()){
                        listener.onSuccess(response.body());
                    }else{
                        listener.onFailure(response.body().getResult().toString());
                    }
                }else{
                    listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
                }
            }

            @Override
            public void onFailure(Call<DataRequest> call, Throwable t) {
                listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
            }
        });
    }
}
