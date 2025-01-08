package com.example.crypto.zip;

import cn.hutool.core.util.ZipUtil;
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
public class HutoolZip {

    @Test
    public void zip() {
        // 将原始文本压缩为字节流
        byte[] gzip = ZipUtil.gzip(Text.ORIGINAL_TEXT, String.valueOf(StandardCharsets.UTF_8));
        log.info("gzip: {}", new String(gzip));

        byte[] ungzip = ZipUtil.unGzip(gzip);
        log.info("ungzip: {}", new String(ungzip));
    }

    @Test
    public void zipAndAes() {
        byte[] gzip = ZipUtil.gzip(Text.ORIGINAL_TEXT, String.valueOf(StandardCharsets.UTF_8));

        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 128).getEncoded();
        AES aes = SecureUtil.aes(key);

        String encryptBase64 = aes.encryptBase64(gzip);
        log.info("先压缩后加密Base64: {}", encryptBase64);

        byte[] decrypt = aes.decrypt(encryptBase64);

        byte[] ungzip = ZipUtil.unGzip(decrypt);
        log.info("解压后: {}", new String(ungzip, StandardCharsets.UTF_8));

        String encryptBase64x = aes.encryptBase64(Text.ORIGINAL_TEXT);
        log.info("加密后Base64: {}", encryptBase64x);
        String decryptStr = aes.decryptStr(encryptBase64x);
        log.info("解密后: {}", decryptStr);
    }
}
