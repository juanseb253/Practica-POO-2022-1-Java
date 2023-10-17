package gestorAplicacion.gente;

import java.util.ArrayList; 
import java.util.Collections;
import java.io.Serializable;
//la clase gerente se encarga de la gestion a nivel general del restaurante, desde el dinero en la caja y
//el inventario, hasta la nomina de empleados, la funcion de la clase gerente radica en la toma de decisiones importantes
public class Gerente extends Persona implements gerente_por_defecto,Serializable {
	private String contrasena_gerente;
	public static Gerente[] arr=new Gerente[1];
	
	//constructor del gerente, como solo puede existir una instancia de tipo gerente, este siempre
	//toma al array statico de la clase gerente (arr) el cual tiene un solo puesto, y lo cambia por
	//el nuevo gerente cada que se crea un gerente
	
	public Gerente(int cedula,String nombre,int telefono,String contrasena_gerente) {
		super(cedula,nombre,telefono);
		this.contrasena_gerente=contrasena_gerente;
		arr[0]=null;
		arr[0]=this;
	}
	//constructor por defecto del gerente, toma las constantes que estan en la interfaz gerente por defecto
	
	public Gerente() {
		super(CEDULA,NOMBRE,TELEFONO);
		contrasena_gerente=CONTRASENA;
		arr[0]=this;
	}
	
	//metodos get y set
	
	//este metodo devuelve la contraseña del gerente
	public String getConstrasena_gerente() {
		return contrasena_gerente;
	}
	//este metodo nos deja establecer la contraseña del gerente
	public void setContrasena_gerente(String contrasena_gerente) {
		this.contrasena_gerente=contrasena_gerente;
	}
	//metodos
	
	//este metodo crea un nuevo empleado y lo agragrega a la lista de empelados

public void contratar_empleado(int cedula,String nombre,int telefono) {
	Empleado b=new Empleado(cedula,nombre,telefono);
}
    //este metodo retira al empleado de la lista de empleados
public void despedir_empleado(Empleado empleado) {
	int in=1; //numero del primer empleado
	
	Empleado.lista_empleados.remove(empleado.getNumero_asignado()-1); //se retira al empleado de la lista de empleados
	
	for (int i=0;i<Empleado.lista_empleados.size();i++) { //se resignan los numero de empleado
		Empleado.lista_empleados.get(i).setNumero_asignado(in);
		in++;
	}
	Empleado.numero_empleados--;
	
	empleado=null;
}
     //este metodo utiliza el metodo empleado menos eficiente de la clase empleado,
     //el cual entrega el empleado que menos cantidad de ventas tiene, despes aplica el metodo
     //despedir empleado sobre el empleado menos eficiente

public void despido_inteligente() { //este metodo despide al empleado menos eficiente
	
	despedir_empleado(Empleado.empleado_menos_eficiente());
	
}   
     //el metodo informacion sobrescribe al metodo informacion de la clase persona,
     //devuelve un String de la siguiente manera, con la informacion del gerente

public String informacion() {
	return "nombre del Gerente: "+this.getNombre()+" \ncedula: "+this.getCedula()+" \ntelefono: "+this.getTelefono();
}
        
    //el metodo perfil es un metodo abstracto que se implementa de la clase abstracta persona
    //para el caso de gerente devuleve un String con un mensaje, que le recuerda su funcion
    //al gerente
 
public String perfil() {
	return "recuerde, su funcion como gerente es administrar y gestionar el restaurante...";
}

}