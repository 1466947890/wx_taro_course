package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Message;

public interface MessageService extends IService<Message> {
    Result saveMessage(Message message, Integer userId);

    Result getMessage(Integer chapterId);
}
