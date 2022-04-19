package com.example.service.impl;

import com.example.service.RsaService;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @date 2022/4/13
 */
@Service
public class RsaServiceImpl implements RsaService {

    @Override
    public Map<String, Object> generateKey(String algorithm) {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
//        byte[] privateKeyEncoded = Base64.getEncoder().encode(privateKey.getEncoded());
//        byte[] publicKeyEncoded = Base64.getEncoder().encode(publicKey.getEncoded());
        HashMap<String, Object> map = new HashMap<>();
        map.put("privateKey", Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        map.put("publicKey", Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        return map;
    }

    @Override
    public Map<String, Object> encryptRsa(String publicKeyString, String decryptedString) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
        PublicKey publicKey = keyFactory.generatePublic(spec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 先RSA加密，再Base64加密
        byte[] bytes = cipher.doFinal(decryptedString.getBytes(StandardCharsets.UTF_8));
        String encryptedString = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
        HashMap<String, Object> map = new HashMap<>();
        map.put("encryptedString", encryptedString);
        return map;
    }

    @Override
    public Map<String, Object> decryptRsa(String privateKeyString, String encryptedString) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
        PrivateKey privateKey = keyFactory.generatePrivate(spec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 先Base64解密，在RSA解密
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encryptedString.getBytes(StandardCharsets.UTF_8)));
        String decryptedString = new String(bytes, StandardCharsets.UTF_8);
        HashMap<String, Object> map = new HashMap<>();
        map.put("decryptedString", decryptedString);
        return map;
    }
}
