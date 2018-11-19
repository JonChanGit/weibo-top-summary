package cn.com.jonpad.weibotopsummary.mapper;

import cn.com.jonpad.weibotopsummary.entities.RealtimeSuperSummary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 实时超级话题 Mapper 接口
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-19
 */
public interface RealtimeSuperSummaryMapper extends BaseMapper<RealtimeSuperSummary> {

    /**
     * 查找最新结果是否是最新一条记录
     * 逻辑有问题
     * @param contentList
     * @return
     */
    @Deprecated
    List<RealtimeSuperSummary> findRealtimeSuperSummaryFromLatestOriginalDataByContent(@Param("contentList") List<String> contentList);

}
