package com.example.crmserver.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crmserver.pojo.Storage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crmserver.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-12-04
 */
public interface StorageService extends IService<Storage> {

    IPage pageCC(IPage<Storage> page, Wrapper wrapper);


}
