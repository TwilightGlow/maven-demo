package com.example.service;

import java.util.Map;

/**
 * @author Javen
 * @date 2022/4/13
 */
public interface RsaService {

    Map<String, Object> generateKey(String algorithm);

    Map<String, Object> encryptRsa(String publicKeyString, String decryptedString) throws Exception;

    Map<String, Object> decryptRsa(String privateKeyString, String encryptedString) throws Exception;
}
