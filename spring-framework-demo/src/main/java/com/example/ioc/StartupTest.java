package com.example.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.core.metrics.StartupStep;

/**
 * @author zhangjw54
 */
public class StartupTest {

    @Test
    public void startupTest() {
        BufferingApplicationStartup startup = new BufferingApplicationStartup(32);
        StartupStep startupStep = startup.start("程序即将启动！");
        startupStep.tag("第一步", "xxxx");
        startupStep.tag("第二步", "xxxx");
        startupStep.end();

        StartupStep startupStep2 = startup.start("程序即将启动2！");
        startupStep2.tag("第一步2", "xxxx");
        startupStep2.tag("第二步2", "xxxx");
        startupStep2.end();

        startup.getBufferedTimeline().getEvents().forEach(x -> {
            System.out.println(x.getStartupStep().getName());
        });
        System.out.println(startup.getBufferedTimeline().getStartTime());

        startupStep.getTags().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));
    }
}
