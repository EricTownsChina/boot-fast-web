package priv.eric.bc.entity;

import lombok.Data;
import priv.eric.bc.entity.group.AddGroup;
import priv.eric.bc.entity.group.UpdateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-29 16:32
 * <p>
 * desc: 用户信息实体类
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 7578410334857425230L;

    /**
     * 用户UID
     */
    @NotNull(message = "用户uid不能为空!", groups = UpdateGroup.class)
    private Long userUid;
    /**
     * 用户信息ID(工号)
     */
    private String userId;
    /**
     * 姓名
     */
    @NotBlank(message = "用户名称不能为空!", groups = AddGroup.class)
    private String userName;
    /**
     * 所在省份ID
     */
    @NotNull(message = "所属省份ID不能为空!", groups = AddGroup.class)
    private Integer provinceId;
    /**
     * 城市ID
     */
    @NotNull(message = "所属城市ID不能为空!", groups = AddGroup.class)
    private Integer cityId;
    /**
     * 创建时间
     */
    private Date createTime;

}
