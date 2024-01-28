import { View, Text, Button } from "@tarojs/components"
import { useCallback, useEffect, useState } from "react"
import { AtInput, AtButton, AtFab } from 'taro-ui'
import { useRouter } from "@tarojs/taro"
import { getMessage, saveMessage } from "../../../utils/interfact"


const Message = (props) => {
  const [replyId, setReplyId] = useState()
  const [tip, setTip] = useState("请留言")
  const [message, setMessage] = useState([])
  const [messageValue, setMessageValue] = useState()

  const getMessageList = useCallback(() => {
    getMessage(props.chapterId).then(res => {
      if (!res) {
        return
      }
      setMessage(res.data)
    })
  }, [props.chapterId])
  useEffect(() => {
    getMessage(props.chapterId).then(res => {
      if (!res) {
        return
      }
      setMessage(res.data)
    })
  }, [])
  const handleReply = (id, name) => {
    console.log(id);
    setReplyId(id)
    setTip("@" + name)
  }
  const handleSaveMessage = () => {
    /*
        private String value;
        private Integer pid;
        private Integer chapterId;
    */
    let message = {
      value: messageValue,
      pid: replyId,
      chapterId: props.chapterId
    }
    saveMessage(message).then(res => {
      console.log(res);
      getMessageList()
    })
  }
  return (
    <View className="message_box">
      {
        message.length == 0 ? ""
          :
          message.map(item => {
            return (
              <View onClick={handleReply.bind(this, item.id, item.name)} className="message">
                <View className="message_details">
                  <View className="nickname">{item.name} <Text className="time">{item.time}</Text></View>
                  <View className="message_container">{item.message}</View>

                </View>
                {
                  (item.children || []).map(itemNode => {
                    return (
                      <View className="message_reply">
                        <View className="nickname">{itemNode.name} <Text className="time">{itemNode.time}</Text></View>
                        <View className="message_container">{itemNode.message}</View>
                      </View>
                    )
                  })

                }
              </View>
            )

          })
      }
      <View className="input_box">
        <View className="input">
          <AtInput
            name='value'
            type='text'
            placeholder={tip}
            onChange={(value) => setMessageValue(value)}
            value={messageValue}
          >
          </AtInput>
        </View>
        <View className="send">
          <AtFab onClick={handleSaveMessage}>
            <Text className='at-fab__icon at-icon at-icon-message'></Text>
          </AtFab>
        </View>
      </View>

    </View>
  )
}

export default Message