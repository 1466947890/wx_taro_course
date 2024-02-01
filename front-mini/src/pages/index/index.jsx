import { View, Text, Image, Button } from '@tarojs/components'
import lunbo from '../../static/img/lunbo.png'
import courseImage from '../../static/img/course.png'
import { Swiper, SwiperItem } from '@tarojs/components'
import { useRouter } from '@tarojs/taro'
import { AtButton } from 'taro-ui'
import './index.scss'


const Index = () => {
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
          <View className='at-col at-col-12'>
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
          </View>
        </View>
        <View className='at-row'>
          <View className='at-col at-col-12'>
            <View className='course-trends-title'>
              <Text>精品课程推荐</Text>
              <View className='more'>
                <Text className='more-text'>查看更多</Text><View className='at-icon at-icon-chevron-right'></View>
              </View>
            </View>
            <View className='course-trends-container'>
              <View className='course-recommend-container-box'>
                <View className='course-recommend-Image-box'>
                  <Image className='course-recommend-Image' src={courseImage}></Image>
                </View>
                <View className='course-recommend-Info-box'>
                  <View className='course-recommend-info-title'>
                    <Text>光电信息检测技术</Text>
                  </View>
                  <View className='course-recommend-info-tip'>
                    <AtButton className='course-recommend-bth' size='small' type='primary'>了解详细</AtButton>
                  </View>
                </View>
              </View>
            </View>
          </View>
        </View>
      </View>
    </View>
  )
}

export default Index