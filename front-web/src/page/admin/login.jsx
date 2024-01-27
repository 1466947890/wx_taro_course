import { LockOutlined, MobileOutlined } from '@ant-design/icons';
import { Button, Checkbox, Form, Input } from 'antd';
import {useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import "./css/login.scss"
import { login, resonse } from '../../utils/interface';
import { changeToken } from '../../utils/toolkit';


const AdminLogin = () => {
  let dispatch = useDispatch()
  let navigate = useNavigate()

  const onFinish = (values) => {
    login(values).then(res => {
      let success = () => {
        let token = res.data.token
        dispatch(changeToken(token))
        localStorage.setItem("token", token)
        setTimeout(() => {
          navigate("/admin/index")
        }, 3000)
      }
      resonse(res,success)
    })
  }
  return (
    <div className="login_index">
      <div className="form_box">
        <div className='form_header'>
          <h2>管理员登录</h2>
        </div>
        <Form
          name="normal_login"
          className="login-form"
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
        >
          <Form.Item
            name="phone"
            rules={[
              {
                required: true,
                message: '请输入电话号码',
              },
            ]}
          >
            <Input prefix={<MobileOutlined />} placeholder="手机号码" />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[
              {
                required: true,
                message: '请输入密码',
              },
            ]}
          >
            <Input
              prefix={<LockOutlined className="site-form-item-icon" />}
              type="password"
              placeholder="密码"
            />
          </Form.Item>
          <Form.Item>
            <Form.Item name="remember" valuePropName="checked" noStyle>
              <Checkbox>记住密码</Checkbox>
            </Form.Item>
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit" className="login-form-button">
              登录
            </Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  )
}

export default AdminLogin