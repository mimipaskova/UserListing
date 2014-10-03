package source;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/users")
public class UserResource {

	private UserDAO userDao = UserDAOMongo.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() throws JsonProcessingException {
		System.out.println("beginning1 " + new Date().getTime());
		// UserDAO userDao = UserDAOMongo.getInstance();
		System.out.println("beginning2 " + new Date().getTime());
		List<User> users = null;

		users = userDao.getUsers();
		String result = new ObjectMapper().writeValueAsString(users);
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addUser(@Context HttpServletRequest request) {
		// System.out.println(request.getParameter("userID"));
		User user = new User();

		int userId = Integer.parseInt(request.getParameter("userID"));
		String userName = request.getParameter("userName");
		String userAddress = request.getParameter("userAddress");
		String userTelNo = request.getParameter("userTelNo");

		user.setUserID(userId);
		user.setUserName(userName);
		user.setUserAddress(userAddress);
		user.setUserTelNo(userTelNo);
		
		userDao.addUser(user);
	}

	@GET
	@Path("{studentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserName(@PathParam("studentid") int studentID)
			throws JsonProcessingException {
		// UserDAO userDao = UserDAOMongo.getInstance();
		User user = null;

		user = userDao.getUserDetails(studentID);
		String result = new ObjectMapper().writeValueAsString(user);

		return result;
	}

	@GET
	@Path("/fill")
	@Produces(MediaType.APPLICATION_JSON)
	public void fillTable() throws JsonProcessingException {
		// UserDAO userDao = UserDAOMongo.getInstance();
		userDao.fillTable();
	}

}
