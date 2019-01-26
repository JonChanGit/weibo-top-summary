package cn.com.jonpad.weibotopsummary.mapper;

import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jon Chan
 * @date 2018/11/15 23:27
 */
public interface TopSummaryDataMapper extends BaseMapper<TopSummaryData> {

    List<TopSummaryData> find(@Param("key") String key);
}
