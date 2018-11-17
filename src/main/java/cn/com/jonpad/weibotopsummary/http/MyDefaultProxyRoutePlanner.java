package cn.com.jonpad.weibotopsummary.http;


import org.apache.http.HttpHost;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * HttpClient代理
 *
 * HttpClient不仅支持简单的直连、复杂的路由策略以及代理。
 * HttpRoutePlanner是基于http上下文情况下，客户端到服务器的路由计算策略，
 * 一般没有代理的话，就不用设置这个东西。
 * 这里有一个很关键的概念—Route：
 *      在HttpClient中，一个Route指 运行环境机器->目标机器host的一条线路，
 *      也就是如果目标url的host是同一个，那么它们的route也是一样的
 *
 * @author Jon Chan
 * @date 2018/11/17 16:14
 */
@Configuration
@ConfigurationProperties(prefix = "httpclient.config")
public class MyDefaultProxyRoutePlanner {
    /**
     * 代理的host地址
     */
    private String proxyHost;

    /**
     * 代理的端口号
     */
    private int proxyPort = 8080;

    @Bean
    public DefaultProxyRoutePlanner defaultProxyRoutePlanner() {
        if(StringUtils.isEmpty(this.proxyHost) || StringUtils.isEmpty(this.proxyPort)){
            return null;
        }
        HttpHost proxy = new HttpHost(this.proxyHost, this.proxyPort);
        return new DefaultProxyRoutePlanner(proxy);
    }
}
