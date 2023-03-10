package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Login;

public interface ILoginDao {
	
	public void insertLogin(Login l) throws SQLException;
	public void updateLogin(Login l) throws SQLException;
	public void deleteLogin(Login l) throws SQLException;
	public Login selectLogin(Login l) throws SQLException;
	public List<Login> selectManyLogin(Login l) throws SQLException;
	
}
