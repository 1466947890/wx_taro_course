import { Layout } from "antd"

const {Header} = Layout



const HeaderComponet = () => {
  const HeaderStyle = {
    backgroundColor: "white"
  }
  return (
    <Header style={HeaderStyle} className="header_box">
      <h2>教师后台管理系统</h2>
    </Header>
  )
}

export default HeaderComponet