package com.example;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	
	public static void addContact (User user) {
		MongoClient mongoClient = new MongoClient();
		try {
		    MongoDatabase db = mongoClient.getDatabase("Database");
		    MongoCollection<Document> collection = db.getCollection("Users");

		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString = mapper.writeValueAsString(user);
		    Document doc = Document.parse(jsonString);
		    collection.insertOne(doc);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally{
		    mongoClient.close();
		}
	}
	
	public static void deleteContact (String ID) {
		MongoClient mongoClient = new MongoClient();
		try {
			MongoDatabase db = mongoClient.getDatabase("Database");
		    MongoCollection<Document> collection = db.getCollection("Users");
		    
		    BasicDBObject filter = new BasicDBObject().append("id", ID);
		    collection.deleteOne(filter);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}
	
	public static User findContact (String ID) {
		MongoClient mongoClient = new MongoClient();
		User user = new User();
		try {
			MongoDatabase db = mongoClient.getDatabase("Database");
		    MongoCollection<Document> collection = db.getCollection("Users");
		    
		    BasicDBObject filter = new BasicDBObject().append("id", ID);
		    Document doc = collection.find(filter).first();
			
		    user.setFirstName((String) doc.get("firstName"));
		    user.setLastName((String)doc.get("lastName"));
		    user.setID((String)doc.get("ID"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
		return user;
	}
	
}
