package com.powernode.SSM.config;

import com.powernode.SSM.interceptors.SSMInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;
import java.util.Properties;

@Configuration
@ComponentScan({"com.powernode.SSM.handler"})
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    //视图解析器
    @Bean
    public ITemplateResolver iTemplateResolver(ApplicationContext applicationContext){

        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);

        springResourceTemplateResolver.setPrefix("WEB-INF/thymeleaf/");
        springResourceTemplateResolver.setSuffix(".html");

        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");

        springResourceTemplateResolver.setCacheable(false);

        return springResourceTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver iTemplateResolver){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(iTemplateResolver);
        return springTemplateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine);
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setOrder(1);
        return thymeleafViewResolver;
    }

    //配置默认视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    //配置拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SSMInterceptor ssmInterceptor = new SSMInterceptor();
        registry.addInterceptor(ssmInterceptor).addPathPatterns("/**").excludePathPatterns("/index");
    }

    //配置异常处理器

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("java.lang.Exception","tip");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        simpleMappingExceptionResolver.setExceptionAttribute("e");

        resolvers.add(simpleMappingExceptionResolver);
    }

//    配置静态资源文件控制器

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
