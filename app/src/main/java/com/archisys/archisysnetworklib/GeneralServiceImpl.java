package com.archisys.archisysnetworklib;

import com.archisys.archisys_network_lib.ApiClientCallBack;
import com.archisys.archisys_network_lib.ApiRequestCallBack;
import com.archisys.archisys_network_lib.RestApiManager;
import com.archisys.archisys_network_lib.RestApiManagerModel;

import java.util.List;

public class GeneralServiceImpl implements IGeneral {
    @Override
    public void Advertisement(ApiClientCallBack apiClientCallBack) {
        RestApiManager.get(RestApi.class,new RestApiManagerModel()).advertisement()
                .enqueue(new ApiRequestCallBack<List<AdvertisementModel>>(apiClientCallBack));
    }
}
