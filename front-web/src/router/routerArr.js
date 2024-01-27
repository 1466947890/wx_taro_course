const routerArr = [
  { path: "/", element: "Index" },
  { path: "/login", element: "Login" },
  { path: "/admin/login", element: "AdminLogin" },
  {
    path: "/teacher", element: "Teacher", children: [
      { path: "index", element: "TeacherCourse" },
      { path: "details", element: "TeacherDetails" },
    ]
  },
  {
    path: "/course", element: "Course", children: [
      { path: "chapter/:courseId", element: "CourseIndex" },
      { path: "grade/:courseId", element: "CourseGrade" },
      { path: "details/:courseId", element: "CourseDetails" },
      { path: ":courseId/:chapterId", element: "ChapterIndex" }
    ]
  },
  {
    path: "/admin", element: "Admin", children: [
      { path: "", element: "AdminRedrict" },
      { path: "index", element: "AdminIndex" },
      { path: "user", element: "AdminUser" },
      { path: "major", element: "AdminMajor" },
      { path: "course", element: "AdminCourse" },
      { path: "video", element: "AdminVideo" },
      { path: "details", element: "AdminDetails" },

    ]
  },
]


export default routerArr