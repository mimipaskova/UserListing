package source;

import java.util.List;

public interface UserDAO {
	
	public void fillTable();


	public User getUserDetails(int userID) ;

	public List<User> getUsers() ;		

//	public User convertRSToUser(ResultSet rs) throws Exception;
	
	public void addUser(User user);

}
