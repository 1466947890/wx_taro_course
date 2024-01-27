import { MailOutlined } from '@ant-design/icons';
import { Menu } from 'antd';
import "./css/sider_bar.scss"
import { NavLink } from 'react-router-dom';

function getItem(label, key, icon, children, type) {
  return {
    key,
    icon,
    children,
    label,
    type,
  };
}

const items = [
  getItem(<NavLink to="/teacher/index">我的课程</NavLink>, '/teacher/index', <MailOutlined />),
  getItem(<NavLink to="/teacher/details">我的资料</NavLink>, '/teacher/details', <MailOutlined />),
];
const SiderBar = () => {
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
export default SiderBar