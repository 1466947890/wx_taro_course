import Taro from "@tarojs/taro";

export const uploadFile = async (url, filePath) => {
  let res = await Taro.uploadFile({
    url,
    filePath,
    header: {
      token: Taro.getStorageSync("token") ? Taro.getStorageSync("token") : ""
    },
    name: "file",
    formData: {},
  })
  return JSON.parse(res.data)
}

export const chooseVideo = async () => {
  let result = await Taro.chooseVideo({
    sourceType: ['album', 'camera'],
    maxDuration: 60,
    compressed: true,
    camera: 'back',
    fail: function () {
      console.log("用户取消上传");
    }
  }).catch(e => {
    console.log(e);
  })

  return result
}