package cn.com.jonpad.weibotopsummary.http;


import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 连接保持策略
 * 长连接并不使用于所有的情况，尤其现在的系统，大都是部署在多台服务器上，
 * 且具有负载均衡的功能，如果我们在访问的时候，一直保持长连接，一旦那台服务器挂了，就会影响客户端，
 * 同时也不能充分的利用服务端的负载均衡的特性，反而短连接更有利一些，这些需要根据具体的需求来定，而不是一言概括。
 * @author Jon Chan
 * @date 2018/11/17 16:12
 */
@Configuration
public class MyconnectionKeepAliveStrategy {
    @Value("${httpclient.config.keepAliveTime}")
    private int keepAliveTime = 30;

    @Bean("connectionKeepAliveStrategy")
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            // Honor 'keep-alive' header
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
                    }
                }
            }
            return 30 * 1000;
        };
    }
}
