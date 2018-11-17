package cn.com.jonpad.weibotopsummary.task;

import cn.com.jonpad.weibotopsummary.entities.BingImage;
import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import cn.com.jonpad.weibotopsummary.service.BingImageService;
import cn.com.jonpad.weibotopsummary.service.SummaryDataService;
import cn.com.jonpad.weibotopsummary.to.Bing;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jon Chan
 * @date 2018/11/16 20:47
 */
@Component
@Slf4j
public class GetBingData {

    @Resource(name = "httpClientManagerFactoryBen")
    private CloseableHttpClient client;

    @Autowired
    BingImageService bingImageService;

    /**
     * 必应每日壁纸的接口模板
     * ZH_CN
     */
    public static String WALLPAPER_INFO_URL_CN_TEMPLATE = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=1&n=%d";
    /**
     * 必应每日壁纸的接口模板
     * EN_US
     */
    public static String WALLPAPER_INFO_URL_US_TEMPLATE = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=%d&IID=SERP.1052&ensearch=1";

    @Scheduled(cron = "0 5 0 * * *")
    public void executeGetData() throws Exception {
        String bingData = this.getBingData(null, BingImage.RegionMeta.ZH_CN);
        Bing bing = JSON.parseObject(bingData, Bing.class);
        List<BingImage> images = bing.getImages().stream().map(item -> item.setRegion("ZH_CN")).collect(Collectors.toList());
        bingData = this.getBingData(null, BingImage.RegionMeta.EN_US);
        bing = JSON.parseObject(bingData, Bing.class);
        images.addAll(bing.getImages().stream().map(item -> item.setRegion("EN_US")).collect(Collectors.toList()));
        bingImageService.saveBatch(images);
        log.info("bingImage Save Completed !");
    }


    /**
     * @param size
     * @param regionMeta BingImage.RegionMeta
     * @return
     */
    public String getBingData(Integer size, BingImage.RegionMeta regionMeta) {
        HttpEntity entity = null;
        String str = "";
        try {
            String urlTmp;
            switch (regionMeta) {
                case EN_US:
                    urlTmp = WALLPAPER_INFO_URL_US_TEMPLATE;
                    break;
                case ZH_CN:
                default:
                    urlTmp = WALLPAPER_INFO_URL_CN_TEMPLATE;
            }
            String url = null;
            if (size != null) {
                url = String.format(urlTmp, size);
            } else {
                url = String.format(urlTmp, 1);
            }
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse execute = client.execute(get);
            entity = execute.getEntity();
            str = EntityUtils.toString(entity, Consts.UTF_8);
            log.info("Forward Bing interface Completed !");
        } catch (Exception e) {
            log.error("ERROR", e);
        } finally {
            if (entity != null) {
                EntityUtils.consumeQuietly(entity);
            }
        }
        return str;
    }

}
