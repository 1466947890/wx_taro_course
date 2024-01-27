import { Layout } from 'antd';
import { Outlet } from 'react-router-dom';
import AdminSider from './chidren/admin_sider';
import AdminHeader from '../../components/admin/header_component';

import "./css/admin.scss"
const { Sider, Content } = Layout;

const Admin = () => {
  return (
    <div className="teacher_index">
      <Layout className='layout_box'>
        <AdminHeader></AdminHeader>
        <Layout hasSider>
          <Sider className='sider_box' theme='light'>
            <AdminSider></AdminSider>
          </Sider>
          <Content className='content_box'>
            <Outlet></Outlet>
          </Content>
        </Layout>
      </Layout>
    </div>
  )
}

export default Admin