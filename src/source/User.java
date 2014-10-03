package source;

//import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class User {
	
	
	 private int userID;
	 private String userName;
	 private String userAddress;
	 private String userTelNo;

	 public User() {}

	 public User(int id, String name, String address, String telno) {
	   this.userID = id;
	   this.userName = name;
	   this.userAddress = address;
	   this.userTelNo = telno;
	 }

	 public int getUserID() {
	   return userID;
	 }

	 public void setUserID(int userID) {
	   this.userID = userID;
	 }

	 public String getUserName() {
	   return userName;
	 }

	 public void setUserName(String userName) {
	   this.userName = userName;
	 }

	 public String getUserAddress() {
	   return userAddress;
	 }

	public String getUserTelNo() {
		return userTelNo;
	}

	public void setUserTelNo(String userTelNo) {
		this.userTelNo = userTelNo;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
