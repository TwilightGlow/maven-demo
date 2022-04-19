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
}
