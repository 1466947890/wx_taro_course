import { View } from "@tarojs/components"
import { useState, useEffect } from "react"
import { getChapter } from "../../utils/interfact"
import { useRouter } from "@tarojs/taro"
import { AtList, AtListItem } from "taro-ui"
import { To } from "../../utils/navigato"



const ChapterList = () => {
  let router = useRouter()
  let routerParams = router.params
  const [chapterList, setChapterList] = useState([])

  const handleClick = (value) => {
    To("../chapter_task/chapter_task?courseId=" + routerParams.courseId +  "&chapterId=" + value)
  }
  useEffect(() => {
    let chapterList = getChapter(routerParams.courseId) 
    chapterList.then(res => {
      if (res.code === 200) {
        setChapterList(res.data)
      }
    })
  }, [])
  return (
    <View className="Index">
      <AtList>
        {
          !chapterList ? "" : chapterList.map(item => {
            return (

              <AtListItem title={item.name} onClick={handleClick.bind(this, item.id)} iconInfo={{ size: 25, color: '#E6B00F', value: 'clock', }} >
              </AtListItem>

            )
          })
        }
      </AtList>
    </View>
  )
}

export default ChapterList