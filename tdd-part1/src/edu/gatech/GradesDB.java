package edu.gatech;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Encapsulates most of the system functionality for interacting with an
 * Microsoft Excel spreadsheet file containing information about students,
 * assignments, and projects for a course. It provides an intuitive interface
 * towards the information in the spreadsheet. The instructor (or the TA)
 * launches the GradesTool application to get information about the students and
 * the class. The application prints the number of students and information
 * about each student: name, GT ID, e-mail, and attendance. It also prints the
 * number of assignments and projects given so far. After that, it asks the user
 * if (s)he wants to continue or exit. In the former case, it prints the latest
 * information again. It assumes that the Excel spreadsheet name listed in the
 * Constants class is in the current working directory.
 * 
 * @author Justin Stringer
 */
public class GradesDB {

	private final static Logger LOGGER = Logger.getLogger(GradesDB.class
			.getName());

	// Constants for the Excel spreadsheet are all configured as static finals
	// at the top for maintainability.
	private final static String STUDENTS_SHEET_NAME = "Details";
	private final static String STUDENTS_SHEET_NAME_COLUMN_HEADER = "NAME";
	private final static int STUDENTS_SHEET_NAME_COLUMN_INDEX = 0;
	private final static int STUDENTS_SHEET_GTID_COLUMN_INDEX = 1;
	private final static int STUDENTS_SHEET_EMAILADDRESS_COLUMN_INDEX = 2;

	private final static String ATTENDANCE_SHEET_NAME = "Attendance";
	private final static int ATTENDANCE_SHEET_STUDENT_NAME_COLUMN_INDEX = 0;
	private final static int ATTENDANCE_SHEET_TOTAL_COLUMN_INDEX = 1;

	private final static String PROJECTS_AND_ASSIGNMENTS_SHEET_NAME = "Data";
	private final static int PROJECTS_AND_ASSIGNMENTS_SHEET_PROJECTS_COLUMN_INDEX = 0;
	private final static String PROJECTS_AND_ASSIGNMENTS_SHEET_PROJECTS_COLUMN_HEADER = "Projects";
	private final static int PROJECTS_AND_ASSIGNMENTS_SHEET_ASSIGNMENTS_COLUMN_INDEX = 3;
	private final static String PROJECTS_AND_ASSIGNMENTS_SHEET_ASSIGNMENTS_COLUMN_HEADER = "Assignments";

	// Use an inner class to be able to store and retrieve the set of student
	// objects by either their names or their GT IDs.
	private StudentsTwoKeyHashMap studentsTwoKeyHashMap;
	private int numAssignments;
	private int numProjects;
	// Key is project number and value is project.
	private Map<Integer, Project> projects = new HashMap<Integer, Project>();
	// Key is assignment number and value is assignment.
	private Map<Integer, Assignment> assignments = new HashMap<Integer, Assignment>();

	/**
	 * Disable the zero argument constructor to prevent someone from using the
	 * class without an Excel spreadsheet.
	 */
	@SuppressWarnings("unused")
	private GradesDB() {
	}

	/**
	 * Uses the file name passed to it to populate student and class information
	 * on instance variables.
	 * 
	 * @param excelSpreadsheetName
	 */
	public GradesDB(String excelSpreadsheetName) {
		// Turn off all logging but SEVERE level statements.
		LOGGER.setLevel(Level.SEVERE);
		LOGGER.finest("ENTER");
		LOGGER.fine("excelSpreadsheetName=" + excelSpreadsheetName);
		Workbook workbook = null;

		// Try to load the Excel spreadsheet listed in the constants file.
		// If the file can't be loaded, then try to tell the user why.
		try {
			workbook = WorkbookFactory.create(new FileInputStream(
					excelSpreadsheetName));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			LOGGER.severe("Input file is not a valid Excel Spreadsheet!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.severe("Input file is not found in working directory = \""
					+ System.getProperty("user.dir") + "\"");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.severe("An error occurred reading the input file!");
		}

		// Load the students, including their attendance.
		this.setStudentsTwoKeyHashMap(this.getStudentsFromWorkbook(workbook));

		this.setNumAssignments(this.getNumAssignmentsFromWorkbook(workbook));

		this.setNumProjects(this.getNumProjectsFromWorkbook(workbook));

		LOGGER.finest("EXIT");
	}

