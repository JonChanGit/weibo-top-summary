package cn.com.jonpad.weibotopsummary.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jon Chan
 * @date 2018/11/15 23:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@TableId(type = IdType.AUTO)
	Long id;
	String name;
	Integer age;
	String email;
}
