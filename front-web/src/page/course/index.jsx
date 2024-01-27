import { useEffect, useState } from "react"
import { Card, List } from "antd"
import { NavLink, useParams } from "react-router-dom"
import { getChapter } from "../../utils/interface"

const CourseIndex = () => {
  let { courseId } = useParams()
  const [chapterList, setChapterList] = useState([])

  useEffect(() => {
    getChapter(courseId).then(res => {
      setChapterList(res.data)
    })
  }, [courseId])

  return (
    <div className="course_chapter">
      <Card
      >
        <List
          header={<div>目录</div>}
          bordered
          dataSource={chapterList}
          renderItem={(item) => (
            <List.Item>
              <List.Item.Meta
              title={<NavLink to={"/course/" + courseId + "/" + item.id}>{item.name}</NavLink>  }
              />
            </List.Item>
          )}
        />
      </Card>
    </div>
  )
}

export default CourseIndex