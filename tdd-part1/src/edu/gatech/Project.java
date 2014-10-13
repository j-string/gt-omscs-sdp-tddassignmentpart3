package edu.gatech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Justin Stringer
 */
public class Project {

	private Integer projectNumber;
	private String projectName;
	private String projectDescription;
	// Key is team number and value is the team.
	private Map<Integer, Team> teamsMap = new HashMap<Integer, Team>();
	private Double averageProjectGrade;

	/**
	 * @return the projectNumber
	 */
	public Integer getProjectNumber() {
		return projectNumber;
	}

	/**
	 * @param projectNumber
	 *            the projectNumber to set
	 */
	public void setProjectNumber(Integer projectNumber) {
		this.projectNumber = projectNumber;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectDescription
	 */
	public String getProjectDescription() {
		return projectDescription;
	}

	/**
	 * @param projectDescription
	 *            the projectDescription to set
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	/**
	 * @return the teams
	 */
	public List<Team> getAllTeams() {
		return new ArrayList<Team>(this.teamsMap.values());
	}

	/**
	 * @param teamNumber
	 * @return
	 */
	public Team getTeamByTeamNumber(Integer teamNumber) {
		return this.teamsMap.get(teamNumber);
	}

	/**
	 * @param teamNumber
	 * @param team
	 */
	public void addTeam(Integer teamNumber, Team team) {
		this.teamsMap.put(teamNumber, team);
	}

	/**
	 * @return the averageProjectGrade
	 */
	public Double getAverageProjectGrade() {
		Double totalTeamScore = 0.0;
		int numberOfTeams = 0;

		for (Team team : this.teamsMap.values()) {
			totalTeamScore += team.getTeamGrade();
			numberOfTeams++;
		}

		if (totalTeamScore > 0) {
			this.averageProjectGrade = totalTeamScore / numberOfTeams;
		} else {
			this.averageProjectGrade = 0.0;
		}

		return this.averageProjectGrade;
	}

	/**
	 * @param averageProjectGrade
	 *            the averageProjectGrade to set
	 */
	public void setAverageProjectGrade(Double averageProjectGrade) {
		this.averageProjectGrade = averageProjectGrade;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Project [projectNumber=" + projectNumber
				+ ", projectDescription=" + projectDescription + ", teamsMap="
				+ teamsMap + ", averageProjectGrade=" + averageProjectGrade
				+ "]";
	}

}
