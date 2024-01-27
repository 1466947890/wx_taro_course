package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.entity.Message;
import com.beizhi.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    /*
        发表留言
     */

    /**
     * 发表留言
     * @param message
     * @param request
     * @return
     */
    @PostMapping
    public Result saveMessage(@RequestBody Message message, HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return messageService.saveMessage(message, userId);
    }

    /**
     * 获取留言列表
     * @return
     */
    @GetMapping("/{chapterId}")
    public Result getMessage(@PathVariable Integer chapterId){
        return messageService.getMessage(chapterId);
    }
}
