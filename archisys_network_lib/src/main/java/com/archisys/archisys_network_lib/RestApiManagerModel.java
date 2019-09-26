package com.archisys.archisys_network_lib;

public class RestApiManagerModel {
    private String baseUrl;
    private String device;
    private String authorization;


    public RestApiManagerModel(){
        this.baseUrl="";
        this.device="";
        this.authorization="";
    }
    public RestApiManagerModel(String baseUrl,String device,String authorization){
        this.baseUrl=baseUrl;
        this.device=device;
        this.authorization=authorization;
    }
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
