package com.yjkj.ks_user.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceViewConfig {
    /**
     * 绑定druid管理台 如果白名单和黑名单重复
     * 优先黑名单
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        //创建StatViewServlet，绑定到/druid/路径下
        //开启后，访问localhost:8080/druid就可以看到druid管理后台
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap();
        map.put("loginUsername", "admin"); //用户名
        map.put("loginPassword", "admin"); //密码
        map.put("allow", ""); //白名单 IP地址
        map.put("deny", "192.168.1.254"); // 黑名单 ip地址
        bean.setInitParameters(map);
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");
        Map<String, String> map = new HashMap<String, String>();
        map.put("exclusions", "*.js,*.css,*.css,*.png,/druid/*");
        bean.setInitParameters(map);
        return bean;
    }
}
