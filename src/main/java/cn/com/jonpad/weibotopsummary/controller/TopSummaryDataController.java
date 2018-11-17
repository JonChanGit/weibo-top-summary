package cn.com.jonpad.weibotopsummary.controller;


import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import cn.com.jonpad.weibotopsummary.task.GetTopSummaryData;
import cn.com.jonpad.weibotopsummary.to.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-16
 */
@RestController
@RequestMapping("/top-summary")
@Api(description = "核心接口")
public class TopSummaryDataController {

    @GetMapping("latest")
    @ApiOperation(value="获取最新数据", notes="获取最新数据")
    public ResponseResult<List<TopSummaryData>> getLatest() throws Exception{
        OriginalTopSummaryData summaryData = GetTopSummaryData.getTopSummaryData();
        return ResponseResult.ok(summaryData.getDataList());
    }

}
