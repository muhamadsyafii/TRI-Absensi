package dev.syafii.triabsensi.model.repository;

import dev.syafii.triabsensi.model.AbsentIn;
import dev.syafii.triabsensi.model.AbsentOut;
import dev.syafii.triabsensi.model.AbsentResponse;
import dev.syafii.triabsensi.model.DataRequest;
import dev.syafii.triabsensi.model.UserRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Repository {

    @GET("/api_services/show_profil.php")
    Call<UserRequest> RetrieveProfile(@Query("nik") String nik);

    @FormUrlEncoded
    @POST("/api_services/add_absensi.php")
    Call<AbsentResponse> AbsentIn(@Field("tgl_absensi") String tgl_absensi, @Field("nik") String nik,
                                  @Field("waktu_in") String waktu_in);

    @FormUrlEncoded
    @POST("/api_services/update_absensi.php")
    Call<AbsentResponse> AbsentOut(@Field("waktu_out") String waktu_in, @Field("nik") String nik);

    @GET("/api_services/showdata_absensi.php")
    Call<DataRequest> RetrieveDataAbsent(@Query("nik") String nik);
}
