import { View } from "@tarojs/components"
import { useEffect, useState } from "react"
import { useRouter } from "@tarojs/taro"
import { AtForm, AtInput, AtButton, AtTextarea } from 'taro-ui'
import { ToastSuccess } from "../../utils/toast"
import Taro from "@tarojs/taro"
import "./message.scss"
import { getNotes, saveNotes } from "../../utils/interfact"





const Message = () => {
  let {chapterId} = useRouter().params
  const [title, setTitle] = useState()
  const [content, setContent] = useState()

  useEffect(() => {
    getNotes(chapterId).then(res => {
      setTitle(res.data.title)
      setContent(res.data.content)
    })
  }, [])


  const onSubmit = () => {
    saveNotes({
      title, content, chapterId
    }).then(res => {
      ToastSuccess(res.msg)
    })
   }
   
  const handleChange = (type, value) => {
    if (type == "title") {
      setTitle(value)
    } else {
      setContent(value)
    }
  }
  return (
    <View className="Index">
      <View className="form_box">
        <AtForm
          onSubmit={onSubmit}
        >
          <AtInput
            name='title'
            title='标题'
            type='text'
            placeholder='标题'
            value={title}
            onChange={handleChange.bind(this, "title")}
          />
          <AtTextarea
            name="content"
            value={content}
            onChange={handleChange.bind(this, "content")}
            maxLength={400}
            placeholder='笔记内容'
          />
          <View className="btn_box">
            <AtButton className="btn" type="primary" formType='submit'>保存</AtButton>
            <AtButton className="btn" onClick={() => {Taro.navigateBack({
              delta: 1
            })}}>返回</AtButton>
          </View>
        </AtForm>
      </View>
    </View>
  )
}

export default Message