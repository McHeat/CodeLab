package com.mcheat.code.lab.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2017/9/29
 */
@ConfigurationProperties(prefix = "custom")
public class CustomConfigurationProperties {

    private final List<CustomUserInfo> customUserInfo = new ArrayList<>();

    public List<CustomUserInfo> getCustomUserInfo() {
        return customUserInfo;
    }

    public static class CustomUserInfo {

        private String name;
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
