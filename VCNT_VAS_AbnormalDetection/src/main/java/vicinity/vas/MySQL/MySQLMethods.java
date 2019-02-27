/**
Copyright 2018-2019. Information Technologies Institute (CERTH-ITI)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package vicinity.vas.MySQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author mkoutli
 * Basic functions for sql communication.
 */
public class MySQLMethods {

	private static String username = "vicinity_mph";
	private static String dbName = "VCNT_ABNORMAL_DETECTION";

	public static int getWeek() {
		int week = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate));
		String input = dtf.format(localDate);
		String format = "yyyy-MM-dd";

		SimpleDateFormat df = new SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			date = (java.util.Date) df.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		week = cal.get(Calendar.WEEK_OF_YEAR);

		return week;
	}

	public static java.sql.Date getEndDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate));

		java.sql.Date sqlDate = java.sql.Date.valueOf(dtf.format(localDate));
		return sqlDate;
	}

	public static java.sql.Date getStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of
											// day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		System.out.println("Start of this week:       " + cal.getTime());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String inActiveDate = null;
		inActiveDate = format1.format(cal.getTime());
		System.out.println(inActiveDate);
		java.sql.Date sqlDate = java.sql.Date.valueOf(inActiveDate);
		System.out.println("Start of this week (sqlDate):       " + sqlDate);
		return sqlDate;
	}
	
	public static java.sql.Date getEndOfMonth(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of
											// day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, day);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String inActiveDate = null;
		inActiveDate = format1.format(cal.getTime());
		//System.out.println(inActiveDate);
		java.sql.Date sqlDate = java.sql.Date.valueOf(inActiveDate);
		System.out.println("End of month (sqlDate):       " + sqlDate);
		return sqlDate;
		
	}
	
	public static java.sql.Date getStartOfMonth(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of
											// day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		int day = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, day);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String inActiveDate = null;
		inActiveDate = format1.format(cal.getTime());
		//System.out.println(inActiveDate);
		java.sql.Date sqlDate = java.sql.Date.valueOf(inActiveDate);
		System.out.println("End of month (sqlDate):       " + sqlDate);
		return sqlDate;
		
	}

	public static void createTable(String table_name, String sql) {
		Connection c = null;
		Statement stmt = null;

		try {
			c = connectToDB("//localhost:3306/" + dbName, username, username);

			stmt = c.createStatement();

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		System.out.println("Table with name " + table_name + " was created successfully");
	}

	public static void insertInTable(String table_name, String insertString) {
		Connection c = null;
		Statement stmt = null;

		try {
			c = connectToDB("//localhost:3306/" + dbName, username, username);

			stmt = c.createStatement();

			String sql = "INSERT INTO " + table_name + insertString;
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
			return;
		}
		System.out.println("New entry was inserted to table " + table_name + " successfully");

	}

	

	public static void createUserInMySqlDB(String username, String dbName) {

		Connection c = null;
		Statement stmt = null;
		// int row = 0;

		try {
			c = connectToMySqlDB("//localhost:3306/" + dbName);
			c.setAutoCommit(false);

			// DatabaseMetaData md = c.getMetaData();
			// ResultSet rs = md.getTables(null, null, "%", null);
			// while (rs.next()) {
			// System.out.println(rs.getString(3));
			// }

			stmt = c.createStatement();

			String sql = "CREATE USER '" + username + "'@'localhost';";
			stmt.executeUpdate(sql);
			// get key
			// row = stmt.getGeneratedKeys().getInt(1);
			// System.out.println(row);

			stmt = c.createStatement();

			String new_sql = "GRANT ALL PRIVILEGES ON " + dbName + ".* To '" + username
					+ "'@'localhost' IDENTIFIED BY '" + username + "';";
			stmt.executeUpdate(new_sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		System.out.println("Records created successfully");
		// return row;

	}

	public static boolean checkMySqlUserExists(String username) {
		boolean exists = false;
		Connection c = null;
		Statement statement;
		ResultSet resultSet;
		String query = "SELECT host, user, password FROM user WHERE user = '" + username + "';";

		try {

			c = connectToMySqlDB("//localhost:3306/mysql");
			if (c != null) {
				statement = c.createStatement();
				resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					exists = true;
				} else {
					exists = false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return exists;
	}

	public static boolean checkIfRowExists(String queryString) {
		boolean exists = false;
		Connection c = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			c = connectToDB("//localhost:3306/" + dbName, username, username);
			if (c != null) {
				statement = c.createStatement();
				resultSet = statement.executeQuery(queryString);
				if (resultSet.next()) {
					exists = true;
				} else {
					exists = false;
				}
			}
			resultSet.close();
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return exists;
	}
	
	public static String selectQuery(String query) {
		Connection c = null;
		Statement statement = null;
		ResultSet resultSet = null;
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ResultSetSerializer());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(module);
		String response = "";
		

		try {

			c = connectToDB("//localhost:3306/" + dbName, username, username);
			if (c != null) {
				statement = c.createStatement();
				resultSet = statement.executeQuery(query);
			}
			response = objectMapper.writeValueAsString(resultSet);
			
			resultSet.close();
			statement.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return response;
	}
	

	public static void createDB(String dbName) {
		// Create DB if it does not exist
		boolean DBExists = false;
		Connection c = null;
		Statement statement;

		String query = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
		try {
			// Connect to MySql Server
			c = connectToDB("//localhost:3306", "root", "");
			if (c != null) {

				DatabaseMetaData meta = c.getMetaData();
				ResultSet rs = meta.getCatalogs();
				while (rs.next()) {
					String db = rs.getString(1);
					if (db.equalsIgnoreCase(dbName)) {
						DBExists = true;
						System.out.println(db + " exists. No need to create it!");
					}
				}
				rs.close();

				if (!DBExists) {
					System.out.println("Creating database...");
					statement = c.createStatement();
					statement.executeUpdate(query);

					ResultSet resultSet = c.getMetaData().getCatalogs();

					// iterate each catalog in the ResultSet
					while (resultSet.next()) {
						// Get the database name, which is at position 1
						String databaseName = resultSet.getString(1);
						if (databaseName.equalsIgnoreCase(dbName)) {
							System.out.println("Database " + dbName + " created successfully...");
						}
					}
					resultSet.close();
				}

			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
	}

	public static Connection connectToMySqlDB(String location) {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// create db if it does not exist
			c = DriverManager.getConnection("jdbc:mysql:" + location, "root", "");
			System.out.println("Opened databaseat at '" + location + "' successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return c;
	}

	public static Connection connectToDB(String location, String user, String pass) {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// create db if it does not exist
			c = DriverManager.getConnection("jdbc:mysql:" + location, user, pass);
			System.out.println("Opened database at '" + location + "' successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return c;
	}

}
