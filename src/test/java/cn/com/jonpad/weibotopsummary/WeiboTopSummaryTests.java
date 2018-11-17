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
        // GetTopSummaryData.getTopSummaryData();
    }

    @Test
    public void updateId(){
        StringBuilder str = new StringBuilder();
        int count = 0;
        for (int idx = 307899; idx <= 309122; idx ++ ){
            str.append(String.format("UPDATE top_summary_data set id = %d WHERE id = %d ; \n\r",++count,idx));
        }
        System.out.println(str);
    }

}
