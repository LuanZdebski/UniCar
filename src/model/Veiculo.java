package model;

import java.sql.Date;

public class Veiculo {
	
	private int ID;
	private String modelo;
	private String fabricante;
	private String categoria;
	private int IDfipe;
	private int qntPortas;
	private String anoFabricacao;
	private String combustivel;
	private float IVRfeminino;
	private float IVRmasculino;
	private Float ValorFIPE;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getQntPortas() {
		return qntPortas;
	}
	public void setQntPortas(int qntPortas) {
		this.qntPortas = qntPortas;
	}
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(String anoFabricação) {
		this.anoFabricacao = anoFabricação;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public float getIVRfeminino() {
		return IVRfeminino;
	}
	public void setIVRfeminino(float iVRfeminino) {
		IVRfeminino = iVRfeminino;
	}
	public float getIVRmasculino() {
		return IVRmasculino;
	}
	public void setIVRmasculino(float iVRmasculino) {
		IVRmasculino = iVRmasculino;
	}
	public int getIDfipe() {
		return IDfipe;
	}
	public void setIDfipe(int iDfipe) {
		IDfipe = iDfipe;
	}
	@Override
	public String toString() {
		return "Veiculo [modelo=" + modelo + ", fabricante=" + fabricante + ", categoria=" + categoria
				+ ", qntPortas=" + qntPortas + ", anoFabricacao=" + anoFabricacao
				+ ", combustivel=" + combustivel
				+ "]";
	}
	public float getValorFIPE() {
		return ValorFIPE;
	}
	public void setValorFIPE(float valorFIPE) {
		ValorFIPE = valorFIPE;
	}
	

}
