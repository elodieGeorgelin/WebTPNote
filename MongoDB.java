package com.example;

import java.util.List;
import java.util.ArrayList;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;

public class MongoDB {
	
	public static final String DATABASE = "usersData";
	public static final String COLLECTION_CONTACTS = "contacts";
	public static void addContact (User user) {
		MongoClient mongoClient = new MongoClient();
		try {
		    MongoDatabase db = mongoClient.getDatabase(DATABASE);
		    MongoCollection<Document> collection = db.getCollection(COLLECTION_CONTACTS);

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
			MongoDatabase db = mongoClient.getDatabase(DATABASE);
		    MongoCollection<Document> collection = db.getCollection(COLLECTION_CONTACTS);
		    
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
			MongoDatabase db = mongoClient.getDatabase(DATABASE);
		    MongoCollection<Document> collection = db.getCollection(COLLECTION_CONTACTS);
		    
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
	
	public static List<User> getAllContacts() {
		List<User> list = new ArrayList<User>();
		
		MongoClient mongoClient = new MongoClient();
		try {
		    MongoDatabase db = mongoClient.getDatabase(DATABASE);
		    MongoCollection<Document> collection = db.getCollection(COLLECTION_CONTACTS);
		    // perform read operation on the collection
	        FindIterable<Document> fi = collection.find();
	        MongoCursor<Document> cursor = fi.iterator();
	        try {
	        	while(cursor.hasNext()) {
	        		Document o = cursor.next();
	        		User user = new User((String) o.get("id"), (String) o.get("First Name"), (String) o.get("Last Name"));
	        		list.add(user);
	        	}
	        } finally {
	        	cursor.close();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
		return list;
	}
	
	public static void modifyUser(String ID, User user) {
		MongoClient mongoClient = new MongoClient();
		try {
			MongoDatabase db = mongoClient.getDatabase(DATABASE);
			MongoCollection<Document> collection = db.getCollection(COLLECTION_CONTACTS);

			BasicDBObject filter = new BasicDBObject().append("id", ID);
			collection.deleteOne(filter);
			
			ObjectMapper mapper = new ObjectMapper();
		    String jsonString = mapper.writeValueAsString(new User(ID, user.getFirstName(), user.getLastName()));
		    Document doc = Document.parse(jsonString);
		    collection.insertOne(doc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}

}
