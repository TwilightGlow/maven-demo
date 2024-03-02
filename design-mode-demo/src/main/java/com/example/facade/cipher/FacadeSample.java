package com.example.facade.cipher;

/**
 * @author Javen
 * @date 2024/3/2
 */
public class FacadeSample {

    public static void main(String[] args) {
        // 针对抽象外观类
        AbstractEncryptFacade abstractFacade;
        abstractFacade = (AbstractEncryptFacade) XmlUtil.getBean();

        // EncryptFacade encryptFacade = new EncryptFacade();
        abstractFacade.fileEncrypt("C:\\Users\\Jinwei Zhang\\IdeaProjects\\maven-demo\\design-mode-demo\\src\\main\\java\\com\\example\\facade\\cipher\\aaa.txt",
                "design-mode-demo/src/main/java/com/example/facade/cipher/bbb.txt");
    }
}
