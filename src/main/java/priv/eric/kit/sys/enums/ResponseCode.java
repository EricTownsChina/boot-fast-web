package priv.eric.kit.sys.enums;

/**
 * @date 2020/3/12 12:50
 * @author 赵元路 18358572500
 *
 * Description: 基础返回码和返回信息定义
 */
public enum ResponseCode {

    /**
     * 错误码暂定都是5位数字，并配有相应解释
     * 错误码按模块按功能场景分级分段，前三位错误码表示模块，第四位表示模块下的功能
     * 数字 1 开头的错误码表示系统级别的错误，比如缺少某种字符集，连不上数据库之类的，系统级的错误码不需要分模块，可以按照自增方式进行添加
     * 数字 4 开头的错误码表示API参数校验失败，比如 “交易模块下单场景中，订单金额参数不能为空” 可以用 40111 错误码来表示
     * 数字 5 开头的错误码表示后台业务校验失败，比如 “交易模块下单场景中，该用户没有下单权限” 可以用 50111 错误码来表示
     * <p>
     * 模块定义：501->用户模块；502->产品模块
     */
    SUCCESS(0, "OK"),
    FAIL(-1, "ERROR"),

    SERVER_EXCEPTION(10000, "服务端发生未知异常"),
    DATA_NOT_EXIST(10001, "数据不存在"),
    PERMISSION_DENIED(1003, "无访问权限"),
    OPERATION_FAILED(1004, "操作失败"),

    INVALID_PARAM(40000, "参数错误"),
    HTTP_METHOD_NOT_SUPPORTED(40001, "Request method not supported"),
    INVALID_TIMESTAMP(40011, "非法的timestamp参数"),
    INVALID_NONCE(40012, "非法的nonce字符串"),
    INVALID_SIGNATURE(40013, "非法的signature签名"),
    INCOMPLETE_PARAMETERS(40101, "参数不全"),
    INVALID_PARAMETER(40202, "参数不合法"),

    //用户模块501
    UN_AUTHENTICATION(50100, "用户未认证,请先登录"),
    EXPIRED(50101, "登录状态已失效,请重新登录"),
    INVALID_TOKEN(50102, "无效的accessToken"),
    USER_NOT_EXIST(50103, "用户不存在"),
    USER_EXIST(50104, "用户已存在，该手机号已注册"),
    USER_IS_DISABLED(50105, "用户已被禁用"),
    USER_PWD_ERROR(50106, "密码错误"),
    NO_PERMISSION(50107, "没有操作权限"),

    //产品模块502
    PRODUCT_NOT_EXIST(50201, "产品不存在"),
    PRODUCT_NAME_EXIST(50202, "产品名称已存在"),
    PRODUCT_NUMBER_ERROR(50203, "最大值不能小于最小值");



    private final Integer code;
    private final String desc;

    ResponseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ResponseCode getEnumByCode(Integer code) {
        for (ResponseCode rce : ResponseCode.values()) {
            if (rce.code.equals(code)) {
                return rce;
            }
        }
        return null;
    }

}
