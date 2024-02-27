import axios from "axios";
import store from "./toolkit";
import config from "../config";
import { message } from "antd";
// import { navigateTo } from "./navigate";

const http = axios.create({
  baseURL: config.baseUrl,
  timeout: 5 * 1000
})
/**
 * 请求拦截器
 */
http.interceptors.request.use((config) => {
  let token = store.getState().loginTokenReducer.token
  if(token){
    config.headers.token = token
  }
  return config
},
  (error) => {
    // console.log(error);
    return Promise.reject(error)
  }
)

http.interceptors.response.use((res) => {
  // console.log(res.data.code);
  if(res.data.code !== 200){
    message.error(res.data.msg)
    return Promise.reject(res.data.msg)
  }
  return res.data
}, (error) => {
  console.log(error.response.status);
  if(error.response.status === 403){
    message.error("登录信息获取，请重新登录")
    setTimeout(() => {
      window.location.href="/#/login"
    }, 3000)
  }
  return Promise.reject(error)
})

export default http

