package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Login;
import persistence.LoginDao;

public class LoginController implements ILoginController {

	private Pane paneLogin;
	
	private TextField tfLogin;
	private TextField tfSenha;
	private Button btnEntrar;
	private Button btnNovoUsuario;
	private Pane paneRegister;
	private TextField tfNome;
	private TextField tfRegisterSenha;
	private DatePicker tfData;
	private TextField tfRegisterLogin;
	private RadioButton rdMasculino;
	private RadioButton rdFeminino;
	
	
	
	public LoginController(Pane paneLogin, TextField tfLogin, TextField tfSenha, Button btnEntrar,
			Button btnNovoUsuario, Pane paneRegister, TextField tfNome, TextField tfRegisterSenha, DatePicker tfData,
			TextField tfRegisterLogin, RadioButton rdMasculino, RadioButton rdFeminino) {
		super();
		this.paneLogin = paneLogin;
		this.tfLogin = tfLogin;
		this.tfSenha = tfSenha;
		this.btnEntrar = btnEntrar;
		this.btnNovoUsuario = btnNovoUsuario;
		this.paneRegister = paneRegister;
		this.tfNome = tfNome;
		this.tfRegisterSenha = tfRegisterSenha;
		this.tfData = tfData;
		this.tfRegisterLogin = tfRegisterLogin;
		this.rdMasculino = rdMasculino;
		this.rdFeminino = rdFeminino;
	}

	@Override
	public void insertLogin(Login l) throws ClassNotFoundException, SQLException {
		LoginDao lDao = new LoginDao();
		lDao.insertLogin(l);
		System.out.println(l.getNome() + " adicionado");
	}

	@Override
	public void updateLogin(Login l) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLogin(Login l) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Login selectLogin(Login l) throws ClassNotFoundException, SQLException {
		LoginDao lDao = new LoginDao();
		l = lDao.selectLogin(l);
		
		return l;
		
	}

	@Override
	public ArrayList<Login> selectManyLogin() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
