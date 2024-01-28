import { AtModal, AtModalHeader, AtModalContent, AtModalAction, AtProgress } from "taro-ui"
import { Button } from "@tarojs/components"
import { ToastNone, ToastSuccess } from "../../../utils/toast"

const VideoProcessModal = (props) => {
  let { 
    videoProcessModalOpen, 
    ProcessChilrenData, } = props

  const handleCloseModal = () => {
    ToastNone("上传过程中请不要离开此页面，否则会中断上传")
    ProcessChilrenData(false)
  }
  return (
    <AtModal isOpened={videoProcessModalOpen} closeOnClickOverlay={false}>
      <AtModalHeader>视频上传中</AtModalHeader>
      <AtModalContent className="modal_content">
        <AtProgress percent={props.videoProcess} color='#FFC82C' />
      </AtModalContent>
      <AtModalAction><Button onClick={handleCloseModal}>后台上传</Button> </AtModalAction>
    </AtModal>
  )
}
export default VideoProcessModal