package source;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOMySQL implements UserDAO{

	private static UserDAOMySQL instance;
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public static UserDAOMySQL getInstance() {
		if (instance == null) {
			instance = new UserDAOMySQL();
		}
		return instance;
	}
	
	
	private UserDAOMySQL() {
		try {
			this.connection = getConnection();
		} catch (WinkException e) {
			e.printStackTrace();
		}
	}	
	
	
	@Override
	public void fillTable() {

		try {
			statement = connection.createStatement();
			String sql = "INSERT INTO userdetails "
					+ "VALUES (2, 'Mimi', 'Sofia', '01')";
			statement.executeUpdate(sql);
			sql = "INSERT INTO userdetails "
					+ "VALUES (3, 'Lqlq', 'Sa', '01111')";
			statement.executeUpdate(sql);
			System.out.println("fill Table");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
		//		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() throws WinkException {
		System.out.println(" get  connection");

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/test5";
		String userName = "root";
		String password = "";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, userName, password);
			connection.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new WinkException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WinkException(e);
		}


		return connection;
	}
	
	@Override
	public User getUserDetails(int userID) {

		try {
			statement = connection.createStatement();
			String sqlStatement = "select * from userdetails where userID = "
					+ userID + "";
			rs = statement.executeQuery(sqlStatement);

			User user = null;
			while (rs.next()) {
				user = convertRSToUser(rs);

			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				statement.close();
		//		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public List<User> getUsers() {
		System.out.println("beginning " + new Date().getTime());
		try {
			statement = connection.createStatement();
			System.out.println("statement created" + new Date().getTime());
			String sqlStatement = "Select * from userdetails";
			rs = statement.executeQuery(sqlStatement);
			List<User> users = new ArrayList<User>();

			while (rs.next()) {
				User user = new User();
				user = convertRSToUser(rs);
				users.add(user);
			}
			System.out.println("the end" + new Date().getTime());
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				statement.close();
	//			connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public User convertRSToUser(ResultSet rs) throws SQLException {
		User user = null;
		String userName = null;
		String userAddress = null;
		String userTelNo = null;
		int userID = 0;
		
		user = new User();
		userID = rs.getInt("userID");
		userName = rs.getString("userName");
		userAddress = rs.getString("userAddress");
		userTelNo = rs.getString("userTelNo");

		user.setUserID(userID);
		user.setUserName(userName);
		user.setUserAddress(userAddress);
		user.setUserTelNo(userTelNo);

		return user;
	}


	@Override
	public void addUser(User user) {
		PreparedStatement statement;
		try {
			String sql = "INSERT INTO userdetails "
					+ "VALUES(?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getUserID());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getUserAddress());
			statement.setString(4, user.getUserTelNo());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
