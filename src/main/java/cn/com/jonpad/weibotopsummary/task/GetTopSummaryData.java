package cn.com.jonpad.weibotopsummary.task;

import cn.com.jonpad.weibotopsummary.config.WeChartConfiguration;
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
import weixin.popular.api.MessageAPI;
import weixin.popular.api.UserAPI;
import weixin.popular.bean.message.MessageSendResult;
import weixin.popular.bean.message.massmessage.MassMessage;
import weixin.popular.bean.message.massmessage.MassTextMessage;
import weixin.popular.bean.user.FollowResult;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Jon Chan
 * @date 2018/11/16 20:47
 */
@Component
@Slf4j
public class GetTopSummaryData {

    @Autowired
    WeChartConfiguration weChartConfiguration;

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
        List<TopSummaryData> supperSummaryList = checkSupperSummary(data.getDataList());
		if(supperSummaryList.size() > 0){
		    // 本次包含超级话题

            StringBuilder sb = new StringBuilder("本时段超级话题：\n\r");
            final Integer[] i = {0};
            supperSummaryList.forEach(tsd -> {
                String content = tsd.getContent();
                if(content.contains("<img")){
                    int begin = content.indexOf("<img");
                    //int end = content.indexOf(" >");
                    content = content.substring(0, begin-1);
                }
                sb.append(i[0]++)
                        .append("\t")
                        .append(content)
                        .append("\t")
                        .append(tsd.getHots())
                        .append("\t")
                        .append(tsd.getMark())
                        .append("\n\r");
            });
            weChartConfiguration.sendMessage(sb.toString());
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

    /**
     * 查找超级话题，
     * @param list 超级话题列表
     * @return
     */
    public static List<TopSummaryData> checkSupperSummary(List<TopSummaryData> list){
        return list.stream()
                .filter(item -> TopSummaryData.Marks.BURST.equals(item.getMark()) || TopSummaryData.Marks.BOIL.equals(item.getMark()))
                .collect(Collectors.toList());
    }

}
