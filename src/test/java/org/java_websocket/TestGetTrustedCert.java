package org.java_websocket;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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

    public static void testAndroid() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("AndroidCAStore");
        keyStore.load(null, null);
        Enumeration keyAliases = keyStore.aliases();
        while (keyAliases.hasMoreElements())
        {
            String alias = (String)keyAliases.nextElement();
            //系统默认CA开头为system  用户的为user
            if (alias.startsWith("system"))
            {
                X509Certificate cert2 = (X509Certificate)keyStore.getCertificate(alias);
                /*if (keyID == cert2.getSerialNumber().toString())
                {
                    isTrustRoot = true;
                }*/
            }
        }
    }

}
