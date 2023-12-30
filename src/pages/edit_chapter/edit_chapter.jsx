import { View } from "@tarojs/components"
import { useState } from "react"
import { Cell, Video } from "@nutui/nutui-react-taro"
import './edit_chapter.scss'

const EditChapterT = () => {
  const [current, setCurrent] = useState(0)
  const [source, setSource] = useState({
    src: 'https://storage.360buyimg.com/nutui/video/video_NutUI.mp4',
    type: 'video/mp4',
  })
  const handleClick = (value) => {
    setCurrent(value)
  }
  const tabList = [{ title: '标签页1' }, { title: '标签页2' }, { title: '标签页3' }]
  return (
    <View className="Index">
      {/* 视频播放器 */}
      <View className="video_box">
        <Cell className='cell'>
          <Video
            source={source}
            // options={options}
            // onPlay={play}
            // onPause={pause}
            // onPlayEnd={playend}
            style={{ height: '163px' }}
          />
        </Cell>
      </View>
      {/* 切换菜单栏 */}
      <View className="tabs_container">

      </View>
    </View>
  )
}

export default EditChapterT