package com.mcheat.code.lab;

import com.mcheat.code.lab.listener.CustomerListener;
import com.mcheat.code.lab.properties.CustomConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@SpringBootApplication
@EnableConfigurationProperties(CustomConfigurationProperties.class)
public class SpringLabApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {

		/*SpringApplication.run(SpringLabApplication.class, args);*/

/*		SpringApplication application = new SpringApplication(SpringLabApplication.class);
        application.addListeners(new CustomerListener());
		application.run(args);*/

        new SpringApplicationBuilder().sources(SpringLabApplication.class)
                .listeners(new CustomerListener())            // 添加监听器
                .run(args);
    }

    /**
     * 用于发布session事件
     *
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
