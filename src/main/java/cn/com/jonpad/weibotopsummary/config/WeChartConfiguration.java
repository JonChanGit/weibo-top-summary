package cn.com.jonpad.weibotopsummary.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import weixin.popular.bean.token.Token;

/**
 * @author Jon Chan
 * @date 2019/1/6 1:43
 */
@Data
@Configuration
public class WeChartConfiguration {
    @Value("${wx.appID:wxa2c3b572b92ec4f7}")
    private  String appID;
    @Value("${wx.appsecret:96a06e32a44876fe9297ecf284374d41}")
    private String appsecret;
    /**
     * 公众平台上，开发者设置的token
     */
    @Value("${wx.token:KV1RdqZBcorcj1VBnDwLXr}")
    private String token;
    @Value("${wx.encodingAesKey:LH2lKV1RdqZBcorcj1VBnDwLXrbtmY7RWZcn1abl10F}")
    private String encodingAesKey;

    /**
     * 是其它API调用的必要参数
     * 需要每两小时刷新一次
     */
    private Token appToken;
}
