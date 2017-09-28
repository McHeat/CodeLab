package com.mcheat.code.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2017/9/28.
 */
@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ThreadPoolTaskExecutor initPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(10);
        pool.setMaxPoolSize(20);
        pool.setQueueCapacity(50);
        pool.setKeepAliveSeconds(10000);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        pool.initialize();
        return pool;
    }

}
