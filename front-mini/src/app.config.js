export default defineAppConfig({
  // lazyCodeLoading: "requiredComponents",
  pages: [
    'pages/user/user', // 用户中心页面
    'pages/chapter_task/chapter_task', // 学生章节页面
    'pages/course/course', // 课程页面
    "pages/upload_ppt/upload_ppt",
    "pages/message/message", // 学生笔记页面
    'pages/edit_chapter/edit_chapter', // 编辑章节页面
    'pages/edit_chapter_list/edit_chapter_list', // 遍历章节列表页面
    'pages/join_course/join_course', // 加入课程页面
    'pages/create_course/create_course', // 创建课程页面
    'pages/index/index', // 首页页面
    'pages/personal_info/personal_info', // 个人资料页面
    'pages/course_student/course_student', // 学生课程信息页面
    "pages/chapter_list/chapter_list", // 学生章节列表
    'pages/topic/topic', // 上传题目页面
    // 'pages/topicTitle/topicTitle', // 题目标题页面
    'pages/course_teacher/course_teacher', // 老师课程信息页面

  ],
  window: {
    backgroundTextStyle: 'light',
    navigationBarBackgroundColor: '#fff',
    navigationBarTitleText: 'WeChat',
    navigationBarTextStyle: 'black'
  },
  lazyCodeLoading: "requiredComponents",
  tabBar: {
    list: [
      {
        pagePath: 'pages/index/index',
        iconPath: 'static/tabBar/home.png',
        selectedIconPath: 'static/tabBar/homeSelected.png',
        text: '首页',
      },
      {
        pagePath: 'pages/user/user',
        iconPath: 'static/tabBar/user.png',
        selectedIconPath: 'static/tabBar/userSelected.png',
        text: '我的',
      },
    ]
  }
})
