package cn.com.jonpad.weibotopsummary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Jon Chan
 * @date 2018/11/16 10:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginalTopSummaryData {
    private long id;
    private Date createDateTime;
    private String dataSource;
    private String originalHtml;
}
