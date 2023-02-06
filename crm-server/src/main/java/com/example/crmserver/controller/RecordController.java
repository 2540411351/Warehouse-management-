package com.example.crmserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmserver.common.QueryPageParam;
import com.example.crmserver.common.Result;
import com.example.crmserver.pojo.Goods;
import com.example.crmserver.pojo.Record;
import com.example.crmserver.service.GoodsService;
import com.example.crmserver.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-12-05
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private GoodsService goodsService;


    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Record record){
        Goods goods = goodsService.getById(record.getGoods());
        int n = record.getCount();
        if("2".equals(record.getAction())){
            n = -n;
            record.setCount(n);
        }
        int num = goods.getCount()+n;
        goods.setCount(num);
        goodsService.updateById(goods);
        return  recordService.save(record)?Result.suc():Result.fail();
    }


    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return  recordService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String goodstype = (String)param.get("goodstype");
        String storage = (String)param.get("storage");
        String roleId = (String)param.get("roleId");
        String userId = (String)param.get("userId");

        Page<Record> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        QueryWrapper<Record> queryWrapper = new QueryWrapper();
        queryWrapper.apply("a.goods=b.id and b.storage=c.id and b.goodsType=d.id"); //占位
       if("2".equals(roleId)){
           queryWrapper.apply("a.userId= "+userId); //占位

       }

        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {

            queryWrapper.like("b.name",name);
        }
        if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
            queryWrapper.eq("d.id",goodstype);
        }
        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
           queryWrapper.eq("c.id",storage);
        }
        IPage result = recordService.pageCC(page, queryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }

}
