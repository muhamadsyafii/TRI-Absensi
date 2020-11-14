package dev.syafii.triabsensi.controller.login;

import dev.syafii.triabsensi.model.UserRequest;
import dev.syafii.triabsensi.model.repository.Repository;
import dev.syafii.triabsensi.network.ApiClient;
import dev.syafii.triabsensi.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    @Override
    public void authLogin(String nik, OnFinishedListener<UserRequest> listener) {
        Repository service = ApiClient.getClient().create(Repository.class);
        Call<UserRequest> call = service.RetrieveProfile(nik);
        call.enqueue(new Callback<UserRequest>() {
            @Override
            public void onResponse(Call<UserRequest> call, Response<UserRequest> response) {
                if (response.isSuccessful() && response.body() !=null){
                    if (response.body().isStatus()){
                        listener.onSuccess(response.body());
                    }else{
                        listener.onFailure(Constants.LOGIN_ERROR_MESSAGE_VALIDATE);
                    }
                }else{
                    listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
                }
            }

            @Override
            public void onFailure(Call<UserRequest> call, Throwable t) {
                listener.onFailure(Constants.MESSAGE_ERROR_REQUEST);
            }
        });
    }
}
