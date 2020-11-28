package com.isharefox.share.web.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IShareFoxConfig {
    @Bean
    public ModelMapper modelMapper() {
        //对象转换工具
        return new ModelMapper();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations(
//                "classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
//                "classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations(
//                "classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
//    }

}
