import { View, Text, Image } from "@tarojs/components"
import { AtAvatar } from 'taro-ui'


const Introduction = (props) => {
  let { teacherList } = props
  return (

    <View className="introdu_box">
      {
        teacherList.length == 0 ? "" : teacherList.map(item => {
          return (
            <View>
              <View className="avatar_box">
                <Image 
                style={{height: "120px", width: "120px"}}
                className="avatar" src={item.avatar}></Image>
                <Text>教师信息：{item.information}</Text>
              </View>
              <View className="experience">
                <Text>教育经历：{item.experience}</Text>
              </View>
            </View>
          )
        })
      }
    </View>
  )
}

export default Introduction