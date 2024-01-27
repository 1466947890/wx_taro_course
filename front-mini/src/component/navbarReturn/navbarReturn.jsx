import { AtNavBar } from 'taro-ui'
import Taro from '@tarojs/taro'
const handleClick = () => {
  Taro.navigateBack({
    delta: 1
  })
}
const NavBarReturn =  () => {
  return (
    <AtNavBar
      onClickLeftIcon={handleClick}
      color='#000'
      leftIconType="chevron-left"
    />
  )
}

export default NavBarReturn