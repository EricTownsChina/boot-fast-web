package priv.eric.kit.sys.util;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-09-02 12:27
 * <p>
 * desc: 手动参数校验的工具类
 */
public class BeanValidateUtil {

    /**
     * 参数校验器
     */
    private static final Validator VALIDATOR = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 参数校验
     *
     * @param bean 要校验的对象
     * @param <T>  T
     */
    public static <T> void validate(T bean) {
        if (null == bean) {
            throw new RuntimeException("待校验对象不能为空!");
        }
        Set<ConstraintViolation<T>> validateResults = VALIDATOR.validate(bean);
        if (CollectionUtils.isNotEmpty(validateResults)) {
            throw new RuntimeException("自定义参数校验异常 : " + validateResults.iterator().next().getMessage());
        }
    }

    /**
     * 分组参数校验
     *
     * @param bean 要校验的对象
     * @param <T>  T
     */
    public static <T> void validate(T bean, Class<?> group) {
        if (null == bean) {
            throw new RuntimeException("待校验对象不能为空!");
        }
        Set<ConstraintViolation<T>> validateResults = VALIDATOR.validate(bean, group);
        if (CollectionUtils.isNotEmpty(validateResults)) {
            throw new RuntimeException("自定义参数校验异常 : " + validateResults.iterator().next().getMessage());
        }
    }

}
