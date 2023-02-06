package com.example.crmserver.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crmserver.pojo.Goodstype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crmserver.pojo.Storage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-12-05
 */
public interface GoodstypeService extends IService<Goodstype> {
    IPage pageCC(IPage<Goodstype> page, Wrapper wrapper);
}
