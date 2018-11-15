package cn.com.jonpad.weibotopsummary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.jonpad.weibotopsummary.mapper")
public class WeiboTopSummaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeiboTopSummaryApplication.class, args);
	}
}
