package cn.com.jonpad.weibotopsummary.task;

import cn.com.jonpad.weibotopsummary.http.HttpClientManagerFactoryBen;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jon Chan
 * @date 2018/12/19 0:13
 */
@Component
@Slf4j
public class GetLagouData {


    @Resource(name = "httpClientManagerFactoryBen")
    private CloseableHttpClient client;

    public void executeGetData() throws Exception {
       /* Document doc = Jsoup.connect()
                .userAgent()
                .post();
        log.info(doc.toString());*/
        HttpPost post = new HttpPost("https://www.lagou.com/jobs/positionAjax.json?gj=3%E5%B9%B4%E5%8F%8A%E4%BB%A5%E4%B8%8B&xl=%E6%9C%AC%E7%A7%91&px=default&city=%E4%B8%8A%E6%B5%B7&needAddtionalResult=false");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
        post.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        post.setHeader("Accept-Encoding", "gzip, deflate, br");
        post.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.setHeader("Host", "www.lagou.com");
        post.setHeader("Origin", "https://www.lagou.com");
        post.setHeader("Referer", "https://www.lagou.com/jobs/list_java?px=default&gj=3%E5%B9%B4%E5%8F%8A%E4%BB%A5%E4%B8%8B&xl=%E6%9C%AC%E7%A7%91&city=%E4%B8%8A%E6%B5%B7");
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity, Consts.UTF_8);
        log.info(str);
    }
}
