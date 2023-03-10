package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Login;

public class LoginDao implements ILoginDao {

	private Connection c;
	
	public LoginDao() throws ClassNotFoundException, SQLException {
		
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
		
		
	}

	@Override
	public void insertLogin(Login l) throws SQLException {
		String sql = "INSERT INTO Tb_Usuario Values (?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setString(1, l.getLogin());
		ps.setString(2, l.getPassword());
		ps.setString(3, l.getGenero());
		ps.setDate(4, l.getDataNascimento());
		ps.setString(5, l.getNome());
		System.out.println(ps);
		ps.execute();
		ps.close();
		
	}

	@Override
	public void updateLogin(Login l) throws SQLException {
		String sql = "UPDATE Tb_Usuario SET login = ?, password = ?, genero = ?, dataNascimento = ?, nomeCompleto = ? WHERE login = ?";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setString(1, l.getLogin());
		ps.setString(2, l.getPassword());
		ps.setString(3, l.getGenero());
		ps.setDate(4, l.getDataNascimento());
		ps.setString(5, l.getNome());
		ps.setString(6, l.getLogin());
		
		ps.execute();
		ps.close();
		
	}

	@Override
	public void deleteLogin(Login l) throws SQLException {
		String sql = "DELETE FROM Tb_Usuario WHERE login = ?";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setString(1, l.getLogin());

		
		ps.execute();
		ps.close();
		
	}

	@Override
	public Login selectLogin(Login l) throws SQLException {
		String sql = "SELECT login, password, genero, dataNascimento, nomeCompleto FROM Tb_Usuario WHERE login = ? AND password = ?";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setString(1, l.getLogin());
		ps.setString(2, l.getPassword());

		int cont = 0;
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			l.setLogin(rs.getString("login"));
			l.setPassword(rs.getString("password"));
			l.setGenero(rs.getString("genero"));
			l.setDataNascimento(rs.getDate("dataNascimento"));
			l.setNome(rs.getString("nomeCompleto"));
			cont++;
		}
		if (cont == 0)
		{
			l = new Login();
		}
		
		ps.execute();
		ps.close();
		return l;
	}

	@Override
	public List<Login> selectManyLogin(Login l) throws SQLException {
		String sql = "SELECT login, password, genero, dataNascimento, nomeCompleto FROM Tb_Usuario";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setString(1, l.getLogin());
		ps.setString(2, l.getPassword());

		ResultSet rs = ps.executeQuery();
		
		List<Login> listaLogins = new ArrayList<Login>();
		
		while(rs.next())
		{
			l.setLogin(rs.getString("login"));
			l.setPassword(rs.getString("password"));
			l.setGenero(rs.getString("genero"));
			l.setDataNascimento(rs.getDate("dataNascimento"));
			l.setNome(rs.getString("nomeCompleto"));
			listaLogins.add(l);
		}
		
		rs.close();

		ps.close();
		return listaLogins;
	}
	
	
}
