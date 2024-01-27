import { useCallback, useEffect, useState } from "react"
import { changeIsReal, userList } from "../../utils/interface"
import { Card, Table, Image, Button, Input, Select, message } from "antd"
import { UpdateUserModal } from "./user/user_modal"
import "./css/user.scss"

const { Search } = Input;

const options = [
  {
    value: '-1',
    label: '未认证',
  },
  {
    value: '0',
    label: '待审核',
  },
  {
    value: '1',
    label: '已认证',
  },
]

const AdminUser = () => {
  const [pageNum, setPageNum] = useState(1)
  const [pageSize, setPageSize] = useState(5)
  const [isReal, setIsReal] = useState(-2) 
  const [loading, setLoading] = useState(true)
  const [name, setName] = useState()
  const [total, setTotal] = useState()
  const [dataSource, setDataSource] = useState([])
  const [messageApi, contextHoler] = message.useMessage()

  const [updateUserOpen, setUpdateUserOpen] = useState(false)
  const [userInfo, setUserInfo] = useState()

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '姓名',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '性别',
      dataIndex: 'sex',
      key: 'sex',
    },
    {
      title: '微信昵称',
      dataIndex: 'nickname',
      key: 'nickname',
    },
    {
      title: '专业',
      dataIndex: 'major',
      key: 'major',
    },
    {
      title: '电话号码',
      dataIndex: 'phone',
      key: 'phone',
    },
    {
      title: '头像',
      dataIndex: 'avatar',
      key: 'avatar',
      align: "center",
      render: (_, record) => {
        return (
          dataSource.length >= 1 ? (<Image
            width={70}
            src={record.avatar}
          />) : null
        )
      }
    },
    {
      title: '角色',
      dataIndex: 'role',
      key: 'role',
      render: (_, record) => {
        return (
          record.role === "ROLE_USER" ? "普通用户" : "管理员"
        )
      }
    },
    {
      title: '认证状态',
      dataIndex: 'isReal',
      key: 'isReal',
      align: "center",
      render: (_, record) => {
        let isReal = ""
        switch (record.isReal) {
          case -1: isReal = "未认证"
            break;
          case 0: isReal = "待审核"
            break;
          case 1: isReal = "已认证"
            break;
          default:
            isReal = "未认证"
        }
        return (
          isReal
        )
      }
    },
    {
      title: '注册时间',
      dataIndex: 'registerTime',
      key: 'registerTime',
    },
    {
      title: '操作',
      dataIndex: 'opration',
      key: 'opration',
      render: (_, record) => {
        return (
          <div className="table_btn_box">
            {
               (record.isReal === 1 &&  <Button onClick={() => handleChangIsReal(record.id, -1)}>打回认证</Button>)
            }
            {
               (record.isReal === 0 &&  <Button onClick={() => handleChangIsReal(record.id, 1)}>审核通过</Button>)
            }
            <Button type="primary" onClick={() => handleEditOpen(record)}>编辑</Button>
            <Button type="default" danger>删除</Button>
          </div>
        )
      }
    },
  ];

  const getData = useCallback(() => {
    userList({
      pageNum,
      pageSize,
      name,
      isReal,
    }).then(res => {
      setDataSource(res.data.records)
      setTotal(res.data.total)
      setLoading(false)
    })
  }, [pageNum, pageSize, name, isReal])


  useEffect(() => {
    getData()
  }, [getData])

  const paginationChange = (pageNum, pageSize) => {
    setPageNum(pageNum)
    setPageSize(pageSize)
  }

  const handleChangIsReal = (id, isReal) => {
    changeIsReal(id, isReal).then(res => {
      messageApi.success(res.msg)
      getData()
    })
  }
  const handleEditOpen = (record) => {
    setUserInfo(record)
    // console.log(record);
    setUpdateUserOpen(true)
  }

  const getModalMsg = (msg) => {
    setUpdateUserOpen(msg.open)
  }

  return (
    <div>
      <Card className="card_table_box">
        {contextHoler}
        <Select
          className="select_box"
          onChange={(value) => setIsReal(value)}
          placeholder="请选择认证状态"
          style={{
            width: 150,
          }}
          options={options}
        />
        <Search
          maxLength={5}
          className="search_box"
          placeholder="姓名"
          allowClear
          enterButton="查找用户"
          size="middle"
          onSearch={(value) => setName(value)}
        />
        <Table
          className="table_box"
          loading={loading}
          rowKey="id"
          bordered
          dataSource={dataSource}
          columns={columns}
          pagination={{
            defaultCurrent: 1,
            current: pageNum,
            defaultPageSize: pageSize,
            pageSize: pageSize,
            total: total,
            onChange: (pageNum, pageSize) => paginationChange(pageNum, pageSize)
          }} />
      </Card>
      <UpdateUserModal open={updateUserOpen} getOpen={getModalMsg} userInfo={userInfo}></UpdateUserModal>
    </div>
  )
}

export default AdminUser