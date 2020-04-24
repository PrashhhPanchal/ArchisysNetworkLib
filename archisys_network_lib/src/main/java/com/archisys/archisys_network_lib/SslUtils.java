package com.archisys.archisys_network_lib;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.internal.platform.Platform;

//https://github.com/dotnet/aspnetcore/tree/master/src/SignalR/clients/java/signalr/src
public class SslUtils {

    static X509TrustManager trustManager = null;
    static SSLSocketFactory sslSocketFactory = null;

    public static  X509TrustManager getTrustManager(){
        return  trustManager;
    }
    public static  SSLSocketFactory getSslSocketFactory(){
        return sslSocketFactory;
    }

    public static void  Init(Context context, int certificateResourceId){
        if(trustManager != null && sslSocketFactory != null)
            return;

        try{
            KeyStore keyStore = getKeyStore(context, certificateResourceId);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }


            trustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = Platform.get().getSSLContext();
            sslContext.init(null, new TrustManager[] { trustManager }, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();

//            HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String s, SSLSession sslSession) {
//                    return s.contains("stockbook.net");
//                }
//            });

        }
        catch (Exception e){
            String msg = "Error during creating SslContext for certificate from assets";
            e.printStackTrace();
            throw new RuntimeException(msg);
        }
    }
    private static KeyStore getKeyStore(Context context, int certificateResourceId){
        KeyStore keyStore = null;
        try {
            String keyStoreType = KeyStore.getDefaultType();
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null,null);

            AssetManager assetManager = context.getAssets();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput =  context.getResources().openRawResource(certificateResourceId);
            int i=0;
            for (Certificate cert: cf.generateCertificates(caInput)) {
                keyStore.setCertificateEntry("ca"+i, cert);
                i++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return  keyStore;
    }


}
