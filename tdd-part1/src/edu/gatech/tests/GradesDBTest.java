package edu.gatech.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import junit.framework.TestCase;
import edu.gatech.Assignment;
import edu.gatech.Constants;
import edu.gatech.GradesDB;
import edu.gatech.Project;
import edu.gatech.Student;

/**
 * This JUnit test class tests the non-trivial public methods of the GradesDB
 * class.
 * 
 * @author Justin Stringer
 */
public class GradesDBTest extends TestCase {
	GradesDB db = null;

	protected void setUp() throws Exception {
		db = new GradesDB(Constants.GRADES_DB);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// ************************************************************************
	// *** BEGIN PART 2 TESTS *************************************************
	// ************************************************************************
	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForNonExistingProject() {
		try {
			Project project = db.getProjectByProjectNumber(99);
			assertNull("project found!", project);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForExistingProject() {
		try {
			Project project = db.getProjectByProjectNumber(1);
			assertNotNull("project not found!", project);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject1Name() {
		try {
			Project project = db.getProjectByProjectNumber(1);
			assertEquals("wrong project 1 name!", "P1",
					project.getProjectName());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject2Name() {
		try {
			Project project = db.getProjectByProjectNumber(2);
			assertEquals("wrong project 2 name!", "P2",
					project.getProjectName());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject3Name() {
		try {
			Project project = db.getProjectByProjectNumber(3);
			assertEquals("wrong project 3 name!", "P3",
					project.getProjectName());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject1Description() {
		try {
			Project project = db.getProjectByProjectNumber(1);
			assertEquals("wrong project 1 description!", "WordCount in Java",
					project.getProjectDescription());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject2Description() {
		try {
			Project project = db.getProjectByProjectNumber(2);
			assertEquals("wrong project 2 description!", "GroceryList App",
					project.getProjectDescription());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject3Description() {
		try {
			Project project = db.getProjectByProjectNumber(3);
			assertEquals("wrong project 3 description!", "GroceryList Manager",
					project.getProjectDescription());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject1TeamsSize() {
		try {
			Project project = db.getProjectByProjectNumber(1);
			assertEquals("wrong number of teams on project 1!", 3, project
					.getAllTeams().size());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject2TeamsSize() {
		try {
			Project project = db.getProjectByProjectNumber(2);
			assertEquals("wrong number of teams on project 2!", 3, project
					.getAllTeams().size());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetProjectByProjectNumberForProject3TeamsSize() {
		try {
			Project project = db.getProjectByProjectNumber(3);
			assertEquals("wrong number of teams on project 3!", 3, project
					.getAllTeams().size());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForNonExistingAssignment() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(99);
			assertNull("assignment found!", assignment);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForExistingAssignment1() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(1);
			assertNotNull("assignment 1 not found!", assignment);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForExistingAssignment2() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(2);
			assertNotNull("assignment 2 not found!", assignment);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForExistingAssignment3() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(3);
			assertNotNull("assignment 3 not found!", assignment);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForAssignment1Name() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(1);
			assertEquals("wrong assignment 1 name!", "Assignment 1",
					assignment.getAssignmentName());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForAssignment2Name() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(2);
			assertEquals("wrong assignment 2 name!", "Assignment 2",
					assignment.getAssignmentName());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForAssignment3Name() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(3);
			assertEquals("wrong assignment 3 name!", "Assignment 3",
					assignment.getAssignmentName());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForAssignment1Description() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(1);
			assertEquals("wrong assignment 1 description!", "swiki page",
					assignment.getAssignmentDescription());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForAssignment2Description() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(2);
			assertEquals("wrong assignment 2 description!",
					"software prototyping",
					assignment.getAssignmentDescription());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAssignmentByAssignmentNumberForAssignment3Description() {
		try {
			Assignment assignment = db.getAssignmentByAssignmentNumber(3);
			assertEquals("wrong assignment 3 description!",
					"junit and coverage", assignment.getAssignmentDescription());
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetStudentAdminInfoForOutputByStudentNameForStudent1() {
		String testStudentName = "Freddie Catlay";
		StringBuffer expectedStudentAdminInfoOutputTextSB = new StringBuffer(
				"Name: ");
		expectedStudentAdminInfoOutputTextSB.append(testStudentName).append(
				"\n");
		expectedStudentAdminInfoOutputTextSB.append("GTID: ")
				.append("901234501").append("\n");
		expectedStudentAdminInfoOutputTextSB.append("EMAIL: ")
				.append("fc@gatech.edu").append("\n");
		expectedStudentAdminInfoOutputTextSB.append("ATTENDANCE: ")
				.append("93.33%").append("\n");

		try {
			String actualStudentAdminInfoOutputText = db
					.getStudentAdminInfoForOutputByStudentName(testStudentName);
			assertEquals(
					"wrong studentAdminInfoOutputText for student with name "
							+ testStudentName + "!",
					expectedStudentAdminInfoOutputTextSB.toString(),
					actualStudentAdminInfoOutputText);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetStudentProjectInfoForOutputByStudentNameAndProjectNumber() {
		String testStudentName = "Freddie Catlay";
		Integer testProjectNumber = 1;

		StringBuffer expectedStudentProjectInfoOutputTextSB = new StringBuffer();

		expectedStudentProjectInfoOutputTextSB.append("PROJECT 1: ").append(
				"\n");
		expectedStudentProjectInfoOutputTextSB.append("PROJECT 1 NAME: ")
				.append("P1").append("\n");
		expectedStudentProjectInfoOutputTextSB
				.append("PROJECT 1 DESCRIPTION: ").append("WordCound in Java")
				.append("\n");
		expectedStudentProjectInfoOutputTextSB
				.append("PROJECT 1 TEAM NUMBER: ").append("1").append("\n");
		expectedStudentProjectInfoOutputTextSB.append("PROJECT 1 TEAM GRADE: ")
				.append("93").append("\n");
		expectedStudentProjectInfoOutputTextSB
				.append("PROJECT 1 AVERAGE TEAM GRADE: ").append("93")
				.append("\n");
		expectedStudentProjectInfoOutputTextSB
				.append("PROJECT 1 AVERAGE TEAM CONTRIBUTION: ").append("9.25")
				.append("\n");

		try {
			String actualStudentProjectInfoOutputText = db
					.getStudentProjectInfoForOutputByStudentNameAndProjectNumber(
							testStudentName, testProjectNumber);
			assertEquals(
					"wrong studentProjectInfoOutputText for student with name "
							+ testStudentName + "!",
					expectedStudentProjectInfoOutputTextSB.toString(),
					actualStudentProjectInfoOutputText);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetStudentAssignmentInfoForOutputByStudentNameAndAssignmentNumber() {
		String testStudentName = "Freddie Catlay";
		Integer testAssignmentNumber = 1;

		StringBuffer expectedStudentAssignmentInfoOutputTextSB = new StringBuffer();
		expectedStudentAssignmentInfoOutputTextSB.append("ASSIGNMENT 1: ")
				.append("\n");
		expectedStudentAssignmentInfoOutputTextSB.append("ASSIGNMENT 1 NAME: ")
				.append("Assignment 1").append("\n");
		expectedStudentAssignmentInfoOutputTextSB
				.append("ASSIGNMENT 1 DESCRIPTION: ").append("swiki page")
				.append("\n");
		expectedStudentAssignmentInfoOutputTextSB
				.append("ASSIGNMENT 1 GRADE: ").append("100").append("\n");
		expectedStudentAssignmentInfoOutputTextSB
				.append("ASSIGNMENT 1 AVERAGE GRADE: ").append("99%")
				.append("\n");
		try {
			String actualStudentAssignmentInfoOutputText = db
					.getStudentAssignmentInfoForOutputByStudentNameAndAssignmentNumber(
							testStudentName, testAssignmentNumber);
			assertEquals(
					"wrong studentAssignmentInfoOutputText for student with name "
							+ testStudentName + "!",
					expectedStudentAssignmentInfoOutputTextSB.toString(),
					actualStudentAssignmentInfoOutputText);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testGetAllStudentInfoForOutputByStudentNameForStudent() {
		String testStudentName = "Freddie Catlay";

		StringBuffer expectedAllStudentInfoOutputTextSB = new StringBuffer(
				"Name: ");
		expectedAllStudentInfoOutputTextSB.append(testStudentName).append("\n");
		expectedAllStudentInfoOutputTextSB.append("GTID: ").append("901234501")
				.append("\n");
		expectedAllStudentInfoOutputTextSB.append("EMAIL: ")
				.append("fc@gatech.edu").append("\n");
		expectedAllStudentInfoOutputTextSB.append("ATTENDANCE: ")
				.append("93.33%").append("\n");

		expectedAllStudentInfoOutputTextSB.append("PROJECT DETAILS: ").append(
				"\n");

		expectedAllStudentInfoOutputTextSB.append("PROJECT 1: ").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 1 NAME: ")
				.append("P1").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 1 DESCRIPTION: ")
				.append("WordCound in Java").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 1 TEAM NUMBER: ")
				.append("1").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 1 TEAM GRADE: ")
				.append("93").append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("PROJECT 1 AVERAGE TEAM GRADE: ").append("93")
				.append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("PROJECT 1 AVERAGE TEAM CONTRIBUTION: ").append("9.25")
				.append("\n");

		expectedAllStudentInfoOutputTextSB.append("PROJECT 2: ").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 2 NAME: ")
				.append("P2").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 2 DESCRIPTION: ")
				.append("GroceryList App").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 2 TEAM NUMBER: ")
				.append("1").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 2 TEAM GRADE: ")
				.append("95").append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("PROJECT 2 AVERAGE TEAM GRADE: ").append("92")
				.append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("PROJECT 2 AVERAGE TEAM CONTRIBUTION: ").append("9.00")
				.append("\n");

		expectedAllStudentInfoOutputTextSB.append("PROJECT 3: ").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 3 NAME: ")
				.append("P3").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 3 DESCRIPTION: ")
				.append("GroceryList Manager").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 3 TEAM NUMBER: ")
				.append("1").append("\n");
		expectedAllStudentInfoOutputTextSB.append("PROJECT 3 TEAM GRADE: ")
				.append("100").append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("PROJECT 3 AVERAGE TEAM GRADE: ").append("99")
				.append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("PROJECT 3 AVERAGE TEAM CONTRIBUTION: ").append("8.75")
				.append("\n");

		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENTS: ").append("\n");

		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 1: ")
				.append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 1 NAME: ")
				.append("Assignment 1").append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 1 DESCRIPTION: ")
				.append("swiki page").append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 1 GRADE: ")
				.append("100").append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("ASSIGNMENT 1 AVERAGE GRADE: ").append("99%")
				.append("\n");

		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 2: ")
				.append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 2 NAME: ")
				.append("Assignment 2").append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 2 DESCRIPTION: ")
				.append("software prototyping").append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 2 GRADE: ")
				.append("95").append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("ASSIGNMENT 2 AVERAGE GRADE: ").append("100%")
				.append("\n");

		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 3: ")
				.append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 3 NAME: ")
				.append("Assignment 3").append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 3 DESCRIPTION: ")
				.append("junit and coverage").append("\n");
		expectedAllStudentInfoOutputTextSB.append("ASSIGNMENT 3 GRADE: ")
				.append("75").append("\n");
		expectedAllStudentInfoOutputTextSB
				.append("ASSIGNMENT 3 AVERAGE GRADE: ").append("77%")
				.append("\n");

		try {
			String actualAllStudentInfoOutputText = db
					.getAllStudentInfoForOutputByStudentName(testStudentName);
			assertEquals("wrong studentInfoOutputText for student with name "
					+ testStudentName + "!",
					expectedAllStudentInfoOutputTextSB.toString(),
					actualAllStudentInfoOutputText);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	/**
	 * Method name describes test scenario.
	 */
	public void testWriteStudentInfoToFile() {
		String testStudentName = "test jim";
		String testStudentInfoOutputTextLine1 = "test student info output text line 1";
		String testStudentInfoOutputTextLine2 = "test student info output text line 2";

		try {
			this.db.writeStudentInfoToFile(testStudentName,
					testStudentInfoOutputTextLine1 + "\n"
							+ testStudentInfoOutputTextLine2);
		} catch (FileNotFoundException fileNotFoundException) {
			fail("FileNotFoundException on method under test!");
		}

		File testOutputFile = new File(testStudentName.replaceAll("\\s+", "")
				+ ".txt");

		assertTrue("output file does not exist!", testOutputFile.exists());

		BufferedReader testOutputFileBR = null;

		try {
			testOutputFileBR = new BufferedReader(
					new FileReader(testOutputFile));

			assertEquals("", testStudentInfoOutputTextLine1,
					testOutputFileBR.readLine());
			assertEquals("", testStudentInfoOutputTextLine2,
					testOutputFileBR.readLine());
			assertNull("", testOutputFileBR.readLine());

		} catch (FileNotFoundException e) {
			fail("FileNotFoundException occurred!");
		} catch (IOException e) {
			fail("IOException occurred!");
		} finally {
			try {
				if (testOutputFileBR != null) {
					testOutputFileBR.close();
					testOutputFile.delete();
				}
			} catch (IOException e) {
				fail("IOException occurred in finally block!");
			}
		}

	}

	// ************************************************************************
	// *** END PART 2 TESTS ***************************************************
	// ************************************************************************

	public void testGetNumStudents() {
		try {
			int numStudents = db.getNumStudents();
			assertEquals(14, numStudents);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	public void testGetNumAssignments() {
		try {
			int numAssignments = db.getNumAssignments();
			assertEquals(3, numAssignments);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	public void testGetNumProjects() {
		int numProjects;
		try {
			numProjects = db.getNumProjects();
			assertEquals(3, numProjects);
		} catch (Exception e) {
			fail("Exception");
		}
	}

	public void testGetStudents1() {
		HashSet<Student> students = null;
		try {
			students = db.getStudents();
		} catch (Exception e) {
			fail("Exception");
		}
		assertEquals(14, students.size());
	}

	public void testGetStudents2() {
		HashSet<Student> students = null;
		try {
			students = db.getStudents();
		} catch (Exception e) {
			fail("Exception");
		}
		boolean found = false;
		for (Student s : students) {
			if ((s.getName().compareTo("Cynthia Faast") == 0)
					&& (s.getGtid().compareTo("901234514") == 0)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	public void testGetStudentsByName1() {
		Student student = null;
		try {
			student = db.getStudentByName("Rastus Kight");
		} catch (Exception e) {
			fail("Exception");
		}
		assertTrue(student.getGtid().compareTo("901234512") == 0);
	}

	public void testGetStudentsByName2() {
		Student student = null;
		try {
			student = db.getStudentByName("Grier Nehling");
		} catch (Exception e) {
			fail("Exception");
		}
		assertEquals(96, student.getAttendance());
	}

	public void testGetStudentsByID() {
		try {
			Student student = db.getStudentByID("901234504");
			assertTrue(student.getName().compareTo("Shevon Wise") == 0);
		} catch (Exception e) {
			fail("Exception");
		}
	}

}
