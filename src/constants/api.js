const rootPath = "http://localhost:9092"
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
  notes: catalog.notes
}

export default api