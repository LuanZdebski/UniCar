package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Veiculo;

public class VeiculoDao implements IVeiculoDao {

	private Connection c;
	
	public VeiculoDao() throws ClassNotFoundException, SQLException {
		
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
		
		
	}

	@Override
	public void insertVeiculo(Veiculo v) throws SQLException {
		String sql = "INSERT INTO Tb_Veiculo Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setInt(1, v.getID());
		ps.setString(2, v.getModelo());
		ps.setString(3, v.getFabricante());
		ps.setInt(4, v.getQntPortas());
		ps.setInt(5, v.getIDfipe());
		ps.setString(6, v.getAnoFabricacao());
		ps.setString(7, v.getCombustivel());
		ps.setFloat(8, v.getIVRfeminino());
		ps.setFloat(9, v.getIVRmasculino());
		
		System.out.println(ps);
		ps.execute();
		ps.close();

		
	}

	@Override
	public void updateVeiculo(Veiculo v) throws SQLException {
		String sql = "UPDATE Tb_Veiculo SET ID = ?, Modelo = ?, Fabricante = ?, QntPortas = ?, IDfipe = ?, AnoFabricante = ?, Combustivel = ?, IVRfeminino = ?, IVRmasculino = ? WHERE ID = ?";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setInt(1, v.getID());
		ps.setString(2, v.getModelo());
		ps.setString(3, v.getFabricante());
		ps.setInt(4, v.getQntPortas());
		ps.setInt(5, v.getIDfipe());
		ps.setString(6, v.getAnoFabricacao());
		ps.setString(7, v.getCombustivel());
		ps.setFloat(8, v.getIVRfeminino());
		ps.setFloat(9, v.getIVRmasculino());
		ps.setInt(10, v.getID());
		
		ps.execute();
		ps.close();
		
	}

	@Override
	public void deleteVeiculo(Veiculo v) throws SQLException {
		String sql = "DELETE FROM Tb_Veiculo WHERE ID = ?";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setInt(1, v.getID());

		
		ps.execute();
		ps.close();
		
	}

	@Override
	public Veiculo selectVeiculo(Veiculo v) throws SQLException {
		String sql = "SELECT ID, Modelo, Fabricante, Categoria, QntPortas, IDfipe, AnoFabricacao, Combustivel, IVRfeminino, IVRmasculino FROM Tb_Veiculo WHERE ID = ?";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		ps.setInt(1, v.getID());

		int cont = 0;
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			v.setID(rs.getInt("ID"));
			v.setModelo(rs.getString("Modelo"));
			v.setFabricante(rs.getString("Fabricante"));
			v.setCategoria(rs.getString("Categoria"));
			v.setQntPortas(rs.getInt("QntPortas"));
			v.setIDfipe(rs.getInt("IDfipe"));
			v.setAnoFabricacao(rs.getString("AnoFabricacao"));
			v.setCombustivel(rs.getString("Combustivel"));
			v.setIVRfeminino(rs.getFloat("IVRfeminino"));
			v.setIVRmasculino(rs.getFloat("IVRmasculino"));
			cont++;
		}
		if (cont == 0)
		{
			v = new Veiculo();
		}
		
		ps.execute();
		ps.close();
		return v;
	}

	@Override
	public List<Veiculo> selectManyVeiculo() throws SQLException {
		String sql = "SELECT ID, Modelo, Fabricante, Categoria, QntPortas, IDfipe, AnoFabricacao, Combustivel, IVRfeminino, IVRmasculino FROM Tb_Veiculo";
		PreparedStatement ps = c.prepareStatement(sql);	
		
		

		ResultSet rs = ps.executeQuery();
		
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		
		while(rs.next())
		{
			Veiculo v = new Veiculo();
			v.setID(rs.getInt("ID"));
			v.setModelo(rs.getString("Modelo"));
			v.setFabricante(rs.getString("Fabricante"));
			v.setCategoria(rs.getString("Categoria"));
			v.setQntPortas(rs.getInt("QntPortas"));
			v.setIDfipe(rs.getInt("IDfipe"));
			v.setAnoFabricacao(rs.getString("AnoFabricacao"));
			v.setCombustivel(rs.getString("Combustivel"));
			v.setIVRfeminino(rs.getFloat("IVRfeminino"));
			v.setIVRmasculino(rs.getFloat("IVRmasculino"));
			listaVeiculos.add(v);
		}
		
		rs.close();

		ps.close();
		return listaVeiculos;
	}
	
	
}
