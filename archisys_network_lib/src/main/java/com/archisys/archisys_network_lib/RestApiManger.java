package com.archisys.archisys_network_lib;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestApiManger {

       public static  <T> T get( Class<T> tClass) {

           final Context context = LibBaseApplication.getCurrentContext();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(100);
            dispatcher.setMaxRequestsPerHost(10);
            builder.dispatcher(dispatcher);


            LibPrefs.getDevice=Device.getInstance(context).toString();
//            if (LibPrefs.hasPrefs(context, LibPrefs.Authorization) && !LibPrefs.getValue(context, LibPrefs.Authorization, "").isEmpty()) {
//                LibPrefs.getAuthorization=LibPrefs.getValue(context,LibPrefs.Authorization,"");
//            }




            //added fix headers
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {


                    Request.Builder builder = chain.request().newBuilder()
                            .addHeader("Content-type", "application/json")
                            .addHeader("Device",LibPrefs.getDevice );

                    LibPrefs.LogInfo("RestAPI","device : "+LibPrefs.getDevice);
                    String Authorization="";
                    LibPrefs.LogInfo("RestAPI","CONTEXT : "+context.getClass().getSimpleName());
                    if (!LibPrefs.getAuthorization.isEmpty()) {
                        builder.addHeader("Authorization", LibPrefs.getAuthorization);
                        LibPrefs.LogInfo("RestAPI","Authorization : "+LibPrefs.getAuthorization);
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
                    .baseUrl(LibPrefs.BaseUrl)
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
