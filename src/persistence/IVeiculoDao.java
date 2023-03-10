package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Veiculo;

public interface IVeiculoDao {

	void insertVeiculo(Veiculo v) throws SQLException;

	void updateVeiculo(Veiculo v) throws SQLException;

	void deleteVeiculo(Veiculo v) throws SQLException;

	Veiculo selectVeiculo(Veiculo v) throws SQLException;

	List<Veiculo> selectManyVeiculo() throws SQLException;

}
