import { View, Text, Image, Button } from '@tarojs/components'
import lunbo from '../../static/img/lunbo.png'
import courseImage from '../../static/img/course.png'
import { Swiper, SwiperItem } from '@tarojs/components'
import { AtButton } from 'taro-ui'
import './index.scss'
import { useEffect, useState } from 'react'
import { WxIndex, joinCourse } from '../../utils/interfact'
import { ToastError, ToastSuccess } from '../../utils/toast'


const Index = () => {
  const [myCourseList, setMyCourseList] = useState([])
  const [otherCourseList, setOtherCourseList] = useState([])

  useEffect(() => {
    WxIndex().then(res => {
      setMyCourseList(res.data.myCourse)
      setOtherCourseList(res.data.otherCourse)
    })
  }, [])

  const handleJoinCourse = (courseId) => {
    // console.log(courseId);
    joinCourse(courseId).then(res => {
      if(res.code ==200){
        ToastSuccess(res.msg)
      }else{
        ToastError(res.msg)
      }
    })
  }

  return (
    <View className='index'>
      <View className='at-row at-row__justify--center'>
        <View className='at-col at-col-12'>
          <Swiper
            className='test-h'
            indicatorColor='#999'
            indicatorActiveColor='#333'
            circular
            indicatorDots
            autoplay>
            <SwiperItem>
              <View className='swiper-image-box'>
                <Image
                  mode='top'
                  className='lunboImag'
                  src={lunbo}></Image></View>
            </SwiperItem>
          </Swiper>
        </View>
      </View>
      <View className='course'>
        <View className='at-row'>
          {/* <View className='at-col at-col-12'>
            <View className='course-trends-title'>
              <Text>课程动态</Text>
              <View className='more'>
                <Text className='more-text'>查看更多</Text><View className='at-icon at-icon-chevron-right'></View>
              </View>
            </View>
            <View className='course-trends-container'>
              <View className='course-trends-container-box'>
                <Swiper
                  className='swiper-trends'
                  vertical
                  circular
                  autoplay>
                  <SwiperItem>
                    <View className='demo-text-1'>
                      ****发布了新课程1
                    </View>
                  </SwiperItem>
                  <SwiperItem>
                    <View className='demo-text-2'>
                      ****发布了新课程2
                    </View>
                  </SwiperItem>
                  <SwiperItem>
                    <View className='demo-text-3'>
                      ****发布了新课程3
                    </View>
                  </SwiperItem>
                </Swiper>
              </View>
            </View>
          </View> */}
        </View>
        <View className='at-row'>
          <View className='at-col at-col-12'>
            <View className='course-trends-title'>
              <Text>专业课程推荐</Text>
              {/* <View className='more'>
                <Text className='more-text'>查看更多</Text><View className='at-icon at-icon-chevron-right'></View>
              </View> */}
            </View>
            <View className='course-trends-container'>
              {
                myCourseList.map(item => {
                  return (
                    <View key={item.id} className='course-recommend-container-box'>
                      <View className='course-recommend-Image-box'>
                        <Image className='course-recommend-Image' src={item.image}></Image>
                      </View>
                      <View className='course-recommend-Info-box'>
                        <View className='course-recommend-info-title'>
                          <Text>{item.name}</Text>
                        </View>
                        <View className='course-recommend-info-tip'>
                          <AtButton onClick={() => handleJoinCourse(item.id)} className='course-recommend-bth' size='small' type='primary'>加入课程</AtButton>
                        </View>
                      </View>
                    </View>
                  )
                })
              }
            </View>
          </View>
        </View>
        <View className='at-row'>
          <View className='at-col at-col-12'>
            <View className='course-trends-title'>
              <Text>业余课程推荐</Text>
              {/* <View className='more'>
                <Text className='more-text'>查看更多</Text><View className='at-icon at-icon-chevron-right'></View>
              </View> */}
            </View>
            <View className='course-trends-container'>
              {
                otherCourseList.map(item => {
                  return (
                    <View key={item.id} className='course-recommend-container-box'>
                      <View className='course-recommend-Image-box'>
                        <Image className='course-recommend-Image' src={item.image}></Image>
                      </View>
                      <View className='course-recommend-Info-box'>
                        <View className='course-recommend-info-title'>
                          <Text>{item.name}</Text>
                        </View>
                        <View className='course-recommend-info-tip'>
                          <AtButton onClick={() => handleJoinCourse(item.id)} className='course-recommend-bth' size='small' type='primary'>加入课程</AtButton>
                        </View>
                      </View>
                    </View>
                  )
                })
              }
            </View>
          </View>
        </View>
      </View>
    </View>
  )
}

export default Index