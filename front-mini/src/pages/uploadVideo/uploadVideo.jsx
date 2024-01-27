import { View, Text } from "@tarojs/components"
import { AtButton, AtProgress } from "taro-ui"
import api from "../../constants/api"
import { chooseVideo, uploadFile } from "../../utils/taroUtils"
import "./uploadVideo.scss"
import { useState } from "react"
import { getVideoProcess } from "../../utils/interfact"


const UploadVideo = () => {
  const [process, setProcess] = useState(0);
  const [videoStatus, setVideoStaus] = useState("视频等待上传")
  // 上传任务列表
  const hanleUploadVideo = () => {
    // 上传视频选项，可以录制上传或者本地上传
    let processTime;
    chooseVideo().then(res => {
      if(!res){
        return
      }
      getProcess()
      setProcess(0)
      setVideoStaus("视频上传中")
      uploadFile(api.video_upload, res.tempFilePath).then(res => {
        if(!res){
          return
        }
        clearInterval(processTime)
        setVideoStaus("视频上传成功")
        setProcess(100)
      })
    })


    const getProcess = () => {
      processTime = setInterval(() => {
        getVideoProcess().then(res => {
          setProcess(res.data)
        })
      }, 1000)
    }
  }
  return (
    <View className="Index">
      <View className="container">
        <View className="task_proccess">
          {/* 任务列表页面 */}
          <Text>{videoStatus}</Text>
          <AtProgress percent={process} color='#13CE66' />
        </View>
        <View className="btn_box">
          <AtButton type="primary" onClick={hanleUploadVideo}>上传视频</AtButton>
        </View>
      </View>
    </View>
  )
}

export default UploadVideo