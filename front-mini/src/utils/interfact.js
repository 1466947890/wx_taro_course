import { postJSON, putJSON, getJSON, deleteJSON } from "./request";
import api from "../constants/api";
import config from "../config";
import { message } from "taro-ui";


export const login = async (params) => {
  let result = await postJSON(api.login, params).catch(message => {
    console.log("登录接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getUserInfo = async () => {
  let result = await getJSON(api.user).catch(message => {
    console.log("获取用户信息接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const updateUserInfo = async (params) => {
  let result = await putJSON(api.login, params).catch(message => {
    console.log("更新用户接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const saveCourse = async (params) => {
  let result = await postJSON(api.course, params).catch(message => {
    console.log("保存课程接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getCourse = async (type) => {
  let result = await getJSON(api.course + "/" + type).catch(message => {
    console.log("获取课程接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const saveChapter = async (params) => {
  let result = await postJSON(api.chapter, params).catch(message => {
    console.log("添加章节接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getChapter = async (courseId) => {
  let result = await getJSON(api.chapter + "/" + courseId).catch(message => {
    console.log("获取章节接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const deleteChapter = async (courseId) => {
  let result = await deleteJSON(api.chapter + "/" + courseId).catch(message => {
    console.log("删除章节接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const saveCourseStudent = async (params) => {
  let result = await postJSON(api.student_course, params).catch(message => {
    console.log("学生添加课程接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const saveTopic = async (params) => {
  let result = await postJSON(api.topic, params).catch(message => {
    console.log("上传题目接口出错了，错误信息如下：");
    console.log(message);
  })

  return result.data;
}

export const getVideoProcess = async () => {
  let result = await getJSON(api.video_process).catch(message => {
    console.log("获取视频进度接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const saveChapterTask = async (params) => {
  let result = await postJSON(api.chapterTask, params).catch(message => {
    console.log("绑定章节视频接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getChapterTask = async (courseId, chapterId) => {
  let result = await getJSON(api.chapterTask + "/" + courseId + "/" + chapterId).catch(message => {
    console.log("获取章节任务接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const saveMessage = async (params) => {
  let result = await postJSON(api.message, params).catch(message => {
    console.log("保存留言接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getMessage = async (chapterId) => {
  let result = await getJSON(api.message + "/" + chapterId).catch(message => {
    console.log("获取留言接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getChapterExamineGrade = async (chapterId, params) => {
  let result = await postJSON(api.grade + "/" + chapterId, params).catch(message => {
    console.log("章节打分接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const recordVideoProcess = async (chapterId, duration, current) => {
  let result = await postJSON(api.process + "/" + chapterId + "/" + duration + "/" + current).catch(message => {
    console.log("记录视频进度接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const saveNotes = async (params) => {
  let result = await postJSON(api.notes, params).catch(message => {
    console.log("保存笔记接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getNotes = async (chapterId) => {
  let result = await getJSON(api.notes + "/" + chapterId).catch(message => {
    console.log("获取笔记接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data;
}

export const getInvitationQrImage = async (courseId) => {
  let result = await getJSON(api.qrImg, { page: config.invitation_page, scene: courseId }).catch(message => {
    console.log("获取课程二维码接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data
}

export const getCourseInfo = async (courseId) => {
  let result = await getJSON(api.courseInfo + "/" + courseId).catch(message => {
    console.log("获取课程信息接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data
}

export const joinCourse = async (courseId) => {
  let result = await postJSON(api.join + "/" + courseId).catch(message => {
    console.log("加入课程信息接口出错了，错误信息如下：");
    console.log(message);
  })
  return result.data
}


export const WxLogin = async (data) => {
  let result = await postJSON(api.wxLogin, data).catch(error => {
    return Promise.reject(error)
  })
  return result.data
}

