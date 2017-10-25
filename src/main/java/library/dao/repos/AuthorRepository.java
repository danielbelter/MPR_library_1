package library.dao.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.domain.Author;
import library.domain.Person;

public class AuthorRepository extends RepositoryBase {
	
	
	public AuthorRepository(Connection connection){
		
		try {
			
			this.connection=connection;
			
			insert = connection.prepareStatement(""
					+ "INSERT INTO author(first_name, last_name) VALUES (?,?)"
					+ "");
			
			selectById = connection.prepareStatement(""
					+ "SELECT * FROM author WHERE id=?");
			
			count = connection.prepareStatement("SELECT COUNT(*) FROM author");
			lastId = connection.prepareStatement("SELECT MAX(id) FROM author");
			selectPage = connection.prepareStatement(""
					+ "SELECT * FROM author OFFSET ? LIMIT ?"
					+ "");
			
			delete = connection.prepareStatement(""
					+ "DELETE FROM author WHERE id=?"
					+ "");
			
			update = connection.prepareStatement(""
					+ "UPDATE author SET (first_name, last_name) = (?,?) WHERE id=?"
					+ "");
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("author"))
					tableExists=true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Author author){
		
		try {
			
			update.setString(1, author.getFirst_name());
			update.setString(2, author.getLast_name());
			update.setInt(3, author.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Author author){
		
		try {
			delete.setInt(1, author.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Author get(int id){
		
		Author result = null;
		try {
			selectById.setInt(1, id);
			ResultSet rs = selectById.executeQuery();
			
			while(rs.next()){
				result = new Author();
				result.setId(rs.getInt("id"));
				result.setFirst_name(rs.getString("first_name"));
				result.setLast_name(rs.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<Author> getPage(int offset, int limit){
		List<Author> result = new ArrayList<Author>();
		try {
			selectPage.setInt(1, offset);
			selectPage.setInt(1, limit);
			ResultSet rs = selectPage.executeQuery();
			while(rs.next()){
				Author p = new Author();
				p.setId(rs.getInt("id"));
				p.setFirst_name(rs.getString("name"));
				p.setLast_name(rs.getString("surname"));
				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
		
	public void add(Author author){
		
		try {
			insert.setString(1, author.getFirst_name());
			insert.setString(2, author.getLast_name());
			insert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public void createTable(){
		
		String sql = "CREATE TABLE author("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "first_name varchar(20),"
				+ "last_name varchar(50)"
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
