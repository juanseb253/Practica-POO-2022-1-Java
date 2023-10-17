package gestorAplicacion.restaurante;

import java.util.ArrayList;
import java.io.Serializable;
import gestorAplicacion.gente.Empleado;

public class ingredientes implements Serializable{ //Debido a que esta clase se deben guardar datos se pone el Serializable
	private int precio_compra; // precio al cual el proveedor vende los articulos
	private int cantidad; // cantidad del ingrediente
	private String tipo; // aqui se especifica que es el ingrediente EJ: Tocino, Huevo, Tomate, etc..
	public static ArrayList<ingredientes> lista_ingredientes = new ArrayList<ingredientes>(); // En este array se guardan los ingredientes para ser usados en armado de platillos
	
	public ingredientes(int precio_compra, int cant, String tipo) { // constructor de ingredientes se crea el ingrendiete y se añade a la lista de ingredientes
		if(lista_ingredientes.size()==0) {
			this.precio_compra=precio_compra;
			this.cantidad = cant;
			this.tipo=tipo;
			lista_ingredientes.add(this);
		}
		else {
			for (int i = 0 ;i < lista_ingredientes.size(); i++) {
				if (!(tipo == lista_ingredientes.get(i).tipo)) {
					if (lista_ingredientes.get(i) == lista_ingredientes.get(lista_ingredientes.size()-1)) { // Se verifica que el ingrediente ongresado no este en la lista de ingredientes
						this.precio_compra = precio_compra;
						this.cantidad = cant;
						this.tipo = tipo;
						lista_ingredientes.add(this);
					}
				}
			}
		}
	}
	//METODOS SET Y GET 
	public String getTipo() {
		return this.tipo;
	}
	public int getCantidad() {// Aqui directamente escogemos cual tipo de carne queremos saber la cantidad
		return cantidad;
	}
	
	public int getPrecio_compra() {
		return precio_compra;
	}
	
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;

	}

	public void setTipo(String type) {
		this.tipo = type;
	}

	public void setPrecio_compra(int precio_compra) {
		this.precio_compra = precio_compra;
	}

	public String nosepuede() {
		return "no se puede anadir un ingrediente repetido";
	}

	public static String anadirCantidad(int cantidad, String tipo) { // busca en la lista de lista_ingredientes y cambia la cantidad.
		for (ingredientes i: lista_ingredientes) {
			if (i.tipo == tipo) {
				i.cantidad += cantidad;
				return "Se aumento el stock de: " + tipo + " en " +  cantidad + " unidades";
			}
			else {
				return "ingrediente inexistente";
			}
		}
		return null;
	}
	
	public String anadirCantidad(int cantidad) { // anade la cantidad en la que se aumenta la cantidad de unidades de esta instancia
		this.cantidad += cantidad;
		return "Se aumento el stock de: " + tipo + " en " +  cantidad + " unidades";
	}
	
	public String retirarCantidad(int cantidad) { // anade la cantidad en la que se aumenta la cantidad de unidades de esta instancia
		this.cantidad -= cantidad;
		return "Se disminuyo el stock de: " + tipo + " en " +  cantidad + " unidades";
	}

	public boolean verificar_inventario() {// Se verifica el stock de un ingrediente
		if (cantidad > 0) {
			return true;
		} else {
			return false;
		}
	}

}