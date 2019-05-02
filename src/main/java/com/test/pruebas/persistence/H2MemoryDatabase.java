package com.test.pruebas.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2MemoryDatabase {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	private Connection dbConnection = null;

	public H2MemoryDatabase(Connection dbConnection) {
		this.dbConnection = dbConnection;
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
