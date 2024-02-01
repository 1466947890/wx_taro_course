import { View, Image } from "@tarojs/components"
import Taro, { useRouter } from "@tarojs/taro"
import { useEffect, useState } from "react";
import { AtButton } from "taro-ui";
import { WxLogin, getCourseInfo, joinCourse } from "../../utils/interfact";
import "./course_info.scss"
import { ToastSuccess } from "../../utils/toast";

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
    // console.log(code);

    // 先登录，然后加入课程
    Taro.login({
      success(res) {
        let data = {
          code: res.code,
          phoneCode: code
        }
        WxLogin(data).then(res => {
          Taro.setStorageSync('token', res.data.token)
          joinCourse(scene).then(res => {
            ToastSuccess(res.msg)
          })
        })
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