import { View, Text } from "@tarojs/components"
import { useState } from "react"
import { useRouter } from "@tarojs/taro"
import { AtNavBar, AtTabs, AtTabsPane, AtGrid } from 'taro-ui'
import ChapterList from "./children/chapter"
import Details from "./children/details"
import './course_student.scss'
import { To } from "../../utils/navigato"


const CourseSInfo = () => {
  let router = useRouter()
  let routerParams = router.params
  const [current, setCurrent] = useState()
  const handleNavBarClick = () => {

  }

  const handleTabsClick = (value) => {
    setCurrent(value)
  }

  const tabList = [{ title: '章节' }, { title: '资料' }]

  const handleTo = (value) => {
    if(value.path) To(value.path)
  }
  return (
    <View className="Index">
      <AtNavBar
        color='#000'
      >
        <Text>{routerParams.name}</Text>
      </AtNavBar>
      <AtGrid
      columnNum={2}
      onClick={(value)=> {handleTo(value)}}
      data={
        [
          {
            image: 'https://img12.360buyimg.com/jdphoto/s72x72_jfs/t6160/14/2008729947/2754/7d512a86/595c3aeeNa89ddf71.png',
            value: '章节',
            path: "../chapter_list/chapter_list?courseId=" + routerParams.courseId
          },
          {
            image: 'https://img20.360buyimg.com/jdphoto/s72x72_jfs/t15151/308/1012305375/2300/536ee6ef/5a411466N040a074b.png',
            value: '成绩'
          },
          // {
          //   image: 'https://img10.360buyimg.com/jdphoto/s72x72_jfs/t5872/209/5240187906/2872/8fa98cd/595c3b2aN4155b931.png',
          //   value: '待开发'
          // },
          // {
          //   image: 'https://img12.360buyimg.com/jdphoto/s72x72_jfs/t10660/330/203667368/1672/801735d7/59c85643N31e68303.png',
          //   value: '待开发'
          // },
          // {
          //   image: 'https://img14.360buyimg.com/jdphoto/s72x72_jfs/t17251/336/1311038817/3177/72595a07/5ac44618Na1db7b09.png',
          //   value: '待开发'
          // },
          // {
          //   image: 'https://img30.360buyimg.com/jdphoto/s72x72_jfs/t5770/97/5184449507/2423/294d5f95/595c3b4dNbc6bc95d.png',
          //   value: '待开发'
          // }
        ]
      } />
      {/* <AtTabs className="atTabs" current={current} tabList={tabList} onClick={handleTabsClick}>
        <AtTabsPane current={current} index={0} >
          <ChapterList></ChapterList>
        </AtTabsPane>
        <AtTabsPane current={current} index={1}>
          <Details></Details>
        </AtTabsPane>
      </AtTabs> */}
    </View>
  )
}

export default CourseSInfo