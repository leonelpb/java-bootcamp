package Integrador.Integrador.controller;

import java.sql.SQLException;
import java.util.List;

import Integrador.Integrador.dao.ProductoDAO;
import Integrador.Integrador.model.Producto;
import Integrador.Integrador.model.exceptions.InvalidProductException;
import Integrador.Integrador.model.exceptions.ProductExceptions;
public class ProductoController {
	 private final ProductoDAO productoDAO;

	    public ProductoController() {
	        this.productoDAO = new ProductoDAO();
	    }

	    public void agregarProducto(int idProducto, String nombreProducto, int stock,  double precio, String descripcion) throws SQLException, ProductExceptions {
	        Producto producto = new Producto(idProducto, nombreProducto, stock, precio, descripcion);
	        productoDAO.crear(producto);
	    }

	    public void modificarStock(int id, int cantidad) throws SQLException, InvalidProductException {
	        productoDAO.modificarStock(id, cantidad);
	    }

	    public List<Producto> listarProductos() throws SQLException, ProductExceptions {
	        return productoDAO.listar();
	    }
}
