package org.grain.tools.security.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * some usage can see RsaUtilsTest
 *
 * @author grain
 */
@SuppressWarnings("unused")
public class RsaUtils {
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String KEY_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 2 << 11;

    public static KeyStore getKeyStore(File file, String pass) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
        if (!file.exists() || !file.isFile()) throw new RuntimeException("key store file is not correct");
        try (InputStream inputStream = Files.newInputStream(file.toPath())) {
            return KeyStore.getInstance(file, pass.toCharArray());
        }
    }

    public static byte[] encrypt(PublicKey publicKey, byte[] charArray) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher instance = Cipher.getInstance(publicKey.getAlgorithm());
        instance.init(Cipher.ENCRYPT_MODE, publicKey);
        return instance.doFinal(charArray);
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] afterCrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher instance = Cipher.getInstance(privateKey.getAlgorithm());
        instance.init(Cipher.DECRYPT_MODE, privateKey);
        return instance.doFinal(afterCrypt);
    }

    public static KeyPair generator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] sign(byte[] data, PrivateKey privateKey) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature instance = Signature.getInstance(SIGNATURE_ALGORITHM);
        instance.initSign(privateKey);
        instance.update(data);
        return instance.sign();
    }

    public static boolean verify(byte[] bytes, byte[] signed, PublicKey publicKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
        verifySign.initVerify(publicKey);
        verifySign.update(bytes);
        return verifySign.verify(signed);
    }

    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decode(String string) {
        return Base64.getDecoder().decode(string);
    }

    public static String getPrivateKeyString(PrivateKey privateKey) {
        return encode(privateKey.getEncoded());
    }

    public static String getPublicKeyString(PublicKey publicKey) {
        return encode(publicKey.getEncoded());
    }

    public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = decode(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = decode(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }
}
