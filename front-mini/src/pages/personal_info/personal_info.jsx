import { View } from "@tarojs/components"
import Taro from "@tarojs/taro"
import { ToastError, ToastSuccess } from "../../utils/toast"
import {
  AtForm, AtInput,
  AtButton, AtActionSheet,
  AtActionSheetItem, AtBadge
} from 'taro-ui'
import { useState } from "react"
import Avatar from "../../component/avatar/avatar"
import './personal_info.scss'
import { updateUserInfo, getUserInfo } from "../../utils/interfact"
const onSubmit = (e) => {
  let userId = Taro.getStorageSync("user").id
  if (!userId) {
    ToastError("请先登录")
    return
  }
  let user = e[0].detail.value
  user.id = userId
  updateUserInfo(user).then(res => {
    if (res.code === 200) {
      userInfo()
      ToastSuccess("提交成功")
    }
  })
}

const userInfo = () => {
  getUserInfo().then(res => {
    if (res.code === 200) {
      Taro.setStorageSync("user", res.data)
    }
  })
}
const onReset = () => {

}

let sexOption = [
  { id: 1, value: "男" },
  { id: 2, value: "女" },
]
let form = Taro.getStorageSync("user") ? Taro.getStorageSync("user") : {}
// console.log(form);
const PersonalInfo = () => {
  const [formData, setFormData] = useState(form)
  const [selectSexOpen, setSelectSexOpen] = useState(false)

  // 表单变化
  const handleChange = (type, value) => {
    switch (type) {
      case "username":
        setFormData({
          ...formData,
          "name": value
        })
        break;
      case "phoneNumber":
        setFormData({
          ...formData,
          phone: value
        })
        break;
      case "major":
        setFormData({
          ...formData,
          major: value
        })
        break;
      default:
    }
  }



  const hanleSelectSex = (value) => {
    setFormData({
      ...formData,
      sex: value
    })
    setSelectSexOpen(false)
  }

  /**
   * 初始化的时候，获取缓存里面的信息填入表格
   * 后面再提交信息即可
   */
  return (
    <View className="Index">
      <View className="avatar_box">
        <AtBadge value={"未认证"} style={{ background: "green" }}>
          <Avatar></Avatar>
        </AtBadge>

      </View>
      <View className="personal_info">
        <AtForm
          onSubmit={onSubmit.bind(this)}
          onReset={onReset.bind(this)}
        >
          <AtInput
            name='name'
            title='姓名'
            type='text'
            placeholder='姓名'
            value={formData.name}
            onChange={handleChange.bind(this, "username")}
          />
          <AtInput
            name='sex'
            title='性别'
            type='text'
            placeholder='性别'
            value={formData.sex}
            onFocus={() => {
              setSelectSexOpen(true)
            }}
          />
          <AtInput
            name='phone'
            title='手机号'
            type={"number"}
            placeholder='手机号'
            value={formData.phone}
            onChange={handleChange.bind(this, "phoneNumber")}
          />
          <AtInput
            name='major'
            title='专业'
            type="text"
            placeholder='专业'
            value={formData.major}
            onChange={handleChange.bind(this, "major")}
          />
          <View className="btn_box">
            <AtButton className="btn" type="primary" formType='submit'>保存</AtButton>
            <AtButton className="btn" formType='reset'>重置</AtButton>
          </View>
        </AtForm>
      </View>
      <View>
        <AtActionSheet cancelText="取消" isOpened={selectSexOpen} onCancel={() => {
          setSelectSexOpen(false)
        }} >
          {
            sexOption.map((item) => {
              return (
                <AtActionSheetItem key={item.id} onClick={hanleSelectSex.bind(this, item.value)}>
                  {item.value}
                </AtActionSheetItem>
              )
            })
          }
        </AtActionSheet>
      </View>
    </View>
  )
}
export default PersonalInfo