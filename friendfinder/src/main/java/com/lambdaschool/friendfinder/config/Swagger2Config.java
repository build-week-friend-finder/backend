package com.lambdaschool.friendfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// http://localhost:2019/swagger-ui.html
@Configuration
public class Swagger2Config
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
                //                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.lambdaschool.friendfinder"))
                .paths(input ->
                        !PathSelectors.regex("/oauth/confirm_access.*").apply(input) &&
                                !PathSelectors.regex("/oauth/authorize.*").apply(input) &&
                                !PathSelectors.regex("/oauth/check_token.*").apply(input) &&
                                !PathSelectors.regex("/oauthconfirm_acccess.*").apply(input) &&
                                !PathSelectors.regex("/oauth/error.*").apply(input) &&
                                !PathSelectors.regex("/actuator.*").apply(input) &&
                                !PathSelectors.regex("/error.*").apply(input) &&
                                PathSelectors.any().apply(input))
                .build().useDefaultResponseMessages(false) // Allows only my exception responses
                .ignoredParameterTypes(Pageable.class) // allows only my paging parameter list
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("BW FriendFinder").description("An application to help connect with others to create friendships.").contact(new Contact("Nicholas", "http://www.lambdaschool.com", "nicholas@lambdaschool.com")).license("MIT").licenseUrl("https://github.com/LambdaSchool/java-starthere/blob/master/LICENSE").version("1.0.0").build();
    }
}
