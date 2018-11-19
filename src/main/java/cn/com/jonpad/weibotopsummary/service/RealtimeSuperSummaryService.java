package cn.com.jonpad.weibotopsummary.service;

import cn.com.jonpad.weibotopsummary.entities.RealtimeSuperSummary;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 实时超级话题 服务类
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-19
 */
public interface RealtimeSuperSummaryService extends IService<RealtimeSuperSummary> {

    /**
     * 保存超级话题
     * @param supperSummaryList
     */
    void saveSupperSummary(List<TopSummaryData> supperSummaryList);

}
