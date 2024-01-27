import api from "../constants/api"
import http from "./request"
import { message } from "antd"

export const resonse = (result, successFun) => {
  if(result.code === 200){
    message.success(result.msg)
    successFun()
  }else{
    message.error(result.msg)
  }
}


export const login = async (data) => {
  let res = await http.post(api.login, data).catch(err => {
    console.log("登录接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const getCourse = async () => {
  let res = await http.get(api.course + "/1").catch(err => {
    console.log("获取课程接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const getChapter = async (courseId) => {
  let res = await http.get(api.chapter + "/" + courseId).catch(err => {
    console.log("获取章节接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const getChapterTask = async (courseId,chapterId) => {
  let res = await http.get(api.chapterTask + "/" + courseId + "/" + chapterId).catch(err => {
    console.log("获取章节任务接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const getDetails = async (courseId) => {
  let res = await http.get(api.details + "/" + courseId).catch(err => {
    console.log("获取资料接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const updateTeacherDetails = async (params) => {
  let res = await http.put(api.teacher, params).catch(err => {
    console.log("更新教师接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const getTeacherDetails = async () => {
  let res = await http.get(api.teacher).catch(err => {
    console.log("获取教师资料接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const userList = async (params) => {
  let res = await http.get(api.user_page, {params}).catch(err => {
    console.log("获取学生列表接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const changeIsReal = async (userid, isReal) => {
  let res = await http.put(api.isReal + "/" + userid + "/" + isReal).catch(err => {
    console.log("更改认证接口出错了");
    return Promise.reject(err)
  })
  return res
}

export const getMajorRole = async () => {
  let res = await http.get(api.majorRole).catch(err => {
    console.log("获取专业角色接口出错了");
    return Promise.reject(err)
  })
  return res
}


