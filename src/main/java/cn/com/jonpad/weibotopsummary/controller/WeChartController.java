package cn.com.jonpad.weibotopsummary.controller;


import cn.com.jonpad.weibotopsummary.config.WeChartConfiguration;
import cn.com.jonpad.weibotopsummary.entities.OriginalTopSummaryData;
import cn.com.jonpad.weibotopsummary.entities.TopSummaryData;
import cn.com.jonpad.weibotopsummary.task.GetTopSummaryData;
import cn.com.jonpad.weibotopsummary.task.WeChartTimer;
import com.qq.weixin.mp.aes.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weixin.popular.api.MessageAPI;
import weixin.popular.api.UserAPI;
import weixin.popular.bean.message.MessageSendResult;
import weixin.popular.bean.message.massmessage.MassMessage;
import weixin.popular.bean.message.massmessage.MassTextMessage;
import weixin.popular.bean.token.Token;
import weixin.popular.bean.user.FollowResult;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jon Chan
 * @since 2019年1月5日
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WeChartController extends BaseController {

    @Autowired
    WeChartConfiguration weChartConfiguration;

    /**
     *
     * @param signature 3cb9264368bf133d7ccbf85f9a487baea0c7b4e
     * @param timestamp 1546706569
     * @param nonce 420514263
     * @param echostr 2939697407316328363
     * @return
     */
    @RequestMapping
    public void wxTokenValidator(
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr,
            HttpServletResponse response) throws Exception{
        log.info("{},{},{},{}",signature,timestamp,nonce,echostr);
        //检查消息是否来自微信服务器
        String _echostr=checkSignature(signature,timestamp,nonce, echostr,weChartConfiguration.getToken());

        if(echostr.equals(_echostr)){
            log.info("SUCCESS!");
        }

        //返回echostr给微信服务器
        OutputStream os=response.getOutputStream();
        os.write(URLEncoder.encode(echostr,"UTF-8").getBytes());
        os.flush();
        os.close();
    }

    public static String checkSignature(String signature,String timestamp,String nonce,String echostr,String token){
        ArrayList<String> list=new ArrayList<String>();
        list.add(nonce);
        list.add(timestamp);
        list.add(token);

        //字典序排序
        Collections.sort(list);
        //SHA1加密
        String checksignature= SHA1.encode(list.get(0)+list.get(1)+list.get(2));
        System.out.println(signature);
        System.out.println(checksignature);

        if(checksignature.equals(signature)){
            return echostr;
        }
        return null;
    }


    @GetMapping("test")
    public Object test() throws Exception{
        OriginalTopSummaryData topSummaryData = GetTopSummaryData.getTopSummaryData();
        List<TopSummaryData> supperSummaryList = GetTopSummaryData.checkSupperSummary(topSummaryData.getDataList());
        //if(supperSummaryList.size() > 0){
            // 本次包含超级话题

            StringBuilder sb = new StringBuilder("本时段热门话题：\n\r");
            final Integer[] i = {0};
        topSummaryData.getDataList().forEach(tsd -> {
            String content = tsd.getContent();
            if(content.contains("<img")){
                int begin = content.indexOf("<img");
                //int end = content.indexOf(" >");
                content = content.substring(0, begin-1);
            }
                sb.append(i[0]++)
                        .append("\t")
                        .append(content)
                        .append("\t")
                        .append(tsd.getHots())
                        .append("\t")
                        .append(tsd.getMark())
                        .append("\n\r");
            });
            Object o = weChartConfiguration.sendMessage(sb.toString());
            return o;
        //}
       // return "SUCCESS";
    }
}
