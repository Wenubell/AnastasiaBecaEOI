package edu.es.eoi.user.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.es.eoi.user.domain.User;

@Repository
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
					.prepareStatement("SELECT idUsuario, nombre, fecha, premium, saldo FROM usuario WHERE idUsuario=?");
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

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entity;
	}

	public void create(User e) {

		Connection con = conexion();

		try {
			PreparedStatement st = con
					.prepareStatement("INSERT INTO usuario (nombre, fecha, premium, saldo) VALUES (?,?,?,?)");
			st.setString(1, e.getNombre());
			st.setDate(2, new Date(e.getFecha().getTime()));
			st.setBoolean(3, e.getPremium());
			st.setDouble(4, e.getSaldo());

			st.executeUpdate();

			con.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

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
