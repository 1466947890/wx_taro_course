import { View } from "@tarojs/components"
import { AtList, AtListItem } from "taro-ui"
import Taro from "@tarojs/taro"


const Details = (props) => {

  const handleRefer = (value) => {
    console.log(value);
    Taro.downloadFile({
      url: value,
      success: function(res){
        console.log("成功下载");
        // console.log(res);
        let path = res.tempFilePath
        Taro.openDocument({
          filePath: path,
          fileType: "pptx",
          showMenu: true,
          success: function(res){
            console.log(res);
          }
        })
      }
    })
  }

  let { detailsList } = props
  return (
    <View>
      <AtList>

        {
          detailsList.length == 0 ?
            ""
            : detailsList.map(item => {
              return (
                <AtListItem key={item.id} title={item.name} onClick={handleRefer.bind(this, item.url)} extraText='查看' />
              )
            })
        }
      </AtList>
    </View>
  )
}

export default Details