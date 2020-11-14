package dev.syafii.triabsensi.controller.home;

import android.util.Log;

import com.google.gson.Gson;

import dev.syafii.triabsensi.model.AbsentResponse;
import dev.syafii.triabsensi.model.UserRequest;
import dev.syafii.triabsensi.model.repository.Repository;
import dev.syafii.triabsensi.network.ApiClient;
import dev.syafii.triabsensi.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements MainContract.Model {

    private static final String TAG = MainModel.class.getSimpleName();

    @Override
    public void authAbsentIn(String tgl_absensi, String nik, String waktu_in, OnFinishedListener<AbsentResponse> listener) {
        Repository service = ApiClient.getClient().create(Repository.class);
        Call<AbsentResponse> call = service.AbsentIn(tgl_absensi,nik,waktu_in);
        call.enqueue(new Callback<AbsentResponse>() {
            @Override
            public void onResponse(Call<AbsentResponse> call, Response<AbsentResponse> response) {
                Log.e(TAG, "Absent In: " + new Gson().toJson(response.body()) );
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onFailure(response.body().getResult());
                    }
                } else {
                    listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
                }
            }

            @Override
            public void onFailure(Call<AbsentResponse> call, Throwable t) {
                listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
            }
        });
    }

    @Override
    public void authAbsentOut(String waktu_out, String nik, OnFinishedListener<AbsentResponse> listener) {
        Repository service = ApiClient.getClient().create(Repository.class);
        Call<AbsentResponse> call = service.AbsentOut(waktu_out,nik);
        call.enqueue(new Callback<AbsentResponse>() {
            @Override
            public void onResponse(Call<AbsentResponse> call, Response<AbsentResponse> response) {
                Log.e(TAG, "Absent Out: " + new Gson().toJson(response.body()) );
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onFailure(response.body().getResult());
                    }
                } else {
                    listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
                }
            }

            @Override
            public void onFailure(Call<AbsentResponse> call, Throwable t) {
                listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
            }
        });
    }

    @Override
    public void retrieveUser(String nik, OnFinishedListener<UserRequest> listener) {
        Repository service = ApiClient.getClient().create(Repository.class);
        Call<UserRequest> call = service.RetrieveProfile(nik);
        call.enqueue(new Callback<UserRequest>() {
            @Override
            public void onResponse(Call<UserRequest> call, Response<UserRequest> response) {
                Log.e("model", "onResponse: " + new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        String responseApi = String.valueOf(response.body().isStatus());
                        listener.onFailure(responseApi);
                    }
                } else {
                    listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
                }
            }

            @Override
            public void onFailure(Call<UserRequest> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
