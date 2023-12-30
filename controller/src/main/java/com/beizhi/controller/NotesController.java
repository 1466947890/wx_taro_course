package com.beizhi.controller;

import com.beizhi.common.result.Result;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.entity.Notes;
import com.beizhi.service.NotesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 14669
 * @date 2023/12/15 15:27
 * @describe
 */
@RestController
@RequestMapping("/notes")
public class NotesController {
    @Resource
    private NotesService notesService;

    /**
     * 保存笔记
     * @param notes
     * @param request
     * @return
     */
    @PostMapping
    public Result saveNotes(@RequestBody Notes notes, HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        notes.setUserid((userId));
        return notesService.saveNotes(notes);
    }

    /**
     * 获取笔记内容
     * @param chapterId
     * @param request
     * @return
     */
    @GetMapping("/{chapterId}")
    public Result getNotes(@PathVariable Integer chapterId, HttpServletRequest request){
        Integer userId = JwtUtils.getUserIdByRequest(request);
        return notesService.getNotes(chapterId,userId);
    }
}
