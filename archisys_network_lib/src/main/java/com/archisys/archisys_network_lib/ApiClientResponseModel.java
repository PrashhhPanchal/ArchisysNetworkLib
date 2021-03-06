package com.archisys.archisys_network_lib;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiClientResponseModel<T> {
    public static final String Res_statusCode="statusCode";
    public static final String Res_statusMessage="statusMessage";
    public static final String Res_serverTime="serverTime";
    public static final String Res_data="Data";


    @JsonProperty("StatusCode")
    public int statusCode;

    @JsonProperty("StatusMessage")
    public String statusMessage;

    @JsonProperty("Data")
    public T data;

    @JsonProperty("ServerTime")
    public String serverTime;

    public Throwable throwable;

    public ApiClientResponseModel(){
        statusCode=-1;
        statusMessage="";
        data=null;
        serverTime="";
        throwable=null;
    }
    public ApiClientResponseModel(Throwable t){
        statusCode=-1;
        statusMessage="";
        data=null;
        serverTime="";
        throwable=t;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ApiClientResponseModel setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public ApiClientResponseModel setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiClientResponseModel setData(T data) {
        this.data = data;
        return this;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public ApiClientResponseModel<T> Process(retrofit2.Response<ApiClientResponseModel<T>> response){

        if(response.code()==200)
        {
            this.setStatusMessage(response.body().getStatusMessage())
                    .setStatusCode(response.body().getStatusCode())
            .setData(response.body());
        }
        else{
            this.setStatusCode(response.code());
            this.setStatusMessage(response.message());
        }
        return this;
    }
}
