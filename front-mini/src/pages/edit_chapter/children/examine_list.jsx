import { View, Text } from "@tarojs/components"
import { useEffect, useState } from "react";
import { AtRadio, AtLoadMore, AtButton } from 'taro-ui'
import { letter } from "../../../utils/StriUtils";
import store from "../../../store/toolkitIndex";
/*
  接收题型数据，渲染选项

*/
const ExamineList = (props) => {
  let { examineList } = props
  const [answer, setAnswer] = useState([])
  const [current, setCurrent] = useState(0)
  const [options, setOption] = useState([])
  const [loadStatus, setLoadStatus] = useState("loading")
  // 判断是否到最后一题
  const [first, setFirst] = useState(true)
  const [last, setLast] = useState(false)
  const handleChange = (value) => {
    setAnswer(value)
  }
  let _option = []
  useEffect(() => {
    examineList.length == 0 ? setLoadStatus("noMore") : examineList[current].options.map((item, index) => {
      _option.push({
        label: item,
        value: letter[index]
      })
    })
    setOption(_option)
  })

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



  }, [current])

  const handleNext = () => {
    console.log(examineList[current].title);
    console.log(answer);
    setCurrent(current + 1)
    setAnswer()
  }
  return (
    <View className="examine_box">
      {examineList.length == 0 ? <AtLoadMore
        status={loadStatus}
      /> :
        <View>
          <View className="examine_type">
            {current + 1} .单选题
          </View>
          <View className="examine_title">
            <Text>{examineList[current].title}</Text>
          </View>

        </View>}
      <AtRadio
        options={options}
        value={answer}
        onClick={handleChange.bind(this)}
      />
                <View className="exmaine_btn_box">
            {
              first ? "" : <AtButton className="exmaine_btn" onClick={() => { setCurrent(current - 1) }} type='secondary'>上一题</AtButton>
            }
            {/* <AtButton className="exmaine_btn" onClick={handleNext} type='primary'>下一题</AtButton> */}
            {
              last ?
                // <AtButton className="exmaine_btn" onClick={handleNext} type='primary'>提交</AtButton> 
                ""
                :
                <AtButton className="exmaine_btn" onClick={handleNext} type='primary'>下一题</AtButton>
            }
          </View>

    </View>
  )
}

export default ExamineList