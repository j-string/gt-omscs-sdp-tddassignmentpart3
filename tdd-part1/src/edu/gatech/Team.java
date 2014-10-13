package edu.gatech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Justin Stringer
 */
public class Team {

	private Integer teamNumber;
	private String teamName;
	private Double teamGrade;
	// Key is student name and value is his or her average contribution rating.
	private Map<String, Double> averageContributionRatingsMap = new HashMap<String, Double>();

	/**
	 * @return the teamNumber
	 */
	public Integer getTeamNumber() {
		return teamNumber;
	}

	/**
	 * @param teamNumber
	 *            the teamNumber to set
	 */
	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName
	 *            the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the teamGrade
	 */
	public Double getTeamGrade() {
		return teamGrade;
	}

	/**
	 * @param teamGrade
	 *            the teamGrade to set
	 */
	public void setTeamGrade(Double teamGrade) {
		this.teamGrade = teamGrade;
	}

	/**
	 * @return
	 */
	public List<String> getAllTeamMemberStudentNames() {
		return new ArrayList<String>(
				this.averageContributionRatingsMap.keySet());
	}

	/**
	 * @return
	 */
	public List<Double> getAllTeamMemberAverageContributionRatings() {
		return new ArrayList<Double>(
				this.averageContributionRatingsMap.values());
	}

	/**
	 * @param studentName
	 * @param averageContributionRating
	 */
	public void addTeamMemberAverageContributionRating(String studentName,
			Double averageContributionRating) {
		this.averageContributionRatingsMap.put(studentName,
				averageContributionRating);
	}

	/**
	 * @param studentName
	 * @return
	 */
	public Double getTeamMemberAverageContributionRatingByStudentName(
			String studentName) {
		return this.averageContributionRatingsMap.get(studentName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Team [teamNumber=" + teamNumber + ", teamGrade=" + teamGrade
				+ ", averageContributionRatingsMap="
				+ averageContributionRatingsMap + "]";
	}

}
