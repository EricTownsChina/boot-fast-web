package priv.eric.kit.sys.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-09-01 12:32
 * <p>
 * desc:
 */
@Configuration
@ConditionalOnProperty(prefix = "fast-web.enabled", name = "hibernate-validator", havingValue = "true", matchIfMissing = true)
public class ValidatorAutoConfig {

    /**
     * 开启fail_fast
     *
     * @return Validator
     */
    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 将fail_fast设置为true即可，如果想验证全部，则设置为false或者取消配置即可
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return factory.getValidator();
    }

    /**
     * ①参数校验(方法参数),实例化MethodValidationPostProcessor拦截器,
     * ②在所要实现方法参数校验的类上面添加@Validated
     * ③在方法上面添加校验规则
     * ④当方法上面的参数校验失败,spring 框架就回抛出异常javax.validation.ConstraintViolationException
     *
     * @return MethodValidationPostProcessor    返回类型
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
