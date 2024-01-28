import { View } from "@tarojs/components"
import { AtSegmentedControl, AtNavBar, AtActionSheet, AtActionSheetItem } from "taro-ui"
import CourseList from "../course_list/course_list"
import { useState } from "react"
import "./course.scss"
import { To } from "../../utils/navigato"

const Course = () => {
  // 我教的课和我学的课
  const [current, setCurrent] = useState(0)
  // 加入或创建课程
  const [courseOpen, setCourseOpen] = useState(false)
  const handleNavBarClick = () => {
    setCourseOpen(true)
  }
  const handleTabClick = (value) => {
    setCurrent(value)
    // 测试
  }

  // 0为加入课程，1为创建课程
  const hanleCourse = (value) => {
    switch(value){
      case 0: To("../join_course/join_course")
      break;
      case 1: To("../create_course/create_course")
      break;
      default:
    }
    setCourseOpen(false)
  }

  return (
    <View className="courseIndex">
      <AtNavBar
        onClickRgIconSt={handleNavBarClick}
        color='#000'
        rightFirstIconType='add'
        fixed
      >
        <View>
          <AtSegmentedControl
            values={['我学的课', '我教的课']}
            onClick={handleTabClick}
            current={current}
          />
        </View>
      </AtNavBar>
      <View className="courseList-container">
        {/* 0为我学的课 */}
        {
          current === 0
            ? <View className='tab-content'>
              <CourseList type={current}></CourseList>
            </View>
            : null
        }
        {
          current === 1
            ? <View className='tab-content'>
              <CourseList type={current}></CourseList>
            </View>
            : null
        }
      </View>
      <View>
        <AtActionSheet cancelText="取消" isOpened={courseOpen} onClose={() => {
          setCourseOpen(false)
        }}>
          <AtActionSheetItem onClick={hanleCourse.bind(this, 0)}>
            加入课程
          </AtActionSheetItem>
          <AtActionSheetItem onClick={hanleCourse.bind(this, 1)}>
            创建课程
          </AtActionSheetItem>
        </AtActionSheet>
      </View>
    </View>
  )
}
export default Course