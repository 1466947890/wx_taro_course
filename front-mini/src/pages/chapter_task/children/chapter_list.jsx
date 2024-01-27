import { View } from "@tarojs/components"
import { AtList, AtListItem } from "taro-ui"

const ChapterList = () => {
  return (
    <View>
      <AtList>
        <AtListItem title='标题文字'  />
        <AtListItem title='标题文字'  />
        <AtListItem title='标题文字'  />
        <AtListItem title='标题文字'  />
        <AtListItem title='标题文字' />
      </AtList>
    </View>
  )
}

export default ChapterList