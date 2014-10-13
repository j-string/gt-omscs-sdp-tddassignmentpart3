package edu.gatech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Justin Stringer
 */
public class Assignment {

	private Integer assignmentNumber;
	private String assignmentName;
	private String assignmentDescription;
	// Key is student name and value is student grade.
	private Map<String, Double> studentGradesMap = new HashMap<String, Double>();
	private Double averageGrade;

	/**
	 * @return the assignmentNumber
	 */
	public Integer getAssignmentNumber() {
		return assignmentNumber;
	}

	/**
	 * @param assignmentNumber
	 *            the assignmentNumber to set
	 */
	public void setAssignmentNumber(Integer assignmentNumber) {
		this.assignmentNumber = assignmentNumber;
	}

	/**
	 * @return the assignmentName
	 */
	public String getAssignmentName() {
		return assignmentName;
	}

	/**
	 * @param assignmentName
	 *            the assignmentName to set
	 */
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	/**
	 * @return the assignmentDescription
	 */
	public String getAssignmentDescription() {
		return assignmentDescription;
	}

	/**
	 * @param assignmentDescription
	 *            the assignmentDescription to set
	 */
	public void setAssignmentDescription(String assignmentDescription) {
		this.assignmentDescription = assignmentDescription;
	}

	/**
	 * @return the averageGrade
	 */
	public Double getAverageGrade() {

		Double totalStudentGrades = 0.0;
		int numberOfStudents = 0;

		for (Double studentGrade : this.studentGradesMap.values()) {
			totalStudentGrades += studentGrade;
			numberOfStudents++;
		}

		if (totalStudentGrades > 0) {
			this.averageGrade = totalStudentGrades / numberOfStudents;
		} else {
			this.averageGrade = 0.0;
		}

		return this.averageGrade;
	}

	/**
	 * @param averageGrade
	 *            the averageGrade to set
	 */
	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	/**
	 * @return
	 */
	public List<Double> getAllStudentGrades() {
		return new ArrayList<Double>(this.studentGradesMap.values());
	}

	/**
	 * @param studentName
	 * @param grade
	 */
	public void addStudentGrade(String studentName, Double grade) {
		this.studentGradesMap.put(studentName, grade);
	}

	/**
	 * @param studentName
	 * @return
	 */
	public Double getStudentGradeByStudentName(String studentName) {
		return this.studentGradesMap.get(studentName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Assignment [assignmentNumber=" + assignmentNumber
				+ ", assignmentDescription=" + assignmentDescription
				+ ", studentGradesMap=" + studentGradesMap + ", averageGrade="
				+ averageGrade + "]";
	}

}
