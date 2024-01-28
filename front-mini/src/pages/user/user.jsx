import { View, Form, Button, Input } from "@tarojs/components"
import {
  AtAvatar, AtList,
  AtListItem, AtButton, AtMessage,
} from 'taro-ui'
import Taro from "@tarojs/taro"
import './user.scss'
import React, { useState, useEffect } from "react"
import Avatar from "../../component/avatar/avatar"
import { login, getUserInfo } from "../../utils/interfact"

const User = () => {
  // 首先创建一个use的信息缓存
  useEffect(() => {
    // if (!Taro.getStorageSync("user")) Taro.setStorageSync("user", {})
  })
  const handleTo = (path) => {
    Taro.navigateTo({
      url: path
    })
  }


  const handleLogin = async (e) => {
    let avatar = Taro.getStorageSync("user") ? Taro.getStorageSync("user").userAvatar : ""
    await Taro.login({
      success(res) {
        login({
          code: res.code,
          avatar,
          nickName: e.detail.value.nickName
        }).then(res => {
          if (res.code === 200) {
            Taro.setStorageSync("token", res.data.token)
            userInfo()
            setNeedAuth(false)
            Taro.atMessage({
              'message': res.msg,
              'type': "success",
            })
          }
        })
      },
      fail(error) {
        console.log(error);
      }
    })
  }

  const userInfo = () => {
     getUserInfo().then(res => {
      if(res.code === 200){
        Taro.setStorageSync("user", res.data)
      }
    })
  }
  const handleLogout = () => {
    Taro.removeStorageSync("user")
    Taro.removeStorageSync("token")
    setNeedAuth(true)
  }
  let nickName = Taro.getStorageSync("user") ? Taro.getStorageSync("user").nickname : ""
  let auth = true
  Taro.getStorageSync("user").id ? auth = false : auth = true
  const [needAuth, setNeedAuth] = useState(auth)
  const [loading, setLoading] = useState(false)
  const formSubmit = (e) => {
    setLoading(true)
    Taro.showLoading({
      title: '登录中',
    })
    handleLogin(e)
    setTimeout(() => {
      setLoading(false)
      Taro.hideLoading()
    }, 2 * 1000)
  }
  return (
    <View className="index">
      <AtMessage />
      <View className="backgroud"></View>
      <Form onSubmit={formSubmit}>
        <View className="personal">
          <View className="personal-avatar">
            <View className="personal-avatar">
              <Avatar></Avatar>
            </View>
            <View className="personal-nickname">
              <Input
                name="nickName"
                type="nickname"
                placeholder="微信昵称"
                value={nickName}
              />
            </View>
          </View>
          <AtList>
            <AtListItem title='我的档案' onClick={handleTo.bind(this, "../personal_info/personal_info")} arrow='right' iconInfo={{ size: 25, color: '#78A4FA', value: 'calendar', }} />
            <AtListItem title='我的课程' onClick={handleTo.bind(this, "../course/course")} arrow='right' iconInfo={{ size: 25, color: '#FF4949', value: 'bookmark', }} />
          </AtList>
          {
            needAuth ? <View className="btn_box">
              <AtButton disabled={loading} formType='submit' type="primary">登录</AtButton>
            </View> :
              <View className="btn_box">
                <AtButton onClick={handleLogout} type="primary">退出登录</AtButton>
              </View>
          }
        </View>
      </Form>
    </View>
  )
}

export default User