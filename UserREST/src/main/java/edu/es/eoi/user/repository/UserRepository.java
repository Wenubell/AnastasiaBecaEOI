package edu.es.eoi.user.repository;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import edu.es.eoi.user.domain.User;

public class UserRepository implements MyRepository<User> {

	private Connection conexion() {
		String url = "jdbc:mysql://localhost:3306/rest?serverTimezone=UTC";
		String user = "root";
		String pass = "1234";

		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(url, user, pass);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexion;
	}

	public User findById(Integer id) {

		Connection con = conexion();
		User entity = null;

		try {
			PreparedStatement st = con
					.prepareStatement("select idUsuario, nombre, fecha, premium, saldo from usuario where idUsuario=?");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				entity = new User();
				entity.setId(rs.getInt("idUsuario"));
				entity.setNombre(rs.getString("nombre"));
				entity.setFecha(rs.getDate("fecha"));
				entity.setPremium(rs.getBoolean("premium"));
				entity.setSaldo(rs.getDouble("saldo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entity;
	}

	public void create(User e) {
		// PreparedStatement st = con.prepareStatement("INSERT INTO usuario (nombre,
		// premium, saldo) VALUES (?,?,?)");

	}

	public void update(User e) {
		// TODO Auto-generated method stub

	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
