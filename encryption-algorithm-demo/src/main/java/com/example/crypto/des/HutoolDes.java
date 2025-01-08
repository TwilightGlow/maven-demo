package com.example.crypto.des;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.example.crypto.Text;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangjw54
 */
@Slf4j
public class HutoolDes {

    @Test
    public void generateKey() {

        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), 56).getEncoded();

        DES des = SecureUtil.des(key);

        byte[] encrypt = des.encrypt(Text.ORIGINAL_TEXT);
        log.info("加密: {}", new String(encrypt));

        byte[] decrypt = des.decrypt(encrypt);
        log.info("解密: {}", new String(decrypt));
    }

    @Test
    public void des() {
        // DES模式下，Key必须为8位
        String key = "12345678";
        // iv: 偏移量，ECB模式不需要，CBC模式下必须为8位
        String iv = "12345678";

        // DES des = new DES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        DES des = new DES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());

        byte[] encrypt = des.encrypt(Text.ORIGINAL_TEXT);
        log.info("加密: {}", new String(encrypt, StandardCharsets.UTF_8));

        String encryptBase64 = des.encryptBase64(Text.ORIGINAL_TEXT);
        log.info("加密Base64: {}", encryptBase64);

        String encryptHex = des.encryptHex(Text.ORIGINAL_TEXT);
        log.info("加密Hex: {}", encryptHex);

        byte[] decrypt = des.decrypt(encrypt);
        log.info("解密: {}", new String(decrypt, StandardCharsets.UTF_8));

        String decryptBase64 = des.decryptStr(encryptBase64);
        log.info("解密Base64: {}", decryptBase64);

        String decryptHex = des.decryptStr(encryptHex);
        log.info("解密Hex: {}", decryptHex);
    }
}
