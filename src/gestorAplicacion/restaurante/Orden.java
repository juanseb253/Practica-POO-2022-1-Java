package gestorAplicacion.restaurante;

import java.util.ArrayList;
import java.util.Collections;
import gestorAplicacion.gente.*;
import java.time.LocalTime;
import java.lang.String;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.io.Serializable;

import java.lang.Math;

public class Orden implements Serializable {

	private ArrayList<Platillo> platillos = new ArrayList<Platillo>();
	private int numero_orden;
	private int precio_total;
	private Cliente cliente;
	private int propina;
	private static int cantidad_orden = 0;
	private boolean estado_pedido;
	private static Caja caja = new Caja(); // serializar

	// constructor
	public Orden() {
		this(new ArrayList<Platillo>(), null, 0);
	}

	public Orden(ArrayList<Platillo> platillos, Cliente cliente) {
		this(platillos, cliente, 0);
	}

	public Orden(ArrayList<Platillo> platillos, Cliente cliente, int propina) {
		this.platillos = platillos;
		int x = 0;
		for (Platillo i : platillos) {
			x += i.getPrecio();
		}
		x += propina;
		precio_total = x;
		this.cliente = cliente;
		this.propina = propina;
		numero_orden = cantidad_orden;
		cantidad_orden++;
		// se tiene cantidad de orden y numero de orden por separado porque
		// uno me sirve para la instancia y el otro a modo de contador para
		// saber cuantas ordenes han sido creadas
	}

	// metodos set
	public ArrayList<Platillo> getPlatillos() {
		return platillos;
	}

	public int getNumero_orden() {
		return numero_orden;
	}

	public int getPrecio_total() {
		return precio_total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public int getPropina() {
		return propina;
	}

	public static int getCantidad_orden() {
		return cantidad_orden;
	}
	public static Caja getCaja() {
		return caja;
	}

	// metodos set
	public void anadirPlatillos(Platillo platillo) { 
		platillos.add(platillo);
		precio_total += platillo.getPrecio(); // aumento el precio
	}

	public String retirarPlatillo(Platillo platillo) {
		for(int i = 0; i < platillos.size(); i++) {	
			if(platillos.get(i) == platillo) {
				precio_total -= platillo.getPrecio();       //disminuyo el precio de la orden
				int x =  platillo.getIngredientes().size();
				for (int e = 0; e < x; e++) {
					platillo.retirarIngrediente(platillo.getIngredientes().get(0));	//retiro cada ingrediente del platillo
				}
				platillos.remove(platillos.indexOf(platillo));
				return "Platillo retirado";
			}
			else if (platillos.size()-1 == i) {
				return "no existe tal platillo";
			}
		}
		return "no existe tal platillo";
	}

	public void descuento() {
		if (precio_total > 100) {
			precio_total = (int) Math.round(precio_total * 0.95); // descuento del 5% por cantiad de dinero gastado
		} 
		if (platillos.size() > 50) {
			precio_total = (int) Math.round(precio_total * 0.90); // descuento del 10% por cantidad de compras
		}
		for (Cliente i: Cliente.getListaSocios()) {               // descuento por ser socio
			if(i.getCedula() == this.getCliente().getCedula()) {
				precio_total = (int) Math.round(precio_total * 0.90);
				break;
			}
		}
		// falta descuento por ser un cliente especifico
	}

	public String cancelar_orden() { // cambiar cancelar orden
		for (int i = 0; i < platillos.size(); i++) {
			retirarPlatillo(platillos.get(i));         //retiro cada platillo
		}
		return "orden cancelada";
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void nuevoSocio(Cliente cliente) {
		Cliente.addSocio(cliente);
	}

	public String duplicar(Platillo platillo) { 

		for (int i = 0; i < platillo.getIngredientes().size(); i++) {
			if (platillo.getIngredientes().get(i).verificar_inventario()) {      //verifico si hay existencias de cada uno de los ingredientes necesarios
				if (i == platillo.getIngredientes().size() - 1) {
					Platillo x = new Platillo(platillo.getIngredientes());       // agrego el platillo duplicado 
					anadirPlatillos(x);
					return "platillo duplicado";
				}
				return "no se pudo duplicar";
			}
			return "no se pudo duplicar";
		}
		return "no se pudo duplicar";
	}

	public String comprobar(int n) {
		LocalDate date = LocalDate.now(); // obtengo la fecha
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		String text = date.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		String dia = parsedDate.getDayOfWeek().toString();    //obtengo dia de la semana asociado a la fecha
		descuento();										//aplico el descuento
		if (dia == "SATURDAY" || dia == "SUNDAY") {			//verifico si es sabado o domingo
			if (horarios.horario2.getInicio() <= Integer.valueOf(LocalTime.now().toString().substring(0, 2))  //verifico si estamos en el horario especifico
					&& Integer.valueOf(LocalTime.now().toString().substring(0, 2)) < horarios.horario2.getFinal()) {
				if (n >= precio_total) {
					estado_pedido = true;
					caja.setEfectivo(precio_total);												//anado dinero a la caja
					caja.nuevoIngreso(precio_total);
					return "Pedido confirmado, su devuelta es de $" + (n - precio_total);
				} 
				else {
					return "dinero insuficiente";
				}
			}
			return "Pedido rechazado";
		} 
		else {
			if (horarios.horario1.getInicio() <= Integer.valueOf(LocalTime.now().toString().substring(0, 2))   //verifico si estamos en el horario especifico
					&& Integer.valueOf(LocalTime.now().toString().substring(0, 2)) < horarios.horario1.getFinal()) {
				if (n >= precio_total) {
					estado_pedido = true;												
					caja.setEfectivo(precio_total);															//anado dinero a la caja
					caja.nuevoIngreso(precio_total);
					return "Pedido confirmado, su devuelta es de $" + (n - precio_total);
				} 
				else {
					return "dinero insuficiente" ;
				}
			}
			return "Pedido rechazado";
		}
	}
}
