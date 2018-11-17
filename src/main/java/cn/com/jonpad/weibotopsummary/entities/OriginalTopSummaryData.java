package cn.com.jonpad.weibotopsummary.entities;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "原始数据")
@TableName("original_top_summary_data")
public class OriginalTopSummaryData {
	@TableId(type = IdType.AUTO)
    private Long id;
    private Long createDateTime;
    private String dataSource;
    @ApiModelProperty(notes = "原始数据", example = "<div></div>")
    private String originalHtml;
    @TableField(exist = false)
    private List<TopSummaryData> dataList;
}
