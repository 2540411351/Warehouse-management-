package com.example.crmserver.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmserver.common.QueryPageParam;
import com.example.crmserver.common.Result;
import com.example.crmserver.pojo.Menu;
import com.example.crmserver.pojo.User;
import com.example.crmserver.service.MenuService;
import com.example.crmserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-11-30
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

     @GetMapping("/list")
      public List<User> list(){
        return  userService.list();
      }


      //新增
      @PostMapping("/save")
      public Result save(@RequestBody User user){
          return  userService.save(user)?Result.suc():Result.fail();
      }
      //更新
      @PostMapping("/update")
      public Result update(@RequestBody User user){
          return  userService.updateById(user)?Result.suc():Result.fail();
      }
      //删除
      @GetMapping("/del")
      public Result del(@RequestParam String id){
          return  userService.removeById(id)?Result.suc():Result.fail();
      }
    //查询(模糊/匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName,user.getName());
        }
        //lambdaQueryWrapper.eq(User::getName,user.getName());
        return Result.suc(userService.list(lambdaQueryWrapper));
    }


    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List list = userService.lambdaQuery()
                .eq(User::getNo,user.getNo())
                .eq(User::getPassword,user.getPassword()).list();
        if(list.size()>0){
            User user1 = (User)list.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuright,user1.getRoleId()).list();
            HashMap res = new HashMap();
            res.put("user",user1);
            res.put("menu",menuList);
            return  Result.suc(res);
        }
        return  Result.fail();
    }

      //修改
      @PostMapping("/mod")
      public boolean mod(@RequestBody User user){
          return  userService.updateById(user);
      }

      //新增或修改
      @PostMapping("/saveOrMpd")
      public boolean saveOrMpd(@RequestBody User user){
          return  userService.saveOrUpdate(user);
      }

      //删除
      @GetMapping("/delete")
      public boolean delete(Integer id){
          return  userService.removeById(id);
      }

       //分页
    @PostMapping("/listPage")
    //public List<User> listPage(@RequestBody HashMap map){
    public List<User> listPage(@RequestBody QueryPageParam query){
         HashMap param =query.getParam();
         String name = (String)param.get("name");
        System.out.println("name==="+(String)param.get("name"));

        //Page<User> page = new Page(1,2); 第一种
        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.page(page,lambdaQueryWrapper);
        System.out.println("total==" +result.getTotal());
        return  result.getRecords();
    }

    @PostMapping("/listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam query){
        HashMap param =query.getParam();
        String name = (String)param.get("name");
        System.out.println("name==="+(String)param.get("name"));

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);

        //IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page,lambdaQueryWrapper);
        System.out.println("total==" +result.getTotal());
        return  result.getRecords();
    }

    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query){
        HashMap param =query.getParam();
        String name = (String)param.get("name");
        String sex = (String)param.get("sex");
        String roleId  = (String)param.get("roleId");

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex) ){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if(StringUtils.isNotBlank(roleId) ){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }
        IPage result = userService.pageCC(page,lambdaQueryWrapper);
        System.out.println("total==" +result.getTotal());
        return  Result.suc(result.getRecords(),result.getTotal());
    }
    //判断前台新增的数据中的账号是否与后台中保存的数据账号中是否有相等，若有相等，则说明该账号不是唯一
     @GetMapping("/findByNo")
      public  Result findByNo(@RequestParam String no){
           List list = userService.lambdaQuery().eq(User::getNo,no).list();
            return list.size()>0?Result.suc(list) : Result.fail();
     }

}
