package edu.gatech.tests;

import java.util.List;

import junit.framework.TestCase;
import edu.gatech.Team;

/**
 * This JUnit test class tests the non-trivial public methods of the Team class.
 * 
 * @author Justin Stringer
 */
public class TeamTest extends TestCase {

	private Team teamUnderTest;

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

		this.teamUnderTest = new Team();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		this.teamUnderTest = null;

		super.tearDown();
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamMemberAverageContributionRatingByStudentNameWithTwoTeamMembers() {
		String teamMember1StudentName = "team member 1 student name";
		Double teamMember1AverageContributionRating = 9.3;
		String teamMember2StudentName = "team member 2 student name";
		Double teamMember2AverageContributionRating = 1.3;

		this.teamUnderTest.addTeamMemberAverageContributionRating(
				teamMember1StudentName, teamMember1AverageContributionRating);
		this.teamUnderTest.addTeamMemberAverageContributionRating(
				teamMember2StudentName, teamMember2AverageContributionRating);

		Double actualTeamMember1AverageContributionRating = this.teamUnderTest
				.getTeamMemberAverageContributionRatingByStudentName(teamMember1StudentName);
		Double actualTeamMember2AverageContributionRating = this.teamUnderTest
				.getTeamMemberAverageContributionRatingByStudentName(teamMember2StudentName);

		assertEquals("wrong team member 1 average contribution rating!",
				teamMember1AverageContributionRating,
				actualTeamMember1AverageContributionRating);
		assertEquals("wrong team member 2 average contribution rating!",
				teamMember2AverageContributionRating,
				actualTeamMember2AverageContributionRating);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamMemberAverageContributionRatingByStudentNameWithZeroTeamMembersAndNonExistingStudenName() {
		Double actualAverageContributionRating = this.teamUnderTest
				.getTeamMemberAverageContributionRatingByStudentName("non-existing student name");

		assertNull("wrong average contribution rating!",
				actualAverageContributionRating);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetTeamMemberAverageContributionRatingByStudentNameWithTwoTeamMembersAndNonExistingStudenName() {
		String teamMember1StudentName = "team member 1 student name";
		Double teamMember1AverageContributionRating = 9.3;

		this.teamUnderTest.addTeamMemberAverageContributionRating(
				teamMember1StudentName, teamMember1AverageContributionRating);

		Double actualAverageContributionRating = this.teamUnderTest
				.getTeamMemberAverageContributionRatingByStudentName("non-existing student name");

		assertNull("wrong average contribution rating!",
				actualAverageContributionRating);
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAllTeamMemberStudentNamesWithTwoTeamMembers() {
		String teamMember1StudentName = "team member 1 student name";
		Double teamMember1AverageContributionRating = 9.3;
		String teamMember2StudentName = "team member 2 student name";
		Double teamMember2AverageContributionRating = 1.3;

		this.teamUnderTest.addTeamMemberAverageContributionRating(
				teamMember1StudentName, teamMember1AverageContributionRating);
		this.teamUnderTest.addTeamMemberAverageContributionRating(
				teamMember2StudentName, teamMember2AverageContributionRating);

		Integer numberOfTeamMembers = this.teamUnderTest
				.getAllTeamMemberStudentNames().size();
		List<String> actualTeamMemberStudentNames = this.teamUnderTest
				.getAllTeamMemberStudentNames();

		assertTrue("wrong number of team members!", numberOfTeamMembers == 2);
		assertTrue("team member 1 student name not found!",
				actualTeamMemberStudentNames.contains(teamMember1StudentName));
		assertTrue("team member 2 student name not found!",
				actualTeamMemberStudentNames.contains(teamMember2StudentName));
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAllTeamMemberAverageContributionRatings() {
		String teamMember1StudentName = "team member 1 student name";
		Double teamMember1AverageContributionRating = 9.3;
		String teamMember2StudentName = "team member 2 student name";
		Double teamMember2AverageContributionRating = 1.3;

		this.teamUnderTest.addTeamMemberAverageContributionRating(
				teamMember1StudentName, teamMember1AverageContributionRating);
		this.teamUnderTest.addTeamMemberAverageContributionRating(
				teamMember2StudentName, teamMember2AverageContributionRating);

		Integer numberOfTeamMembers = this.teamUnderTest
				.getAllTeamMemberAverageContributionRatings().size();
		List<Double> actualTeamMemberAverageContributionRatings = this.teamUnderTest
				.getAllTeamMemberAverageContributionRatings();

		assertTrue("wrong number of team members!", numberOfTeamMembers == 2);
		assertTrue("team member 1 average contribution rating not found!",
				actualTeamMemberAverageContributionRatings
						.contains(teamMember1AverageContributionRating));
		assertTrue("team member 2 average contribution rating not found!",
				actualTeamMemberAverageContributionRatings
						.contains(teamMember2AverageContributionRating));
	}

}
