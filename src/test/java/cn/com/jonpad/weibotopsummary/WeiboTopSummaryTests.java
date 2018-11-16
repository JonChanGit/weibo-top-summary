package cn.com.jonpad.weibotopsummary;

import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jon Chan
 * @date 2018/11/16 10:06
 */
@Slf4j
public class WeiboTopSummaryTests {
    @Test
    public void jsoupTest() throws IOException {
        List<TopSummaryData> list = new ArrayList<>(55);
        Document doc = Jsoup.connect("https://s.weibo.com/top/summary").get();
        log.info(doc.title());
        Elements tr = doc.select("#pl_top_realtimehot table tbody tr");
        tr.forEach(item -> {
            Elements td02 = item.select(".td-02");
            Elements td02A = td02.select("a");
            Elements td03 = item.select(".td-03 i");
            TopSummaryData e = new TopSummaryData(0,
                    td02A.html(),
                    td03.html(),
                    td02A.attr("href"),
                    td02.select("span").html(),
                    0
            );
            list.add(e);
        });
        list.forEach(System.out::println);
    }
}
