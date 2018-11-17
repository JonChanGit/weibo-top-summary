package cn.com.jonpad.weibotopsummary.http;

import org.apache.http.client.config.RequestConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jon Chan
 * @date 2018/11/17 16:19
 */
@Configuration
@ConfigurationProperties(prefix = "httpclient.config")
public class HttpRequestConfig {
    private int connectTimeout = 2000;

    private int connectRequestTimeout = 2000;

    private int socketTimeout = 2000;
    @Bean
    public RequestConfig config(){
        return RequestConfig.custom()
                .setConnectionRequestTimeout(this.connectRequestTimeout)
                .setConnectTimeout(this.connectTimeout)
                .setSocketTimeout(this.socketTimeout)
                .build();
    }
}
