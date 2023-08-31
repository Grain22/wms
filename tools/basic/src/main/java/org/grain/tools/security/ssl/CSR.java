package org.grain.tools.security.ssl;


import jakarta.json.bind.JsonbBuilder;
import jakarta.xml.bind.DatatypeConverter;
import lombok.Getter;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

/**
 * csr 工具
 * <p>
 * use
 * <p>
 * <a href="https://mvnrepository.com/artifact/org.bouncycastle">maven</a>
 * <dependency>
 * <groupId>org.bouncycastle</groupId>
 * <artifactId>bcpkix-jdk18on</artifactId>
 * <version>1.76</version>
 * </dependency>
 * <dependency>
 * <groupId>org.bouncycastle</groupId>
 * <artifactId>bcpkix-debug-jdk18on</artifactId>
 * <version>1.76</version>
 * </dependency>
 * <dependency>
 * <groupId>org.bouncycastle</groupId>
 * <artifactId>bcutil-jdk18on</artifactId>
 * <version>1.76</version>
 * </dependency>
 * <dependency>
 * <groupId>org.bouncycastle</groupId>
 * <artifactId>bcprov-jdk18on</artifactId>
 * <version>1.76</version>
 * </dependency>
 * </p>
 *
 * @author laowu
 * @since 2023年8月31日
 */
@SuppressWarnings({"SameParameterValue", "unused"})
public class CSR {
    private static final Logger LOGGER = Logger.getLogger(CSR.class.getName());
    private static final String X509 = "X.509";

