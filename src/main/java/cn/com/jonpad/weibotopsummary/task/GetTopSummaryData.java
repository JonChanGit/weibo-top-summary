package cn.com.jonpad.weibotopsummary.task;

import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import cn.com.jonpad.weibotopsummary.service.RealtimeSuperSummaryService;
import cn.com.jonpad.weibotopsummary.service.SummaryDataService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jon Chan
 * @date 2018/11/16 20:47
 */
@Component
@Slf4j
public class GetTopSummaryData {

    @Autowired
    private SummaryDataService service;
    @Autowired
    private RealtimeSuperSummaryService realtimeSuperSummaryService;

    @Scheduled(cron = "0 5 * * * *")
    @Scheduled(cron = "0 35 * * * *")
    public void executeGetData() throws Exception {
        log.info("executeGetData");
        OriginalTopSummaryData data = getTopSummaryData();
        service.insert(data);
        List<TopSummaryData> supperSummaryList = data.getDataList().stream()
                .filter(item -> TopSummaryData.Marks.BURST.equals(item.getMark()) || TopSummaryData.Marks.BOIL.equals(item.getMark()))
                .collect(Collectors.toList());
		if(supperSummaryList.size() > 0){
		    // 本次包含超级话题
        }
    }

    public static OriginalTopSummaryData getTopSummaryData() throws IOException {
        Document doc = Jsoup.connect("https://s.weibo.com/top/summary").get();
        log.info(doc.title());
        Elements tr = doc.select("#pl_top_realtimehot table tbody tr");
        OriginalTopSummaryData data = new OriginalTopSummaryData(
                null,
                System.currentTimeMillis(),
                null,
                tr.toString(),
                null
        );
        List<TopSummaryData> list = new ArrayList<>(55);
        tr.forEach(item -> {
            Elements td02 = item.select(".td-02");
            Elements td02A = td02.select("a");
            Elements td03 = item.select(".td-03 i");
            TopSummaryData e = new TopSummaryData(
                    null,
                    td02A.html(),
                    td03.html(),
                    td02A.attr("href"),
                    td02.select("span").html(),
                    null
            );
            list.add(e);
        });
        return data.setDataList(list);
    }

}
