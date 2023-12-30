import { View, Text } from "@tarojs/components"
import { useEffect, useState } from "react";
import { AtRadio, AtLoadMore, AtButton } from 'taro-ui'
import { letter } from "../../../utils/StriUtils";
import { useSelector, useDispatch } from "react-redux";
import store from "../../../store/toolkitIndex";
import { getChapterExamineGrade } from "../../../utils/interfact";
/*
  接收题型数据，渲染选项

*/
const ExamineList = (props) => {
  let { examineList } = props
  let {chapterId} = props
  const [status, setStatus] = useState("loading")
  const [answer, setAnswer] = useState([])
  const [current, setCurrent] = useState(0)
  const [options, setOption] = useState([])
  // 判断是否到最后一题
  const [first, setFirst] = useState(true)
  const [last, setLast] = useState(false)
  const handleChange = (value) => {
    setAnswer(value)
  }
  // 取出state
  let answerTopic = useSelector((state) => {
    return state.answerReducer.answer
  })
  let dispatch = useDispatch()


  let _option = []
  useEffect(() => {
    examineList.length == 0 ? setStatus("noMore") : examineList[current].options.map((item, index) => {
      _option.push({
        label: item,
        value: letter[index]
      })
    })
    setOption(_option)
  }, [examineList.length])

  useEffect(() => {
    // 判断是否为第一题
    if (current == 0) {
      setFirst(true)
      setLast(false)
    } else {
      setFirst(false)
    }

    // 判断是否为最后一题
    if (current == examineList.length - 1) {
      setLast(true)
    } else {
      setLast(false)
    }


    if (answerTopic[current]) setAnswer(answerTopic[current].answer)

  }, [current, examineList.length])

  const handleNext = () => {
    let _topic = {
      answer,
      title: examineList[current].title,
      current
    }
    dispatch({
      type: "answerSlice/addAnswer",
      payload: _topic
    })
    setCurrent(current + 1)
    setAnswer()
  }

  const handleSubmit = () => {
    let _topic = {
      answer,
      title: examineList[current].title,
      current
    }
    dispatch({
      type: "answerSlice/addAnswer",
      payload: _topic
    })
    console.log("提交的数据为");
    let topic = [...answerTopic]
    if (topic.length == current) {
      topic.push(_topic)
    }
    console.log(topic);
    getChapterExamineGrade(chapterId, topic).then(res => {
      console.log(res);
    })
  }
  return (
    <View className="examine_box">
      {examineList.length == 0 ? <AtLoadMore
        status={status}
        noMoreText="没有习题"
      /> :
        <View>
          <View className="examine_type">
            {current + 1} .单选题
          </View>
          <View className="examine_title">
            <Text>{examineList[current].title}</Text>
          </View>
          <AtRadio
        options={options}
        value={answer}
        onClick={handleChange.bind(this)}
      />
      <View className="exmaine_btn_box">
        {
          first ? "" : <AtButton className="exmaine_btn" onClick={() => { setCurrent(current - 1) }} type='secondary'>上一题</AtButton>
        }
        {
          last ?
            <AtButton className="exmaine_btn" onClick={handleSubmit} type='primary'>提交</AtButton>
            :
            <AtButton className="exmaine_btn" onClick={handleNext} type='primary'>下一题</AtButton>
        }
      </View>

          
        </View>}
      

    </View>
  )
}

export default ExamineList