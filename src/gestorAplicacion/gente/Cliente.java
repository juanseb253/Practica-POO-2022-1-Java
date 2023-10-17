package gestorAplicacion.gente;

import java.util.ArrayList; 
import java.util.Collections;
import java.io.Serializable;

//la clase cliente se usa en el software para crear un perfil para las
//personas que consumen los productos del restaurante

public class Cliente extends Persona implements Serializable{
	private static ArrayList<Cliente> lista_socio = new ArrayList<Cliente>();//Serializar
	
	//este metodo devuelve una lista con los clientes que son socios
	public static ArrayList<Cliente> getListaSocios(){
		return lista_socio;
	}
	//agrega un nuevo socio a la lista de clientes
	public static void addSocio(Cliente cliente) {
		lista_socio.add(cliente);
	}
	// el metodo perfil implementa el metod abstracto perfil de la clase abstracta persona
	// para el caso de cliente devuelve un  array que explica la importancia del cliente
	public String perfil() {
		return "El cliente es la razon de ser del restaurante, puesto que es el quien consume los productos que se venden en el";
	}
	
	//constructor para las instancias de cliente
	public Cliente(int cedula,String nombre,int telefono) {
		super(cedula,nombre,telefono);
	}
	//este metodo sobreescribe al metodo informacion de persona
	public String informacion() {
		return "nombre del cliente: "+this.getNombre()+" \ncedula: "+this.getCedula()+" \ntelefono: "+this.getTelefono();
	}
	
}