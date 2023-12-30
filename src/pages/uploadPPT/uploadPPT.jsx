import { View, Text } from "@tarojs/components"
import { AtList, AtListItem, AtFab } from "taro-ui"
import "./uploadPPT.scss"

const UploadPPT = () => {
  return (
    <View className="Index">
      <View className="file_list_box">
        <AtList>
          <AtListItem title='标题文字' />
        </AtList>
      </View>
      <View className="btn_box">
        <AtFab>
          <Text className='at-fab__icon at-icon at-icon-upload'></Text>
        </AtFab>
      </View>
    </View>
  )
}

export default UploadPPT