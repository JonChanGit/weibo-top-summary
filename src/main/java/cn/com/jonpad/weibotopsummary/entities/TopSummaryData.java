package cn.com.jonpad.weibotopsummary.entities;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "解析数据")
@TableName("top_summary_data")
public class TopSummaryData {
    @TableId(type = IdType.AUTO)
    private Long  id;
    private String content;
    @ApiModelProperty(notes = "热度值，为空表示被官方置顶数据", example = "832792")
    private String hots;
    private String link;
    @ApiModelProperty(notes = "微博标记", example = "爆")
    private String mark;
    @JSONField(deserialize = false)
    private Long  originalDataId;
}
