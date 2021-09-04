package priv.eric.bc.service;

import priv.eric.bc.entity.User;
import priv.eric.kit.sys.entity.Page;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-29 16:42
 * <p>
 * desc: user service
 */
public interface UserService {

    /**
     * 获取用户信息列表
     *
     * @param page 查询的分页条件
     */
    void getUsers(Page<User> page);

    /**
     * 添加一条用户信息
     *
     * @param user 用户信息
     */
    void addOneUser(User user);

    /**
     * 根据uid获取用户信息
     *
     * @param uid uid
     * @return 用户信息
     */
    User getUser(Long uid);

    /**
     * 更新一条用户信息
     *
     * @param user 用户信息
     */
    void updateOneUser(User user);

}
