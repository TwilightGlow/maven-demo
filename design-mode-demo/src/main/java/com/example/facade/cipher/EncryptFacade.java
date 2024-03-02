package com.example.facade.cipher;

/**
 * @author Javen
 * @date 2024/3/2
 */
public class EncryptFacade extends AbstractEncryptFacade {

    private final CipherFileReader cipherFileReader = new CipherFileReader();
    private final CipherMachine cipherMachine = new CipherMachine();
    private final CipherFileWriter cipherFileWriter = new CipherFileWriter();

    @Override
    public void fileEncrypt(String filePath, String fileDest) {
        String plainStr = cipherFileReader.read(filePath);
        String encryptStr = cipherMachine.encrypt(plainStr);
        cipherFileWriter.write(encryptStr, fileDest);
    }
}
