package cn.com.jonpad.weibotopsummary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cn.com.jonpad.weibotopsummary.mapper")
public class WeiboTopSummaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeiboTopSummaryApplication.class, args);
	}
}
