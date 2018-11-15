package cn.com.jonpad.weibotopsummary;

import cn.com.jonpad.weibotopsummary.entities.User;
import cn.com.jonpad.weibotopsummary.mapper.UserMapper;
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

	@Test
	public void contextLoads() {
		User user = new User(10L, "name", 0, "@qq.com");
		userMapper.insert(user);
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		Assert.assertEquals(6, userList.size());
		userList.forEach(System.out::println);
	}
	@Test
	public void jsoupTest() throws IOException {
		Document doc = Jsoup.connect("https://s.weibo.com/top/summary").get();
		log.info(doc.title());
		log.info(doc.toString());

	}
}
