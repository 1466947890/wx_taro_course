package com.beizhi.service.impl;

import cn.hutool.core.text.StrSplitter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.common.dto.ExamineDto;
import com.beizhi.common.dto.chapterTask.ChapterTaskDto;
import com.beizhi.common.result.Result;
import com.beizhi.dao.ChapterTaskMapper;
import com.beizhi.dao.DetailsMapper;
import com.beizhi.dao.TeacherMapper;
import com.beizhi.dao.VideoMapper;
import com.beizhi.entity.ChapterTask;
import com.beizhi.entity.Details;
import com.beizhi.entity.Examine;
import com.beizhi.entity.Teacher;
import com.beizhi.service.ChapterTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.beizhi.service.impl.VideoServiceImpl.getVodUrl;

@Service
public class ChapterTaskServiceImpl extends ServiceImpl<ChapterTaskMapper, ChapterTask> implements ChapterTaskService {
    @Resource
    private ChapterTaskMapper chapterTaskMapper;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private DetailsMapper detailsMapper;
    @Override
    public Result saveChapterTask(ChapterTask chapterTask) {
        LambdaQueryWrapper<ChapterTask>
                chapterTaskLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterTaskLambdaQueryWrapper.eq(ChapterTask::getChapterId, chapterTask.getChapterId());
//        检测章节任务是否存在
        ChapterTask task = chapterTaskMapper.selectOne(chapterTaskLambdaQueryWrapper);
        if(Objects.isNull(task)){
            chapterTaskMapper.insert(chapterTask);
            if(Objects.nonNull(chapterTask.getVideoId()) && Objects.isNull(chapterTask.getExamineId())){
                return Result.success("上传视频成功");
            }else if(Objects.isNull(chapterTask.getVideoId()) && Objects.nonNull(chapterTask.getExamineId())){
                return Result.success("上传测验成功");
            }
        }else{
            chapterTaskMapper.update(chapterTask, chapterTaskLambdaQueryWrapper);
            if(Objects.nonNull(chapterTask.getVideoId()) && Objects.isNull(chapterTask.getExamineId())){
                return Result.success("更新视频成功");
            }else if(Objects.isNull(chapterTask.getVideoId()) && Objects.nonNull(chapterTask.getExamineId())){
                return Result.success("更新测验成功");
            }
        }
        return Result.error(500, "未知错误");
    }

    @Override
    public Result getChapterTask(Integer courseId,Integer chapterId) {
        ChapterTaskDto taskDto = new ChapterTaskDto();
//        获取播放视频地址
        String videoFlag = chapterTaskMapper.selectChapterVideoFlag(chapterId);

//        获取教师资料
        List<Teacher> teacherList = teacherMapper.selectTeacherListByCourseId(courseId);

        List<Examine> examineList = chapterTaskMapper.selectChapterExamineList(chapterId);
//        获取课程习题列表
        List<ExamineDto> examineDtoList = new ArrayList<>();
        for(Examine examine : examineList){
            ExamineDto examineDto = new ExamineDto();
            examineDto.setTitle(examine.getTitle());
            examineDto.setOptions(StrSplitter.split(examine.getEOptions(), "#", true, true));
            examineDto.setType(examine.getType());
            examineDto.setValue("");
            examineDtoList.add(examineDto);
        }

        // 获取课程资料
        LambdaQueryWrapper<Details> detailsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        detailsLambdaQueryWrapper.eq(Details::getCourseId, courseId);
        List<Details> details = detailsMapper.selectList(detailsLambdaQueryWrapper);

        taskDto.setVideoInfo(getVodUrl(videoFlag));
        taskDto.setTeacherList(teacherList);
        taskDto.setExamineList(examineDtoList);
        taskDto.setDetailsList(details);

        return Result.successData(taskDto);
    }
}
