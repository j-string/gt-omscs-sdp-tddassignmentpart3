package edu.gatech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the information about a student. Has four private instance variables,
 * getters and setters for each instance variable, and a toString method.
 * 
 * @author Justin Stringer
 */
public class Student {

	private String name;
	private String gtid;
	private int attendance;
	private String emailAddress;
	// Project number is the key and team number is the value.
	private Map<Integer, Integer> projectTeamNumbers = new HashMap<Integer, Integer>();

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getGtid() {
		return gtid;
	}

	/**
	 * @param gtid
	 */
	public void setGtid(String gtid) {
		this.gtid = gtid;
	}

	/**
	 * @return
	 */
	public int getAttendance() {
		return attendance;
	}

	/**
	 * @param attendance
	 */
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	/**
	 * @return
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @param projectNumber
	 * @param teamNumber
	 */
	public void addProjectTeamNumber(Integer projectNumber, Integer teamNumber) {
		projectTeamNumbers.put(projectNumber, teamNumber);
	}

	/**
	 * @param projectNumber
	 * @return
	 */
	public Integer getTeamNumberByProjectNumber(Integer projectNumber) {
		return this.projectTeamNumbers.get(projectNumber);
	}

	/**
	 * @return
	 */
	public List<Integer> getAllProjectNumbers() {
		return new ArrayList<Integer>(this.projectTeamNumbers.keySet());
	}

	/**
	 * @return
	 */
	public List<Integer> getTeamNumbersOfAllTeamsStudentWasAMemberOf() {
		return new ArrayList<Integer>(this.projectTeamNumbers.values());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [name=" + name + ", gtid=" + gtid + ", attendance="
				+ attendance + ", emailAddress=" + emailAddress
				+ ", projectTeamNumbers=" + projectTeamNumbers + "]";
	}

}
