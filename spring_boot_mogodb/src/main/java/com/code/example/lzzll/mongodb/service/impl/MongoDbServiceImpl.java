package com.code.example.lzzll.mongodb.service.impl;

import com.code.example.lzzll.mongodb.entity.StudyTest;
import com.code.example.lzzll.mongodb.service.MongoDbService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @Author lf
 * @Date 2020/9/10 11:25
 * @Description:
 */
@Service
public class MongoDbServiceImpl implements MongoDbService {


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertData(StudyTest study) {
        StudyTest insertStudy = mongoTemplate.insert(study);
        String id = insertStudy.getId();
        System.out.println(id);
        return 0;
    }

    @Override
    public List<StudyTest> queryAll() {
        return mongoTemplate.findAll(StudyTest.class);
    }

    @Override
    public StudyTest queryById(String id) {
//        return mongoTemplate.findById(id, StudyTest.class);
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, StudyTest.class);
    }

    @Override
    public List<StudyTest> queryByEntity(StudyTest andParam,StudyTest orParam,StudyTest notParam) {
        Criteria criteria = new Criteria();
        // 拼接"and"条件
        if (!StringUtils.isEmpty(andParam.getId())){
            criteria.and("id").is(andParam.getId());
        }
        if (!StringUtils.isEmpty(andParam.getStudy())){
            criteria.and("study").is(andParam.getStudy());
        }
        // 拼接"or"条件
        if (orParam != null){
            Criteria orCriteria = new Criteria();
            if (!StringUtils.isEmpty(orParam.getId())){
                orCriteria.and("id").is(andParam.getId());
            }
            if (!StringUtils.isEmpty(andParam.getStudy())){
                criteria.and("study").is(andParam.getStudy());
            }
            criteria.orOperator(orCriteria);
        }
        // 拼接"not"条件
        Query query = new Query(criteria);
        return mongoTemplate.find(query,StudyTest.class);
    }


    @Override
    public StudyTest queryData() {
//        Criteria criteria = new Criteria();
//        criteria.and("study").is("学而不思则罔，思而不学则殆");
//        Criteria criteria1 = Criteria.where("study").is("学而不思则罔，思而不学则殆");
//        Criteria criteria1 = Criteria.where("_id").is("ObjectId(5f59bd561ebdae089ee797d5)");
        Criteria criteria1 = Criteria.where("_id").is(new ObjectId("5f59bd561ebdae089ee797d5"));
        Query query=new Query(criteria1);
        StudyTest one = mongoTemplate.findOne(query, StudyTest.class);
        return one;
    }

    @Override
    public String updateData() {
        Query query = new Query(Criteria.where("_id").is(new ObjectId("5f59bd561ebdae089ee797d5")));
        Update update = new Update();
        update.set("userInfo","[{\"三国演义\":\"赵子龙\"}]");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, StudyTest.class);
        System.out.println(updateResult.getMatchedCount());
        System.out.println(updateResult.getModifiedCount());
        System.out.println(updateResult.getUpsertedId());
        return null;
    }

    @Override
    public String removeData() {
        Query query = new Query(Criteria.where("_id").is(new ObjectId("5f59bd561ebdae089ee797d5")));
        DeleteResult remove = mongoTemplate.remove(query, StudyTest.class);
        System.out.println(remove.getDeletedCount());
        System.out.println(remove.wasAcknowledged());
        return null;
    }


}
