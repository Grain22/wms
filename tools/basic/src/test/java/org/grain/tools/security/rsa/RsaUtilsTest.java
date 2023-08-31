package org.grain.tools.security.rsa;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Slf4j
public class RsaUtilsTest {
    private static final String source_str = "随便写写用来测试,可以放你需要的内容";

    @Test
    public void keyStore() throws Exception {
        KeyPair generator = RsaUtils.generator();
        byte[] encrypt = RsaUtils.encrypt(generator.getPublic(), source_str.getBytes(StandardCharsets.UTF_8));
        byte[] decrypt = RsaUtils.decrypt(generator.getPrivate(), encrypt);
        System.out.println(new String(decrypt, StandardCharsets.UTF_8));
    }

    @Test
    public void doGeneratorAndSignAndVerify() throws Exception {
        try {
            log.info("generate");
            KeyPair generator = RsaUtils.generator();
            log.info("write key to console : public");
            PublicKey aPublic = generator.getPublic();
            String publicKeyString = RsaUtils.getPublicKeyString(aPublic);
            System.out.println(publicKeyString);
            log.info("write key to console : private");
            PrivateKey aPrivate = generator.getPrivate();
            String privateKeyString = RsaUtils.getPrivateKeyString(aPrivate);
            System.out.println(privateKeyString);
            log.info("read public key");
            PublicKey publicKey = RsaUtils.getPublicKey(publicKeyString);
            log.info("read private key");
            PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyString);
            log.info("sign source_str");
            byte[] sign_source = source_str.getBytes(StandardCharsets.UTF_8);
            byte[] sign = RsaUtils.sign(sign_source, privateKey);
            String signStr = Base64.getEncoder().encodeToString(sign);
            log.info("signed result {}", signStr);
            byte[] signed = Base64.getDecoder().decode(signStr);
            log.info("verify");
            boolean verify = RsaUtils.verify(sign_source, signed, publicKey);
            log.info("verify result {}", verify);
        } catch (Exception e) {
            log.error("catch", e);
            throw e;
        }
    }
}
