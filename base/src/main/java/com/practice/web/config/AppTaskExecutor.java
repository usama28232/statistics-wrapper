package com.practice.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Profile("threads")
@Configuration
public class AppTaskExecutor {

    @Value("${app.thread.prefix:u28232}")
    private String threadNm;

    @Value("${app.core.thread.pool.size:30}")
    private int corePoolSize;

    @Value("${app.max.thread.pool.size:100}")
    private int maxPoolSize;

    @Value("${app.thread.queue.size:0}")
    private int queueCapacity;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.corePoolSize);
        if (this.maxPoolSize > 0) {
            executor.setMaxPoolSize(this.maxPoolSize);
        }
        if (this.queueCapacity > 0) {
            executor.setQueueCapacity(this.queueCapacity);
        }
        executor.setThreadNamePrefix(this.threadNm);
        executor.initialize();
        return executor;
    }

}
