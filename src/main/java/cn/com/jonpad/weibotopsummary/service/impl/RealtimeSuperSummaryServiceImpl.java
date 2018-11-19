package cn.com.jonpad.weibotopsummary.service.impl;

import cn.com.jonpad.weibotopsummary.entities.RealtimeSuperSummary;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import cn.com.jonpad.weibotopsummary.mapper.RealtimeSuperSummaryMapper;
import cn.com.jonpad.weibotopsummary.service.RealtimeSuperSummaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 实时超级话题 服务实现类
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-19
 */
@Service
public class RealtimeSuperSummaryServiceImpl extends ServiceImpl<RealtimeSuperSummaryMapper, RealtimeSuperSummary> implements RealtimeSuperSummaryService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSupperSummary(List<TopSummaryData> supperSummaryList) {
        List<String> collectList = supperSummaryList.stream().map(item -> item.getContent()).collect(Collectors.toList());
        // 查找已经存在的
        List<RealtimeSuperSummary> firstContent = baseMapper.findRealtimeSuperSummaryFromLatestOriginalDataByContent(collectList);
        // 创建所有的，包括不存在的
        List<RealtimeSuperSummary> newRss = supperSummaryList.stream()
                .map(item -> new RealtimeSuperSummary(null, 1, LocalDateTime.now(), item.getId()))
                .collect(Collectors.toList());
        this.saveBatch(newRss);

    }
}
