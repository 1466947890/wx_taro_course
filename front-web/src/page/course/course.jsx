import { Layout } from 'antd';
import { Outlet } from "react-router-dom";
import CourseSider from "./course_sider";
import HeaderComponet from '../../components/header_component';
import "./css/course.scss"

const { Sider, Content } = Layout;




const Course = () => {
  return (
    <div className="course_index">
      <Layout className="layout_box">
        <HeaderComponet></HeaderComponet>
        <Layout hasSider>
          <Sider theme="light">
            <CourseSider></CourseSider>
          </Sider>
          <Content className='layout_content'>
            <Outlet></Outlet>
          </Content>
        </Layout>
      </Layout>

    </div>
  )
}
export default Course