package cn.com.jonpad.weibotopsummary.service;

import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.mapper.OriginalTopSummaryDataMapper;
import cn.com.jonpad.weibotopsummary.mapper.TopSummaryDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jon Chan
 * @date 2018/11/16 22:15
 */
@Service
public class SummaryDataService {
	@Autowired
	private OriginalTopSummaryDataMapper originalTopSummaryDataMapper;
	@Autowired
	private TopSummaryDataMapper topSummaryDataMapper;

	/**
	 *
	 * @param data
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insert(OriginalTopSummaryData data){
		originalTopSummaryDataMapper.insert(data);
		//topSummaryDataMapper.
	}
}
