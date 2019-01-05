package cn.com.jonpad.weibotopsummary.task;

import cn.com.jonpad.weibotopsummary.config.WeChartConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;

/**
 * @author Jon Chan
 * @date 2019/1/6 2:00
 */
@Component
@Slf4j
public class WeChartTimer {

    @Autowired
    WeChartConfiguration weChartConfiguration;

    @Scheduled(cron = "0 0 */2 * * *")
    public Token refreshToken() {
        Token token = TokenAPI.token(weChartConfiguration.getAppID(), weChartConfiguration.getAppsecret());
        log.info("refreshToken: ==> {},{}",token.getAccess_token(),token.getExpires_in());
        weChartConfiguration.setAppToken(token);
        return token;
    }
}
