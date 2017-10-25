package library.dao.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.domain.Person;
import library.domain.User;

public class UserRepository {
String url = "jdbc:hsqldb:hsql://localhost/workdb";
	
	Connection connection;
	private boolean tableExists;
	
	PreparedStatement insert;
	PreparedStatement selectById;
	PreparedStatement lastId;
	PreparedStatement count;
	PreparedStatement selectPage;
	
	public UserRepository(){
		
		try {
			
			connection = DriverManager.getConnection(url);
			
			insert = connection.prepareStatement(""
					+ "INSERT INTO user(login,password,email) VALUES (?,?,?)"
					+ "");
			selectById = connection.prepareStatement("SELECT * FROM User WHERE id=?");
			
			count = connection.prepareStatement("SELECT COUNT(id) FROM User");
			
			lastId = connection.prepareStatement("SELECT MAX(id) FROM User");
			
			selectPage = connection.prepareStatement(""
					+ "SELECT * FROM User OFFSET ? LIMIT ?"
					+ "");
			
					
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("user"))
					tableExists=true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void add(User user){
		
		try {
			insert.setString(1,user.getLogin());
			insert.setString(2, user.getPassword());
			insert.setString(3, user.getEmail());
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		 
			
		}
	
	public int count(){
		try {
			ResultSet rs = count.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int selectById(){try {
		ResultSet rs = lastId.executeQuery();
		while(rs.next()){
			return rs.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
	}
		
	public int lastId(){try {
		ResultSet rs = lastId.executeQuery();
		while(rs.next()){
			return rs.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return 0;
	}
	
	public List<User> getPage(int offset, int limit){
		List<User> result = new ArrayList<User>();
		try {
			selectPage.setInt(1, offset);
			selectPage.setInt(1, limit);
			ResultSet rs = selectPage.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				result.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public void createTable(){
		
		String sql = "CREATE TABLE user("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "login varchar(20),"
				+ "password varchar(20),"
				+ "email varchar(50)"
				+ ")";
		
		try {
			Statement createTable = connection.createStatement();
			createTable.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

