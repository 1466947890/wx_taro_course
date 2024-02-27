import Taro from "@tarojs/taro";
import { ToastError } from "./toast";

const send = (url, data, method) => {
  let token = Taro.getStorageSync("token")
  if (token) {
    let request = Taro.request({
      url, data, method, header: {
        token
      }
    })
    request.then(res => {
      let data = res.data
      if(data.code != 200){
        ToastError(res.data.msg)
        return Promise.reject(res.data.msg)
      }
    })
    return request;
  } else {
    return Taro.request({ url, data, method })
  }
}

export const postJSON = (url, data) => {
  return send(url, data, "POST")
}

export const putJSON = (url, data) => {
  return send(url, data, "PUT")
}

export const getJSON = (url, data) => {
  return send(url, data, "GET")
}

export const deleteJSON = (url, data) => {
  return send(url, data, "DELETE")
}



