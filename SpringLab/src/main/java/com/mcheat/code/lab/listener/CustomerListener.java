package com.mcheat.code.lab.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * Application Event实验
 * <p>
 * ApplicationStartingEvent 监听不到
 * ApplicationEnvironmentPreparedEvent 可监听到
 * ApplicationPreparedEvent 可监听到
 * ApplicationReadyEvent 可监听到
 * ApplicatinFailEvent 可监听到
 */
public class CustomerListener implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("正在执行。。。。。。。");
    }
}