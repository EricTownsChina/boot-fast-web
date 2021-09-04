package priv.eric.kit.sys.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.eric.kit.sys.global.xss.XssFilter;

/**
 * @author Levin
 * @since 2018/12/15 0005
 */
@Configuration
@ConditionalOnProperty(prefix = "fast-web.enabled", name = "xss-filter", havingValue = "true", matchIfMissing = true)
public class XssFilterAutoConfig {

    @Bean
    public FilterRegistrationBean<XssFilter> registrationXssFilter() {
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("XssFilterBean");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
