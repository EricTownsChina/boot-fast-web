package priv.eric.bc.dao;

import priv.eric.bc.entity.User;
import priv.eric.kit.sys.entity.Page;

import java.util.List;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-29 16:44
 * <p>
 * desc:
 */
public interface UserDao {

    /**
     * 获取用户信息
     *
     * @param page 查询条件
     * @return 用户信息
     */
    List<User> selectUsers(Page<User> page);

    /**
     * 根据用户uid查询用户信息
     *
     * @param uid 用户uid
     * @return 用户信息
     */
    User selectUserById(Long uid);

    /**
     * 修改User
     * @param newUser 需要更新的User
     * @return 受影响行数
     */
    Integer updateUser(User newUser);

    /**
     * 获取总条数
     *
     * @return 总数
     * @see #selectUsers(Page page) <-该方法的总条数
     */
    Integer countUsers();

    /**
     * 插入一条用户信息
     *
     * @param user 用户信息
     * @return 受影响行数
     */
    Integer insertUser(User user);
}
