package cn.com.jonpad.weibotopsummary.boot;

import cn.com.jonpad.weibotopsummary.task.WeChartTimer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Jon Chan
 * @date 2019/1/6 2:14
 */
@Slf4j
@Component
@Order(1)
public class InitializationRunner implements CommandLineRunner{

    @Autowired
    WeChartTimer timer;

    @Override
    public void run(String... args) throws Exception {
        timer.refreshToken();
    }
}
