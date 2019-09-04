package com.archisys.archisys_network_lib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequestCallBack<T> implements Callback<ApiClientResponseModel<T>> {

    public static final String APIRESPONCE_TIME="TIME(ms)";
    ApiClientCallBack _apiClientCallBack;
    public ApiRequestCallBack(ApiClientCallBack apiClientCallBack){
        _apiClientCallBack = apiClientCallBack;
    }
    @Override
    public void onResponse(Call<ApiClientResponseModel<T>> call, Response<ApiClientResponseModel<T>> response) {
        long time=(response.raw().receivedResponseAtMillis()-response.raw().sentRequestAtMillis());
        LibPrefs.LogInfo("RestAPI",call.request().url().toString() +"  ,"+APIRESPONCE_TIME+":"+ time);
        if(call.request().method().toLowerCase().equals("post")){
            LibPrefs.LogInfo("restapi","post:"+call.request().body().toString());
        }
        LibPrefs.LogInfo("restapi","network:"+response.message());
        if(response.code()==200) {
            try {
                if(response.body()!=null) {
                    LibPrefs.LogInfo("RestAPI", "response: " + new ObjectMapper().writeValueAsString(response.body()));
                }else{
                    LibPrefs.LogInfo("RestAPI", "response: " + new ObjectMapper().writeValueAsString(response.errorBody()));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(_apiClientCallBack!=null) {
            _apiClientCallBack.Response(processResponse(response));
        }
    }

    @Override
    public void onFailure(Call<ApiClientResponseModel<T>> call, Throwable t) {
        LibPrefs.LogInfo("RestAPI",call.request().url().toString());
        LibPrefs.LogInfo("restapi","failed:"+t.fillInStackTrace());
        if(_apiClientCallBack!=null){
            _apiClientCallBack.Response(new ApiClientResponseModel(t));
        }


    }
    private static <T> ApiClientResponseModel<T> processResponse(Response<ApiClientResponseModel<T>> response)
    {
        ApiClientResponseModel<T> apiClientResponseModel=new ApiClientResponseModel<>();
        if(response.code()==200){
            apiClientResponseModel=response.body();
        }else{
            apiClientResponseModel.setStatusCode(response.code());
            apiClientResponseModel.setStatusMessage(response.message());
        }
        return apiClientResponseModel;
    }
}
