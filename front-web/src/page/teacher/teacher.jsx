import { Layout } from 'antd';
import { Outlet } from 'react-router-dom';
import SiderBar from './children/sider_bar';
import HeaderComponet from '../../components/header_component';

import "./css/teacher.scss"
const {Sider, Content } = Layout;

const Teacher = () => {
  return (
    <div className="teacher_index">


      <Layout className='layout_box'>
        <HeaderComponet></HeaderComponet>
        <Layout hasSider>
          <Sider className='sider_box' theme='light'>
            <SiderBar></SiderBar>
          </Sider>
          <Content className='content_box'>
            <Outlet></Outlet>
          </Content>
        </Layout>
      </Layout>
    </div>
  )
}

export default Teacher