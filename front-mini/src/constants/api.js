const rootPath = "http://localhost:9092"
// const rootPath = "https://course.lesyun.com"
const catalog = {
  user: rootPath + "/user",
  file: rootPath + "/file",
  course: rootPath + "/course",
  chapter: rootPath + "/chapter",
  topic: rootPath + "/topic",
  video: rootPath + "/video",
  chapterTask: rootPath + "/chapterTask",
  message: rootPath + "/message",
  examine: rootPath + "/examine",
  process: rootPath + "/process",
  notes: rootPath + "/notes"
}

const wx_catalog = {
  invitation: rootPath + "/wx/invitation",
  user:  rootPath +"/wx/user",
  course: rootPath + "/wx/course"
}

const api = {
  login:  rootPath + "/login",
  upload: catalog.file + "/upload",
  user: catalog.user,
  course: catalog.course,
  chapter: catalog.chapter,
  student_course: catalog.course + "/studentCourse",
  topic: catalog.topic,
  video_upload: catalog.video + "/uploadVideo/interAspect",
  video_process: catalog.video + "/process",
  chapterTask: catalog.chapterTask,
  message: catalog.message,
  grade: catalog.examine + "/grade",
  process: catalog.process,
  notes: catalog.notes,

  courseInfo: wx_catalog.invitation + "/courseInfo",
  qrImg: wx_catalog.invitation + "/qrImage",
  join: wx_catalog.invitation + "/join",
  wxLogin: wx_catalog.user + "/login",
  index: wx_catalog.course + "/index"
}

export default api