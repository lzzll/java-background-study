package com.code.lzzll.mongodb.controller;

import com.code.lzzll.common.R;
import com.code.lzzll.mongodb.entity.StudyTest;
import com.code.lzzll.mongodb.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lf
 * @Date 2020/9/10 10:52
 * @Description:
 */
@RestController
@RequestMapping("mongo")
public class MongoDbController {

    @Autowired
    MongoDbService mongoDbService;

    /**
     * 测试
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        System.out.println("外部请求访问");
        return "访问成功";
    }

    /**
     * 创建mongoDb的数据库
     * @return
     */
    @RequestMapping("/createMongoDb")
    public R createMongoDb(){



        return R.ok();
    }


    /**
     * 插入数据
     * @return
     */
    @RequestMapping("/insetData")
    public R insetData(){
        StudyTest study = new StudyTest();
        study.setStudy("学而不思则罔，思而不学则殆");
        study.setUserInfo("{\"user\":\"lzzll\"}");
        int count = mongoDbService.insertData(study);
        return R.ok().put("data",count);
    }

    /**
     * 查询数据
     * @return
     */
    @RequestMapping("/queryData")
    public R queryData(){
        StudyTest study = mongoDbService.queryData();
        return R.ok().put("data",study);
    }

    /**
     * 修改数据
     * @return
     */
    @RequestMapping("/updateData")
    public R updateData(){
        String updateData = mongoDbService.updateData();
        return R.ok().put("data",updateData);
    }


    /**
     * 删除数据
     * @return
     */
    @RequestMapping("/removeData")
    public R removeData(){
        String deleteData = mongoDbService.removeData();
        return R.ok().put("data",deleteData);
    }
}
