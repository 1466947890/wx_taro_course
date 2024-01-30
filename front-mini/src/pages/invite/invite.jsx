import Taro, { useRouter } from "@tarojs/taro"
import { View, Image } from "@tarojs/components"
// import Taro from "@tarojs/taro"
import { AtButton } from "taro-ui"
import { useEffect, useState } from "react"
import { getInvitationQrImage } from "../../utils/interfact"
import './invite.scss'
/**
 * 
 * @returns 邀请学生页面，生成界面二维码
 */
const Invite = () => {
  let { courseId } = useRouter().params
  if (!courseId) {
    courseId = 100004
  }

  const [invitationImageSrc, setInvitationImageSrc] = useState()

  useEffect(() => {
    getInvitationQrImage(courseId).then(res => {
      setInvitationImageSrc(res.data)
    })
  }, [])

  const handleShareImage = () => {
    Taro.downloadFile({
      url: invitationImageSrc,
      success: (res) => {
        Taro.showShareImageMenu({
          path: res.tempFilePath
        })
      }
    })
  }

  return (
    <View className="Index">
      <View className="image_box">
        <Image
          src={invitationImageSrc}
        />
      </View>
      <View className="btn_box">
        <AtButton type='primary' onClick={handleShareImage}>分享二维码</AtButton>
      </View>
    </View>
  )
}

export default Invite