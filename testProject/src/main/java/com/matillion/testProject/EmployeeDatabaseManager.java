package com.matillion.testProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * command line Java program that allows the user to specify a department, pay
 * type and education level, and then connects to the shared database and runs
 * the query with those options. The program then displays the results of the
 * query.
 * 
 * @author radhakrishnan
 *
 */
public class EmployeeDatabaseManager {

	// database connection properties
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://mysql-technical-test.cq5i4y35n9gg.eu-west-1.rds.amazonaws.com:3306/foodmart";
	private static final String DB_USER = "technical_test";
	private static final String DB_PASSWORD = "HopefullyProspectiveDevsDontBreakMe";

	private List<Employee> employees = null;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * command line Java program that allows the user to specify a department, pay
	 * type and education level, and then connects to the shared database and runs
	 * the query with those options. The program then display the results of the
	 * query.
	 */
	public static void main(String[] args) {

		EmployeeDatabaseManager employeeDBM = new EmployeeDatabaseManager();
		employeeDBM.displayEmployeeRecords(args);
	}

	/**
	 * This method validates the user input before calling getEmployeeRecordsFromDB
	 * Prints the results
	 * 
	 * @param args, contains all the required arguments.
	 * @throws IllegalArgumentException if the input is not valid
	 */
	public void displayEmployeeRecords(String[] args) {

		if (args.length != 3) {
			throw new IllegalArgumentException("Usage: TestExcersise2 department payType educationLevel");
		}

		try {
			String department = args[0];
			String payType = args[1];
			String educationLevel = args[2];

			setEmployees(this.getEmployeeRecordsFromDB(department, payType, educationLevel));
			if (getEmployees().size() == 0) {
				System.out.println("No Matching Employee reconds found for given arguments");
			} else {
				System.out.println("Full Name \tPosition \tDepartment \tEducational Level "
						+ "\tManagement Role \tPay Type");
				for (Employee employee : getEmployees()) {
					System.out.println(employee);
				}
			}

		} catch (SQLException e) {
			System.out.println("Database Error");
		}
	}

	/**
	 * Validates the user input, This method connects to database to get Employee
	 * records and prints them
	 * 
	 * @param department
	 * @param payType
	 * @param educationLevel
	 * @return List of Employees
	 * @throws SQLException
	 */
	private List<Employee> getEmployeeRecordsFromDB(String department, String payType, String educationLevel)
			throws SQLException {

		List<Employee> employees = new ArrayList<Employee>();

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT emp.employee_id, emp.full_name, emp.position_title, emp.management_role "
				+ "FROM employee emp \r\n" + "INNER JOIN department dept \r\n"
				+ "ON dept.department_id = emp.department_id \r\n" + "INNER JOIN position post \r\n"
				+ "ON post.position_id = emp.position_id \r\n" + "WHERE dept.department_description = ? \r\n"
				+ "AND post.pay_type = ? \r\n" + "AND emp.education_level = ? ";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, department);
			preparedStatement.setString(2, payType);
			preparedStatement.setString(3, educationLevel);
			// execute select SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setDepartment(department);
				employee.setEducationLevel(educationLevel);
				employee.setPayType(payType);
				employee.setFullName(rs.getString("full_name"));
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setPositionTitle(rs.getString("position_title"));
				employee.setManagementRole(rs.getString("management_role"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return employees;
	}

	/**
	 * Create database connection and return it
	 * 
	 * @return dbConnection
	 */
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}
