package cn.com.jonpad.weibotopsummary.mapper;

import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Jon Chan
 * @date 2018/11/15 23:27
 */
public interface OriginalTopSummaryDataMapper extends BaseMapper<OriginalTopSummaryData> {

    /**
     * 获取最后一个
     * @return
     */
    OriginalTopSummaryData getLast();
}
