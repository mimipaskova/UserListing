package source;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class UserDAOMongo implements UserDAO {

	private static UserDAOMongo instance;
	boolean auth;
	DB db = null;
	MongoClient mongo = null;
	DBCollection coll = null;

	public static UserDAOMongo getInstance() {
		if (instance == null) {
			instance = new UserDAOMongo();
		}
		return instance;
	}

	private UserDAOMongo() {
		try {
			this.coll = getConnection();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private DBCollection getConnection() throws UnknownHostException {
		mongo = new MongoClient("localhost", 27017);

		MongoClient mongoClient = new MongoClient();
		db = mongoClient.getDB("mydb");
		auth = db.authenticate("root", "root".toCharArray());
		coll = db.createCollection("myusers", null);
		System.out.println("get connection");
		return coll;
	}

	@Override
	public void fillTable() {
		System.out.println("begin fill");
		DBCollection table = db.getCollection("myusers");
		BasicDBObject document = new BasicDBObject();
		document.put("userName", "Mimieeee");
		document.put("userID", 1);
		document.put("userAddress", "Sofia");
		document.put("userTelNo", 010101);
		System.out.println("user " + document);
		table.insert(document);

	}

	@Override
	public User getUserDetails(int userID) {

		DBCollection table = db.getCollection("myusers");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("userID", userID);

		DBCursor cursor = table.find(searchQuery);
		User user = null;
		while (cursor.hasNext()) {
			user = new User();
			user = convertRSToUser(cursor.next());
		}
		return user;
	}

	@Override
	public List<User> getUsers() {
		System.out.println("get users");

		List<User> users = new ArrayList<User>();

		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {

			User user = new User();
			user = convertRSToUser(cursor.next());
			users.add(user);
		}
		return users;
	}
	
	
	
	public User convertRSToUser (DBObject theObj) {
//		DBObject theObj = cursor.next();
		
		BasicDBObject userObj = (BasicDBObject) theObj;
		String userName = userObj.getString("userName");
		int userID = userObj.getInt("userID");
		String userAddress = userObj.getString("userAddress");
		String userTelNo = userObj.getString("userTelNo");

		User user = new User();
		user.setUserName(userName);
		user.setUserID(userID);
		user.setUserAddress(userAddress);
		user.setUserTelNo(userTelNo);
		
		return user;
	}

	@Override
	public void addUser(User user) {
		
		DBCollection table = db.getCollection("myusers");
		BasicDBObject document = new BasicDBObject();
		document.put("userName", user.getUserName());
		document.put("userID", user.getUserID());
		document.put("userAddress", user.getUserAddress());
		document.put("userTelNo", user.getUserTelNo());
		System.out.println("user " + document);
		table.insert(document);
		
	}
}
