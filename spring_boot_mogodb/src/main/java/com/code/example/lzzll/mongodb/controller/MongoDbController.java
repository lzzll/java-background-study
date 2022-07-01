package com.code.example.lzzll.mongodb.controller;

import com.code.example.lzzll.common.R;
import com.code.example.lzzll.mongodb.entity.StudyTest;
import com.code.example.lzzll.mongodb.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 查询所有的数据
     * @return
     */
    @RequestMapping("/queryAll")
    public R queryAll(){
        List<StudyTest> studys = mongoDbService.queryAll();
        return R.ok().put("data",studys);
    }

    /**
     * 根据id查询数据
     * @return
     */
    @RequestMapping("/queryById")
    public R queryById(@RequestParam String id){
        StudyTest study = mongoDbService.queryById(id);
        return R.ok().put("data",study);
    }

    /**
     * 根据试题类查询数据
     * @return
     */
    @RequestMapping("/queryByEntity")
    public R queryByEntity(@RequestBody StudyTest andParam,@RequestBody StudyTest orParam,@RequestBody StudyTest notParam){
        List<StudyTest> studys = mongoDbService.queryByEntity(andParam,orParam,notParam);
        return R.ok().put("data",studys);
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
