import Taro from "@tarojs/taro";

export const To = (url) => {
  Taro.navigateTo({
    url
  })
}


export const redirectTo = (url) => {
  Taro.redirectTo({
    url
  })
}
