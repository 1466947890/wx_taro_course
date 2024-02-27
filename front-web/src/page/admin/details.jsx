import { Table, Card } from "antd"
import { useEffect, useState } from "react";
import { getDetailsPage } from "../../utils/interface";

const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '资料文件名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '资料文件地址',
    dataIndex: 'url',
    key: 'url',
  },
];


const AdminDetails = () => {

  const [dataSource, setDataSource] = useState()
  useEffect(() => {
    getDetailsPage({ pageNum: 1, pageSize: 2, isReal: 1 }).then(res => {
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

export default AdminDetails