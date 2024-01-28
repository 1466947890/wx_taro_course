import { View, Button, Text } from "@tarojs/components"
import {
  AtList,
  AtListItem,
  AtLoadMore,
  AtInput,
  AtNavBar,
  AtSwipeAction,
  AtModal,
  AtModalHeader,
  AtModalContent,
  AtModalAction,
  AtFab
} from "taro-ui"
import './edit_chapter_list.scss'
import { useRouter } from "@tarojs/taro"
import { useEffect, useState } from "react"
import { deleteChapter, getChapter, saveChapter } from "../../utils/interfact"
import { ToastError, ToastSuccess } from "../../utils/toast"
import { To } from "../../utils/navigato"
import Taro from "@tarojs/taro"




// 点击进入章节
const handleClick = (id, value, chapterName) => {
  To("../edit_chapter/edit_chapter?courseId=" + id  +"&chapterId=" + value + "&name=" + chapterName)
}

// 滑动单元或者章节
const handleSingle = (value) => {
  console.log(value);
}

const options = [
  {
    text: '删除',
    style: {
      backgroundColor: '#FF4949'
    }
  }
]
const editChapterList = () => {
  // 进这个章节之前先清理一次题目缓存
  Taro.removeStorageSync("topic")
  const router = useRouter()
  // 列表更新标记
  const [updataId, setUpdataId] = useState(0)
  const [saveChapterOpen, setSaveChapterOpen] = useState(false)
  const [chapterName, setChapterName] = useState()
  const [chapterList, setChapterList] = useState([])
  const [loadStatus, setLoadStatus] = useState("loading")
  useEffect(() => {
    console.log(routerParams);
    let chapterList = getChapter(routerParams.id)
    chapterList.then(res => {
      if (res.code === 200) {
        setChapterList(res.data)
        if(res.data.length == 0){
          setLoadStatus("noMore")
        }
      }
    })
  }, [updataId])
  // 保存章节
  const handleSaveChapter = () => {
    // 弹窗
    setSaveChapterOpen(true)
    setChapterName()
  }
  // 记录章节标题变化
  const handleChapterNameChange = (value) => {
    setChapterName(value)
  }
  // 提交章节名称
  const handleSubmit = () => {
    let params = {
      courseId: parseInt(routerParams.id),
      name: chapterName
    }
    saveChapter(params).then(res => {
      if (res.code === 200) {
        setUpdataId(updataId + 1)
        ToastSuccess(res.msg)
      } else {
        ToastError(res.msg)
      }
    })
  }
  const handleDelete = (value) => {
    // 删除单元或者章节
    deleteChapter(value)
    setUpdataId(updataId - 1)
  }
  let routerParams = router.params


  return (
    <View className="Index">
      <AtNavBar
        color='#000'
        title={routerParams.name}
      >
      </AtNavBar>
      <View className="list">
        <AtList>
          {
            chapterList.length == 0 ?
              <AtLoadMore
                status={loadStatus}
              />
              :
              chapterList.map(item => {
                return (
                  <AtSwipeAction
                    key={item.chapterId}
                    onOpened={handleSingle.bind(this, item.id)}
                    isOpened={false}
                    options={options}
                    onClick={handleDelete.bind(this, item.id)}
                  >
                    {
                      <AtListItem title={item.name} onClick={handleClick.bind(this,routerParams.id, item.id, item.name)} iconInfo={{ size: 25, color: '#E6B00F', value: 'clock', }} >
                      </AtListItem>
                    }
                  </AtSwipeAction>
                )
              })
          }
        </AtList>
      </View>
      <View className="btn_box">
        {/* <AtButton className="btn" onClick={handleSaveChapter} type='primary'>新建章节</AtButton> */}
        <AtFab onClick={handleSaveChapter}>
          <Text className='at-fab__icon at-icon at-icon-add'></Text>
        </AtFab>
      </View>
      <View>
        <AtModal isOpened={saveChapterOpen}>
          <AtModalHeader>章节标题</AtModalHeader>
          <AtModalContent>
            <AtInput
              name='value'
              title='标题'
              type='text'
              placeholder='章节标题'
              value={chapterName}
              onChange={handleChapterNameChange.bind(this)}
            />
          </AtModalContent>
          <AtModalAction> <Button onClick={() => { setSaveChapterOpen(false) }}>取消</Button> <Button onClick={handleSubmit}>确定</Button> </AtModalAction>
        </AtModal>
      </View>
    </View>
  )
}
export default editChapterList