	/**
	 * The main method provides an intuitive interface towards the information
	 * in the spreadsheet. The instructor (or the TA) launches the GradesTool
	 * application to get information about the students and the class. The
	 * application prints the number of students and information about each
	 * student: name, GT ID, e-mail, and attendance. It also prints the number
	 * of assignments and projects given so far. After that, it asks the user if
	 * (s)he wants to continue or exit. In the former case, it prints the latest
	 * information again. It does not use any command line arguments.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean again = true;
		Scanner scanner = new Scanner(System.in);
		while (again) {
			GradesDB gradesDB = new GradesDB(Constants.GRADES_DB);

			System.out.println("*** CS 6300 Class Information ***");
			System.out.println("Number of Students: "
					+ gradesDB.getNumStudents());
			if (gradesDB.getNumStudents() > 0) {
				System.out.println("Students information:");

				int outputRowNumber = 1;
				for (Student student : gradesDB.getStudents()) {
					StringBuffer outputStudentRow = new StringBuffer("  "
							+ outputRowNumber + ") ");
					outputStudentRow.append(student.getName());
					outputStudentRow.append(", GT ID: ");
					outputStudentRow.append(student.getGtid());
					outputStudentRow.append(", E-mail: ");
					outputStudentRow.append(student.getEmailAddress());
					outputStudentRow.append(", Attendance: ");
					outputStudentRow.append(student.getAttendance());
					outputStudentRow.append("%");
					System.out.println(outputStudentRow.toString());
					++outputRowNumber;
				}
			}

			System.out.println("Class information:");
			System.out.println("Number of assignments: "
					+ gradesDB.getNumAssignments());
			System.out.println("Number of projects: "
					+ gradesDB.getNumProjects());

			System.out.println("Continue [Y/n]");

			String keyboardInput = scanner.nextLine();

			if ("n".equalsIgnoreCase(keyboardInput)) {
				again = false;
			}
		}
		scanner.close();
	}

	/**
	 * @return
	 */
	private StudentsTwoKeyHashMap getStudentsTwoKeyHashMap() {
		return studentsTwoKeyHashMap;
	}

	/**
	 * @param studentsTwoKeyHashMap
	 */
	private void setStudentsTwoKeyHashMap(
			StudentsTwoKeyHashMap studentsTwoKeyHashMap) {
		this.studentsTwoKeyHashMap = studentsTwoKeyHashMap;
	}

	/**
	 * Returns a HashSet of all students
	 * 
	 * @return students
	 */
	public HashSet<Student> getStudents() {
		return new HashSet<Student>(
				this.studentsTwoKeyHashMap
						.getStudentsFromTwoKeyHashMapAsHashSet());
	}

	/**
	 * @return
	 */
	public int getNumAssignments() {
		return numAssignments;
	}

	/**
	 * @param numAssignments
	 */
	private void setNumAssignments(int numAssignments) {
		this.numAssignments = numAssignments;
	}

	/**
	 * @return
	 */
	public int getNumProjects() {
		return numProjects;
	}

	/**
	 * @param numProjects
	 */
	private void setNumProjects(int numProjects) {
		this.numProjects = numProjects;
	}

