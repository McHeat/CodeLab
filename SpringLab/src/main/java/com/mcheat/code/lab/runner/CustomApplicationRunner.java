package com.mcheat.code.lab.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 探索ApplicationRunner用法
 *
 * @author wangjy
 * @date 2017/9/28
 */
@Component
public class CustomApplicationRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;

    public void run(ApplicationArguments args) throws Exception {
        poolTaskExecutor.submit(() -> {
            try {
                Thread.sleep(10000);
                logger.info("I'm in CustomApplicationRunner........");
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        });

    }
}
