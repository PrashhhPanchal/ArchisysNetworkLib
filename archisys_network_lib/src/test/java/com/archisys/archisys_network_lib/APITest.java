package com.archisys.archisys_network_lib;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.CountDownLatch;

public class APITest {

    private static final String login = "your@login";
    private static final String pass = "pass";
    private final CountDownLatch latch = new CountDownLatch(1);
    private RestApiManager apiManager;
    @Mock
    LibPrefs libPrefs;
    //private OAuthToken oAuthToken;

    @Before
    public void beforeTest() {
        apiManager = new RestApiManager();
    }

    @Test
    public void test_login() throws InterruptedException {
        Assert.assertNotNull(apiManager);
        /*apiManager.get(login, pass, new ApiCallback<OAuthToken>() {
            @Override
            public void onSuccess(OAuthToken token) {
                oAuthToken = token;
                latch.countDown();
            }

            @Override
            public void onFailure(@ResultCode.Code int errorCode, String errorMessage) {
                latch.countDown();
            }
        });*/

        RestApiManager.get(ApiClientCallBack.class).Response(new ApiClientResponseModel())/*.enqueue(new ApiRequestCallBack<>(new ApiClientCallBack() {
            @Override
            public void Response(ApiClientResponseModel response) {
                latch.countDown();
                response.toString();
            }
        }))*/;
        latch.await();
        //Assert.assertNotNull(oAuthToken);
    }

    @After
    public void afterTest() {
        //oAuthToken = null;
    }
}
