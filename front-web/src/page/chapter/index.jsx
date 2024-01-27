import { useEffect, useState } from "react"
import ReactPlayer from "react-player"
import { useParams, NavLink } from "react-router-dom"
import { UploadOutlined } from "@ant-design/icons"
import { getChapter, getChapterTask } from "../../utils/interface"
import api from "../../constants/api"
import HeaderComponet from "../../components/header_component"
import { Layout, Card, Button, List, Upload } from "antd"
import { useSelector } from "react-redux"
import "./css/index.scss"
import config from "../../config"
const { Sider, Content } = Layout
const ChapterIndex = () => {
  let { chapterId } = useParams()
  let { courseId } = useParams()
  let token = useSelector((state) => {
    return state.loginTokenReducer.token
  })
  // const [videoInfo, setVideoInfo] = useState()
  const [videoUrl, setVideoUrl] = useState()
  const [chapterList, setChapterList] = useState([])

  useEffect(() => {
    getChapterTask(courseId, chapterId).then(res => {
      setVideoUrl(res.data.videoInfo.url)
    })
  }, [courseId, chapterId])

  useEffect(() => {
    getChapter(courseId).then(res => {
      setChapterList(res.data)
    })
  }, [courseId])
  return (
    <div className="chapter_index">
      <Layout className="layout_box">
        <HeaderComponet></HeaderComponet>
        <Layout hasSider>
          <Content className="layout_content">
            <Card
              title="视频任务"
              extra={
                <Upload
                  accept="video/*"
                  withCredentials={true}
                  headers={{
                    token
                  }}
                  action={config.baseUrl + api.uploadVideo + "/" + chapterId}
                >
                  <Button type="primary" icon={<UploadOutlined />}>上传视频</Button>
                </Upload>
              }>
              <div className="video_box">
                <ReactPlayer muted={true} width="100%" height="100%" controls={true} url={videoUrl}></ReactPlayer>
              </div>
            </Card>
          </Content>
          <Sider className="layout_sider" theme="light">
            <List
              className="layout_list"
              header={<div>目录</div>}
              bordered
              dataSource={chapterList}
              renderItem={(item) => (
                <List.Item>
                  <List.Item.Meta
                    title={<NavLink to={"/course/" + courseId + "/" + item.id}>{item.name}</NavLink>}
                  />
                </List.Item>
              )}
            />
          </Sider>
        </Layout>
      </Layout>
    </div>
  )
}

export default ChapterIndex