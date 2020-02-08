package com.mzx.rbacdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

    /**
     * 跨域问题解决
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")// 1 允许任何域名使用
                .allowedHeaders("*")// 2 允许任何头
                .allowedMethods("*");// 3 允许任何方法（post、get等）
    }


    /**
     * 解决 AuthInterceptor 类 中 Autowired无法加载 无法注入bean问题
     *
     */
    @Bean
    AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }


    /**
     * 配置自定义类 LoginInterceptor 实现拦截登陆
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(new String[]{"/**"})
                .excludePathPatterns(new String[]{"/","/login","/doAjaxLogin","/bootstrap/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**"});


        registry.addInterceptor(authInterceptor())
                .addPathPatterns(new String[]{"/**"})
                .excludePathPatterns(new String[]{"/","/login","/doAjaxLogin","/bootstrap/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**"});

    }
}
