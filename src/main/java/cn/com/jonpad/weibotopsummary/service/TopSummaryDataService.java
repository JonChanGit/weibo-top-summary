package cn.com.jonpad.weibotopsummary.service;

import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.Instant;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-16
 */
public interface TopSummaryDataService extends IService<TopSummaryData> {

    List<TopSummaryData> find(String key);

    List<TopSummaryData> getTopSummaryData(Instant beginTime, Instant endTime);
}
