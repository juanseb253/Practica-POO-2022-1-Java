package gestorAplicacion.gente;
import gestorAplicacion.restaurante.*;
import java.util.ArrayList; 
import java.util.Collections;
import java.io.Serializable;

//la clase Empleado tiene como objetivo crear un perfil para las personas que atienden el restaurante
//desde esta clase se pueden manipular los pedidos(o como estan definidos en el sistema "ordenes") asi como registrar cuando
//se realiza un pedido, esta clase se encarga de manipular lo relacionado con la interaccion compra-venta que tienen el cliente yb el empleado

public class Empleado extends Persona implements Serializable {
private int numero_ventas; //numero de ventas del empleado
private int numero_asignado; //el numero asignado al empleado
public static ArrayList<Empleado> lista_empleados = new ArrayList<Empleado>(); //variable de clase, es una lista con todos los empleados que se crean
public static int numero_empleados; //variable estatica con el numero actual de empleados


//constructor para las instancias de empleado

public Empleado(int cedula,String nombre,int telefono) {
	super(cedula,nombre,telefono);
	Empleado.lista_empleados.add(this);
	numero_empleados++;
	numero_asignado=numero_empleados;
}

//metodos get y set

//metodo que devuelve el numero que tiene asignado el empleado
public int getNumero_asignado(){
	return numero_asignado;
}

//metodo que establece cual es el numero asignad del empleado
public void setNumero_asignado(int numero_asignado){
	this.numero_asignado=numero_asignado;
}

//metodo de clase que nos devuelve la variable de clase numero_empleados
public static int getNumero_empleados() { 
	return numero_empleados;
}

//metodo que devuelve el numero de ventas que tiene una instancia de empleado
public int getNumero_ventas() {
	return numero_ventas;
}


// otros metodos

//este metodo se encarga de sumarle uno al total de la variable numero de ventas de un empleado
public void nuevaVenta() {
	numero_ventas++;
}

//este metodo DE CLASE recorre la lista de empleados y obtiene su numero de ventas paras posteriormente agregarlos a una lista con la cantidad de ventas
//de dicha lista se sacaq el mayor numero de ventas, y posteriormente se usa el numeral con que tal numero aparece en la lista y se usa para obtener el
//empleado con mayor numero de ventas
public static Empleado empleado_mas_eficiente() { //este metodo compara la cantidad de ventas de los empleados y devuelve el que MAS ventas tiene
	ArrayList<Integer> cantventasporempleado = new ArrayList<Integer>(); //arraylist con la cantidad de ventas por empleado
	for (int l=0;l<Empleado.lista_empleados.size();l++) {
		cantventasporempleado.add(lista_empleados.get(l).getNumero_ventas());
	}
	Integer maximo = Collections.max(cantventasporempleado); //maximo numero de ventas por empleado
	for (int j=0;j<cantventasporempleado.size();j++) {
		if(cantventasporempleado.get(j)==maximo) {
			return lista_empleados.get(j);
		}
	}
	return null;	
}

//este metodo DE CLASE es parecido al anterior, con la salvedad de que devuelve al empleado cuyas ventas son menores

public static Empleado empleado_menos_eficiente() { //este metodo compara la cantidad de ventas de los empleados y devuelve el que MENOS ventas tiene
	ArrayList<Integer> cantventasporempleado = new ArrayList<Integer>(); //arraylist con la cantidad de ventas por empleado
	for (int l=0;l<Empleado.lista_empleados.size();l++) {
		cantventasporempleado.add(lista_empleados.get(l).getNumero_ventas());
	}
	Integer minimo = Collections.min(cantventasporempleado); //minimo numero de ventas por empleado
	for (int j=0;j<cantventasporempleado.size();j++) {
		if(cantventasporempleado.get(j)==minimo) {
			return lista_empleados.get(j);
		}
	}
	return null;	
}

//el metodo informacion sobrescribe al metodo informacion de persona, devuelve la informacion del empleado
public String informacion() {
	return "nombre del Empleado: "+this.getNombre()+" \ncedula: "+this.getCedula()+" \ntelefono: "+this.getTelefono()+" \nnumero asignado: "+numero_asignado+" \nnumero de ventas: "+numero_ventas;
}

//este metodo llama al metodo informacion de la clase persona y devuelve su mismo resultado
public String info_basi() {
	return super.informacion();
}

//el metodo perfil implementa el metodo abstracto de la clase persona, perfil, en el caso de la calse Empleado
//se encarga de revisar si el numero de ventas es mayor a 25, posteriormente si es asi devuelve el String
//"tiene mas de 50 ventas, felicidades", si no devuelve "tiene menos de 50 ventas, debe esforzarce mas"
public String perfil() {
	if(this.getNumero_ventas()>25) {
		return "tiene mas de 50 ventas, felicidades";
	}
	else {
		return "tiene menos de 50 ventas, debe esforzarce mas";
	}
}


}