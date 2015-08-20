package com.example.yorkyan.myapplication.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.yorkyan.myapplication.volley.LruBitmapCache;
import com.example.yorkyan.myapplication.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

public class BaseApplication extends Application{
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();

        // init request queue
        requestQueue = getRequestQueue();
        // init imageLoader
        imageLoader = getImageLoader();
    }

    public ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(getRequestQueue(), new LruBitmapCache(LruBitmapCache.getCacheSize(this)));
        }

        return imageLoader;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }

        return requestQueue;
    }

    public RequestQueue getHttpsRequestQueue() {
        if (requestQueue == null) {
            try {
                // Load CAs from an InputStream
                // (could be from a resource or ByteArrayInputStream or ...)
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "BC");
                // From https://www.washington.edu/itconnect/security/ca/load-der.crt
                InputStream inputStream = new BufferedInputStream(getAssets().open("client.crt"));
                Certificate certificate;
                try {
                    certificate = certificateFactory.generateCertificate(inputStream);
                } finally {
                    inputStream.close();
                }

                // Create a KeyStore containing our trusted CAs
                String keyStoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", certificate);

                // Create a TrustManager that trusts the CAs in our KeyStore
                String defaultAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(defaultAlgorithm);
                trustManagerFactory.init(keyStore);

                // Create an SSLContext that uses our TrustManager
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                HttpsURLConnection.setDefaultHostnameVerifier(new HostNameVerifier());
                HurlStack hurlStack = new HurlStack(null, sslContext.getSocketFactory());
                requestQueue = Volley.newRequestQueue(this, hurlStack);
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }

        return requestQueue;
    }

    class HostNameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return hostname.equals(getString(R.string.host));
        }
    }
}
