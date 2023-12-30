package com.beizhi.controller.admin;

import com.beizhi.common.result.Result;
import com.beizhi.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2023/12/22 9:40
 * @describe
 */

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {
    @Resource
    private UserService userService;


    /**
     * 获取用户列表（分页）
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result userList(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "-2") Integer isReal){
        return userService.selectUserList(pageNum, pageSize, name, isReal);
    }

    /**
     * 更改用户审核状态
     * @param userid
     * @param isReal
     * @return
     * @author beizhi at 12
     */
    @PutMapping("/isReal/{userid}/{isReal}")
    public Result updateIsReal(@PathVariable Integer userid,@PathVariable Integer isReal){
        return userService.updateIsReal(userid, isReal);
    }
}
