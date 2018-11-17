package cn.com.jonpad.weibotopsummary.http;

import org.springframework.context.annotation.Configuration;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * 当HttpClient实例不再需要并且即将超出范围时，
 * 重要的是关闭其连接管理器，
 * 以确保管理器保持活动的所有连接都被关闭，
 * 并释放由这些连接分配的系统资源
 * @author Jon Chan
 * @date 2018/11/17 16:10
 */
@Configuration
public class MyPoolingHttpClientConnectionManager {
    /**
     * 连接池最大连接数
     */
    @Value("${httpclient.config.connMaxTotal}")
    private int connMaxTotal = 20;

    /**
     *
     */
    @Value("${httpclient.config.maxPerRoute}")
    private int maxPerRoute = 20;

    /**
     *  连接存活时间，单位为s
     *     
     */
    @Value("${httpclient.config.timeToLive}")
    private int timeToLive = 60;

    @Bean
    public PoolingHttpClientConnectionManager poolingClientConnectionManager() {
        PoolingHttpClientConnectionManager poolHttpcConnManager = new PoolingHttpClientConnectionManager(60, TimeUnit.SECONDS);
        // 最大连接数
        poolHttpcConnManager.setMaxTotal(this.connMaxTotal);
        // 路由基数
        poolHttpcConnManager.setDefaultMaxPerRoute(this.maxPerRoute);
        return poolHttpcConnManager;
    }
}
