package com.archisys.archisysnetworklib;

import android.content.Context;

import com.archisys.archisys_network_lib.ApiClientCallBack;
import com.archisys.archisys_network_lib.ApiClientResponseModel;
import com.archisys.archisys_network_lib.ApiRequestCallBack;
import com.archisys.archisys_network_lib.RestApiManger;

import java.util.List;

public class GeneralServiceImpl implements IGeneral {
    @Override
    public void Advertisement(ApiClientCallBack apiClientCallBack) {
        RestApiManger.get(RestApi.class).advertisement()
                .enqueue(new ApiRequestCallBack<List<AdvertisementModel>>(apiClientCallBack));
    }
}
