package com.beizhi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beizhi.dao.MajorMapper;
import com.beizhi.entity.Major;
import com.beizhi.service.MajorService;
import org.springframework.stereotype.Service;

/**
 * @author 14669
 * @date 2024/1/4 17:27
 * @describe
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
}
