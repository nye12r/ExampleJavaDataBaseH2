package com.test.prueba.ejemplo;

import java.sql.SQLException;

import com.test.pruebas.persistence.H2MemoryDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        System.out.println( "Hello World!" );
        H2MemoryDatabase db = new H2MemoryDatabase();
        
        if(db.CreateTableWithPrepStatement()) {
        	System.out.println( "Create Table As Succesful!" );
        	db.insertWithPreparedStatement(1, "Luis Eduardo Rosales Prieto.");
        }
        else {
        	db.closeConnection();
        }
    }
}
