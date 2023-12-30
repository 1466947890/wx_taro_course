package com.beizhi.service;

import com.beizhi.entrance.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//import static com.beizhi.service.impl.VideoServiceImpl.getVodUrl;

@SpringBootTest(classes = Application.class)
public class VideoTest {
    @Test
    public void getUrlTest(){
//        System.out.println(getVodUrl("59f4ae70842871ee807e5017e1e90102"));
    }
}
