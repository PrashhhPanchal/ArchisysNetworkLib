package com.archisys.archisys_network_lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

///https://medium.com/@kibotu/handling-custom-ssl-certificates-on-android-and-fixing-sslhandshakeexception-65ffb9dc612e
///https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.Builder.html#sslSocketFactory-javax.net.ssl.SSLSocketFactory-
public class SslUtils {

    static  X509TrustManager trustManager = null;
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

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            sslSocketFactory = sslContext.getSocketFactory();

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
            AssetManager assetManager = context.getAssets();
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput =  context.getResources().openRawResource(certificateResourceId);
            Certificate cert = cf.generateCertificate(caInput);
            Log.d("SslUtilsAndroid", "ca=" + ((X509Certificate) cert).getSubjectDN());
            String keyStoreType = KeyStore.getDefaultType();
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null,null);
            keyStore.setCertificateEntry("ca", cert);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return  keyStore;
    }
}

