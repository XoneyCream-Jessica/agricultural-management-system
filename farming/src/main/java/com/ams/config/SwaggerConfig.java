package com.ams.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
/**
 * @ProjectName: AMS
 * @ClassName: SwaggerConfig
 * @Author: chenxi
 * @Description: swagger2
 * @Date: 2022/5/6 11:18
 */
@Configuration
@EnableSwagger2         //开启swagger2
@EnableSwaggerBootstrapUI   //开启增强功能
public class SwaggerConfig {

    //创建一个Swagger配置的实例
    @Bean
    public Docket blog(Environment environment) {

        //设置要显示swagger的环境
//        Profiles profiles = Profiles.of("test","dev");
        //判断不同环境中profiles的布尔值,并将enable传到enable(enable)方法中
//        Boolean enable = environment.acceptsProfiles(profiles);

//        System.out.println(enable);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("test")         //组名
//                .enable(enable)                //是否启用swagger
                .select()
                //any() 都扫描、 none() 都不扫描、...
                .apis(RequestHandlerSelectors.basePackage("com.ht.module"))    //扫描自定义的包
//                .paths(PathSelectors.ant("com.example.swagger.xxx"))              //过滤路径
                .build();
    }

    //new ApiInfo的信息传给上面的Docket
    public ApiInfo apiInfo() {
        //CONTACT指的是作者的信息,name，url,qq邮箱
        Contact contact = new Contact("api文档","https://blog.csdn.net/","837850097@qq.com");
        return new ApiInfo(
                "swagger文档",
                "前后端交互的api",
                "v1.0",
                "https://blog.csdn.net/",           //组织地址
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}