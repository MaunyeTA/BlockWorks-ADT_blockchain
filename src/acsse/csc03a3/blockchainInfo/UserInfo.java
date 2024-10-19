package acsse.csc03a3.blockchainInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserInfo implements Serializable{

	private String userID = "";
    private String name = "";
    private String surname = "";
    private String companyName = "";
    private String EmployerID = "";
    private String position = "";
    private String startDate = "";
    private String endDate = "";
    private String comment = "";

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{"
        		+ "'\t" + userID
        		+ "\t" + name 
        		+ "\t" + surname 
        		+ "\t" + companyName 
        		+ "\t" + EmployerID
        		+ "\t" + position 
        		+ "\t" + startDate 
        		+ "\t" + endDate 
        		+ "\t" + comment 
        		+ "'\t}";
    }

    public static UserInfo RecreateUserInfo(String strInfo) {
    	
    	UserInfo userInfo = new UserInfo();
    	
    	String[] parts = strInfo.split("\t");
    	
    	userInfo.setUserID(parts[1]);
    	userInfo.setName(parts[2]);
    	userInfo.setSurname(parts[3]);
    	userInfo.setCompanyName(parts[4]);
    	userInfo.setEmployerID(parts[5]);
    	userInfo.setPosition(parts[6]);
    	userInfo.setStartDate(parts[7]);
    	userInfo.setEndDate(parts[8]);
    	userInfo.setComment(parts[9]);
    	
    	return userInfo;
    }
    
   

    /**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * @return the userID
	 */
	public String getEmployerID() {
		return EmployerID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setEmployerID(String EmployerID) {
		this.EmployerID = EmployerID;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


}
