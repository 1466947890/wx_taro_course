import { Table, Card } from "antd"
import { useEffect, useState } from "react";
import { getVideoPage } from "../../utils/interface";

const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '唯一标识',
    dataIndex: 'flag',
    key: 'flag',
  },
  {
    title: '所属课程ID',
    dataIndex: 'chapterId',
    key: 'chapterId',
  },
  {
    title: '是否认证',
    dataIndex: 'isReal',
    key: 'isReal',
  },
];

const AdminVideo = () => {

  const [dataSource, setDataSource] = useState()
  useEffect(() => {
    getVideoPage({ pageNum: 1, pageSize: 2, isReal: 1 }).then(res => {
      setDataSource(res.data.records)
    })
  }, [])

  return (
    <div>
      <Card>
        <Table bordered dataSource={dataSource} columns={columns} />
      </Card>
    </div>
  )
}

export default AdminVideo