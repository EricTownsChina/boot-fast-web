package priv.eric.kit.sys.global.api;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author alex
 * @date Updated in 2020/12/03 11:50
 * Description: api请求相关配置项
 */
@Data
@Component
public class ApiProperties {

    @Value("${api.token.jwt-secret}")
    private String headerToken;

    @Value("${api.token.expiration}")
    private Long tokenExpiration;

    @Value("${api.token.sign}")
    private String signToken;

    @Value("${api.timeout}")
    private Integer apiTimeout;
}
