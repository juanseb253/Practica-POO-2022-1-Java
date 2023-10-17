package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import gestorAplicacion.*;
import gestorAplicacion.gente.Cliente;
import gestorAplicacion.gente.Empleado;
import gestorAplicacion.gente.Gerente;
import gestorAplicacion.restaurante.Caja;
import gestorAplicacion.restaurante.Orden;
import gestorAplicacion.restaurante.Platillo;
import gestorAplicacion.restaurante.horarios;
import gestorAplicacion.restaurante.ingredientes;

public class Serializador {
	/**
	 * Serializamos una lista por el nombre de la clase
	 * 
	 * @param <E>       el generico se usa para poder agredar las clases que se
	 *                  crearon
	 * @param lista     Una lista de objetos
	 * @param className El nombre de la clase que queremos usar como nombre del
	 *                  archivo
	 */

	public static <E> void serializar(ArrayList<E> list, String className) {// para Arraylist
		FileOutputStream fileOut;
		try { // try catch para el manejo de archivos
			String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
			// se crea un fileoutputstream para saber donde serializar los archivos
			fileOut = new FileOutputStream(path);
			// Se crea un objeto output stream para poder escribir en el archivo
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			// Guardamos la lista de objetos
			out.writeObject(list); // Se serializa el objeto
			out.close(); // Cierre del out
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Serilizando " + className);
	}
	public static void serializar(int n, String className){
		FileOutputStream fileOut;
		try { // try catch para el manejo de archivos
			String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
			// se crea un fileoutputstream para saber donde serializar los archivos
			fileOut = new FileOutputStream(path);
			// Se crea un objeto output stream para poder escribir en el archivo
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			// Guardamos la lista de objetos
			out.writeInt(n); // Se serializa el objeto
			out.close(); // Cierre del out
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Serilizando " + className);
	}
	public static <E> void serializar(Gerente[] list,String className){ // Serializardor de la lista  gerente 
		FileOutputStream fileOut;
		try { // try catch para el manejo de archivos
			String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
			// se crea un fileoutputstream para saber donde serializar los archivos
			fileOut = new FileOutputStream(path);
			// Se crea un objeto output stream para poder escribir en el archivo
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			// Guardamos la lista de objetos
			out.writeObject(list); // Se serializa el objeto
			out.close(); // Cierre del out
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Serilizando " + className);
	}
	

	public static void serializarTodo() { // AQUI SERIALIZAMOS EN UN SOLO METODO LAS CLASES DE INTERES
		Serializador.serializar(Gerente.arr, "Gerente");
		Serializador.serializar(Empleado.numero_empleados,"NumEmple");
		Serializador.serializar(Empleado.lista_empleados, "Empleado");
		Serializador.serializar(ingredientes.lista_ingredientes, "ingredientes");
		Serializador.serializar(Cliente.getListaSocios(), "Cliente");
		

	}
}