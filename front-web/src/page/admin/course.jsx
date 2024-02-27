import { Table, Card, Image } from "antd"
import { useEffect, useState } from "react";
import { getCoursePage } from "../../utils/interface";

const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '课程封面',
    dataIndex: 'image',
    
    render: (_, record) => {
      return <Image height='50px' src={record.image}></Image>
    }
  },
  {
    title: '专业名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '学分',
    dataIndex: 'credit',
    key: 'credit',
  },
];



const AdminCourse = () => {
  const [dataSource, setDataSource] = useState()

  useEffect(() => {
    getCoursePage({pageNum: 1, pageSize: 2, name: ""}).then(res => {
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

export default AdminCourse