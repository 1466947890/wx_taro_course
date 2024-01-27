import { Card, List } from "antd"
import { Upload, Button } from "antd"
import { UploadOutlined } from "@ant-design/icons"
import config from "../../config"
import api from "../../constants/api"
import { useParams } from "react-router-dom"
import { useSelector } from "react-redux"
import { useEffect, useState } from "react"
import { getDetails } from "../../utils/interface"

const CourseDetails = () => {




  let token = useSelector((state) => {
    return state.loginTokenReducer.token
  })
  let { courseId } = useParams()

  const [detailsList, setDetailsList] = useState([])

  useEffect(() => {
    getDetails(courseId).then(res => {
      console.log(res.data);
      setDetailsList(res.data)
    })
  }, [courseId])
  return (
    <div>
      <Card
        title="课程资料"
        extra={
          <Upload
            accept=".ppt, .pptx"
            withCredentials={true}
            headers={{
              token
            }}
            action={config.baseUrl + api.upload + courseId}
          >
            <Button type="primary" icon={<UploadOutlined />}>上传资料</Button>
          </Upload>
        }
      >
        <List
          // header={<div>资料</div>}
          bordered
          dataSource={detailsList}
          renderItem={(item) => (
            <List.Item>
              <List.Item.Meta
                title={<a href={item.url} target="_blank" rel='noopener noreferrer'>{item.name}</a>}
              />
            </List.Item>
          )}
        />
      </Card>
    </div>
  )
}

export default CourseDetails