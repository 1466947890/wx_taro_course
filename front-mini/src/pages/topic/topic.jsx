import { View, Form, Text, Picker } from "@tarojs/components"
import { AtForm, AtList, AtListItem, AtButton, AtTextarea, AtNavBar } from "taro-ui";
import { useState } from "react"
import Taro, { useRouter } from "@tarojs/taro";
import RadioTopic from "./children/RadioTopic";
import './topic.scss'
import { To, redirectTo } from "../../utils/navigato";
import { saveChapterTask, saveTopic } from "../../utils/interfact";
import { ToastError, ToastSuccess } from "../../utils/toast";
import { useSelector, useDispatch } from "react-redux";

let topicList = ['单选题', '多选题', '判断题', '填空题']
const Topic = () => {
  let router = useRouter()
  let routerParams = router.params
  let current = routerParams.current
  let dispatch = useDispatch()
  let topic = useSelector(state => {
    return state.uploadTopicReducer.topic
  })
  const [topicType, setTopicType] = useState(0);
  const [selectedType, setSelectedType] = useState("单选题")
  const [value, setValue] = useState()
  // const [current, setCurrent] = useState()
  // console.log(topic);

  // 父组件接收子组件的传值

  let params
  const getData = (value) => {
    params = value
  }

  // 改变题目类型
  const TopicTypeChange = (e) => {
    setTopicType(e.detail.value)
    setSelectedType(topicList[e.detail.value])
  }



  // 提交题目信息
  const formSubmit = () => {
    params.topicTitle = value
    params.current = current
    // console.log(params);
    dispatch({
      type: "uploadTopicSlice/changeTopic",
      payload: params
    })
    // setCurrent(current + 1)
    // let topic = Taro.getStorageSync("topic")
    // topic.topic.push(params)
    // topic[topic.length-1].topic.push(params)
    // Taro.setStorageSync("topic", topic)
    current ++;
    redirectTo("../topic/topic?chapterId=" + routerParams.chapterId + "&current=" + current)
  }
  // 重置题目信息
  const formReset = () => {

  }

  // 导航栏点击
  const handleClick = () => {

  }
  // 改变题目
  const handleChange = (value) => {
    setValue(value)
  }

  // 创建题目完成，返回章节页面
  const handleFinishTopic = () => {
    let topicObject = {}
    params.topicTitle = value
    // let topic = Taro.getStorageSync("topic")
    dispatch({
      type: "uploadTopicSlice/changeTopic",
      payload: params
    })
    let _topic = [...topic]
    console.log("当前页面为：" + current);
    _topic[current] = params
    topicObject.chapterId = routerParams.chapterId
    topicObject.topic = _topic

    console.log(topicObject);
    // Taro.setStorageSync("topic", topic)
    // 提交题目到题库

    saveTopic(topicObject).then(res => {
      if(res.code  === 200){
        ToastSuccess(res.msg)
        redirectTo("../editchapter/editChapter?chapterId=" + routerParams.chapterId)
      }else{
        ToastError(res.msg)
      }
    })
  }
  return (
    <View className="Index">
      <View>
        <AtNavBar
          color='#000'
        >
          <Text className="navbar_title">第{current + 1}题</Text><Picker mode='selector' range={topicList} onChange={TopicTypeChange}>
            <AtList>
              <AtListItem
                title='题目类型'
                extraText={selectedType}
              />
            </AtList>
          </Picker>
        </AtNavBar>
      </View>
      <AtForm
        onSubmit={formSubmit.bind(this)}
        onReset={formReset.bind(this)}
      >
        <AtTextarea
          name="topic_title"
          value={value}
          onChange={handleChange.bind(this)}
          maxLength={200}
          placeholder='题目'
        />

        {
          topicType == 0 ? <RadioTopic getData={getData}></RadioTopic> : ""
        }
        {
          topicType == 1 ? <RadioTopic getData={getData}></RadioTopic> : ""
        }
        <View className="btn_box">
          <AtButton className="btn" formType='submit' type="primary">继续创建下一个题</AtButton>
          <AtButton className="btn" onClick={handleFinishTopic}>完成创建</AtButton>
        </View>
      </AtForm>
    </View>
  )
}

export default Topic