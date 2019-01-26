package cn.com.jonpad.weibotopsummary;

import cn.com.jonpad.weibotopsummary.entities.User;
import cn.com.jonpad.weibotopsummary.mapper.UserMapper;
import cn.com.jonpad.weibotopsummary.service.SummaryDataService;
import cn.com.jonpad.weibotopsummary.task.GetLagouData;
import cn.com.jonpad.weibotopsummary.task.GetTopSummaryData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeiboTopSummaryApplicationTests {
    @Resource(name = "httpClientManagerFactoryBen")
    private CloseableHttpClient client;
    @Autowired
    private GetTopSummaryData data;

    @Test
    public void contextLoads() {
    }

    @Test
    public void httpTest() throws Exception {
        data.executeGetData();
    }


}
