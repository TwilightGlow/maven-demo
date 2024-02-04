package com.example.controller;

import com.example.service.RsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Javen
 * @date 2022/4/13
 */
@RestController
@RequestMapping("rsa")
@RequiredArgsConstructor
public class RsaToolsController {

    private final RsaService rsaService;

    @GetMapping("generateKeys")
    public Map<String, Object> generateKey() {
        return rsaService.generateKey("RSA");
    }

    @GetMapping("encryptRsa")
    public Map<String, Object> encrypt(@RequestParam String publicKeyString,
                                       @RequestParam String decryptedString) throws Exception {
        return rsaService.encryptRsa(publicKeyString, decryptedString);
    }

    @GetMapping("decryptRsa")
    public Map<String, Object> decrypt(@RequestParam String privateKeyString,
                                       @RequestParam String encryptedString) throws Exception {
        return rsaService.decryptRsa(privateKeyString, encryptedString);
    }

    @GetMapping("sign")
    public Map<String, Object> sign(@RequestParam String privateKeyString,
                                    @RequestParam String message) throws Exception {
        return rsaService.sign(privateKeyString, message);
    }

    @GetMapping("verifySignature")
    public Map<String, Object> verifySignature(@RequestParam String publicKeyString,
                                               @RequestParam String message,
                                               @RequestParam String signature) throws Exception {
        return rsaService.verifySignature(publicKeyString, message, signature);
    }


    @GetMapping("test")
    public void test() throws Exception {
        Map<String, Object> map = rsaService.generateKey("RSA");
        String publicKey = (String) map.get("publicKey");
        String privateKey = (String) map.get("privateKey");
        String encryptString = (String) rsaService.encryptRsa(publicKey, "Hello World").get("encryptedString");
        String decryptedString = (String) rsaService.decryptRsa(privateKey, encryptString).get("decryptedString");
        System.out.println("加密字符串：" + encryptString);
        System.out.println("解密字符串：" + decryptedString);

        String signMessage = (String) rsaService.sign(privateKey, "Hello World").get("signMessage");
        boolean result = (boolean) rsaService.verifySignature(publicKey, "Hello World", signMessage).get("result");
        System.out.println("验证签名1：" + result);
        boolean result1 = (boolean) rsaService.verifySignature(publicKey, "Hello World123", signMessage).get("result");
        System.out.println("验证签名2：" + result1);
    }
}
