package library.dao.repos.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.dao.mappers.IMapper;
import library.domain.Author;
import library.domain.Person;

public class AuthorRepository extends RepositoryBase<Author> {
	
	
	public AuthorRepository(Connection connection,IMapper<Author> mapper) throws SQLException{
		super(connection,mapper);
	}

	@Override
	protected String getUpdateQuerySql() {
		return "UPDATE author SET (first_name,last_name) = (?,?) WHERE id=?";
	}

	@Override
	protected String getInsertQuerySql() {
		return "INSERT INTO author(first_name, last_name) VALUES (?,?)";
	}

	@Override
	protected String getTableName() {
		return "author";
	}

	@Override
	protected String createTableStatementSql() {
		return "CREATE TABLE author("
		+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
		+ "first_name varchar(20),"
		+ "last_name varchar(50)"
		+ ")";
	}

	@Override
	protected void setUpdate(Author person) throws SQLException {
		update.setString(1, person.getFirstName());
		update.setString(2, person.getLastName());
		update.setInt(3, person.getId());
		
	}

	@Override
	protected void setInsert(Author person) throws SQLException {
		insert.setString(1, person.getFirstName());
		insert.setString(2, person.getLastName());
		
	}

}
