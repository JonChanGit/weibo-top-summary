package cn.com.jonpad.weibotopsummary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Jon Chan
 * @date 2018/11/16 10:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TopSummaryData {
    private Long id;
    private String content;
    private String hots;
    private String link;
    private String mark;
    private Long originalDataId;
}
