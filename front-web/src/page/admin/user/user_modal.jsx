import { Modal, Form, Input, Select } from "antd"
import { useEffect, useState } from "react"
import { getMajorRole } from "../../../utils/interface"


const sexOption = [
  {
    value: '男',
    label: '男',
  },
  {
    value: '女',
    label: '女',
  },
  {
    value: '不详',
    label: '不详',
  },
]


export const UpdateUserModal = (props) => {
  let { getOpen } = props
  let { userInfo } = props
  const [form] = Form.useForm()


  const [majorOption, setMajorOption] = useState([])
  const [roleOption, setRoleOption] = useState([])
  useEffect(() => {
    if (userInfo) {
      getMajorRole().then(res => {
        let majors = []
        let _majors = res.data.majors
        _majors.forEach((item) => {
          majors.push({label: item.name, value: item.id})
        })
        setMajorOption(majors)

        let roles = []
        let _roles = res.data.roles
        _roles.forEach((item) => {
          roles.push({label: item.name, value: item.flag})
        })
        setRoleOption(roles)
      })
      form.setFieldsValue(userInfo)
    }
  }, [form, userInfo])

  const handleCancel = () => {
    getOpen({
      open: false
    })
  }
  const handleOk = () => {
    // console.log(11);
    form.submit()
  }
  const onFinish = (values) => {
  }
  return (
    <Modal
      onOk={handleOk}
      open={props.open}
      onCancel={handleCancel}
      okText="保存"
      cancelText="取消"
      title="编辑信息">
      <Form
        onFinish={onFinish}
        layout="vertical"
        form={form}>
        <Form.Item
          label='ID'
          name="id"
          rules={
            [{ required: true, message: "请输入ID" }]
          }>
          <Input disabled />
        </Form.Item>
        <Form.Item
          label='姓名'
          name="name"
          rules={
            [{ required: true, message: "请输入姓名" }]
          }>
          <Input />
        </Form.Item>
        <Form.Item
          label='性别'
          name="sex"
          rules={
            [{ required: true, message: "请输入性别" }]
          }>
          <Select
            style={{
              width: 200,
            }}
            options={sexOption}
          />
        </Form.Item>
        <Form.Item
          label='微信昵称'
          name="nickname"
          rules={
            [{ required: true, message: "请输入微信昵称" }]
          }>
          <Input />
        </Form.Item>
        <Form.Item
          label='专业'
          name="major"
          rules={
            [{ required: true, message: "请输入专业" }]
          }>
          <Select
            style={{
              width: 200,
            }}
            options={majorOption}
          />
        </Form.Item>
        <Form.Item
          label='电话号码'
          name="phone"
          rules={
            [{ required: true, message: "请输入电话号码" }]
          }>
          <Input />
        </Form.Item>
        <Form.Item
          label='角色'
          name="role"
          rules={
            [{ required: true, message: "请输入角色" }]
          }>
          <Select
            style={{
              width: 200,
            }}
            options={roleOption}
          />
        </Form.Item>
      </Form>
    </Modal>
  )
}

