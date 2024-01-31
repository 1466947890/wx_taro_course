import { View, Image } from "@tarojs/components"
import Taro, { useRouter } from "@tarojs/taro"
import { useEffect, useState } from "react";
import { AtButton } from "taro-ui";
import { getCourseInfo } from "../../utils/interfact";
import "./course_info.scss"

const CourseInfo = () => {
  let { scene } = useRouter().params
  if (!scene) {
    scene = 100004
  }
  const [courseImage, setCourseImage] = useState()
  const [courseName, setCourseName] = useState()
  useEffect(() => {
    getCourseInfo(scene).then(res => {
      setCourseImage(res.data.image)
      setCourseName(res.data.name)
    })

  }, [])
  const getPhoneNumber = (e) => {
    let {code} = e.detail
    Taro.login({
      success(res) {
        
      }
    })
  }
  return (
    <View className="Index">
      <View className="image_box">
        <Image src={courseImage}></Image>
      </View>
      <View className="name_box">
        {courseName}
      </View>
      <View className="btn_box">
        <AtButton type="primary" openType="getPhoneNumber" onGetPhoneNumber={(e) => getPhoneNumber(e)}>加入课程</AtButton>
      </View>
    </View>
  )
}

export default CourseInfo