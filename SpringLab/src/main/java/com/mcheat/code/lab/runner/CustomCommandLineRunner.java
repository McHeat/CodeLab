package com.mcheat.code.lab.runner;

import com.mcheat.code.lab.properties.CustomConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 探索CommandLineRunner用法
 *
 * @author wangjy
 * @date 2017/9/28
 */
@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;
    @Autowired
    private CustomConfigurationProperties customProperties;


    public void run(String... args) throws Exception {
        poolTaskExecutor.submit(() -> {
            try {
                logger.info("I'm in CustomCommandLineRunner.........");
                List<CustomConfigurationProperties.CustomUserInfo> customUserInfo = customProperties.getCustomUserInfo();
                customUserInfo.forEach(userInfo -> {
                    logger.info("用户名：{}，描述：{}", userInfo.getName(), userInfo.getDesc());
                });
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
    }
}
