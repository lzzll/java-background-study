package com.code.example.lzzll.mongodb.service;

import com.code.example.lzzll.mongodb.entity.StudyTest;

import java.util.List;

/**
 * @Author lf
 * @Date 2020/9/10 11:25
 * @Description:
 */
public interface MongoDbService {

    /**
     * 插入数据
     * @param study
     * @return
     */
    int insertData(StudyTest study);

    /**
     * 查询所有的数据
     * @return
     */
    List<StudyTest> queryAll();

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    StudyTest queryById(String id);

    /**
     * 通过实体类通过and关联查询数据
     * @param andParam
     * @param orParam
     * @param notParam
     * @return
     */
    List<StudyTest> queryByEntity(StudyTest andParam,StudyTest orParam,StudyTest notParam);

    /**
     * 条件查询数据
     * @return
     */
    StudyTest queryData();

    /**
     * 修改数据
     * @return
     */
    String updateData();

    /**
     * 删除数据
     * @return
     */
    String removeData();

}
