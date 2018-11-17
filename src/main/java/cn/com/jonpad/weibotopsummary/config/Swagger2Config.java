package cn.com.jonpad.weibotopsummary.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * @author Jon Chan
 * @date 2018/11/17 12:16
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    @Value("${app.client-id}")
    private String clientId;
    @Value("${app.client-secret}")
    private String clientSecret;
    @Value("${app.auth-server}")
    private String authServer;

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.com.jonpad.weibotopsummary.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                //将LocalDate 解析成 String
                .directModelSubstitute(LocalDate.class, String.class)
                //将ResponseEntity<T>解析成 T
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                //将DeferredResult<ResponseEntity<T>>解析成T
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                //.responseModel(new ModelRef("Error"))
                                .build()))
                .securitySchemes(newArrayList(oAuth()))
                .securityContexts(newArrayList(securityContext()))
                .enableUrlTemplating(true)
                //https://github.com/springfox/springfox/issues/2642
                .ignoredParameterTypes(Authentication.class)
                .apiInfo(getApiInfo());
    }
    @Autowired
    private TypeResolver typeResolver;

    /**
     * Sets up the security schemes used to protect the apis.
     * Supported schemes are ApiKey, BasicAuth and OAuth
     */
    private OAuth oAuth(){
        List<GrantType> grantTypes = newArrayList();
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(authServer + "/oauth/token");
        grantTypes.add(grantType);
        return new OAuth("oauth2schema", Arrays.asList(scopes()), grantTypes);
    }

    /**
     * Provides a way to globally set up security contexts for operation.
     * provide a way to select operations to be protected by one of the specified security schemes.
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                //17
                .forPaths(PathSelectors.ant("/**"))
                .build();
    }

    private AuthorizationScope[] scopes(){
        AuthorizationScope[] scopes = {
                new AuthorizationScope("weibo-top-summary", "weibo-top-summary")
        };
        return scopes;
    }

    private List<SecurityReference> defaultAuth() {
        return newArrayList(
                new SecurityReference("oauth2schema", scopes()));
    }

    @Bean
    SecurityConfiguration security() {
        //19 swagger-ui security configuration for oauth
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("weibo-top-summary")
                .description(" wb 热搜接口")
                .version("1.0")
                .contact(new Contact("Jon Chan ", "http://www.jonpad.com.cn/", "jonchan.hv@outlook.com"))
                .build();
    }
}
