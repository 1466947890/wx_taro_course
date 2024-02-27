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

  adminUser: type + "/admin/user",
  adminMajor: type + "/admin/major",
  adminCourse: type + "/admin/course",
  adminVideo: type + "/admin/video",
  adminDetails: type + "/admin/details"
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
  user_page: catalog.adminUser + "/page",
  isReal: catalog.user + "/isReal",
  majorRole: catalog.user + "/majorRole",





  adminUserRole: catalog.adminUser + "/majorRole",
  adminUser: catalog.adminUser,
  adminMajorPage: catalog.adminMajor + "/page",
  adminCoursePage: catalog.adminCourse + "/page",
  adminVideoPage: catalog.adminVideo + "/page",
  adminDetailsPage: catalog.adminDetails + "/page"
}

export default api