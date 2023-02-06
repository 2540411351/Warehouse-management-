package com.example.crmserver.service;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmserver.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2022-11-30
 */
public interface UserService extends IService<User> {

    IPage pageC(IPage<User> page);


    IPage pageCC(IPage<User> page, Wrapper wrapper);
}
