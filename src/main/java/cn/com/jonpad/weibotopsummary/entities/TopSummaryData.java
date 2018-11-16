package cn.com.jonpad.weibotopsummary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jon Chan
 * @date 2018/11/16 10:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopSummaryData {
    private long id;
    private String content;
    private String hots;
    private String link;
    private String mark;
    private long originalDataId;
}