    private static final String SHA256withRSA = "SHA256withRSA";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());

    public static CSRStore generateCSR(String name) throws NoSuchAlgorithmException, IOException, OperatorCreationException {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(2048);
        KeyPair keyPair = rsa.generateKeyPair();
        String publicPem = opensslPemFormatKeyFileContent(keyPair.getPublic(), Type.RSA);
        LOGGER.warning(publicPem);
        String privatePem = opensslPemFormatKeyFileContent(keyPair.getPrivate(), Type.RSA);
        LOGGER.warning(privatePem);
        X500Principal x500Principal = new X500Principal(name);
        JcaContentSignerBuilder jcaContentSignerBuilder = new JcaContentSignerBuilder(SHA256withRSA);
        ContentSigner contentSigner = jcaContentSignerBuilder.build(keyPair.getPrivate());
        JcaPKCS10CertificationRequestBuilder jcaPKCS10CertificationRequestBuilder = new JcaPKCS10CertificationRequestBuilder(x500Principal, keyPair.getPublic());
        ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
        GeneralName email = new GeneralName(GeneralName.rfc822Name, "email=grain@gmail.com");
        GeneralName host = new GeneralName(GeneralName.rfc822Name, "ip=127.0.0.1");
        GeneralName port = new GeneralName(GeneralName.rfc822Name, "port=10443");
        GeneralNames generalNames = new GeneralNames(new GeneralName[]{email, host, port});
        extensionsGenerator.addExtension(Extension.subjectAlternativeName, false, generalNames);
        jcaPKCS10CertificationRequestBuilder.addAttribute(PKCSObjectIdentifiers.pkcs_9_at_extensionRequest, extensionsGenerator.generate());
        PKCS10CertificationRequest pkcs10CertificationRequest = jcaPKCS10CertificationRequestBuilder.build(contentSigner);
        return new CSRStore(keyPair.getPublic(), publicPem, keyPair.getPrivate(), privatePem, pkcs10CertificationRequest, opensslFormatPKCS10CertificationRequest(pkcs10CertificationRequest));
    }

    private static String opensslFormatPKCS10CertificationRequest(PKCS10CertificationRequest pkcs10CertificationRequest) throws IOException {
        return "-----BEGIN CERTIFICATE REQUEST-----\n" + Base64.getEncoder().encodeToString(pkcs10CertificationRequest.getEncoded()) + "\n-----END CERTIFICATE REQUEST-----\n";
    }

    /**
     * format openssl pem file
     *
     * @param key  key {@link PublicKey} {@link PrivateKey}
     * @param type {@link Type}
     * @return pem file string
     * @throws IOException io
     */
    private static String opensslPemFormatKeyFileContent(Key key, Type type) throws IOException {
        String pemObjectType = "";
        switch (type) {
            case RSA -> {
            }
            case ECC -> pemObjectType += "EC ";
            default -> throw new RuntimeException("not support");
        }
        if (key instanceof PrivateKey) {
            pemObjectType += "PRIVATE KEY";
        } else if (key instanceof PublicKey) {
            pemObjectType += "PUBLIC KEY";
        }
        PemObject pemObject = new PemObject(pemObjectType, key.getEncoded());
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        stringWriter.close();
        return stringWriter.toString();
    }

    public static String getDigest(X509Certificate certificate) throws NoSuchAlgorithmException, CertificateEncodingException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(certificate.getEncoded());
        byte[] digest = instance.digest();
        String hexBinary = DatatypeConverter.printHexBinary(digest);
        return hexBinary.toLowerCase();
    }

    public static CERT anaCert(Certificate certificate) throws CertificateEncodingException, NoSuchAlgorithmException {
        if (certificate instanceof X509Certificate x509Certificate) {
            PublicKey publicKey = x509Certificate.getPublicKey();
            String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            String dn = subjectX500Principal.toString();
            String[] split = dn.split(",");
            Map<String, String> subjectNameMap = new HashMap<>();
            for (String s : split) {
                String[] sin = s.trim().split("=");
                subjectNameMap.put(sin[0].trim(), sin[1].trim());
            }
            return new CERT(certificate, publicKey, publicKeyStr, LocalDateTime.ofInstant(x509Certificate.getNotBefore().toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(x509Certificate.getNotAfter().toInstant(), ZoneId.systemDefault()), x509Certificate.getSerialNumber(), getDigest(x509Certificate), subjectNameMap);
        }
        throw new RuntimeException("not a x509Certificate");
    }

    public static CERT anaCert(Path path) throws CertificateException, IOException, NoSuchAlgorithmException {
        CertificateFactory instance = CertificateFactory.getInstance(X509);
        try (InputStream inputStream = Files.newInputStream(path)) {
            Certificate certificate = instance.generateCertificate(inputStream);
            return anaCert(certificate);
        }
    }

    public static CERT anaCert(String pemStr) throws CertificateException, NoSuchAlgorithmException, IOException {
        CertificateFactory instance = CertificateFactory.getInstance(X509);
        byte[] bytes = pemStr.getBytes(StandardCharsets.UTF_8);
        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            Certificate certificate = instance.generateCertificate(inputStream);
            return anaCert(certificate);
        }
    }

    enum Type {
        RSA, ECC,
    }

    @Getter
    public static class CSRStore {
        PublicKey publicKey;
        String publicKeyFileContent;
        PrivateKey privateKey;
        String privateKeyFileContent;
        PKCS10CertificationRequest pkcs10CertificationRequest;
        String pkcs10CertificationRequestFileContent;

        public CSRStore(PublicKey publicKey, String publicKeyFileContent, PrivateKey privateKey, String privateKeyFileContent, PKCS10CertificationRequest pkcs10CertificationRequest, String pkcs10CertificationRequestFileContent) {
            this.publicKey = publicKey;
            this.publicKeyFileContent = publicKeyFileContent;
            this.privateKey = privateKey;
            this.privateKeyFileContent = privateKeyFileContent;
            this.pkcs10CertificationRequest = pkcs10CertificationRequest;
            this.pkcs10CertificationRequestFileContent = pkcs10CertificationRequestFileContent;
        }
    }

    @Getter
    public static class CERT {
        Certificate certificate;
        PublicKey publicKey;
        String publicKeyString;
        LocalDateTime start;
        LocalDateTime end;
        BigInteger certNumber;
        String thumbprint;
        Map<String, String> subjectNames;

        public CERT(Certificate certificate, PublicKey publicKey, String publicKeyString, LocalDateTime start, LocalDateTime end, BigInteger certNumber, String thumbprint, Map<String, String> subjectNames) {
            this.certificate = certificate;
            this.publicKey = publicKey;
            this.publicKeyString = publicKeyString;
            this.start = start;
            this.end = end;
            this.certNumber = certNumber;
            this.thumbprint = thumbprint;
            this.subjectNames = subjectNames;
        }

        @SuppressWarnings("resource")
        @Override
        public String toString() {
            return "certificate " + certificate.toString() + "\n" + "public key " + publicKey.toString() + "\n" + "start time " + dateTimeFormatter.format(start) + "\n" + "end time " + dateTimeFormatter.format(end) + "\n" + "cert number " + certNumber.toString() + "\n" + "thumbprint " + thumbprint + "\n" + "subjectNames " + JsonbBuilder.create().toJson(subjectNames);
        }
    }

}