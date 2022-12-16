package com.luv2code.springboot.cruddemo.dao;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Mascota;

@Repository
public class MascotaDAOJdbcImpl implements MascotaDAO {

	@Autowired
	DataSource dataSource;

	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;

	@Override
	public List<Mascota> findAll() {
		System.out.println("Implementación DAO con JDBC: "+ dataSource);
		
		List<Mascota> listaMascotas = new ArrayList<>();
		
		try {
			myConn = dataSource.getConnection();
			// create sql statement
			String sql = "select * from mascota";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
										
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
							
				// create new student object
				Mascota tempMascota = new Mascota(id, firstName, lastName, email);
							
				// add it to the list of students
				listaMascotas.add(tempMascota);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaMascotas;
	}

	@Override
	public Mascota findById(int theId) {
		Mascota theMascota = null;

		try (Connection myConn = dataSource.getConnection();
				PreparedStatement myStmt = createPreparedStatement(myConn, theId);
				ResultSet myRs = myStmt.executeQuery();) {

			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// use the studentId during construction
				theMascota = new Mascota(theId, firstName, lastName, email);
			} else {
				throw new SQLException("Could not find mascota id: " + theId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theMascota;
	}
	
	private PreparedStatement createPreparedStatement(Connection con, int mascotaId) throws SQLException {
		String sql = "select * from mascota where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, mascotaId);
		return ps;
	}

	@Override
	public void save(Mascota theMascota) {
		String sql = "";

		if (theMascota.getId() == 0)
			sql = "insert into mascota (first_name, last_name, email) values (?, ?, ?)";
		else
			sql = "update mascota set first_name=?, last_name=?, email=?, where id=?";
		
		try (Connection myConn = dataSource.getConnection();
			 PreparedStatement myStmt = myConn.prepareStatement(sql)) {

			myStmt.setString(1, theMascota.getFirstName());
			myStmt.setString(2, theMascota.getLastName());
			myStmt.setString(3, theMascota.getEmail());
			
			System.out.println(theMascota.getFirstName());	
			System.out.println(theMascota.getLastName());
			System.out.println(theMascota.getEmail());
			if (theMascota.getId() != 0)
				myStmt.setInt(4, theMascota.getId());
			System.out.println(theMascota.getId());
			myStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(int theId) {
String sql = "delete from mascota where id=?";
		
		try (Connection myConn = dataSource.getConnection(); 
			 PreparedStatement myStmt = myConn.prepareStatement(sql)) {

			myStmt.setInt(1, theId);

			myStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
