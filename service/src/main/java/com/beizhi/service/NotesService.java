package com.beizhi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beizhi.common.result.Result;
import com.beizhi.entity.Notes;

/**
 * @author 14669
 * @date 2023/12/15 15:26
 * @describe
 */
public interface NotesService extends IService<Notes> {
    Result saveNotes(Notes notes);

    Result getNotes(Integer chapterId, Integer userId);
}
