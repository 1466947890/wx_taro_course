package com.beizhi.service;

import com.beizhi.entrance.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author 14669
 * @date 2023/12/7 16:47
 * @describe
 */

@SpringBootTest(classes = Application.class)
public class PasswordTest {
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void encodeTest(){
        System.out.println(passwordEncoder.encode("123456"));
    }
}
