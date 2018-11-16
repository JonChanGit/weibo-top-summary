package cn.com.jonpad.weibotopsummary;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

import cn.com.jonpad.weibotopsummary.task.GetTopSummaryData;

/**
 * @author Jon Chan
 * @date 2018/11/16 10:06
 */
@Slf4j
public class WeiboTopSummaryTests {
    @Test
    public void jsoupTest() throws IOException {
        GetTopSummaryData.getTopSummaryData();
    }

}
