import Taro from "@tarojs/taro";

export const ToastSuccess = (title) => {
  return(
    Taro.showToast({
      title: title,
      icon: 'success',
      duration: 2000
    })
  )
}

export const ToastError = (title) => {
  return(
    Taro.showToast({
      title: title,
      icon: 'error',
      duration: 2000
    })
  )
}

export const ToastLoading = (title) => {
  return(
    Taro.showToast({
      title: title,
      icon: 'loading',
      duration: 2000
    })
  )
}

export const ToastNone = (title) => {
  return(
    Taro.showToast({
      title: title,
      icon: 'none',
      duration: 2000
    })
  )
}