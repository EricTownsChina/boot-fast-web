package priv.eric.kit.sys.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import priv.eric.kit.sys.entity.Resp;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

import static priv.eric.kit.sys.enums.ResponseCode.FAIL;


/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-27 0:08
 * <p>
 * desc: 全局异常处理类
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 全局异常处理
     *
     * @param e Exception
     * @return 统一返回
     */
    @ExceptionHandler(value = Exception.class)
    public Resp baseExceptionHandler(Exception e) {
        log.error("统一异常处理 Exception : ", e);
        return Resp.n()
                .setCode(FAIL.getCode())
                .setMsg(e.getMessage())
                .build();
    }

    /**
     * POST请求处理入参时，参数校验异常
     *
     * @param e BindException
     * @return 统一返回
     */
    @ExceptionHandler(BindException.class)
    public Resp handleException(BindException e) {
        log.error("hibernate validator POST参数校验异常 : ", e);
        // 获取错误信息
        String msg = e.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
        return Resp.error(msg);
    }

    /**
     * GET请求处理入参时，参数校验异常
     *
     * @param e ConstraintViolationException
     * @return 统一返回
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Resp handleException(ConstraintViolationException e) {
        log.error("hibernate validator GET参数校验异常 : ", e);
        // 获取错误信息
        String msg = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return Resp.error(msg);
    }

}
