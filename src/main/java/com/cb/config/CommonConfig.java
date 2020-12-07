package com.cb.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author:
 * @create: 2020-11-05 17:26
 **/
@Component
@ConfigurationProperties(prefix = "common")
@SuppressWarnings("static-access")
public class CommonConfig {
    public static String servername;
    public static String timeformat;

    public void setServername(String servername) {
        this.servername = servername;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat;
    }
}
