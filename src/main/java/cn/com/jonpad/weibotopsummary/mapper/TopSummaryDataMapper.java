package cn.com.jonpad.weibotopsummary.mapper;

import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author Jon Chan
 * @date 2018/11/15 23:27
 */
public interface TopSummaryDataMapper extends BaseMapper<TopSummaryData> {

    List<TopSummaryData> find(@Param("key") String key);

    List<TopSummaryData> getTopSummaryData(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime);
}
