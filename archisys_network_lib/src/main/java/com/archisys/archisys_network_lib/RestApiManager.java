package com.archisys.archisys_network_lib;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestApiManager {

        public static void isShowLog(boolean isShow){
            LibPrefs.isShowLog=isShow;
        }

       public static  <T> T get( Class<T> tClass,RestApiManagerModel model) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            //Cache cache = new Cache(context.getCacheDir(), cacheSize);
            //builder.cache(cache);
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(100);
            dispatcher.setMaxRequestsPerHost(10);
            builder.dispatcher(dispatcher);
            if(model.isUseHTTPS()){
                builder.sslSocketFactory(SslUtils.getSslSocketFactory(), SslUtils.getTrustManager());
            }

//           LibPrefs.getDevice= userDevice.toString();
//            if (LibPrefs.hasPrefs(context, LibPrefs.Authorization) && !LibPrefs.getValue(context, LibPrefs.Authorization, "").isEmpty()) {
//                LibPrefs.getAuthorization=LibPrefs.getValue(context,LibPrefs.Authorization,"");
//            }




            //added fix headers
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {


                    Request.Builder builder = chain.request().newBuilder()
                            .addHeader("Content-type", "application/json")
                            .addHeader("Device",model.getDevice() );

                    LibPrefs.LogInfo("RestAPI","device : "+model.getDevice());
                    String Authorization="";
                    if (!model.getAuthorization().isEmpty()) {
                        builder.addHeader("Authorization", model.getAuthorization());
                        LibPrefs.LogInfo("RestAPI","Authorization : "+model.getAuthorization());
                    }
                    Request request = builder.build();
                    return chain.proceed(request);
                }
            });
            ObjectMapper mapper=new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            //.client(builder.build())
            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(JacksonConverterFactory.create(mapper))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(model.getBaseUrl())
                    .build();


            return retrofit.create(tClass);
        }



//        static RestApi restApi = null;
//        public static RestApi getAPI(){
//            if(restApi == null)
//                restApi = get(RestApi.class);
//            return restApi;
//        }

}
