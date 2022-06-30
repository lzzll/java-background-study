package com.code.lzzll.mongodb.service.impl;

import com.code.lzzll.mongodb.entity.StudyTest;
import com.code.lzzll.mongodb.service.MongoDbService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

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
    public int insertData(StudyTest study) {
        StudyTest insertStudy = mongoTemplate.insert(study);
        String id = insertStudy.getId();
        System.out.println(id);
        return 0;
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
