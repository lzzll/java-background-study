package com.code.lzzll.mongodb.service;

import com.code.lzzll.mongodb.entity.StudyTest;

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
