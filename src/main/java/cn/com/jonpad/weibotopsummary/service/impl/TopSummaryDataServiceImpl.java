package cn.com.jonpad.weibotopsummary.service.impl;

import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import cn.com.jonpad.weibotopsummary.mapper.TopSummaryDataMapper;
import cn.com.jonpad.weibotopsummary.service.TopSummaryDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-16
 */
@Service
public class TopSummaryDataServiceImpl extends ServiceImpl<TopSummaryDataMapper, TopSummaryData> implements TopSummaryDataService {

    @Override
    public List<TopSummaryData> find(String key) {
        return baseMapper.find(key);
    }


    @Override
    public List<TopSummaryData> getTopSummaryData(Instant beginTime, Instant endTime) {
        return baseMapper.getTopSummaryData(beginTime.getEpochSecond()*1000, endTime.getEpochSecond()*1000);
    }
}
