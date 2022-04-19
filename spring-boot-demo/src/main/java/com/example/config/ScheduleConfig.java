package com.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Javen
 * @date 2022/4/1
 */
@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix = "schedule", name = "enable", havingValue = "true")
public class ScheduleConfig {
}
