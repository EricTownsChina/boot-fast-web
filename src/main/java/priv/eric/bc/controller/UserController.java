package priv.eric.bc.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.eric.bc.entity.User;
import priv.eric.bc.service.UserService;
import priv.eric.kit.sys.entity.Page;
import priv.eric.kit.sys.entity.Resp;

import javax.annotation.Resource;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-29 17:15
 * <p>
 * desc:
 */
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("get")
    public Resp getUsers(Page<User> page) {
        userService.getUsers(page);
        return Resp.ok(page);
    }

    @PostMapping("add")
    public Resp addUser(User user) {
        userService.addOneUser(user);
        return Resp.ok();
    }

    @GetMapping("get/{uid}")
    public Resp getUser(@PathVariable Long uid) {
        return Resp.ok(userService.getUser(uid));
    }

    @PostMapping("update")
    public Resp updateUser(User user) {
        userService.updateOneUser(user);
        return Resp.ok();
    }

}
