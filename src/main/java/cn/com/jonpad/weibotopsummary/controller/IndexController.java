package cn.com.jonpad.weibotopsummary.controller;


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

    @Resource(name = "httpClientManagerFactoryBen")
    private CloseableHttpClient client;

    @GetMapping("bing")
    @ApiOperation(value="转发Bing接口", notes="转发Bing接口")
    public ResponseResult<Bing> bing(
            @ApiParam("个数,最大8")
            @RequestParam(required = false) Integer size){
        HttpEntity entity = null;
        String str = "";
        try {
            String url = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=1&n=1";
            if(size != null){
                url = String.format("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=1&n=%d", size);
            }
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse execute = client.execute(get);
            entity = execute.getEntity();
            str = EntityUtils.toString(entity, Consts.UTF_8);
            log.info("Forward Bing interface Completed !");
        }catch (Exception e){
            log.error("ERROR",e);
        }finally {
            if(entity != null){
                EntityUtils.consumeQuietly(entity);
            }
        }
        if(StringUtils.isEmpty(str)){
            return ResponseResult.faill();
        }
        return ResponseResult.ok(JSON.parseObject(str, Bing.class));
    }
}

