package cn.com.jonpad.weibotopsummary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 正式环境认证配置
 * @author Jon Chan
 * @date 2018/11/17 13:05
 */
@Profile("prod")
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerProdConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources/**",  "/swagger-ui.html**", "/webjars/**", "/top-summary", "favicon.ico").permitAll()
                .and().authorizeRequests().antMatchers("/actuator/**").hasAuthority("SUPPER").antMatchers("/**").authenticated();
    }
}
