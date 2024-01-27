import { 
  UserOutlined, 
  DeploymentUnitOutlined, 
  FundProjectionScreenOutlined,
  VideoCameraOutlined,
  TagsOutlined,
  HomeOutlined
 } from '@ant-design/icons';
import { Menu } from 'antd';
import "./css/admin_bar.scss"
import { NavLink } from 'react-router-dom';
import { useEffect, useState } from 'react';

function getItem(label, key, icon, children, type) {
  return {
    key,
    icon,
    children,
    label,
    type,
  };
}
let sider_data = [
  { title: "首页", path: "/admin/index", icon: <HomeOutlined /> },
  { title: "用户管理", path: "/admin/user", icon: <UserOutlined /> },
  { title: "专业管理", path: "/admin/major", icon: <DeploymentUnitOutlined /> },
  { title: "课程管理", path: "/admin/course", icon: <FundProjectionScreenOutlined /> },
  { title: "视频管理", path: "/admin/video", icon: <VideoCameraOutlined /> },
  { title: "课程资料", path: "/admin/details", icon: <TagsOutlined /> },
]


const AdminSider = () => {
  const [items, setItems] = useState([])
  useEffect(() => {
    let _items = []
    sider_data.forEach((item, index) => {
      _items.push(
        getItem(<NavLink key={index} to={item.path}>{item.title}</NavLink>, item.path, item.icon),
      )
    })
    setItems(_items)
  }, [])

  return (
    <div className='sider_index'>
      <Menu
        defaultSelectedKeys={[window.location.hash.replace("#", "")]}
        mode="inline"
        items={items}
      />
    </div>
  )
}
export default AdminSider