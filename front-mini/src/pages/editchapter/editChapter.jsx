import { View, Text, } from "@tarojs/components"
import VideoView from "../../component/video"
import "./editChapter.scss"
import { useEffect, useState } from "react"
import { chooseVideo, uploadFile } from "../../utils/taroUtils"
import VideoProcessModal from "./children/videoProcessModal"
import api from "../../constants/api"
import { getChapterTask, getVideoProcess, saveChapterTask } from "../../utils/interfact"
import { ToastError, ToastSuccess } from "../../utils/toast"
import { useRouter } from "@tarojs/taro"
import { To } from "../../utils/navigato"
import {
  AtTabs,
  AtTabsPane,
  AtActionSheet,
  AtActionSheetItem,
  AtFab,
} from 'taro-ui'
import ExamineList from "./children/examine_list"
import Details from "./children/details"
import Introduction from "./children/introduction"
import Message from "./children/message"
import Taro from "@tarojs/taro"





const EditChapter = () => {
  let router = useRouter()
  let routerParams = router.params
  const [current, setCurrent] = useState(0)
  const [actionOpen, setActionOpen] = useState(false)
  const [videoProcessModalOpen, setVideoProcessModalOpen] = useState(false)
  const [videoProcess, setVideoProcess] = useState(0)
  const [windowHeight, setWindowHeight] = useState();
  const [detailsList, setDetailsList] = useState([])



  /**
   * 构思：
   * 当第一次进入页面时，获取视频相关信息
   */
  // let routerParams = {
  //   chapterId: 9
  // }
  /**
   * 临时注释
   */
  // routerParams.chapterId = 9
  useEffect(() => {
    const getData = async () => await getChapterTask(routerParams.courseId, routerParams.chapterId)
    getData().then(res => {
      // console.log(res.data);
      setVideoSrc(res.data.videoInfo.url)
      setExamineList(res.data.examineList)
      setDetailsList(res.data.detailsList)
      setExamineList(res.data.examineList)
      setTeacherList(res.data.teacherList)
    })
  }, [])

  const handleChangeCurrent = (value) => {
    setCurrent(value)
  }
  /**
   * 选项
   * @param {*} value 
   */
  const handleSelect = (value) => {
    switch (value) {
      case 0:
        uploadVideo()
        break;
      case 1:
        uploadExamine()
        break;
      default:
    }
    setActionOpen(false)
  }

  /**
   * 上传视频
   */
  let processTime;
  /**
   * 上传视频
   */
  Taro.getSystemInfoAsync({
    success(res) {
      setWindowHeight(res.windowHeight)
    }
  })
  
  const uploadVideo = () => {
    console.log("上传视频");
    chooseVideo().then(res => {
      if (!res) {
        return
      }
      setVideoSrc(res.tempFilePath)
      setVideoProster(res.thumbTempFilePath)
      setVideoProcessModalOpen(true)
      getProcess()
      uploadFile(api.video_upload + "/" + routerParams.chapterId, res.tempFilePath).then(res => {
        if (!res) {
          return
        }
        if (res.code === 200) {
          clearInterval(processTime)
          ToastSuccess("上传完成")
          setVideoProcessModalOpen(false)
        } else {
          ToastError("上传失败")
        }
      })
    })
  }

  const getProcess = () => {
    processTime = setInterval(() => {
      getVideoProcess().then(res => {
        setVideoProcess(res.data)
      })
    }, 1000)
  }
  /**
   * 上传习题
   */
  const uploadExamine = () => {
    To("../topic/topic?chapterId=" + routerParams.chapterId + "&current=0")
  }

  const ProcessChilrenData = (value) => {
    setVideoProcessModalOpen(value)
  }
  const [videoSrc, setVideoSrc] = useState()
  const [videoProster, setVideoProster] = useState()
  const [examineList, setExamineList] = useState([])
  const [teacherList, setTeacherList] = useState([])
  const tabList = [
    { title: '课程简介', component: <Introduction teacherList={teacherList}/> },
    { title: '课程资料', component: <Details  detailsList={detailsList}/> },
    { title: '课程习题', component: <ExamineList chapterId={routerParams.chapterId} examineList={examineList} /> },
    { title: '留言讨论', component: <Message chapterId={routerParams.chapterId} /> }]
  const selectList = ["上传视频", "上传习题"]
  return (
    <View className="Index">
      <AtFab onClick={() => {
        setActionOpen(true)
      }} className="btn">
        <Text className='at-fab__icon at-icon at-icon-menu'></Text>
      </AtFab>
      <View className="video_box">
        <View className='components-page'>
          <VideoView
            src={videoSrc}
            proster={videoProster}
            showProgress={true}
            title={routerParams.name} />
        </View>
      </View>
      <View className="tabs_box">
        <View className="tabs_container">
          <AtTabs tabDirection="horizontal" current={current} tabList={tabList} onClick={handleChangeCurrent.bind(this)}>
            {
              tabList.map((item, index) => {
                return (
                  <AtTabsPane current={current} index={index}>
                    <View className="tabs_container_box">{item.component}</View>
                  </AtTabsPane>
                )
              })
            }
          </AtTabs>
          <View>
          <AtActionSheet isOpened={actionOpen} onClose={() => setActionOpen(false)} onCancel={() => {
            setActionOpen(false)
          }} cancelText='取消' title='上传视频或者习题'>
            {
              selectList.map((item, index) => {
                return (
                  <AtActionSheetItem onClick={handleSelect.bind(this, index)}>
                    {item}
                  </AtActionSheetItem>
                )
              })
            }
          </AtActionSheet>
        </View>
        <VideoProcessModal
          videoProcessModalOpen={videoProcessModalOpen}
          ProcessChilrenData={ProcessChilrenData}
          videoProcess={videoProcess}>
        </VideoProcessModal>
        </View>
      </View>
    </View>
  )
}

export default EditChapter