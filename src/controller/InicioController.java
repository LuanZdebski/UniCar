package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Login;
import view.Home;

public class InicioController {
	
	@FXML
	private Pane paneLogin;
	@FXML
	private TextField tfLogin;
	@FXML
	private TextField tfSenha;
	@FXML
	private Button btnEntrar;
	@FXML
	private Button btnNovoUsuario;
	@FXML
	private Pane paneRegister;
	@FXML
	private TextField tfNome;
	@FXML
	private TextField tfRegisterSenha;
	@FXML
	private Button btnRegistrar;
	@FXML
	private Button btnVoltar;
	@FXML
	private DatePicker tfData;
	@FXML
	private TextField tfRegisterLogin;
	@FXML
	private RadioButton rdMasculino;
	@FXML
	private RadioButton rdFeminino;

	// Event Listener on Button[#btnEntrar].onAction
	@FXML
	public void onEntrar(ActionEvent event) throws Exception {
		if(tfLogin.getText() != "" && tfSenha.getText() != "")
		{
			LoginController loginController = new LoginController(paneLogin, tfLogin, tfLogin, btnEntrar, btnEntrar, paneLogin, tfLogin, tfLogin, tfData, tfLogin, rdFeminino, rdFeminino);
			Login login = new Login();
			
			login.setLogin(tfLogin.getText());
			login.setPassword(tfSenha.getText());
			
			try 
			{				
				login = loginController.selectLogin(login);
				
			}
			catch(Exception e) {}
			
			
			if(login.getNome() == null)
			{JOptionPane.showMessageDialog(null, "Dados invalidos");}
			if(login.getNome() != null)
			{
				System.out.println(login.getNome() + " Logou");
							
				
				Home home = new Home();
				Stage stage = new Stage();
				home.user = login;

				Stage thisStage = (Stage) paneLogin.getScene().getWindow();
				thisStage.close();
				
				home.start(stage);
			
			}
			
			
		}
		else {JOptionPane.showMessageDialog(null, "Inserir login e senha");}
	}
	@FXML
	public void onNovoUsuario(ActionEvent event) {
		paneRegister.visibleProperty().setValue(true);
		paneLogin.visibleProperty().setValue(false);
		LimparCampos();
	}
	@FXML
	public void onRegistrar(ActionEvent event) throws ClassNotFoundException, SQLException {
		if(tfNome.getText() != "" && tfRegisterSenha.getText() != "" && tfRegisterLogin.getText() != "" && tfData.getValue() != null)
		{
			Login novoUser = new Login();
			LoginController loginController = new LoginController(paneLogin, tfLogin, tfLogin, btnEntrar, btnEntrar, paneLogin, tfLogin, tfLogin, tfData, tfLogin, rdFeminino, rdFeminino);
			
			String genero = null;
			if(rdMasculino.isSelected()) {genero = "M";}
			if(rdFeminino.isSelected()) {genero = "F";}
			
			LocalDate locald = tfData.getValue();
			Date date = Date.valueOf(locald);
						
			//novoUser.setUserId(null);
			novoUser.setNome(tfNome.getText());
			novoUser.setGenero(genero);
			novoUser.setDataNascimento(date);
			novoUser.setLogin(tfRegisterLogin.getText());
			novoUser.setPassword(tfRegisterSenha.getText());
			
			loginController.insertLogin(novoUser);
			LimparCampos();
		}
		else {JOptionPane.showMessageDialog(null, "não deve conter campos vazios");}
	}
	// Event Listener on Button[#btnVoltar].onAction
	@FXML
	public void onVoltar(ActionEvent event) {
		paneLogin.visibleProperty().setValue(true);
		paneRegister.visibleProperty().setValue(false);
		LimparCampos();
	}
	// Event Listener on RadioButton[#rdMasculino].onAction
	@FXML
	public void onMasculino(ActionEvent event) {
		rdFeminino.setSelected(false);
	}
	// Event Listener on RadioButton[#rdFeminino].onAction
	@FXML
	public void onFeminino(ActionEvent event) {
		rdMasculino.setSelected(false);
	}
	public void LimparCampos()
	{
		tfLogin.setText("");
		tfSenha.setText("");
		tfNome.setText("");
		tfRegisterSenha.setText("");
		tfRegisterLogin.setText("");
		tfData.setValue(null);
	}

}
