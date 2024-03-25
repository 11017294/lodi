package com.lodi.common.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lodi.common.mybatis.mapper.BaseMapper;
import com.lodi.common.mybatis.service.BaseService;

/**
 * Service 实现类 基类
 *
 * @author MaybeBin
 * @createDate 2023-09-19
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

}
