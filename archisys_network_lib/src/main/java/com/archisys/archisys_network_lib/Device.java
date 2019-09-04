package com.archisys.archisys_network_lib;

import android.content.Context;
import android.os.Build;
import android.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class Device {

    static String _deviceString ;
    static  Device _device;
    public  static  Device getInstance(Context context){
        String uuid="";
        if(LibPrefs.getValue(context,LibPrefs.UUID,null)!=null && LibPrefs.hasPrefs(context,LibPrefs.UUID) && !LibPrefs.getValue(context,LibPrefs.UUID,"").isEmpty()){
            uuid=LibPrefs.getValue(context,LibPrefs.UUID,"");
        }else{
            uuid= UUID.randomUUID().toString();
            LibPrefs.setValue(context,LibPrefs.UUID,uuid);
        }
        _device = new Device()
                .setAppSignature("")
                .setAppVersion(BuildConfig.VERSION_NAME)
                .setUuid(uuid)
                .setManufacturer(Build.MANUFACTURER)
                .setModel(Build.MODEL)
                .setOs("Android")
                .setOsVersion(android.os.Build.VERSION.RELEASE)
                .setPlatform("Mobile")
                .setNotificationId("");

        return _device;
    }
    public  static  Device getInstance(){

        if(_device == null){
            _device = new Device()
                    .setAppSignature("")
            .setAppVersion(BuildConfig.VERSION_NAME)
            .setManufacturer(Build.MANUFACTURER)
            .setModel(Build.MODEL)
            .setNotificationId("")
            .setOs("Android")
            .setOsVersion(android.os.Build.VERSION.RELEASE)
            .setPlatform("Mobile")
            .setUuid("");
        }

        if(_deviceString == null){
            _deviceString = _device.toString();
        }
        return  _device;
    }


    @JsonProperty("AppSignature")
    String appSignature;
    @JsonProperty("AppVersion")
    String appVersion;
    @JsonProperty("UUID")
    String uuid;
    @JsonProperty("Manufacturer")
    String manufacturer;
    @JsonProperty("Model")
    String model;
    @JsonProperty("OS")
    String os;
    @JsonProperty("OSVersion")
    String osVersion;
    @JsonProperty("Platform")
    String platform;
    @JsonProperty("NotificationId")
    String notificationId;

    public String getAppSignature() {
        return appSignature;
    }

    public Device setAppSignature(String appSignature) {
        this.appSignature = appSignature;
        return this;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public Device setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Device setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Device setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Device setModel(String model) {
        this.model = model;
        return this;
    }

    public String getOs() {
        return os;
    }

    public Device setOs(String os) {
        this.os = os;
        return this;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public Device setOsVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Device setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public Device setNotificationId(String notificationId) {
        this.notificationId = notificationId;
        return this;
    }


    @Override
    public String toString() {
        if(_deviceString == null || _deviceString.isEmpty()){
            try{
                ObjectMapper mapper = new ObjectMapper();
                _deviceString = Base64.encodeToString(mapper.writeValueAsString(this).getBytes("UTF-8"), Base64.NO_WRAP);

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return  _deviceString;
    }
}
