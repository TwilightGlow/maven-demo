package com.example.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zhangjw54
 */
public class ShutdownHookTest {

    static class MyGenericApplicationContext extends GenericApplicationContext {
        private Thread shutdownHook = null;
        @Override
        public void registerShutdownHook() {
            if (this.shutdownHook == null) {
                // No shutdown hook registered yet.
                this.shutdownHook = new Thread(SHUTDOWN_HOOK_THREAD_NAME) {
                    @Override
                    public void run() {
                        System.out.println("容器关闭了，该干啥干啥吧！");
                    }
                };
                Runtime.getRuntime().addShutdownHook(this.shutdownHook);
            }
        }
    }

    @Test
    public void testShutdownHook() throws InterruptedException {
        MyGenericApplicationContext context = new MyGenericApplicationContext();
        context.registerShutdownHook();
        System.out.println("before");
        Thread.sleep(2000);
        System.out.println("after");
    }
}
