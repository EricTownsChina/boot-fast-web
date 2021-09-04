package priv.eric.bc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import priv.eric.bc.dao.UserDao;
import priv.eric.bc.entity.User;
import priv.eric.bc.entity.group.UpdateGroup;
import priv.eric.bc.service.UserService;
import priv.eric.kit.sys.entity.Page;
import priv.eric.kit.sys.util.BeanValidateUtil;
import priv.eric.kit.sys.util.RedisUtil;
import priv.eric.kit.sys.util.Uid;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-29 17:13
 * <p>
 * desc:
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "userInfo")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void getUsers(Page<User> page) {
        // 查询记录
        List<User> users = userDao.selectUsers(page);
        // null值替换
        page.setRecords(Optional.ofNullable(users).orElseGet(ArrayList::new));
        // 总数
        Integer count = userDao.countUsers();
        page.setTotal(count);
    }

    @Override
    public void addOneUser(User user) {
        user.setUserUid(Uid.next());
        user.setUserId(UUID.randomUUID().toString());
        Integer rows = userDao.insertUser(user);
        if (rows < 1) {
            throw new RuntimeException("插入用户信息失败!");
        }
    }

    @Override
    @Cacheable(key = "#p0")
    public User getUser(Long uid) {
        User user = userDao.selectUserById(uid);
        return Optional.ofNullable(user).orElseGet(User::new);
    }

    @Override
    @CacheEvict(key = "#user.getUserUid()")
    public void updateOneUser(User user) {
        // 不需要更新
        if (null == user) {
            return;
        }
        // 参数校验
        BeanValidateUtil.validate(user, UpdateGroup.class);
        userDao.updateUser(user);
    }

}
