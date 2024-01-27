import { View } from "@tarojs/components"
import Taro from "@tarojs/taro"
import { useState, useEffect } from "react"
import { AtList, AtListItem, AtLoadMore } from "taro-ui"
import './courseList.scss'
import { getCourse } from "../../utils/interfact"
import { To } from "../../utils/navigato"



const CourseList = (props) => {
  let { type } = props
  useEffect(() => {
    getCourse(type).then(res => {
      if (res.code === 200) {
        setCourseList(res.data)
      }
    })
  }, [type])


  const handleToCourseInfo = (value, name) => {
    switch(type){
      case 0 : To("../courseSInfo/courseSInfo?courseId=" + value + "&name=" + name) 
      break;
      case 1 : To("../courseTInfo/courseTInfo?courseId=" + value + "&name=" + name) 
      break;
      default:
    }
  }
  const [courseList, setCourseList] = useState([])
  const [status, setStatus] = useState("more")
  const handleLoadingClick = (value) => {
    console.log(value);
    setStatus("loading")
  }
  return (
    <View className="courseListIndex">
      <AtList className="at-list">
        {
          !courseList ? "" : courseList.map(item => {
            return <AtListItem
              onClick={handleToCourseInfo.bind(this, item.id, item.name)}
              title={item.name}
              note={item.teacherList}
              arrow='right'
              className="at-list-item"
              thumb={item.image}
            />
          })
        }
      </AtList>
    </View>
  )
}

export default CourseList