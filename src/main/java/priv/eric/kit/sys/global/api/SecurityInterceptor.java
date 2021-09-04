package priv.eric.kit.sys.global.api;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import priv.eric.kit.sys.global.api.ApiProperties;
import priv.eric.kit.sys.util.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static priv.eric.kit.sys.enums.ResponseCode.*;

/**
 * @author alex
 * @date Created in 2021/1/13 9:44
 * Description:
 *
 * 1. 重放攻击
 * 2. api参数防篡改
 */
@Slf4j
@Configuration
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private ApiProperties sysConfig;

    @Resource
    private RedisUtil redisUtil;

    public static String signature(String token, String timestamp, String nonce) {
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String signStr = token + timestamp + nonce;
        return SecureUtil.sha1(signStr);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取时间戳
        String timestamp = request.getHeader("timestamp");
        // 获取随机字符串
        String nonceStr = request.getHeader("nonceStr");
        // 获取签名
        String signature = request.getHeader("signature");

        // 没有timestamp参数或者当前时间超过了过期时间
        if (StringUtils.isEmpty(timestamp) || new Date().after(new Date(Long.parseLong(timestamp) + sysConfig.getApiTimeout() * 1000))) {
            log.info("接口安全防护异常 url: {} msg: {}", request.getRequestURL(), INVALID_TIMESTAMP.getDesc());
            throw new RuntimeException(INVALID_TIMESTAMP.getDesc());
        }

        if (StringUtils.isEmpty(nonceStr) || redisUtil.exists(nonceStr)) {
            log.info("接口安全防护异常 url: {} msg: {}", request.getRequestURL(), INVALID_NONCE.getDesc());
            throw new RuntimeException(INVALID_NONCE.getDesc());
        }

        String sign = signature(sysConfig.getSignToken(), timestamp, nonceStr);
        if (StringUtils.isEmpty(signature) || (!signature.equals(sign))) {
            log.info("接口安全防护异常 url: {} msg: {}", request.getRequestURL(), INVALID_SIGNATURE.getDesc());
            throw new RuntimeException(INVALID_SIGNATURE.getDesc());
        }

        redisUtil.setIfAbsent(nonceStr, nonceStr, Long.valueOf(sysConfig.getApiTimeout()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
