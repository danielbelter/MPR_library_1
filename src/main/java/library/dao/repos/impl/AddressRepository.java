package library.dao.repos.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.dao.mappers.IMapper;
import library.domain.Address;

public class AddressRepository extends RepositoryBase<Address> {
	
	public AddressRepository(Connection connection, IMapper<Address> mapper) throws SQLException{
		super(connection, mapper);
	}
	
	@Override
	protected String getUpdateQuerySql() {
		return ""
				+ "UPDATE address SET (street,city,postCode,country,houseNumber,localNumber,phone) = (?,?,?,?,?,?,?) WHERE id=?"
				+ "";
	}

	@Override
	protected String getInsertQuerySql() {
		return ""
				+ "INSERT INTO address (street,city,postCode,country,houseNumber,localNumber,phone) = (?,?,?,?,?,?,?)"
				+ "";
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "address";
	}


	@Override
	protected String createTableStatementSql() {
		// TODO Auto-generated method stub
		return "CREATE TABLE address("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "street varchar(20),"
				+ "city varchar(50),"
				+ "postCode varchar(20),"
				+ "country varchar(20),"
				+ "houseNumber varchar(20),"
				+ "localNumber varchar(20),"
				+ "phone varchar(20)"
				+ ")";
	}

	@Override
	protected void setUpdate(Address address) throws SQLException {
		update.setString(1, address.getStreet());
		update.setString(2, address.getCity());
		update.setString(3, address.getPostCode());
		update.setString(4, address.getCountry());
		update.setString(5, address.getHouseNumber());
		update.setString(6, address.getLocalNumber());
		update.setString(7, address.getPhone());
		
	}

	@Override
	protected void setInsert(Address address) throws SQLException {
		update.setString(1, address.getStreet());
		update.setString(2, address.getCity());
		update.setString(3, address.getPostCode());
		update.setString(4, address.getCountry());
		update.setString(5, address.getHouseNumber());
		update.setString(6, address.getLocalNumber());
		update.setString(7, address.getPhone());
		
		
	}
	

}
	
