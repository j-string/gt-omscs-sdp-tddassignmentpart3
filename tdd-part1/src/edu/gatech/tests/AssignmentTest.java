package edu.gatech.tests;

import junit.framework.TestCase;
import edu.gatech.Assignment;

/**
 * This JUnit test class tests the non-trivial public methods of the Assignment
 * class.
 * 
 * @author Justin Stringer
 */
public class AssignmentTest extends TestCase {

	private Assignment assignmentUnderTest;

	/**
	 * @throws java.lang.Exception
	 */
	protected static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected static void tearDownAfterClass() throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

		this.assignmentUnderTest = new Assignment();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		this.assignmentUnderTest = null;

		super.tearDown();
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageGradeWithTwoStudents() {
		String testStudentName1 = new String("test student name 1");
		Double testStudentGrade1 = new Double(23.33f);
		String testStudentName2 = new String("test student name 2");
		Double testStudentGrade2 = new Double(55.18f);

		this.assignmentUnderTest.addStudentGrade(testStudentName1,
				testStudentGrade1);
		this.assignmentUnderTest.addStudentGrade(testStudentName2,
				testStudentGrade2);

		Double expectedAverageGrade = (testStudentGrade1 + testStudentGrade2)
				/ this.assignmentUnderTest.getAllStudentGrades().size();

		Double actualAverageGrade = this.assignmentUnderTest.getAverageGrade();

		assertEquals(
				"expectedAverageGrade did not equal actualAverageGrade with two students",
				expectedAverageGrade, actualAverageGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageGradeWithZeroStudents() {

		Double expectedAverageGrade = 0.0;

		Double actualAverageGrade = this.assignmentUnderTest.getAverageGrade();

		assertEquals(
				"expectedAverageGrade did not equal actualAverageGrade with zero students",
				expectedAverageGrade, actualAverageGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageGradeWithOneStudent() {

		String testStudentName1 = new String("test student name 1");
		Double testStudentGrade1 = new Double(23.33f);

		this.assignmentUnderTest.addStudentGrade(testStudentName1,
				testStudentGrade1);

		Double expectedAverageGrade = (testStudentGrade1)
				/ this.assignmentUnderTest.getAllStudentGrades().size();

		Double actualAverageGrade = this.assignmentUnderTest.getAverageGrade();

		assertEquals(
				"expectedAverageGrade did not equal actualAverageGrade with one student",
				expectedAverageGrade, actualAverageGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageGradeWithFiveStudents() {
		String testStudentName1 = new String("test student name 1");
		Double testStudentGrade1 = new Double(23.33f);
		String testStudentName2 = new String("test student name 2");
		Double testStudentGrade2 = new Double(55.18f);
		String testStudentName3 = new String("test student name 3");
		Double testStudentGrade3 = new Double(0.0f);
		String testStudentName4 = new String("test student name 4");
		Double testStudentGrade4 = new Double(100.00f);
		String testStudentName5 = new String("test student name 5");
		Double testStudentGrade5 = new Double(0.25f);

		this.assignmentUnderTest.addStudentGrade(testStudentName1,
				testStudentGrade1);
		this.assignmentUnderTest.addStudentGrade(testStudentName2,
				testStudentGrade2);
		this.assignmentUnderTest.addStudentGrade(testStudentName3,
				testStudentGrade3);
		this.assignmentUnderTest.addStudentGrade(testStudentName4,
				testStudentGrade4);
		this.assignmentUnderTest.addStudentGrade(testStudentName5,
				testStudentGrade5);

		Double expectedAverageGrade = (testStudentGrade1 + testStudentGrade2
				+ testStudentGrade3 + testStudentGrade4 + testStudentGrade5)
				/ this.assignmentUnderTest.getAllStudentGrades().size();

		Double actualAverageGrade = this.assignmentUnderTest.getAverageGrade();

		assertEquals(
				"expectedAverageGrade did not equal actualAverageGrade with five students",
				expectedAverageGrade, actualAverageGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetStudentGradeByStudentNameWithOneStudent() {
		String testStudentName1 = new String("test student name 1");
		Double testStudentGrade1 = new Double(23.33f);

		this.assignmentUnderTest.addStudentGrade(testStudentName1,
				testStudentGrade1);

		Double actualGrade = this.assignmentUnderTest.getAverageGrade();

		assertEquals("wrong actualGrade!", testStudentGrade1, actualGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetStudentGradeByStudentNameWithZeroStudentsAndNonExistentStudentName() {
		Double actualGrade = this.assignmentUnderTest
				.getStudentGradeByStudentName("non-existant student");

		assertNull("wrong actualGrade!", actualGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetStudentGradeByStudentNameWithOneStudentsAndNonExistentStudentName() {
		String testStudentName1 = new String("test student name 1");
		Double testStudentGrade1 = new Double(23.33f);

		this.assignmentUnderTest.addStudentGrade(testStudentName1,
				testStudentGrade1);

		Double actualGrade = this.assignmentUnderTest
				.getStudentGradeByStudentName("non-existant student");

		assertNull("wrong actualGrade!", actualGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetStudentGradeByStudentNameWithTwoStudentsAndExistingStudentNames() {
		String testStudentName1 = new String("test student name 1");
		Double testStudentGrade1 = new Double(23.33f);

		String testStudentName2 = new String("test student name 2");
		Double testStudentGrade2 = new Double(44.33f);

		this.assignmentUnderTest.addStudentGrade(testStudentName1,
				testStudentGrade1);
		this.assignmentUnderTest.addStudentGrade(testStudentName2,
				testStudentGrade2);

		Double actualStudent1Grade = this.assignmentUnderTest
				.getStudentGradeByStudentName(testStudentName1);
		Double actualStudent2Grade = this.assignmentUnderTest
				.getStudentGradeByStudentName(testStudentName2);

		assertEquals("wrong student 1 grade!", testStudentGrade1,
				actualStudent1Grade);
		assertEquals("wrong student 2 grade!", testStudentGrade2,
				actualStudent2Grade);
	}

}
