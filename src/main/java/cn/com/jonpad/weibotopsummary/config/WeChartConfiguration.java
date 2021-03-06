package cn.com.jonpad.weibotopsummary.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import weixin.popular.api.MessageAPI;
import weixin.popular.api.UserAPI;
import weixin.popular.bean.message.MessageSendResult;
import weixin.popular.bean.message.massmessage.MassMessage;
import weixin.popular.bean.message.massmessage.MassTextMessage;
import weixin.popular.bean.token.Token;
import weixin.popular.bean.user.FollowResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jon Chan
 * @date 2019/1/6 1:43
 */
@Slf4j
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

    public Object sendMessage (String content){
        FollowResult followResult = UserAPI.userGet(getAppToken().getAccess_token(), null);
        MassMessage massMessage = new MassTextMessage(content);
        Set<String> touser = new HashSet<>();
        touser.addAll(Arrays.asList(followResult.getData().getOpenid()));
        if(touser.size() < 1){
            log.error("touser size < 1");
            return "error";
        }else if (touser.size() == 1){
            log.error("touser size == 1");
            return "error";
        }else{
            log.info("message: {}", content);
            massMessage.setTouser(touser);
            MessageSendResult messageSendResult = MessageAPI.messageMassSend(getAppToken().getAccess_token(), massMessage);
            return messageSendResult;
        }
    }
}
