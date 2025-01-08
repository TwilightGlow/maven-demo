package com.example.crypto.aes;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.example.crypto.Text;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangjw54
 */
@Slf4j
public class HutoolAes {

    @Test
    public void generateKey() {

        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 128).getEncoded();

        AES aes = SecureUtil.aes(key);

        byte[] encrypt = aes.encrypt(Text.ORIGINAL_TEXT);
        log.info("加密: {}", new String(encrypt));

        byte[] decrypt = aes.decrypt(encrypt);
        log.info("解密: {}", new String(decrypt));
    }

    @Test
    public void aes() {
        // AES模式下，Key必须为16/24/32位
        String key = "1234567812345678";
        // iv: 偏移量，ECB模式不需要，CBC模式下必须为16位
        String iv = "1234567812345678";

        // AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());

        byte[] encrypt = aes.encrypt(Text.ORIGINAL_TEXT);
        log.info("加密: {}", new String(encrypt, StandardCharsets.UTF_8));

        String encryptBase64 = aes.encryptBase64(Text.ORIGINAL_TEXT);
        log.info("加密Base64: {}", encryptBase64);

        String encryptHex = aes.encryptHex(Text.ORIGINAL_TEXT);
        log.info("加密Hex: {}", encryptHex);

        byte[] decrypt = aes.decrypt(encrypt);
        log.info("解密: {}", new String(decrypt, StandardCharsets.UTF_8));

        String decryptBase64 = aes.decryptStr(encryptBase64);
        log.info("解密Base64: {}", decryptBase64);

        String decryptHex = aes.decryptStr(encryptHex);
        log.info("解密Hex: {}", decryptHex);
    }

}
