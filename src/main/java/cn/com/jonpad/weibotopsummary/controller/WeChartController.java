package cn.com.jonpad.weibotopsummary.controller;


import cn.com.jonpad.weibotopsummary.controller.BaseController;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

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

    private static String appID = "wx49ba225c360db1ea";
    private static String appsecret = "KV1RdqZBcorcj1VBnDwLXr";
    /**
     * 公众平台上，开发者设置的token
     */
    private static String token = "KV1RdqZBcorcj1VBnDwLXr";
    private static String encodingAesKey = "LH2lKV1RdqZBcorcj1VBnDwLXrbtmY7RWZcn1abl10F";

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
        String _echostr=checkSignature(signature,timestamp,nonce, echostr,token);

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

}
