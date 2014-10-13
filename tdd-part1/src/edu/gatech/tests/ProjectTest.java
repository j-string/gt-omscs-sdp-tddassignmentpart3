package edu.gatech.tests;

import junit.framework.TestCase;
import edu.gatech.Project;
import edu.gatech.Team;

/**
 * This JUnit test class tests the non-trivial public methods of the Project
 * class.
 * 
 * @author Justin Stringer
 */
public class ProjectTest extends TestCase {

	private Project projectUnderTest;

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

		this.projectUnderTest = new Project();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		this.projectUnderTest = null;

		super.tearDown();
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageProjectGradeWithTwoTeams() {
		Team testTeam1 = new Team();
		testTeam1.setTeamGrade(100.0);
		Team testTeam2 = new Team();
		testTeam2.setTeamGrade(50.0);

		this.projectUnderTest.addTeam(1, testTeam1);
		this.projectUnderTest.addTeam(2, testTeam2);

		Double expectedAverageProjectGrade = (testTeam1.getTeamGrade() + testTeam2
				.getTeamGrade()) / this.projectUnderTest.getAllTeams().size();

		Double actualAverageProjectGrade = this.projectUnderTest
				.getAverageProjectGrade();

		assertEquals(
				"expectedAverageProjectGrade did not equal actualAverageProjectGrade with two teams",
				expectedAverageProjectGrade, actualAverageProjectGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageProjectGradeWithZeroTeams() {

		Double expectedAverageProjectGrade = 0.0;

		Double actualAverageProjectGrade = this.projectUnderTest
				.getAverageProjectGrade();

		assertEquals(
				"expectedAverageProjectGrade did not equal actualAverageProjectGrade with zero teams",
				expectedAverageProjectGrade, actualAverageProjectGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageProjectGradeWithOneTeam() {
		Team testTeam1 = new Team();
		testTeam1.setTeamGrade(77.33);

		this.projectUnderTest.addTeam(1, testTeam1);

		Double expectedAverageProjectGrade = testTeam1.getTeamGrade();

		Double actualAverageProjectGrade = this.projectUnderTest
				.getAverageProjectGrade();

		assertEquals(
				"expectedAverageProjectGrade did not equal actualAverageProjectGrade with one team",
				expectedAverageProjectGrade, actualAverageProjectGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAverageProjectGradeWithFiveTeams() {
		Integer testTeam1Number = 1;
		Team testTeam1 = new Team();
		testTeam1.setTeamGrade(77.33);
		Integer testTeam2Number = 2;
		Team testTeam2 = new Team();
		testTeam2.setTeamGrade(0.0);
		Integer testTeam3Number = 3;
		Team testTeam3 = new Team();
		testTeam3.setTeamGrade(100.0);
		Integer testTeam4Number = 4;
		Team testTeam4 = new Team();
		testTeam4.setTeamGrade(0.95);
		Integer testTeam5Number = 5;
		Team testTeam5 = new Team();
		testTeam5.setTeamGrade(41.33);

		this.projectUnderTest.addTeam(testTeam1Number, testTeam1);
		this.projectUnderTest.addTeam(testTeam2Number, testTeam2);
		this.projectUnderTest.addTeam(testTeam3Number, testTeam3);
		this.projectUnderTest.addTeam(testTeam4Number, testTeam4);
		this.projectUnderTest.addTeam(testTeam5Number, testTeam5);

		Double expectedAverageProjectGrade = (testTeam1.getTeamGrade()
				+ testTeam2.getTeamGrade() + testTeam3.getTeamGrade()
				+ testTeam4.getTeamGrade() + testTeam5.getTeamGrade())
				/ this.projectUnderTest.getAllTeams().size();

		Double actualAverageProjectGrade = this.projectUnderTest
				.getAverageProjectGrade();

		assertEquals(
				"expectedAverageProjectGrade did not equal actualAverageProjectGrade with five teams!",
				expectedAverageProjectGrade, actualAverageProjectGrade);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamByTeamNumberWithOneTeam() {
		Integer testTeam1Number = 1;
		Team testTeam1 = new Team();
		testTeam1.setTeamGrade(77.33);

		this.projectUnderTest.addTeam(testTeam1Number, testTeam1);

		Team actualTeam1 = this.projectUnderTest
				.getTeamByTeamNumber(testTeam1Number);

		assertEquals("wrong team 1!", testTeam1, actualTeam1);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamByTeamNumberWithOneTeamAndNonExistantTeamNumber() {
		Integer testTeam1Number = 1;
		Team testTeam1 = new Team();
		testTeam1.setTeamGrade(77.33);

		this.projectUnderTest.addTeam(testTeam1Number, testTeam1);

		Team actualTeam1 = this.projectUnderTest.getTeamByTeamNumber(99);

		assertNull("team found!", actualTeam1);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamByTeamNumberWithZeroTeamsAndNonExistantTeamNumber() {
		Team actualTeam1 = this.projectUnderTest.getTeamByTeamNumber(99);

		assertNull("team found!", actualTeam1);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamByTeamNumberWithTwoTeams() {
		Integer testTeam1Number = 1;
		Team testTeam1 = new Team();
		testTeam1.setTeamGrade(77.33);
		Integer testTeam2Number = 2;
		Team testTeam2 = new Team();
		testTeam2.setTeamGrade(0.0);

		this.projectUnderTest.addTeam(testTeam1Number, testTeam1);
		this.projectUnderTest.addTeam(testTeam2Number, testTeam2);

		Team actualTeam1 = this.projectUnderTest
				.getTeamByTeamNumber(testTeam1Number);
		Team actualTeam2 = this.projectUnderTest
				.getTeamByTeamNumber(testTeam2Number);

		assertEquals("wrong team 1!", testTeam1, actualTeam1);
		assertEquals("wrong team 2!", testTeam2, actualTeam2);
	}

}
