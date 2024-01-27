import { Card } from "antd"
import { getCourse } from "../../../utils/interface"
import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import "./css/teacher_course.scss"
const TeacherCourse = () => {
  const [courseList, setCourseList] = useState([])

  useEffect(() => {
    getCourse().then(res => {
      setCourseList(res.data)
    })
  }, [])


  return (
    <div className="teacher_index_coures">
      <div className="course_list_box">
        {
          courseList.size = 0 ? "" : courseList.map((item, index) => {
            return (
              <Link key={item.id} to={'/course/chapter/' + item.id}>
                <Card  className="course_box">
                  <img className="course_img" src={item.image} alt="" />
                  <div className="course_info">
                    <h3 className="course_title">{item.name}</h3>
                  </div>
                </Card>
              </Link>
            )
          })
        }
      </div>

    </div>
  )
}

export default TeacherCourse