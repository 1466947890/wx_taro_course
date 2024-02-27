import Taro from "@tarojs/taro";

export const To = (url) => {
  if(!url){
    console.log("路径不存在");
    return
  }
  Taro.navigateTo({
    url
  })
}


export const redirectTo = (url) => {
  if(!url){
    console.log("路径不存在");
    return
  }
  Taro.redirectTo({
    url
  })
}

export const navigateTo = (url) => {
  if(!url){
    console.log("路径不存在");
    return
  }
  Taro.navigateTo({
    url
  })
}

