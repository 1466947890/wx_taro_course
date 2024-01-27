const type = "/web";

const catalog = {
  login: type + "/login",
  user:  type + "/user",
  cousre: "/course",
  chapter: "/chapter",
  chapterTask: "/chapterTask",
  file: "/file",
  video: "/video",
  details: "/details",
  teacher: "/teacher",
}

const api = {
  login: catalog.login,
  // 教师课程接口
  course: catalog.cousre,
  chapter: catalog.chapter,
  chapterTask: catalog.chapterTask,
  uploadVideo: catalog.video + "/uploadVideo/interAspect",
  upload: catalog.file + "/upload/",
  details: catalog.details,
  teacher: catalog.teacher,
  user_page: catalog.user + "/page",
  isReal: catalog.user + "/isReal",
  majorRole: catalog.user + "/majorRole"
}

export default api