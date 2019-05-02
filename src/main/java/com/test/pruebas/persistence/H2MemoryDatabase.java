package com.test.pruebas.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2MemoryDatabase {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	private Connection dbConnection = null;

	public H2MemoryDatabase() {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			this.dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean CreateTableWithPrepStatement() throws SQLException {
		PreparedStatement createPreparedStatement = null;
		String CreateQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
		try {
			this.dbConnection.setAutoCommit(false);

			createPreparedStatement = this.dbConnection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();

			System.out.println("H2 In-Memory Database Created Succes");
			this.dbConnection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
			System.out.println("H2 In-Memory Database Created Failed");
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("H2 In-Memory Database Created Failed");
			return false;
		}

		return true;
	}

	public boolean insertWithPreparedStatement(int cod, String name) throws SQLException {
		PreparedStatement insertPreparedStatement = null;

		String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";

		try {
			this.dbConnection.setAutoCommit(false);

			insertPreparedStatement = this.dbConnection.prepareStatement(InsertQuery);
			insertPreparedStatement.setInt(1, cod);
			insertPreparedStatement.setString(2, name);
			insertPreparedStatement.executeUpdate();
			insertPreparedStatement.close();
			System.out.println("H2 In-Memory Database inserted through PreparedStatement");

			this.dbConnection.commit();
		} catch (SQLException e) {
			System.out.println("H2 In-Memory Database Inserted Failed");
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("H2 In-Memory Database Inserted Failed");
			return false;
		}

		return true;
	}

	public boolean selectWithPreparedStatement() throws SQLException {
		PreparedStatement selectPreparedStatement = null;

		String SelectQuery = "select * from PERSON";

		try {
			this.dbConnection.setAutoCommit(false);

			selectPreparedStatement = this.dbConnection.prepareStatement(SelectQuery);
			ResultSet rs = selectPreparedStatement.executeQuery();
			System.out.println("H2 In-Memory Database Select through PreparedStatement");
			while (rs.next()) {
				System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
			}
			selectPreparedStatement.close();

			this.dbConnection.commit();
		} catch (SQLException e) {
			System.out.println("H2 In-Memory Database Inserted Failed");
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("H2 In-Memory Database Inserted Failed");
			return false;
		}

		return true;
	}

	public void closeConnection() {
		try {
			this.dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("H2 In-Memory Database Closed  Failed");
		}
	}

	public Connection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
