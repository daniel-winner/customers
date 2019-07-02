package com.crm.customers.configuration;

import com.crm.customers.filter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Autowired
  private LoginInterceptor loginInterceptor;

  // 这个方法是用来配置静态资源的，比如html，js，css，等等
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**")
            .addResourceLocations("/js/**")
            .addResourceLocations("/static/**")
            .addResourceLocations("/images/**")
            .addResourceLocations("classpath:/static/")
            .addResourceLocations("classpath:/META-INF/resources/");
  }

  // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor)
//            .addPathPatterns("/**")//拦截的路径所有
            .excludePathPatterns("/static/**")
            .excludePathPatterns("/js/**")
            .excludePathPatterns("/images/**")
            .excludePathPatterns("/login");//排除拦截的路径
  }
}