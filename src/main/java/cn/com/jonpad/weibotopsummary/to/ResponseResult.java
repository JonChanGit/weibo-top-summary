package cn.com.jonpad.weibotopsummary.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jon Chan
 * @date 2018/11/17 12:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseResult<T> {
    @Getter
    private int code;
    @Getter
    private String message;
    @Getter
    private T responseBody;
    public static <T> ResponseResult<T> ok(T responseBody){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setResponseBody(responseBody).setCode(ResponseCode.RESULT_OK).setMessage(ResponseMessage.RESULT_OK);
        return responseResult;
    }
    public static ResponseResult ok(){
        return ok(null);
    }

    /**
     * 获取简单失败
     * @return
     */
    public static ResponseResult<Boolean> error(String msg){
        ResponseResult<Boolean> responseResult = new ResponseResult<Boolean>();
        responseResult.setCode(ResponseCode.RESULT_ERROR).setMessage(msg);
        return responseResult;
    }
    public static ResponseResult faill(Integer responseCode){
        return error(RESPONSE_MSG_MAP.get(responseCode)).setCode(responseCode);
    }
    public static ResponseResult faill(){
        return error(null);
    }
    public static class ResponseCode{
        public static final int RESULT_OK = 0;
        public static final int RESULT_ERROR = 1;
        public static final int RESULT_EMPTY_STRING = 2;
        public static final int RESULT_DUPLICATE_VALUE = 3;
        public static final int RESULT_DUPLICATE_VALUE_USERNAME = 4;
    }
    @Deprecated
    public static class ResponseMessage{
        public static final String RESULT_OK = "ok";
        public static final String RESULT_ERROR = "系统异常";
    }

    /**
     * 错误消息和代码
     */
    public static Map<Integer,String> RESPONSE_MSG_MAP = new HashMap<>(16);
    static {
        RESPONSE_MSG_MAP.put(ResponseCode.RESULT_OK,"ok");
        RESPONSE_MSG_MAP.put(ResponseCode.RESULT_ERROR,"系统异常");
        RESPONSE_MSG_MAP.put(ResponseCode.RESULT_EMPTY_STRING,"空字符串");
        RESPONSE_MSG_MAP.put(ResponseCode.RESULT_DUPLICATE_VALUE,"值重复");
        RESPONSE_MSG_MAP.put(ResponseCode.RESULT_DUPLICATE_VALUE_USERNAME,"登陆账号重复");

    }

}
