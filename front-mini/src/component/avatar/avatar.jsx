import { Button } from "@tarojs/components"
import { AtAvatar } from "taro-ui"
import { useState, useEffect } from "react"
import Taro from "@tarojs/taro"
import './avatar.scss'
import api from "../../constants/api"

const Avatar = () => {

  let avatar = Taro.getStorageSync("user") ? Taro.getStorageSync("user").userAvatar : ""
  const [avatarImageUrl, setAvatarImageUrl] = useState(avatar)
  useEffect(() => {
    avatar = Taro.getStorageSync("user") ? Taro.getStorageSync("user").avatar : ""
    setAvatarImageUrl(avatar)
  }, [avatar])

  const handleChooseAvatar = (e) => {
    let user = Taro.getStorageSync("user") ? Taro.getStorageSync("user") : {}
    let avatarUrl = e.detail.avatarUrl
    Taro.uploadFile({
      url: api.upload,
      filePath: avatarUrl,
      name: "file",
      formData: {},
      success(res){
        setAvatarImageUrl(res.data)
        avatarUrl = res.data
        user.avatar = avatarUrl;
        Taro.setStorageSync("user", user)
      }
    })
  }

  return (
    <Button className="btn_avatar" openType="chooseAvatar" onChooseAvatar={handleChooseAvatar.bind(this)}>
      <AtAvatar name="avatar" className="personal-avatar-image" circle image={avatarImageUrl}></AtAvatar>
    </Button>
  )
}

export default Avatar