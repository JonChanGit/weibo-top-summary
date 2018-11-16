package cn.com.jonpad.weibotopsummary;

import cn.com.jonpad.weibotopsummary.entities.User;
import cn.com.jonpad.weibotopsummary.mapper.UserMapper;
import cn.com.jonpad.weibotopsummary.service.SummaryDataService;
import cn.com.jonpad.weibotopsummary.task.GetTopSummaryData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeiboTopSummaryApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GetTopSummaryData service;

	@Test
	public void contextLoads() {
	}
	@Test
	public void jsoupTest() throws Exception {
	}
}
