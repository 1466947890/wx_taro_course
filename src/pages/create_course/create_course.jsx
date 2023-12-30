import { View } from "@tarojs/components"
import { useState } from "react"
import { AtForm, AtInput, AtButton, AtImagePicker } from "taro-ui"
import './create_course.scss'
import Taro from "@tarojs/taro"
import api from "../../constants/api"
import { ToastError, ToastSuccess } from "../../utils/toast"
import { saveCourse } from "../../utils/interfact"

const CreateCourse = () => {
  const [FormData, setFormData] = useState({})
  const [file, setFile] = useState()
  const onSubmit = (e) => {
    let value = e[0].detail.value
    let isEmpty = false
    let obj = Object.values(value)
    obj.forEach(item => {
      if (item.length === 0) {
        isEmpty = true
        return
      }
    })
    if (!isEmpty) {
      // 发送请求
      console.log(e[0].detail.value);
      console.log("发送请求");
      saveCourse(e[0].detail.value).then(res => {
        if(res.code === 200){
          ToastSuccess(res.msg)
        }else{
          ToastSuccess(res.msg)
        }
      })
    } else {
      ToastError("请填写数据")
    }
  }

  const onReset = () => {

  }
  const handleChange = (type, value) => {
    switch (type) {
      case "courseName":
        setFormData({
          ...FormData, "courseName": value
        })
        break;
      case "courseCredit":
        setFormData({
          ...FormData, "courseCredit": value
        })
        break;
      case "professorTime":
        setFormData({
          ...FormData, "professorTime": value
        })
        break;
      default:
    }
  }
  const hanleImageChange = (e) => {
    if (file && e.length > 1) {
      ToastError("仅封面")
      return
    }
    setFile(e)
    if (!e[0]) {
      setFormData({
        ...FormData, "courseImage": ""
      })
    } else {

      Taro.uploadFile({
        url: api.upload,
        filePath: e[0].url,
        name: "file",
        formData: {},
        success(res) {
          setFormData({
            ...FormData, "courseImage": res.data
          })
        }
      })
    }
  }
  const onImageClick = (e) => {
    console.log(e);
    console.log("111");
  }
  return (
    <View className="Index">
      <View className="form_box">
        <AtForm
          onSubmit={onSubmit.bind(this)}
          onReset={onReset.bind(this)}
        >
          <AtInput
            name='courseName'
            title='课程名称'
            type='text'
            placeholder='课程名称'
            value={FormData.courseName}
            onChange={handleChange.bind(this, "courseName")}
            required
          />
          <AtInput
            name='courseImage'
            title='课程封面'
            type='text'
            placeholder='上传课程封面图片'
            value={FormData.courseImage}
            onChange={handleChange.bind(this)}
            required
          >
          </AtInput>
          <AtImagePicker
            files={file}
            name="img"
            className="imagePicker"
            count={0}
            multiple={false}
            onChange={hanleImageChange.bind(this)}
            onImageClick={onImageClick.bind(this)}
            showAddBtn={true}
          />
          <AtInput
            name='courseCredit'
            title='课程学分'
            type='number'
            placeholder='课程学分'
            required
            value={FormData.courseCredit}
            onChange={handleChange.bind(this, "courseCredit")}
          />
          <AtInput
            name='professorTime'
            title='课程学期'
            type='text'
            placeholder='上课学期，例如：2024上半年'
            required
            value={FormData.professorTime}
            onChange={handleChange.bind(this, "professorTime")}
          />
          <View className="btn_box">
            <AtButton className="btn" type="primary" formType='submit'>创建</AtButton>
            <AtButton className="btn" formType='reset'>重置</AtButton>
          </View>
        </AtForm>
      </View>
    </View>
  )
}

export default CreateCourse