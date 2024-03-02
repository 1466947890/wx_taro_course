import { View, ScrollView, Text, Button } from "@tarojs/components"
import { useState, useEffect } from "react"
import ExamineList from "./children/examine_list"
import Details from "./children/details"
import Introduction from "./children/introduction"
import Message from "./children/message"
import { getChapterTask, saveMessage } from "../../utils/interfact"
import { useRouter } from "@tarojs/taro"
import VideoView from "../../component/video"
import { AtTabs, AtTabsPane, AtFab, AtModal, AtModalHeader, AtModalContent, AtModalAction, AtForm, AtTextarea } from "taro-ui"
import { To } from "../../utils/navigato"
import Taro from "@tarojs/taro"
import './chapter_task.scss'
import { ToastSuccess } from "../../utils/toast"

const ChapterTask = () => {
  let router = useRouter()
  let routerParams = router.params

  if (!routerParams.chapterId) {
    routerParams.chapterId = 100000
    routerParams.courseId = 100004
  }

  let {chapterId} = routerParams

  const [videoSrc, setVideoSrc] = useState()
  const [videoDuration, setVideoDuration] = useState()
  const [current, setCurrent] = useState(0)
  const [videoProster, setVideoProster] = useState()
  const [examineList, setExamineList] = useState([])
  const [detailsList, setDetailsList] = useState([])
  const [teacherList, setTeacherList] = useState([])
  const [videoHeight, setVideoHeight] = useState()
  const [tabsHeaderHeight, setTabsHeaderHeight] = useState()

  // 添加留言
  const [messageOpen, setMessageOpen] = useState(false)
  const [messageText, setMessageText] = useState()
  // 发送时机
  const [messageRandom, setMessageRandom] = useState(1)
  const [replyId, setReplyId] = useState()
  const [replyUser, setReplyUser] = useState()
  const [messageTip, setMessageTip] = useState("请留言")
  const getChildMessage = (value) => {
    let { open, replyId, replyUser} = value
    setMessageOpen(open)
    setReplyId(replyId)
    // setReplyUser(replyUser)
    if(replyId){
      setMessageTip("回复 @ " + replyUser)
    }else{
      setMessageTip("请留言")
    }
  }
  const handleSendMessage = () => {
    let message = {
      value: messageText,
      pid: replyId,
      chapterId: chapterId
    }
    saveMessage(message).then(res => {
      setMessageText()
      ToastSuccess(res.msg)
      setMessageRandom(Math.random())
      setMessageOpen(false)
    })
  }
  const tabList = [
    { title: '课程简介', component: <Introduction teacherList={teacherList} /> },
    { title: '课程资料', component: <Details detailsList={detailsList} /> },
    { title: '课程习题', component: <ExamineList chapterId={routerParams.chapterId} examineList={examineList} /> },
    { title: '留言讨论', component: <Message chapterId={routerParams.chapterId} messageRandom={messageRandom} getMessageData={getChildMessage} /> }]




  useEffect(() => {
    let { courseId } = routerParams
    const getData = async () => await getChapterTask(courseId, routerParams.chapterId)
    getData().then(res => {
      setVideoDuration(parseInt(res.data.videoInfo.duration))
      setVideoSrc(res.data.videoInfo.url)
      setDetailsList(res.data.detailsList)
      setExamineList(res.data.examineList)
      setTeacherList(res.data.teacherList)
    })
    getElementHeight(".at-tabs__header")
    getElementHeight(".video_box")
  }, [])

  const getWindowHeight = () => {
    return Taro.getSystemInfoSync().windowHeight
  }

  const getElementHeight = (className) => {
    const query = Taro.createSelectorQuery()
    query.select(className).boundingClientRect()
    query.exec((res) => {
      if (res && res[0]) {
        className == ".video_box" ? setVideoHeight(res[0].height) : setTabsHeaderHeight(res[0].height)
      }
    })
  }
  const tabsHeight = () => {
    return getWindowHeight() - videoHeight - tabsHeaderHeight - 30
  }


  const handleChangeCurrent = (value) => {
    setCurrent(value)
  }


  return (
    <View className="Index">
      <View className="video_box">
        <View className='components-page'>
          <VideoView
            chapterId={routerParams.chapterId}
            videoDuration={videoDuration}
            src={videoSrc}
            proster={videoProster}
            showProgress={false}
            title={routerParams.name} />
        </View>
      </View>

      <View className="tabs_box">
        <View className="tabs_container">
          <AtTabs className="tabs" tabDirection="horizontal" current={current} tabList={tabList} onClick={handleChangeCurrent.bind(this)}>
            {
              tabList.map((item, index) => {
                return (
                  <AtTabsPane className="tabs_panl" current={current} index={index}>
                    {/* {item.component} */}
                    <ScrollView
                      style={{ height: tabsHeight() + "px" }} scrollY={true}
                      className="tabs_container_box">{item.component}</ScrollView>
                  </AtTabsPane>
                )
              })
            }
          </AtTabs>
          <View>
          </View>
        </View>
      </View>
      {/* <View className="fab_box">
        <AtFab onClick={() => { To("../message/message?chapterId=" + routerParams.chapterId) }}>
          <Text className='at-fab__icon at-icon at-icon-file-new'></Text>
        </AtFab>
      </View> */}
      {/* 留言对话框 */}
      <View>
        <AtModal isOpened={messageOpen}>
          <AtModalHeader>请留言</AtModalHeader>
          <AtModalContent>
            <AtForm
            >
              <AtTextarea
                maxLength={200}
                value={messageText}
                placeholder={messageTip}
                onChange={(value) => { setMessageText(value) }}
              />
            </AtForm>
          </AtModalContent>
          <AtModalAction> <Button onClick={() => {
            setMessageOpen(false)
            setMessageText()
            setReplyId()
          }}>取消</Button> <Button onClick={handleSendMessage}>确定</Button> </AtModalAction>
        </AtModal>
      </View>
      <View>

      </View>
    </View>
  )
}

export default ChapterTask