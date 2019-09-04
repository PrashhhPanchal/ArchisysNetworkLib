package com.archisys.archisysnetworklib;

import com.archisys.archisys_network_lib.ApiClientCallBack;
import com.archisys.archisys_network_lib.ApiRequestCallBack;
import com.archisys.archisys_network_lib.RestApiManeger;

import java.util.List;

public class GeneralServiceImpl implements IGeneral {
    @Override
    public void Advertisement(ApiClientCallBack apiClientCallBack) {
        RestApiManeger.get(RestApi.class).advertisement()
                .enqueue(new ApiRequestCallBack<List<AdvertisementModel>>(apiClientCallBack));
    }
}
