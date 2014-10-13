package edu.gatech.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import edu.gatech.Student;

/**
 * This JUnit test class tests the non-trivial public methods of the Student
 * class.
 * 
 * @author Justin Stringer
 */
public class StudentTest extends TestCase {

	private Student studentUnderTest;

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

		this.studentUnderTest = new Student();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		this.studentUnderTest = null;

		super.tearDown();
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamNumberByProjectNumberWithTwoProjects() {
		Integer project1Number = 1;
		Integer project1TeamNumber = 1;
		Integer project2Number = 2;
		Integer project2TeamNumber = 2;

		this.studentUnderTest.addProjectTeamNumber(project1Number,
				project1TeamNumber);
		this.studentUnderTest.addProjectTeamNumber(project2Number,
				project2TeamNumber);

		Integer actualProject1TeamNumber = this.studentUnderTest
				.getTeamNumberByProjectNumber(project1Number);
		Integer actualProject2TeamNumber = this.studentUnderTest
				.getTeamNumberByProjectNumber(project2Number);

		assertEquals("wrong project 1 team number!", project1TeamNumber,
				actualProject1TeamNumber);
		assertEquals("wrong project 2 team number!", project2TeamNumber,
				actualProject2TeamNumber);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamNumberByProjectNumberWithZeroProjectsAndNonExistingProjectNumber() {
		Integer actualProjectTeamNumber = this.studentUnderTest
				.getTeamNumberByProjectNumber(99);

		assertNull("wrong project team number!", actualProjectTeamNumber);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamNumberByProjectNumberWithOneProjectAndNonExistingProjectNumber() {
		Integer project1Number = 1;
		Integer project1TeamNumber = 1;

		this.studentUnderTest.addProjectTeamNumber(project1Number,
				project1TeamNumber);

		Integer actualProjectTeamNumber = this.studentUnderTest
				.getTeamNumberByProjectNumber(99);

		assertNull("wrong project team number!", actualProjectTeamNumber);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamNumbersOfAllTeamsStudentWasAMemberOf() {
		Integer project1Number = 1;
		Integer project1TeamNumber = 1;
		Integer project2Number = 2;
		Integer project2TeamNumber = 2;

		this.studentUnderTest.addProjectTeamNumber(project1Number,
				project1TeamNumber);
		this.studentUnderTest.addProjectTeamNumber(project2Number,
				project2TeamNumber);

		List<Integer> expectedProjectTeamNumbers = new ArrayList<Integer>(
				Arrays.asList(project1TeamNumber, project2TeamNumber));

		List<Integer> actualProjectTeamNumbers = this.studentUnderTest
				.getTeamNumbersOfAllTeamsStudentWasAMemberOf();

		assertEquals("wrong project team numbers!", expectedProjectTeamNumbers,
				actualProjectTeamNumbers);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAllProjectNumbers() {
		Integer project1Number = 1;
		Integer project1TeamNumber = 1;
		Integer project2Number = 2;
		Integer project2TeamNumber = 2;

		this.studentUnderTest.addProjectTeamNumber(project1Number,
				project1TeamNumber);
		this.studentUnderTest.addProjectTeamNumber(project2Number,
				project2TeamNumber);

		List<Integer> expectedProjectNumbers = new ArrayList<Integer>(
				Arrays.asList(project1Number, project2Number));

		List<Integer> actualProjectNumbers = this.studentUnderTest
				.getAllProjectNumbers();

		assertEquals("wrong project numbers!", expectedProjectNumbers,
				actualProjectNumbers);
	}

}
