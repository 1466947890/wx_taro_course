import { View } from "@tarojs/components"
import { useRouter } from "@tarojs/taro"
import { AtNavBar, AtButton, AtGrid  } from 'taro-ui'
import { To } from "../../utils/navigato"
import './courseTInfo.scss'

const CourseTInfo = () => {
  const router = useRouter()
  let routerParams = router.params
  const hanldeSetting = () => {

  }
  const hanleEditChapter = () => {
    To("../editchapterlist/editchapterlist?id=" + routerParams.id + "&name=" + routerParams.name)
  }
  const handleSelect = (value) => {
    To(value.path)
  }
  return (
    <View className="Index">
      <AtNavBar
        onClickRgIconSt={hanldeSetting}
        title={routerParams.name}
        rightFirstIconType='settings'
      />
      <AtGrid onClick={handleSelect.bind(this)} data={
        [
          {
            image: 'https://img12.360buyimg.com/jdphoto/s72x72_jfs/t6160/14/2008729947/2754/7d512a86/595c3aeeNa89ddf71.png',
            value: '章节',
            path: "../editchapterlist/editchapterlist?id=" + routerParams.courseId + "&name=" + routerParams.name
          },
          {
            image: 'https://img20.360buyimg.com/jdphoto/s72x72_jfs/t15151/308/1012305375/2300/536ee6ef/5a411466N040a074b.png',
            value: '资料'
          },
          {
            image: 'https://img10.360buyimg.com/jdphoto/s72x72_jfs/t5872/209/5240187906/2872/8fa98cd/595c3b2aN4155b931.png',
            value: '留言'
          },
          {
            image: 'https://img12.360buyimg.com/jdphoto/s72x72_jfs/t10660/330/203667368/1672/801735d7/59c85643N31e68303.png',
            value: '教师资料'
          },
          {
            image: 'https://img14.360buyimg.com/jdphoto/s72x72_jfs/t17251/336/1311038817/3177/72595a07/5ac44618Na1db7b09.png',
            value: '邀请教师'
          },
          {
            image: 'https://img30.360buyimg.com/jdphoto/s72x72_jfs/t5770/97/5184449507/2423/294d5f95/595c3b4dNbc6bc95d.png',
            value: '邀请学生'
          }
        ]
      } />

    </View>
  )
}

export default CourseTInfo