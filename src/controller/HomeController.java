package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import ServiceApplication.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import model.Login;
import model.Veiculo;
import model.VeiculoAPIModel;
import persistence.VeiculoDao;

public class HomeController {
	@FXML
	private Pane PanePesquisa;
	@FXML
	private ListView listView;
	@FXML
	private Label txtLoginInfo;
	@FXML
	private TextField tfPesquisa;
	@FXML 
	private Label txtLoginInfo1;
	@FXML
	private Button btnBuscar;
	@FXML
	private Pane PaneDetalhes;
	@FXML
	private Label txtModelo;
	@FXML
	private Button btnVoltar;
	@FXML
	private Label txtCombustivel;
	@FXML
	private Label txtCategoria;
	@FXML
	private Label txtIVR;
	@FXML
	private Label txtQntPortas;
	@FXML
	private Label txtAno;
	@FXML
	private ImageView imgHolder;
	@FXML
	private Label txtFIPE;
	@FXML
	private RadioButton rdBarato;
	@FXML
	private RadioButton rdCaro;
	@FXML
	private RadioButton rdIVRBaixo;
	@FXML
	private RadioButton rdIVRAlto;
	@FXML
	private RadioButton rdNovos;
	@FXML
	private RadioButton rdAntigos;
	private ToggleGroup radios;
	ObservableList<String> list = FXCollections.observableArrayList();
	List<Veiculo> Veiculos = new ArrayList<>();
	List<Veiculo> VeiculosMatch = new ArrayList<>();
	public Login user;
	public String classificarPor;
	
	public void SetLogon(Login login) throws ClassNotFoundException, SQLException
	{
		listView.setItems(list);	
		txtLoginInfo.setText("logado como: " + login.getNome());
		this.user = login;
		//Carregar veiculos do banco
		VeiculoDao vDao = new VeiculoDao();
		Veiculos = vDao.selectManyVeiculo();
		//demais inicializações
		this.classificarPor = "Barato";
		radios = new ToggleGroup();
		rdBarato.isSelected();
		rdBarato.setToggleGroup(radios);
		rdCaro.setToggleGroup(radios);
		rdIVRBaixo.setToggleGroup(radios);
		rdIVRAlto.setToggleGroup(radios);
		rdNovos.setToggleGroup(radios);
		rdAntigos.setToggleGroup(radios);
		
	}
	
	// METODO DE CARREGAR DETALHES
	@FXML
	public void OnMouseClick(MouseEvent event) throws IOException {
		String nome = (String)listView.getSelectionModel().getSelectedItem();
		Veiculo v = VeiculosMatch.get(listView.getSelectionModel().getSelectedIndex());
		
		File file = new File("src/img/" + v.getIDfipe() + ".jpg");
        Image img = new Image(file.toURI().toString());
		
		PanePesquisa.setVisible(false);
		PaneDetalhes.setVisible(true);
		
		//Carregar Detalhes do veiculo
		txtModelo.setText(v.getFabricante() + ' ' + v.getModelo());
		txtCategoria.setText(v.getCategoria());
		txtCombustivel.setText(v.getCombustivel());
		txtAno.setText(v.getAnoFabricacao());	
		imgHolder.setImage(img);
		
		if(user.getGenero().equals("M"))
		{
			txtIVR.setText(Float.toString(v.getIVRmasculino()));	
		}
		else if(user.getGenero().equals("F"))
		{
			txtIVR.setText(Float.toString(v.getIVRfeminino()));
		}
		
		txtFIPE.setText(String.valueOf(v.getValorFIPE()));
		
		
	}
	// METODO DA PESQUISA
	@FXML
	public void onBuscar(ActionEvent event) throws IOException {
		ArrayList<String> matches = new ArrayList<String>();
		listView.getItems().clear();
		VeiculosMatch.clear();
		
		if(tfPesquisa.equals(""))
		{
			
		}
		else
		{
			for(Veiculo v : Veiculos) {		 
		        if (v.toString().contains(tfPesquisa.getText().toLowerCase())) {
		        	
		        	v.setValorFIPE(Float.valueOf(RequestFromAPI(v).getValor() .replaceAll("[^\\d.]", "")));
		        	
		    	    VeiculosMatch.add(v);		            
		        }   
	        }
			
			if(classificarPor.equals("Barato"))
			{VeiculosMatch.sort((v1, v2) -> Float.compare(v1.getValorFIPE(), (v2.getValorFIPE())));}
			if(classificarPor.equals("Caro"))
			{VeiculosMatch.sort(Collections.reverseOrder((v1, v2) -> Float.compare(v1.getValorFIPE(), (v2.getValorFIPE()))));}
			
			if(classificarPor.equals("IVRBaixo") && user.getGenero().equals("M"))
			{VeiculosMatch.sort((v1, v2) -> Float.compare(v1.getIVRmasculino(), (v2.getIVRmasculino())));}
			if(classificarPor.equals("IVRBaixo") && user.getGenero().equals("F"))
			{VeiculosMatch.sort((v1, v2) -> Float.compare(v1.getIVRfeminino(), (v2.getIVRfeminino())));}
			
			if(classificarPor.equals("IVRAlto") && user.getGenero().equals("M"))
			{VeiculosMatch.sort(Collections.reverseOrder((v1, v2) -> Float.compare(v1.getIVRmasculino(), (v2.getIVRmasculino()))));}
			if(classificarPor.equals("IVRAlto") && user.getGenero().equals("F"))
			{VeiculosMatch.sort(Collections.reverseOrder((v1, v2) -> Float.compare(v1.getIVRfeminino(), (v2.getIVRfeminino()))));}
			
			if(classificarPor.equals("Antigos"))
			{VeiculosMatch.sort((v1, v2) -> v1.getAnoFabricacao().compareTo(v2.getAnoFabricacao()));}
			if(classificarPor.equals("Novos"))
			{VeiculosMatch.sort(Collections.reverseOrder((v1, v2) -> v1.getAnoFabricacao().compareTo(v2.getAnoFabricacao())));}
			
			
			for(Veiculo v : VeiculosMatch)
			{matches.add(v.getFabricante() + " " +  v.getModelo() + " " + v.getAnoFabricacao());}
			
		listView.getItems().addAll(matches);

			
			
		}
	}
	// Event Listener on Button[#btnVoltar].onAction
	@FXML
	public void OnVoltar(ActionEvent event) {
		PanePesquisa.setVisible(true);
		PaneDetalhes.setVisible(false);
	}
	
	public VeiculoAPIModel RequestFromAPI(Veiculo v) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String id = String.valueOf(v.getID());
		String idFIPE = String.valueOf(v.getIDfipe());
		String ano = String.valueOf(v.getAnoFabricacao());
		
		String json = new Request().execute("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + id + "/modelos/" + idFIPE + "/anos/" + ano);
		
        VeiculoAPIModel model = mapper.readValue(json, VeiculoAPIModel.class);
        return model;
	}
	public void radioChange()
	{
		if (radios.getSelectedToggle().equals(rdBarato))
		{this.classificarPor = "Barato"; }
		if (radios.getSelectedToggle().equals(rdCaro))
		{this.classificarPor = "Caro";}
		if (radios.getSelectedToggle().equals(rdIVRBaixo))
		{this.classificarPor = "IVRBaixo";}
		if (radios.getSelectedToggle().equals(rdIVRAlto))
		{this.classificarPor = "IVRAlto";}
		if (radios.getSelectedToggle().equals(rdNovos))
		{this.classificarPor = "Novos";}
		if (radios.getSelectedToggle().equals(rdAntigos))
		{this.classificarPor = "Antigos";}
	}
}
