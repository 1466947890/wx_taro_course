import { Table, Card } from "antd"
import { useEffect, useState } from "react";
import { getMajorPage } from "../../utils/interface";

const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '专业名称',
    dataIndex: 'name',
    key: 'name',
  },
];






const AdminMajor = () => {
  const [dataSource, setDataSource] = useState()
  useEffect(() => {
    getMajorPage({pageNum: 1, pageSize: 2, name: ""}).then(res => {
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

export default AdminMajor