package com.archisys.archisysnetworklib;

import com.archisys.archisys_network_lib.ApiClientResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    // General
    @GET("/api/v1/General/Advertisement")
    Call<ApiClientResponseModel<List<AdvertisementModel>>> advertisement();
}
