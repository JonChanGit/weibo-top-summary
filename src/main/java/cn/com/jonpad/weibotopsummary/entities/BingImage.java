package cn.com.jonpad.weibotopsummary.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * Bing图片信息
 * </p>
 *
 * @author Jon Chan
 * @since 2018-11-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "Bing图片信息")
@TableName("bing_image")
public class BingImage {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String startdate;

    private String fullstartdate;

    private String enddate;

    private String url;

    private String urlbase;

    private String copyright;

    private String copyrightlink;

    private String title;

    private String quiz;

    private Integer wp;

    private String hsh;

    private Integer drk;

    private Integer top;

    private Integer bot;

    /**
     * 地区
     */
    private String region;

    @TableField(exist = false)
    private List<?> hs;

    public enum  RegionMeta{
        ZH_CN,EN_US
    }

}