	/**
	 * @return the projects
	 */
	public Map<Integer, Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(Map<Integer, Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the assignments
	 */
	public Map<Integer, Assignment> getAssignments() {
		return assignments;
	}

	/**
	 * @param assignments
	 *            the assignments to set
	 */
	public void setAssignments(Map<Integer, Assignment> assignments) {
		this.assignments = assignments;
	}

	/**
	 * @param projectNumber
	 * @return
	 */
	public Project getProjectByProjectNumber(Integer projectNumber) {
		return this.projects.get(projectNumber);
	}

	/**
	 * @param assignmentNumber
	 * @return
	 */
	public Assignment getAssignmentByAssignmentNumber(Integer assignmentNumber) {
		return this.assignments.get(assignmentNumber);
	}

	/**
	 * Returns a class that provides two ways of retrieving a student: by name
	 * and by GT ID.
	 * 
	 * @param workbook
	 * @return StudentsTwoKeyHashMap
	 */
	private StudentsTwoKeyHashMap getStudentsFromWorkbook(Workbook workbook) {
		LOGGER.finest("ENTER");

		StudentsTwoKeyHashMap studentsTwoKeyHashMap = new StudentsTwoKeyHashMap();

		Sheet detailsSheet = workbook.getSheet(STUDENTS_SHEET_NAME);

		// Loop over every row in the sheet ignoring the header and any blank
		// rows.
		for (int i = 0; i <= detailsSheet.getLastRowNum(); i++) {
			Row tempRow = detailsSheet.getRow(i);
			LOGGER.finest("tempRow=" + tempRow);
			if (tempRow != null) {
				Cell nameCell = tempRow
						.getCell(STUDENTS_SHEET_NAME_COLUMN_INDEX);
				LOGGER.finest("tempCell=" + nameCell);
				Cell gtidCell = tempRow
						.getCell(STUDENTS_SHEET_GTID_COLUMN_INDEX);
				LOGGER.finest("gtidCell=" + gtidCell);
				Cell emailAddressCell = tempRow
						.getCell(STUDENTS_SHEET_EMAILADDRESS_COLUMN_INDEX);
				LOGGER.finest("gtidCell=" + gtidCell);
				if (nameCell != null) {
					String studentName = nameCell.getStringCellValue();
					LOGGER.fine("studentName=" + studentName);
					if (!STUDENTS_SHEET_NAME_COLUMN_HEADER
							.equalsIgnoreCase(studentName)) {
						Student student = new Student();
						student.setName(studentName);
						// Set the GTID's cell type to text.
						gtidCell.setCellType(1);
						String gtid = gtidCell.getStringCellValue();
						student.setGtid(gtid);
						String emailAddress = emailAddressCell
								.getStringCellValue();
						student.setEmailAddress(emailAddress);

						LOGGER.fine("student.toString()=" + student.toString());
						studentsTwoKeyHashMap.addStudent(student);
					}
				}
			}
		}

		// Logging.
		LOGGER.fine("studentsTwoKeyHashMap.size()="
				+ studentsTwoKeyHashMap.size());
		if (!studentsTwoKeyHashMap.getStudentsFromTwoKeyHashMapAsHashSet()
				.isEmpty()) {
			for (Student student : studentsTwoKeyHashMap
					.getStudentsFromTwoKeyHashMapAsHashSet()) {
				LOGGER.fine("student.toString()=" + student.toString());
			}
		}

		// Now go get the students attendance from a different sheet in the
		// workbook.
		studentsTwoKeyHashMap = this.getStudentsAttendanceFromWorkbook(
				studentsTwoKeyHashMap, workbook);

		// Logging.
		if (!studentsTwoKeyHashMap.getStudentsFromTwoKeyHashMapAsHashSet()
				.isEmpty()) {
			for (Student student : studentsTwoKeyHashMap
					.getStudentsFromTwoKeyHashMapAsHashSet()) {
				LOGGER.fine("student.toString()=" + student.toString());
			}
		}

		LOGGER.finest("EXIT");
		return studentsTwoKeyHashMap;
	}

	/**
	 * Get the students attendance from a different sheet in the workbook and
	 * add their attedances to the existing set of students in
	 * studentsTwoKeyHashMap.
	 * 
	 * @param studentsTwoKeyHashMap
	 *            , the students to be updated with attendances
	 * @return studentsTwoKeyHashMap, the updated students
	 */
	private StudentsTwoKeyHashMap getStudentsAttendanceFromWorkbook(
			StudentsTwoKeyHashMap studentsTwoKeyHashMap, Workbook workbook) {
		LOGGER.finest("ENTER");

		if (studentsTwoKeyHashMap != null
				&& !studentsTwoKeyHashMap
						.getStudentsFromTwoKeyHashMapAsHashSet().isEmpty()) {
			Sheet attendanceSheet = workbook.getSheet(ATTENDANCE_SHEET_NAME);

			// Loop over every row in the sheet. Use the students name to
			// retrieve them from the set of students and update their
			// attendance. Blank and header rows are ignored.
			for (int i = 0; i <= attendanceSheet.getLastRowNum(); i++) {
				Row tempRow = attendanceSheet.getRow(i);
				LOGGER.finest("tempRow=" + tempRow);
				if (tempRow != null) {
					Cell studentNameCell = tempRow
							.getCell(ATTENDANCE_SHEET_STUDENT_NAME_COLUMN_INDEX);
					LOGGER.finest("studentNameCell=" + studentNameCell);
					String studentName = studentNameCell.getStringCellValue();
					LOGGER.fine("studentName=" + studentName);

					Cell totalCell = tempRow
							.getCell(ATTENDANCE_SHEET_TOTAL_COLUMN_INDEX);
					LOGGER.finest("totalCell=" + totalCell);

					// Set the TOTAL's cell type to numeric.
					totalCell.setCellType(0);
					Double total = totalCell.getNumericCellValue();
					LOGGER.finest("total=" + total);

					// And now convert the attendance to a rounded down
					// percentage.
					int percentTotal = (int) Math.floor(total * 100);
					LOGGER.fine("percentTotal=" + percentTotal);

					// Get the student by name.
					Student student = studentsTwoKeyHashMap
							.getStudentFromTwoKeyHashMapByName(studentName);

					// If their is a student with this matching name then update
					// their attendance. Otherwise do nothing.
					if (student != null) {
						student.setAttendance(percentTotal);
					}
				}
			}
		}

		// Logging.
		if (studentsTwoKeyHashMap != null
				&& !studentsTwoKeyHashMap
						.getStudentsFromTwoKeyHashMapAsHashSet().isEmpty()) {
			for (Student student : studentsTwoKeyHashMap
					.getStudentsFromTwoKeyHashMapAsHashSet()) {
				LOGGER.fine("student.toString()=" + student.toString());
			}
		}

		LOGGER.finest("EXIT");
		return studentsTwoKeyHashMap;
	}

	/**
	 * @param workbook
	 * @return numProjects, the number of projects in the workbook
	 */
	private int getNumProjectsFromWorkbook(Workbook workbook) {
		LOGGER.finest("ENTER");
		int numberOfProjects = 0;

		Sheet projectAndAssignmentsSheet = workbook
				.getSheet(PROJECTS_AND_ASSIGNMENTS_SHEET_NAME);

		// Loop over every row in the sheet and ignore the header and any empty
		// rows.
		for (int i = 0; i <= projectAndAssignmentsSheet.getLastRowNum(); i++) {
			Row tempRow = projectAndAssignmentsSheet.getRow(i);
			if (tempRow != null) {
				Cell tempCell = tempRow
						.getCell(PROJECTS_AND_ASSIGNMENTS_SHEET_PROJECTS_COLUMN_INDEX);
				if (tempCell != null) {
					if (!"".equalsIgnoreCase(tempCell.getStringCellValue())) {
						if (!PROJECTS_AND_ASSIGNMENTS_SHEET_PROJECTS_COLUMN_HEADER
								.equalsIgnoreCase(tempCell.getStringCellValue())) {
							LOGGER.finest("tempRow=" + tempRow);
							LOGGER.finest("tempCell=" + tempCell);
							++numberOfProjects;
						}
					}
				}
			}
		}

		LOGGER.fine("numberOfAssignments=" + numberOfProjects);

		LOGGER.finest("EXIT");

		return numberOfProjects;
	}

	/**
	 * @param workbook
	 * @return numAssignments, the number of assignments in the workbook
	 */
	private int getNumAssignmentsFromWorkbook(Workbook workbook) {
		LOGGER.finest("ENTER");
		int numberOfAssignments = 0;

		Sheet projectAndAssignmentsSheet = workbook
				.getSheet(PROJECTS_AND_ASSIGNMENTS_SHEET_NAME);

		// Loop over every row in the sheet and ignore the header and any empty
		// rows.
		for (int i = 0; i <= projectAndAssignmentsSheet.getLastRowNum(); i++) {
			Row tempRow = projectAndAssignmentsSheet.getRow(i);
			if (tempRow != null) {
				Cell tempCell = tempRow
						.getCell(PROJECTS_AND_ASSIGNMENTS_SHEET_ASSIGNMENTS_COLUMN_INDEX);
				if (tempCell != null) {
					if (!"".equalsIgnoreCase(tempCell.getStringCellValue())) {
						if (!PROJECTS_AND_ASSIGNMENTS_SHEET_ASSIGNMENTS_COLUMN_HEADER
								.equalsIgnoreCase(tempCell.getStringCellValue())) {
							LOGGER.finest("tempRow=" + tempRow);
							LOGGER.finest("tempCell=" + tempCell);
							++numberOfAssignments;
						}
					}
				}
			}
		}

		LOGGER.fine("numberOfAssignments=" + numberOfAssignments);

		LOGGER.finest("EXIT");
		return numberOfAssignments;
	}

	/**
	 * 
	 * @return numStudents, the number of students in the workbook's "Details"
	 *         sheet
	 */
	public int getNumStudents() {
		LOGGER.finest("ENTER");

		int numStudents = this.getStudents().size();

		LOGGER.fine("numStudents=" + numStudents);

		LOGGER.finest("EXIT");

		return this.getStudents().size();
	}

	/**
	 * @param gtid
	 * @return student, a student with the matching GT ID in the workbook if
	 *         there is one, else null
	 */
	public Student getStudentByID(String gtid) {
		LOGGER.finest("ENTER");
		LOGGER.fine("gtid=" + gtid);

		Student matchingStudent = this.getStudentsTwoKeyHashMap()
				.getStudentFromTwoKeyHashMapByGTID(gtid);

		// Logging.
		if (matchingStudent != null) {
			LOGGER.fine("matchingStudent.toString()="
					+ matchingStudent.toString());
		} else {
			LOGGER.fine("matchingStudent=null");
		}

		LOGGER.finest("EXIT");

		return matchingStudent;
	}

	/**
	 * @param name
	 * @return student, a student with the matching name in the workbook if
	 *         there is one, else null
	 */
	public Student getStudentByName(String name) {
		LOGGER.finest("ENTER");
		LOGGER.fine("name=" + name);

		Student matchingStudent = this.getStudentsTwoKeyHashMap()
				.getStudentFromTwoKeyHashMapByName(name);

		// Logging.
		if (matchingStudent != null) {
			LOGGER.fine("matchingStudent.toString()="
					+ matchingStudent.toString());
		} else {
			LOGGER.fine("matchingStudent=null");
		}

		LOGGER.finest("EXIT");

		return matchingStudent;
	}

	/**
	 * Inner class used to be able to retrieve each student by either their name
	 * or their GT ID. This avoids having to loop over all students and check
	 * either their name or GT ID when we want to retrieve or update a student.
	 * Note, there is still just one instance of a Student class for each
	 * student. There are just two different HashMaps containing them. One with
	 * name keys and the other with GT ID keys. This simplified the
	 * getStudentByName, getStudentByID, and getStudentsAttendanceFromWorkbook
	 * methods.
	 * 
	 * @author Justin Stringer
	 */
	private class StudentsTwoKeyHashMap {

		private HashMap<String, Student> studentsNameKeyHashMap = new HashMap<String, Student>();
		private HashMap<String, Student> studentsGTIDKeyHashMap = new HashMap<String, Student>();

		/**
		 * Adds the student to both HashMaps so that it can be retrieved by
		 * either name or GT ID
		 * 
		 * @param student
		 */
		public void addStudent(Student student) {
			this.studentsNameKeyHashMap.put(student.getName(), student);
			this.studentsGTIDKeyHashMap.put(student.getGtid(), student);
		}

		/**
		 * @return HashSet of all student
		 */
		public HashSet<Student> getStudentsFromTwoKeyHashMapAsHashSet() {
			return new HashSet<Student>(studentsNameKeyHashMap.values());
		}

		/**
		 * @param name
		 * @return student with the matching name, else null
		 */
		public Student getStudentFromTwoKeyHashMapByName(String name) {
			return this.studentsNameKeyHashMap.get(name);
		}

		/**
		 * @param gtid
		 * @return student with the matching GT ID, else null
		 */
		public Student getStudentFromTwoKeyHashMapByGTID(String gtid) {
			return this.studentsGTIDKeyHashMap.get(gtid);
		}

		/**
		 * @return the number of students in StudentsTwoKeyHashMap
		 */
		public int size() {
			return this.studentsNameKeyHashMap.size();
		}
	}

	/**
	 * @param studentName
	 * @return
	 */
	public String getStudentAdminInfoForOutputByStudentName(String studentName) {
		StringBuffer studentAdminInfoForOutputSB = new StringBuffer();

		return studentAdminInfoForOutputSB.toString();
	}

	/**
	 * @param studentName
	 * @return
	 */
	public String getStudentProjectInfoForOutputByStudentNameAndProjectNumber(
			String studentName, Integer projectNumber) {
		StringBuffer studentProjectInfoForOutputSB = new StringBuffer();

		return studentProjectInfoForOutputSB.toString();
	}

	/**
	 * @param studentName
	 * @return
	 */
	public String getStudentAssignmentInfoForOutputByStudentNameAndAssignmentNumber(
			String studentName, Integer assignmentNumber) {
		StringBuffer studentAssignmentInfoForOutputSB = new StringBuffer();

		return studentAssignmentInfoForOutputSB.toString();
	}

	/**
	 * @param studentName
	 * @return
	 */
	public String getAllStudentInfoForOutputByStudentName(String studentName) {
		StringBuffer allStudentInfoForOutputSB = new StringBuffer();

		return allStudentInfoForOutputSB.toString();
	}

	/**
	 * @param fileName
	 * @param studentInfoForOutputToFile
	 * @throws FileNotFoundException
	 */
	public void writeStudentInfoToFile(String fileName,
			String studentInfoForOutputToFile) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(fileName.replaceAll("\\s+",
				"") + ".txt");
		printWriter.println(studentInfoForOutputToFile);
		printWriter.close();
	}

}
