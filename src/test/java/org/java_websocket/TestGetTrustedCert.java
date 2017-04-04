package org.java_websocket;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jm on 17-4-4.
 */


public class TestGetTrustedCert {

    public static void main(String[] args) throws Exception {
        TrustManagerFactory trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        List<Certificate> x509Certificates = new ArrayList<Certificate>();
        trustManagerFactory.init((KeyStore)null);

        List<TrustManager> trustManagers = Arrays.asList(trustManagerFactory.getTrustManagers());
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                X509Certificate[] acceptedIssuers = ((X509TrustManager) trustManager).getAcceptedIssuers();
                List<X509Certificate> x509Certificates1 = Arrays.asList(acceptedIssuers);
                x509Certificates.addAll(x509Certificates1);
                System.out.println(x509Certificates1.toString());
            }
        }
    }



}
