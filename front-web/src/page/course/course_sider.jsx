import { AppstoreOutlined } from '@ant-design/icons';
import { Menu } from "antd";
import { NavLink, useParams } from 'react-router-dom';
function getItem(label, key, icon, children, type) {
  return {
    key,
    icon,
    children,
    label,
    type,
  };
}





const CourseSider = () => {
  let {courseId} = useParams()


  const items = [
    getItem(<NavLink to={"/course/chapter/" + courseId}>章节列表</NavLink>, '1234', <AppstoreOutlined></AppstoreOutlined>),
    getItem(<NavLink to={"/course/grade/" + courseId}>学生分数</NavLink>, 'sub2', <AppstoreOutlined></AppstoreOutlined>),
    getItem(<NavLink to={"/course/details/" + courseId}>课程资料</NavLink>, 'sub3', <AppstoreOutlined></AppstoreOutlined>),
  ];
  return (
    <div className="course_index">
      <Menu
        className="course_menu"
        defaultSelectedKeys={["1234"]}
        mode="inline"
        items={items}
      />

    </div>
  )
}

export default CourseSider