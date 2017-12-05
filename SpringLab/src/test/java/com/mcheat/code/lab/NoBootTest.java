package com.mcheat.code.lab;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Mongo 集群连接测试
 */
@Slf4j
public class NoBootTest {

    private MongoClient mongoClient;

    @Before
    public void prepare() {
        //连接到MongoDB服务
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress_01 = new ServerAddress("10.164.197.223",20000);
        ServerAddress serverAddress_02 = new ServerAddress("10.164.197.238",20000);
        ServerAddress serverAddress_03 = new ServerAddress("10.164.197.239",20000);

        List<ServerAddress> addrs = new ArrayList<>();
        addrs.add(serverAddress_01);
        addrs.add(serverAddress_02);
        addrs.add(serverAddress_03);

/*            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential("username",
                    "databaseName", "password".toCharArray());
            List<MongoCredential> credentials = new ArrayList<>();
            credentials.add(credential);*/


        // 连接到 mongodb 服务
        mongoClient = new MongoClient(addrs);
    }

    @Test
    public void testInsert() {
        try {
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("wangjy");
            log.info("连接成功");
/*            mongoDatabase.createCollection("java");
            log.info("集合创建成功");*/

            MongoCollection<Document> collection = mongoDatabase.getCollection("java");
            log.info("集合选择成功");

            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document doc_01 = new Document("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "McHeat");

            Document doc_02 = new Document("title", "Java").
                    append("description", "database").
                    append("likes", 150).
                    append("by", "McHeat");

            Document doc_03 = new Document("title", "Lambda").
                    append("description", "database").
                    append("likes", 200).
                    append("by", "McHeat");

            List<Document> documents = new ArrayList<>();
            documents.add(doc_01);
            documents.add(doc_02);
            documents.add(doc_03);

            collection.insertMany(documents);
            System.out.println("文档插入成功");
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Test
    public void testFind() {
        try {
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("wangjy");
            log.info("连接成功");

            MongoCollection<Document> collection = mongoDatabase.getCollection("java");
            log.info("集合选择成功");

            //检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * */
            BsonDocument bson = new BsonDocument("title", new BsonString("Java"));
            FindIterable<Document> findIterable = collection.find(Filters.gt("likes", 100));
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                log.info(mongoCursor.next().toJson());
            }
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        try {
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("wangjy");
            log.info("连接成功");

            MongoCollection<Document> collection = mongoDatabase.getCollection("java");
            log.info("集合选择成功");

            //更新文档   将文档中likes=100的文档修改为likes=250
            collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",250)));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                log.info(mongoCursor.next().toJson());
            }
        } catch (Exception e) {
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
    }






}
