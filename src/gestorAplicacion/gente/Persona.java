package gestorAplicacion.gente;
import java.io.Serializable;

//persona es una clase abstracta que hereda caracteristicas y metodos utiles para las clases
//Gerente, Cliente y Empleado
public abstract class Persona implements Serializable{
protected int cedula; //numero de cedula de la persona
protected String nombre; //nombre de la persona
protected int telefono; //telefono de la persona

//constructor
public abstract String perfil(); //metodo abstracto que es implementado por las clases Cliente, Empleado y Persona



public String informacion() { //metodo que devuelve un string con la informacion de la persona
	return "cedula: "+cedula+" nombre: "+nombre+" telefono: "+telefono;
}

//constructor para persona
public Persona(int cedula,String nombre,int telefono) {
	this.cedula=cedula;
	this.nombre=nombre;
	this.telefono=telefono;
}

//metodos get y set

//metodo que devuelve la cedula de la persona
public int getCedula() {
	return cedula;
}

//metodo que devuelve el nombre de la persona
public String getNombre() {
	return nombre;
}

//metodo que establece el nombre de la persona
public void setNombre(String nombre) {
	this.nombre=nombre;
}
//metodo que devuelve el telefono de la persona
public int getTelefono() {
	return telefono;
}

//metodo que establece el telefono de la persona
public void setTelefono(int telefono) {
	this.telefono=telefono;
}

}
