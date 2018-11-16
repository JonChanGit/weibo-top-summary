package cn.com.jonpad.weibotopsummary.service;

import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import cn.com.jonpad.weibotopsummary.mapper.OriginalTopSummaryDataMapper;
import cn.com.jonpad.weibotopsummary.mapper.TopSummaryDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jon Chan
 * @date 2018/11/16 22:15
 */
@Service
public class SummaryDataService {
	@Autowired
	private OriginalTopSummaryDataService originalTopSummaryDataService;
	@Autowired
	private TopSummaryDataService topSummaryDataService;

	/**
	 *
	 * @param data
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insert(OriginalTopSummaryData data){
		originalTopSummaryDataService.save(data);
		List<TopSummaryData> dataList = data.getDataList();
		dataList.forEach(item -> item.setOriginalDataId(data.getId()));
		topSummaryDataService.saveBatch(dataList);
	}
}
