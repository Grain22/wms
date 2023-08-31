package org.grain.tools.security.ssl;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

@SuppressWarnings("unused")
public class SSL {

    public static SSLContext initSSLContext() throws NoSuchAlgorithmException {
        return SSLContext.getDefault();
    }

    public static void keyManager(File keyCertChainFile, File keyFile, String keyPassword) {
        X509Certificate[] keyCertChain;
        PrivateKey key;
    }


}
