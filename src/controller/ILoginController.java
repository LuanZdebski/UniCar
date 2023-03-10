package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Login;

public interface ILoginController {
	
	public void insertLogin(Login p) throws ClassNotFoundException, SQLException;
	public void updateLogin(Login p) throws ClassNotFoundException, SQLException;
	public void deleteLogin(Login p) throws ClassNotFoundException, SQLException;
	public Login selectLogin(Login p) throws ClassNotFoundException, SQLException;
	public ArrayList<Login> selectManyLogin() throws ClassNotFoundException, SQLException;
	
}
