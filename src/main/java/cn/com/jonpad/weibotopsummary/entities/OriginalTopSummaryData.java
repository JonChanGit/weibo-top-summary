package cn.com.jonpad.weibotopsummary.entities;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 原始数据
 * @author Jon Chan
 * @date 2018/11/16 10:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OriginalTopSummaryData {
    private long id;
    private long createDateTime;
    private String dataSource;
    private String originalHtml;
    @TableField(exist = false)
    private List<TopSummaryData> dataList;
}
