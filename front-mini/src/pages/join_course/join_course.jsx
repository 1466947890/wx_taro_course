import { View } from "@tarojs/components"
import Taro from '@tarojs/taro'
import { useState } from "react"
import { AtForm, AtInput, AtButton } from 'taro-ui'
import './join_course.scss'
import { saveCourseStudent } from "../../utils/interfact"
import { ToastSuccess, ToastError } from "../../utils/toast"

const JoinCourse = () => {

  const [courseId, setCourseId] = useState()
  const handleChangeCourseId = (value) => {
    setCourseId(value)
  }
  const onSubmit = (e) => {
    saveCourseStudent(e[0].detail.value).then(res => {
      if(res.code === 200){
        ToastSuccess(res.msg)
      }else{
        ToastError(res.msg)
      }
    })
  }
  const onReset = (e) => {

  }
  return (
    <View className="Index">
      <View className="form_box">
        <AtForm
          onSubmit={onSubmit.bind(this)}
          onReset={onReset.bind(this)}
        >
          <AtInput
            name='courseId'
            title='课程ID'
            type='number'
            placeholder='课程ID'
            value={courseId}
            onChange={handleChangeCourseId.bind(this)}
          />
          <View className="btn_box">
            <AtButton className="btn" type="primary" formType='submit'>加入</AtButton>
            <AtButton className="btn" type="secondary" formType='reset'>重置</AtButton>
          </View>
        </AtForm>
      </View>

    </View>

  )
}

export default JoinCourse