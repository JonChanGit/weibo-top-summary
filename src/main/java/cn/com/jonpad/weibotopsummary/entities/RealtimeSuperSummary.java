package cn.com.jonpad.weibotopsummary.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import cn.com.jonpad.weibotopsummary.entities.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实时超级话题
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("realtime_super_summary")
@ApiModel(value="RealtimeSuperSummary对象", description="实时超级话题")
public class RealtimeSuperSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "当前发生次数")
    private Integer number;

    @ApiModelProperty(value = "第一次发生时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "第一条ID")
    private Long tsdFirstId;


}
