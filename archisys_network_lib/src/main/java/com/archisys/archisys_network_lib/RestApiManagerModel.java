package com.archisys.archisys_network_lib;

import android.content.Context;

public class RestApiManagerModel {
    private Context context;
    private String baseUrl;
    private String device;
    private String authorization;
    private boolean useHTTPS;


    public RestApiManagerModel(){
        this.baseUrl="";
        this.device="";
        this.authorization="";
        this.useHTTPS=false;
    }
    public RestApiManagerModel(String baseUrl,String device,String authorization){
        this.baseUrl=baseUrl;
        this.device=device;
        this.authorization=authorization;
        this.useHTTPS=false;
    }

    public RestApiManagerModel(Context context,String baseUrl,String device,String authorization,boolean useHTTPS){
        this.context=context;
        this.baseUrl=baseUrl;
        this.device=device;
        this.authorization=authorization;
        this.useHTTPS=useHTTPS;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
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


    public boolean isUseHTTPS() {
        return useHTTPS;
    }

    public void setUseHTTPS(boolean useHTTPS) {
        this.useHTTPS = useHTTPS;
    }
}
