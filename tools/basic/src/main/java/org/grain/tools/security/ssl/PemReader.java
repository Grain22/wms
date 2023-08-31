package org.grain.tools.security.ssl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class PemReader {
    private static final Logger logger = Logger.getLogger(PemReader.class.getName());
    private static final Pattern CERT_HEADER = Pattern.compile("-+BEGIN\\s[^-\\r\\n]*CERTIFICATE[^-\\r\\n]*-+(?:\\s|\\r|\\n)+");
    private static final Pattern CERT_FOOTER = Pattern.compile("-+END\\s[^-\\r\\n]*CERTIFICATE[^-\\r\\n]*-+(?:\\s|\\r|\\n)*");
    private static final Pattern KEY_HEADER = Pattern.compile("-+BEGIN\\s[^-\\r\\n]*PRIVATE\\s+KEY[^-\\r\\n]*-+(?:\\s|\\r|\\n)+");
    private static final Pattern KEY_FOOTER = Pattern.compile("-+END\\s[^-\\r\\n]*PRIVATE\\s+KEY[^-\\r\\n]*-+(?:\\s|\\r|\\n)*");
    private static final Pattern BODY = Pattern.compile("[a-z0-9+/=][a-z0-9+/=\\r\\n]*", Pattern.CASE_INSENSITIVE);

    private PemReader() {
    }

    static List<byte[]> readCertificates(File file) throws CertificateException {
        try {
            InputStream in = new FileInputStream(file);
            try {
                return readCertificates(in);
            } finally {
                safeClose(in);
            }
        } catch (FileNotFoundException e) {
            throw new CertificateException("could not find certificate file: " + file);
        }
    }

    static byte[] readPrivateKey(File file) throws KeyException {
        try {
            InputStream in = new FileInputStream(file);
            try {
                return readPrivateKey(in);
            } finally {
                safeClose(in);
            }
        } catch (FileNotFoundException e) {
            throw new KeyException("could not find key file: " + file);
        }
    }

    static byte[] readPrivateKey(InputStream in) throws KeyException {
        String content;
        try {
            content = readContent(in);
        } catch (IOException e) {
            throw new KeyException("failed to read key input stream", e);
        }

        Matcher m = KEY_HEADER.matcher(content);
        if (!m.find()) {
            throw keyNotFoundException();
        }
        m.usePattern(BODY);
        if (!m.find()) {
            throw keyNotFoundException();
        }
        byte[] base64 = m.group(0).getBytes(StandardCharsets.US_ASCII);
        m.usePattern(KEY_FOOTER);
        if (!m.find()) {
            // Key is incomplete.
            throw keyNotFoundException();
        }
        return Base64.getDecoder().decode(base64);
    }

    private static KeyException keyNotFoundException() {
        return new KeyException("could not find a PKCS #8 private key in input stream" + " (see https://netty.io/wiki/sslcontextbuilder-and-private-key.html for more information)");
    }

    static List<byte[]> readCertificates(InputStream in) throws CertificateException {
        String content;
        try {
            content = readContent(in);
        } catch (IOException e) {
            throw new CertificateException("failed to read certificate input stream", e);
        }

        List<byte[]> certs = new ArrayList<>();
        Matcher m = CERT_HEADER.matcher(content);
        int start = 0;
        for (; ; ) {
            if (!m.find(start)) {
                break;
            }
            m.usePattern(BODY);
            if (!m.find()) {
                break;
            }
            byte[] base64 = m.group(0).getBytes(StandardCharsets.US_ASCII);
            m.usePattern(CERT_FOOTER);
            if (!m.find()) {
                // Certificate is incomplete.
                break;
            }
            byte[] der = Base64.getDecoder().decode(base64);
            certs.add(der);
            start = m.end();
            m.usePattern(CERT_HEADER);
        }

        if (certs.isEmpty()) {
            throw new CertificateException("found no certificates in input stream");
        }

        return certs;
    }

    private static String readContent(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buf = new byte[8192];
            for (; ; ) {
                int ret = in.read(buf);
                if (ret < 0) {
                    break;
                }
                out.write(buf, 0, ret);
            }
            return out.toString(StandardCharsets.US_ASCII);
        } finally {
            safeClose(out);
        }
    }

    private static void safeClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to close a stream", e);
        }
    }
}
