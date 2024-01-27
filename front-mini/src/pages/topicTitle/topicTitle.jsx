import { View, Form } from "@tarojs/components"
import { useRouter } from "@tarojs/taro"
import { AtButton, AtInput } from "taro-ui"
import Taro from "@tarojs/taro"
import "./topicTitle.scss"
import { useState } from "react"
import { To, redirectTo } from "../../utils/navigato"

// 确定题目名称
/*
  最终思路，每次编辑题目后，直接提交题目到后台记录题库

*/
const TopicTitle = () => {
  let router = useRouter()
  let routerParams = router.params
  const [value, setValue] = useState()
  const formSubmit = (e) => {
    console.log(e.detail.value);
    // 缓存题目方法，把所有题目创建成一个数组
    Taro.setStorageSync("topic", {
      topicId: routerParams.id,
      title: e.detail.value.title,
      topic: []
    })
    // if(!Taro.getStorageSync("topic")){
    //   Taro.setStorageSync("topic", [])
    // }

    // let topic = {
    //   topicId: routerParams.id,
    //   title: e.detail.value.title,
    //   topic: []
    // }
    // let _topic = Taro.getStorageSync("topic")
    // _topic.push(topic)
    // console.log(_topic);

    // Taro.setStorageSync("topic", _topic)
    
    To("../topic/topic?chapterId=" + routerParams.chapterId)
  }
  const formReset = () => {

  }
  const handleChange = (value) => {
    setValue(value)
  }
  return (
    <View className="Index">
      <View className="form_box">
        <Form onSubmit={formSubmit} onReset={formReset} className="form">
          <AtInput
            name='title'
            title='标题'
            type='text'
            placeholder={"测验" + routerParams.chapterId}
            value={value}
            onChange={handleChange.bind(this)}
          />
          <View className="btn_box">
            <AtButton className="btn btn_left" formType='reset'>取消</AtButton>
            <AtButton className="btn" formType='submit' type="primary">下一步</AtButton>
          </View>
        </Form>
      </View>

    </View>
  )
}

export default TopicTitle