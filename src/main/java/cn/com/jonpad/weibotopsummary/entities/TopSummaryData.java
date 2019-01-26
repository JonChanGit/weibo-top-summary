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
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 热点指mark标记为爆/沸时
 * 使用新表记录实时处于爆款状态
 * 可以考虑使用redis
 * @author Jon Chan
 * @date 2018/11/16 10:30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "解析数据")
@TableName("top_summary_data")
public class TopSummaryData {
    @TableId(type = IdType.AUTO)
    private Long  id;
    private String content;
    /**
     * 正文Hash
     * 暂时无用
     */
    @JSONField(deserialize = false)
    private String contentHash;
    @ApiModelProperty(notes = "热度值，为空表示被官方置顶数据", example = "832792")
    private String hots;
    private String link;
    @ApiModelProperty(notes = "微博标记", example = "爆")
    private String mark;
    @JSONField(deserialize = false)
    private Long  originalDataId;
    @TableField(exist = false)
    private Long createDateTime;

    /**
     *
     * @param id
     * @param content
     * @param hots
     * @param link
     * @param mark
     * @param originalDataId
     */
    public TopSummaryData(Long id, String content, String hots, String link, String mark, Long originalDataId) {
        this.id = id;
        this.content = StringUtils.isEmpty(content)?content:content.trim();
        this.hots = hots;
        this.link = link;
        this.mark = mark;
        this.originalDataId = originalDataId;
    }

    /**
     * 微博标记取值
     */
    public interface Marks{
        String BURST = "爆";
        String BOIL = "沸";
        String HOT = "热";
        String FRESH = "新";

    }

    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        if(createDateTime != null && createDateTime > 0){
            return Date.from(Instant.ofEpochMilli(createDateTime));
        }else {
            return null;
        }
    }
}
