import { View } from '@tarojs/components'
import { useState } from 'react'
import { AtInput, AtActionSheet, AtActionSheetItem, AtList, AtListItem } from 'taro-ui'
import { letter } from '../../../utils/StriUtils'

const RadioTopic = (props) => {
  let { getData } = props
  let type = 1;
  const [isOpened, setIsOpened] = useState(false)
  const [option, setOption] = useState([
    { value: '' },
    { value: '' },
    { value: '' },
    { value: '' },
  ])
  const [correct, setCorrect] = useState()
  const handleOptionValueChange = (i, value) => {
    let newOption = [...option];
    newOption[i].value = value
    setOption(newOption)
  }
  const handleOnClose = () => {
    setIsOpened(false)
  }
  const handleChangeCorrect = () => {
    setIsOpened(true)
  }
  const handleChangeCorrectOption = (value) => {
    setCorrect(letter[value])
    setIsOpened(false)
    let data = {
      option, correct: letter[value] , type
    }
    getData(data)
  }
  let i = 0,j = 0;
  return (
    <View className='RadioIndex'>
      <View>
        {
          option.map(item => {
            return (
              <AtInput
                name='value'
                type='text'
                title={letter[i] + "："}
                value={item.value}
                placeholder={'选项' + (i + 1)}
                onChange={handleOptionValueChange.bind(this, i++)}
              />
            )
          })
        }
      </View>
      <View>
        <AtInput
          name='value'
          title='正确选项'
          type='text'
          placeholder='例如A、B、C'
          value={correct}
          onFocus={handleChangeCorrect.bind(this)}
          onChange={handleChangeCorrect.bind(this)}
        />
      </View>
      <View>
        <AtActionSheet isOpened={isOpened} onClose={handleOnClose}>
          {
            option.map(item => {
              return (
                <AtActionSheetItem onClick={handleChangeCorrectOption.bind(this, j)}>
                  {letter[j++]}
                </AtActionSheetItem>
              )
            })
          }
        </AtActionSheet>
      </View>
    </View>

  )
}

export default RadioTopic