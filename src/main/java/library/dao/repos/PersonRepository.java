package library.dao.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import library.domain.Person;

public class PersonRepository {

	String url = "jdbc:hsqldb:hsql://localhost/workdb";
	
	Connection connection;
	
	private boolean tableExists;
	
	PreparedStatement insert;
	
	public PersonRepository(){
		
		try {
			
			connection = DriverManager.getConnection(url);
		
			insert = connection.prepareStatement(""
					+ "INSERT INTO person(name, surname) VALUES (?,?)"
					+ "");
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("person"))
					tableExists=true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Person person){
		
		try {
			insert.setString(1, person.getName());
			insert.setString(2, person.getSurname());
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public void createTable(){
		
		String sql = "CREATE TABLE person("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "name varchar(20),"
				+ "surname varchar(50)"
				+ ")";
		
		try {
			Statement createTable = connection.createStatement();
			if(!tableExists)
				createTable.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}