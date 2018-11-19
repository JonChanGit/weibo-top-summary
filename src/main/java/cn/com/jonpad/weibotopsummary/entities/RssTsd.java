package cn.com.jonpad.weibotopsummary.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cn.com.jonpad.weibotopsummary.entities.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("rss_tsd")
@ApiModel(value="RssTsd对象", description="")
public class RssTsd extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "实时超级话题表ID")
    @TableId(value = "rss_id", type = IdType.AUTO)
    private Long rssId;

    @ApiModelProperty(value = "历时热门记录ID")
    private Long tsdId;


}
