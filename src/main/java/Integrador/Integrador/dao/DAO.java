package Integrador.Integrador.dao;

import java.sql.SQLException;
import java.util.List;

import Integrador.Integrador.model.exceptions.ClienteExceptions;
import Integrador.Integrador.model.exceptions.FacturaExceptions;
import Integrador.Integrador.model.exceptions.ProductExceptions;

public interface DAO<T> {
	void crear(T t) throws SQLException, ProductExceptions, ClienteExceptions, FacturaExceptions;
	List<T> listar() throws ProductExceptions, ClienteExceptions, FacturaExceptions;
}
