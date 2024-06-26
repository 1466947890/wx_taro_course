import { Video } from "@tarojs/components"
import { recordVideoProcess } from "../utils/interfact"
import { useEffect, useRef, useState } from "react"

const VideoView = (props) => {
  // 当前视频进度
  let time = 60
  let current = 0
  let intervalHandle = useRef()

  const recode = () => [
    intervalHandle.current = setInterval(() => {
      recordProcess()
    }, time * 1000)
  ]

  const recordProcess = () => {
    recordVideoProcess(props.chapterId, props.videoDuration, current).then(res => {
      console.log(res);
    })
  }

  const onPlay = () => {
    recode()
  }

  /**
   * 视频暂停
   */
  const onPause = () => {
    clearInterval(intervalHandle.current)
  }

  /**
   * 视频播放到结尾
   */
  const onEnded = () => {
    current = props.videoDuration
    recordProcess()
    clearInterval(intervalHandle.currentl)
  }
  /**
   * 视频结束
   */
  const onStop = () => {
    clearInterval(intervalHandle.current)
  }

  useEffect(() => {
    return () => {
      clearInterval(intervalHandle.current)
    }

  }, [])


  return (
    <Video
      className="video"
      id='video'
      src={props.src}
      poster={props.proster}
      initialTime={0}
      direction={90}
      autoplay={false}
      objectFit={"fill"}
      showScreenLockButton={true}
      enableProgressGesture={true}
      showFullscreenBtn={true}
      showThinProgressBar={true}
      showSnapshotButton={true}
      showProgress={props.showProgress}
      vslideGesture={true}
      controls={true}
      showBottomProgress={true}
      title={props.title}
      onPlay={onPlay}
      onPause={onPause}
      onEnded={onEnded}
      onStop={onStop}
    />
  )
}

export default VideoView