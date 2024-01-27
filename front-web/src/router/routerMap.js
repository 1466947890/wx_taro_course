import { Navigate } from "react-router-dom";
import Login from "../page/login";
import Teacher from "../page/teacher/teacher";
import TeacherCourse from "../page/teacher/children/teacher_course";
import TeacherDetails from "../page/teacher/children/teacher_details";
import Course from "../page/course/course";
import CourseIndex from "../page/course";
import CourseGrade from "../page/course/grade";
import CourseDetails from "../page/course/details";
import ChapterIndex from "../page/chapter";

import AdminLogin from "../page/admin/login";
import Admin from "../page/admin/admin";
import AdminIndex from "../page/admin";
import AdminUser from "../page/admin/user";
import AdminMajor from "../page/admin/major";
import AdminCourse from "../page/admin/course";
import AdminVideo from "../page/admin/video";
import AdminDetails from "../page/admin/details";


const routerMap = {
  Index: <Navigate to="/login" />,
  Login: <Login></Login>,
  Teacher: <Teacher></Teacher>,
  TeacherCourse: <TeacherCourse></TeacherCourse>,
  TeacherDetails: <TeacherDetails></TeacherDetails>,
  Course: <Course></Course>,
  CourseIndex: <CourseIndex></CourseIndex>,
  CourseGrade: <CourseGrade></CourseGrade>,
  CourseDetails: <CourseDetails></CourseDetails>,
  ChapterIndex: <ChapterIndex></ChapterIndex>,

  AdminLogin: <AdminLogin></AdminLogin>,
  Admin: <Admin></Admin>,
  AdminRedrict: <Navigate to="index"></Navigate>,
  AdminIndex: <AdminIndex></AdminIndex>,
  AdminUser: <AdminUser></AdminUser>,
  AdminMajor: <AdminMajor></AdminMajor>,
  AdminCourse: <AdminCourse></AdminCourse>,
  AdminVideo: <AdminVideo></AdminVideo>,
  AdminDetails: <AdminDetails></AdminDetails>
}

export default routerMap