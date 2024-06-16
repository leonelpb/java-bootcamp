package Practica6.Practica6.DAO;

import java.util.List;

public interface DAO<T> {
	void crear(T t);
	List<T> listar();
}
