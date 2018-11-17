package cn.com.jonpad.weibotopsummary.controller;


import cn.com.jonpad.weibotopsummary.entities.BingImage;
import cn.com.jonpad.weibotopsummary.task.GetBingData;
import cn.com.jonpad.weibotopsummary.to.Bing;
import cn.com.jonpad.weibotopsummary.to.ResponseResult;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-16
 */
@RestController
@Api(description = "外部接口")
@Slf4j
public class IndexController {

    @Autowired
    GetBingData getBing;

    @GetMapping("bing")
    @ApiOperation(value="转发Bing接口", notes="转发Bing接口")
    public ResponseResult<Bing> bing(
            @ApiParam("个数,最大8")
            @RequestParam(required = false) Integer size){
        String str = getBing.getBingData(size, BingImage.RegionMeta.EN_US);
        if(StringUtils.isEmpty(str)){
            return ResponseResult.faill();
        }
        return ResponseResult.ok(JSON.parseObject(str, Bing.class));
    }

}

