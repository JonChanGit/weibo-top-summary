package cn.com.jonpad.weibotopsummary.service;

import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 热点推送思路
 * 在插入时过滤出热点数据
 * @author Jon Chan
 * @date 2018/11/16 22:15
 */
@Slf4j
@Service
public class SummaryDataService {
    @Autowired
    private OriginalTopSummaryDataService originalTopSummaryDataService;
    @Autowired
    private TopSummaryDataService topSummaryDataService;

    /**
     * @param data
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(OriginalTopSummaryData data) {
        originalTopSummaryDataService.save(data);
        List<TopSummaryData> dataList = data.getDataList();
        dataList.forEach(item -> item.setOriginalDataId(data.getId()));
        topSummaryDataService.saveBatch(dataList);
        log.info("Operation completed! {} items", dataList.size());
    }

    public List<TopSummaryData> getLast(Integer size) {
        OriginalTopSummaryData last = originalTopSummaryDataService.getLast();
        List<TopSummaryData> list = last.getDataList();
        if (size == null || 0 >= size) {
            return list;
        }else {
            List<TopSummaryData> collect = list.stream().limit(size - 1).collect(Collectors.toList());
            Optional<TopSummaryData> one = list.stream().filter(item -> {
                if (StringUtils.isEmpty(item.getMark())) {
                    return true;
                } else {
                    if (item != null && StringUtils.isEmpty(item.getMark().trim())) {
                        return true;
                    }
                    return false;
                }
            }).findFirst();
            if (one.isPresent()){
                collect.add(one.get());
            }
            collect = collect.stream().map(item->{
                if(!StringUtils.isEmpty(item.getLink())){
                    if (item.getLink().trim().startsWith("javascript")) {
                        item.setLink("");
                    }
                }
                return item;
            }).collect(Collectors.toList());
            return collect;
        }
    }

    public void calculate(List<TopSummaryData> oData){

    }

    public List<TopSummaryData> find(String key) {
        return topSummaryDataService.find(key);
    }

    /**
     * 获取指点时间的超级话题
     * @param beginTime
     */
    public List<TopSummaryData> getTopSummaryData(Instant beginTime, Instant endTime) {
        return topSummaryDataService.getTopSummaryData(beginTime,endTime);
    }
}
