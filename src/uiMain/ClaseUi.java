package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.gente.Cliente;
import gestorAplicacion.gente.Empleado;
import gestorAplicacion.gente.Gerente;
import gestorAplicacion.restaurante.Orden;
import gestorAplicacion.restaurante.Platillo;
import gestorAplicacion.restaurante.ingredientes;

public class ClaseUi {
	static Scanner R = new Scanner(System.in); // para los int
	static Scanner N = new Scanner(System.in); // para los String

//Nombrar gerente, este metodo se encarga de ingresar la informacion del nuevo gerente y eliminar la del anterior

	public static void nombrar_gerente() {
		boolean x = true;
		do {
			System.out.println(" (1)Ingresar contrasena \n (2)Volver");
			System.out.print(" Respuesta: ");
			int option = R.nextInt();
			switch (option) {
			case 1:
				System.out.print("Ingrese contrasena de gerente: ");
				String w = N.nextLine();
				
				if (w.equals(Gerente.arr[0].getConstrasena_gerente())) {
					System.out.print("\ncedula: ");
					int cedula = R.nextInt();
					System.out.print("\nnombre: ");
					String nombre = N.nextLine();
					System.out.print("\ntelefono: ");
					int telefono = R.nextInt();
					System.out.print("\ncontrasena: ");
					String contrasena = N.nextLine();
					Gerente gerente = new Gerente(cedula, nombre, telefono, contrasena);
					x = false;
				}
				else {
					System.out.println("Contrasena incorrecta");
				}

				break;
			case 2:
				x = false;
				break;
			}
		} while (x == true);
	}

///////////////////////////////////////////////////////////////////////////////////	

//inicio de sesion empleado, recibe el numero del empleasdo y verifica que este en la lista de empleados

	public static boolean inicio_Sesion_empledo(int nemp) { // recibe el numero de empleado, para verificar que si sea
															// un nunmero de empleado
		if (nemp <= Empleado.numero_empleados && 0 < nemp) {
			System.out.println(
					"\nesta es la informacion del empleado asociado a este numero de empleado, si no coincide con su informacion cierre sesion\n");
			System.out.println(Empleado.lista_empleados.get(nemp - 1).informacion());
			return opciones_empleado(nemp);
		} else {
			System.out.println("\nEste numero de empleado no es valido, ingrese nuevamente\n");
			return false;
		}

	}

///////////////////////////////////////////////////////////////////////////////////	

//inicio de sesion gerente, recibe una contrasena para verificar que se trate del mismisimo gerente en persona

	public static boolean inicio_Sesion_gerente(String contrasena) {
		if (contrasena.equals(Gerente.arr[0].getConstrasena_gerente())) {
			return opciones_gerente();
		} else {
			System.out.println("\nEste contrasena no es valida, ingrese nuevamente\n");
			return false;
		}

	}

///////////////////////////////////////////////////////////////////////////////////		

//lista de ingredientes, imprime una lista con todos los ingredientes y sus precios

	public static void lista_ingredientes() {
		for (int l = 0; l < ingredientes.lista_ingredientes.size(); l++) {
			System.out.println(l + 1 + ") ingrediente: " + ingredientes.lista_ingredientes.get(l).getTipo()
					+ " precio de venta: " + ingredientes.lista_ingredientes.get(l).getPrecio_compra() * 2
					+ " cantidad: " + ingredientes.lista_ingredientes.get(l).getCantidad() + "\n");
		}
	}

///////////////////////////////////////////////////////////////////////////////////	

//construir platillo

	public static Platillo crear_platillo() {
		Platillo platillo = new Platillo();
		boolean x = true;
		do {
			System.out.println("\ningredientes en el platillo :\n"); // se imprime una pequena lista con los
																		// ingredientes que tiene el platillo
			for (int l = 0; l < platillo.getIngredientes().size(); l++) {
				System.out.println(platillo.getIngredientes().get(l).getTipo());
			}
			System.out.print(
					"\nseleccione una opcion: \n\n(1)anadir ingrediente \n\n(2)retirar ingrediente \n\n(3)terminar platillo\n\nrespuesta: ");
			int res = R.nextInt();
			switch (res) {
			case (1):
				System.out.println("");
				lista_ingredientes();
				System.out.print("seleccione el ingrediente que va a anadir: ");
				int ingre = R.nextInt(); // numero correspondiente al lugar en la lista que ocupa el ingredientes

				// en caso de que se tenga en inventario el ingrediente
				if (ingre > 0 && ingredientes.lista_ingredientes.size() >= ingre) {
					System.out.println("");
					System.out.println(platillo.anadirIngrediente(ingredientes.lista_ingredientes.get(ingre - 1)));
					System.out.println("");
				}
				// en caso de que no haya inventario
				else {
					System.out.println("\nese ingrediente no se encuentra en la lista de intregientes");
				}

				break;
			case (2):
				if (platillo.getIngredientes().size() > 0) {
					System.out.println("");
					System.out.println(platillo.retirarIngrediente(platillo.getIngredientes().get(platillo.getIngredientes().size()-1)));
					System.out.println("");

				} else {
					System.out.println("\nerror este platillo no cuenta con ingredientes");
				}
				break;
			case (3):
				if (platillo.getIngredientes().size() == 0) {
					System.out.println("\neste platillo no tiene ingredientes\n");
					x = false;
				} else {
					System.out.println("\nplatillo terminado");
					x = false;
				}
				break;
			}

		} while (x == true);

		if (platillo.getIngredientes().size() >= 1) { // aqui se crea el platillo con los ingredientes que anadimos
													// anteriormente
			return platillo;
		} else {
			System.out.print("platillo sin ingredientes"); // se imprime en caso de que no se pongan ingredientes, se
															// retorna un null
			return null;
		}
	}

/////////////////////////////////////////////////////////////////////////

//es lo que el gerente puede realizar mientras esta en el sistema

	public static boolean opciones_gerente() {
		System.out.println("");
		System.out.println(Gerente.arr[0].perfil());
		System.out.print(
				"\nselecione una opcion: \n\n (1) anadir/retirar ingredientes \n (2) arqueo de caja \n (3) contratar empleado"
						+ "\n (4) despedir empleado \n (5) despido inteligente \n (6) empleado mas eficiente \n (7) ver empleados \n (8) agregar/retirar efectivo \n"
						+" (9) ver inventario \n (10) cerrar sesion \n\n respuesta: ");
		int ob = R.nextInt();

		switch (ob) {
		case 1:
			System.out.print("Seleccione una opcion: \n(1) anadir ingrediente \n(2) retirar ingredientes \n(3) actualizar stock \n\nRespuesta: ");
			System.out.println("");
			int seleccion = R.nextInt();
			switch(seleccion) {
			case 1:
				System.out.println("\ncoloque la informacion del nuevo ingrediente\n");
				System.out.print("\nPrecio de compra: ");
				int precio_compra=R.nextInt();
				System.out.println("");
				System.out.print("\ncantidad: ");
				int cant=R.nextInt();
				System.out.println("");
				System.out.print("\ntipo: ");
				String tipo=N.nextLine();
				ingredientes ing= new ingredientes(precio_compra,cant,tipo);
				break;
			case 2:
				lista_ingredientes();
				System.out.print("\nseleccione el ingrediente que va a retirar del inventario: ");
				int retirando=R.nextInt();
				if (retirando>0 && retirando<=ingredientes.lista_ingredientes.size()) {
					ingredientes.lista_ingredientes.remove(retirando-1);
				}
				break;
				
			case 3:
				System.out.print("\nelija una opcion: \n(1) anadir stock \n(1) retirar stock \n\n respuesta: ");
				int eleccion=R.nextInt();
				switch(eleccion){
				case 1:
					lista_ingredientes();
					System.out.print("\nseleccione el ingrediente del cual se va a actualizar el stock: ");
					int actualiza=R.nextInt();
					System.out.println("");
					System.out.print("indique la cantidad de ingrediente que se va a adicionar al stock: ");
					int cantida=R.nextInt();
					System.out.println("");
					if(actualiza>0 && actualiza<=ingredientes.lista_ingredientes.size()) {
						ingredientes.lista_ingredientes.get(actualiza-1).anadirCantidad(cantida);
					}
					else {
						System.out.println("esa opcion no es valida");
					}
					break;
				case 2:
					lista_ingredientes();
					System.out.print("\nseleccione el ingrediente del cual se va a actualizar el stock: ");
					int actualizar=R.nextInt();
					System.out.println("");
					System.out.print("indique la cantidad de ingrediente que se va a retirar del stock: ");
					int cantidar=R.nextInt();
					System.out.println("");
					if(actualizar>0 && actualizar<=ingredientes.lista_ingredientes.size()) {
						ingredientes.lista_ingredientes.get(actualizar-1).retirarCantidad(cantidar);
					}
					else {
						System.out.println("esa opcion no es valida");
					}
					break;
				
				}
				break;
				
			}
			return true;
		case 2:
			System.out.println("");
			ArrayList<Integer> ingresos_caja=Orden.getCaja().getIngresos();
			ArrayList<Integer> egresos_caja=Orden.getCaja().getEgresos();
			System.out.println("lista de ingresos:");
			for (int k=0;k<ingresos_caja.size();k++) {
				System.out.println("");
				System.out.println(ingresos_caja.get(k));
			}
			System.out.println("");
			System.out.println("lista de egresos:");
			for (int k=0;k<egresos_caja.size();k++) {
				System.out.println("");
				System.out.println(egresos_caja.get(k));
			}
			System.out.println("\nLa cantidad de dinero en la caja es: "+Orden.getCaja().arqueo());
			return true;
		case 3:
			System.out.println("\ningrese los datos del empleado que desea contratar ");
			System.out.print("\ncedula: ");
			int cedula_e = R.nextInt();
			System.out.print("\nnombre: ");
			String nombre_e = N.nextLine();
			System.out.print("\ntelefono: ");
			int telefono_e = R.nextInt();
			Empleado e = new Empleado(cedula_e, nombre_e, telefono_e);
			return true;
		case 4:
			System.out.println("\nverificando lista de empleados..");
			if (Empleado.lista_empleados.size() > 0) {
				System.out.print("\ndigite el numero del empleado al que quiere despedir: ");
				int nu_e = R.nextInt(); // numero de empleado que se piensa despedir
				if (nu_e > 0 && nu_e <= Empleado.lista_empleados.size()) {
					System.out.println("\nSe despidio al empleado: "+Empleado.lista_empleados.get(nu_e - 1).info_basi()+"\n");
					Gerente.arr[0].despedir_empleado(Empleado.lista_empleados.get(nu_e - 1));
				} else {
					System.out.println("\nerror, ese numero de empleado no existe \n");
				}
			} else {
				System.out.println(
						"\nerror la lista de empleados se encuentra vacia, para poder despedir empleados primero debe contratar empleados\n");
			}

			return true;
		case 5:
			System.out.print(
					"\nel despido inteligente se encarga de despedir al empleado con peor rendimiento, seguro que quiere continuar?: \n\n(1)si\n(2)no \n\nRespuesta: ");
			int confirmacion = R.nextInt();
			switch (confirmacion) {
			case 1:
				System.out.println("\nverificando lista de empleados..\n");
				if (Empleado.lista_empleados.size() > 0) {
					System.out.println("\nrealizando despido inteligente");
					System.out.println("\nSe despidio al empleado: \n"+Empleado.empleado_menos_eficiente().informacion()+"\n");
					Gerente.arr[0].despido_inteligente();
				} else {
					System.out.println(
							"\nerror la lista de empleados se encuentra vacia, para poder despedir empleados primero debe contratar empleados\n");
				}
				break;
			case 2:
				System.out.println("\nvolviendo al menu principal\n");
				break;
			}

			return true;
		case 6:
			System.out.println("\nAqui podemos dar un vistazo mas a fondo sobre el empleado mas eficiente: \n");
			System.out.println(Empleado.empleado_mas_eficiente().informacion());
			return true;
		case 7:
			System.out.print("\nSeleccione una opcion: \n(1)mostrar empleados en la nomina\n(2)mostrar informacion de un empleado en especifico\n\n respuesta: ");
			int mostrar=R.nextInt();
			switch (mostrar) {
			case 1:
				for (int l=0;l<Empleado.lista_empleados.size();l++) {
					Empleado a=Empleado.lista_empleados.get(l);
					System.out.println("");
					System.out.println(a.info_basi()+" observaciones: "+a.perfil());
					System.out.println("");
				}
				break;
			case 2:
				System.out.print("\ndigite el numero de empleado: ");
				int numero_empleado=R.nextInt();
				if (numero_empleado>0 && numero_empleado<=Empleado.lista_empleados.size()) {
					System.out.println("\n"+Empleado.lista_empleados.get(numero_empleado-1).informacion()+"\n");
				}
				else {
					System.out.println("\neste numero de empleado no esta en la nomina");
				}
			}
			return true;
		case 8:
			System.out.print("\nSeleccione una opcion: \n(1)anadir efectivo\n(2)retirar efectivo\n\n respuesta: ");
			int mop=R.nextInt();
			switch(mop) {
			case 1:
				System.out.print("\ndigite el monto de efectivo que va a ingresar a la caja: ");
				int efectivo=R.nextInt();
				Orden.getCaja().nuevoIngreso(efectivo);
				System.out.println("");
				break;
			case 2:
				System.out.print("\ndigite el monto de efectivo que va a retirar de la caja: ");
				int efectivoret=R.nextInt();
				Orden.getCaja().nuevoEgreso(efectivoret);
				System.out.println("");
				break;
			}
			return true;
		case 9:
			if (ingredientes.lista_ingredientes.size()>=1) {
				System.out.println("");
				lista_ingredientes();
				System.out.println("");
			}
			else {
				System.out.println("");
				System.out.println("todavia no hay articulos en el inventario");
				System.out.println("");
			}
			return true;
			
		case 10:
			System.out.println("\ncerrando  sesion\n");
			return false;
			
		}
		return false;

	}

/////////////////////////////////////////////////////////////////////////

//es lo que el empleado puede realizar mientras esta en el sistema

	public static boolean opciones_empleado(int nemp) {
		System.out.print("\nseleciones una opcion: \n\n (1) tomar Orden \n (2) agregar socio \n (3) cerrar sesion \n\n respuesta: ");
		int opcion = R.nextInt();

		switch (opcion) {
		case 1:
			tomar_orden(nemp);
			return true;
		case 2:
			System.out.print("\nseleccione una opcion: \n\n (1) crear nuevo cliente \n (2) cerrar");
			int n = R.nextInt();
			switch (n) {
			case 1:
				System.out.print("\ningrese cedula: ");
				int cedula = R.nextInt();
				System.out.print("\ningrese nombre: ");
				String nombre = N.nextLine();
				System.out.print("\ningrese telefono: ");
				int telefono = R.nextInt();
				for (Cliente i: Cliente.getListaSocios()) {
					if (cedula == i.getCedula()) {
						System.out.print("\nEste usuario ya ha sido registrado");
						break;
					}
				}
				Cliente cliente = new Cliente(cedula, nombre, telefono);
				Cliente.addSocio(cliente);
				System.out.print("\nUsuario creado exitosamente");
				break;
			case 2:
				break;
				}
			return true;
			
		case 3:
			System.out.println("\ncerrando  sesion\n");
			return false;
		}
		return false;
	}

///////////////////////////////////////////////////////////////////////////////////	

//tomar orden
	public static void tomar_orden(int nemp) {
		Orden o = new Orden();
		ArrayList<Platillo> lista_platillos = new ArrayList<Platillo>();
		boolean estado_orden = true;
		do {
			System.out.print(
					"\nseleccione una opcion: \n\n(1)anadir platillo \n(2)retirar platillo \n(3)duplicar platillo\n(4)ingresar cedula cliente\n(5)terminar orden\n(6)cancelar orden\n\n respuesta: ");
			int Respuesta = R.nextInt();
			switch (Respuesta) {
			case 1:
				Platillo a = crear_platillo();
				if (a != null) {
					lista_platillos.add(a);
					o.anadirPlatillos(a);
				}
				break;
			case 2:
				System.out.print("");
				if (lista_platillos.size() >= 1) {
					int num = 0;
					for (int l = 0; l < lista_platillos.size(); l++) {
						num += 1;
						System.out.println("");
						System.out.print("platillo numero: " + num + " ingredientes: ");
						Platillo plato = lista_platillos.get(l);
						for (int j = 0; j < plato.getIngredientes().size(); j++) {
							System.out.print(" " + plato.getIngredientes().get(j).getTipo() + " ");
							if (j == plato.getIngredientes().size() - 1) {
								System.out.println("");

							}
						}
					}
					System.out.print("seleccione el platillo que desea retirar: \n\nrespuesta: ");
					int platillo_ret = R.nextInt() - 1;
					if (platillo_ret < lista_platillos.size()) {
						o.retirarPlatillo(lista_platillos.get(platillo_ret));
						lista_platillos.remove(platillo_ret);
						System.out.println("");
					} else {
						System.out.println("\nEste paltillo no esta en la lista de platillos en la orden");
					}
				} else {
					System.out.println("\ntodavia no hay platillos en esta orden\n");
				}
				break;

			case 3:
				System.out.print("");
				if (lista_platillos.size() >= 1) {
					int num = 0;
					for (int l = 0; l < lista_platillos.size(); l++) {
						num += 1;
						System.out.print("platillo numero: " + num + " ingredientes: ");
						Platillo plato = lista_platillos.get(l);
						for (int j = 0; j < plato.getIngredientes().size(); j++) {
							System.out.print(" " + plato.getIngredientes().get(j).getTipo() + " ");
							if (j == plato.getIngredientes().size() - 1) {
								System.out.println("");

							}
						}
					}
					System.out.print("seleccione el platillo que desea duplicar: \n\nrespuesta: ");
					int platillo_ret = R.nextInt() - 1;
					if (platillo_ret < lista_platillos.size()) {
						o.duplicar(lista_platillos.get(platillo_ret));
						lista_platillos.add(lista_platillos.get(platillo_ret));
						System.out.println("");
					} else {
						System.out.println("\nEste paltillo no esta en la lista de platillos en la orden");
					}
				} else {
					System.out.println("\ntodavia no hay platillos en esta orden\n");
				}
				break;
			case 4:
				System.out.print("\ningrese cedula: ");
				int cedula = R.nextInt();
				o.setCliente(new Cliente(cedula,null, 0));
			case 5:
				int pago = 0;
				System.out.print("");
				while (pago < o.getPrecio_total()) {
					if (lista_platillos.size() > 0) {
						System.out.println("el total a pagar es de $" + o.getPrecio_total());
						System.out.print("Con cuanto vas a pagar?: ");
						pago = R.nextInt();
						System.out.println(o.comprobar(pago));
					}
					Empleado.lista_empleados.get((nemp - 1)).nuevaVenta();
				}
				;
				estado_orden = false;
				break;
			case 6:
				System.out.print("");
				if (lista_platillos.size() > 0) {
					o.cancelar_orden();
					System.out.println("\nOrden cancelada");
				}
				estado_orden = false;
				break;
			}
		} while (estado_orden == true);
		// Switch()
	}

//en esta clase se implementa lo necesario para la interfaz generica por consola

	public static void main(String[] args) throws ClassNotFoundException {
	
	Deserializador.deserializarTodo();
	Gerente g = new Gerente();
	/*if (ingredientes.lista_ingredientes.isEmpty() && Empleado.lista_empleados.isEmpty()) { // Se comprueba si existen elementos
		Empleado e1 = new Empleado(1, "empleado_1", 1);			// Esto se ejecuta si los archivos de deserializacion 
		Empleado e2 = new Empleado(2, "empleado_2", 2);			// Estan Vacios
		Empleado e3 = new Empleado(3, "empleado_3", 3);
		Empleado e4 = new Empleado(4, "empleado_4", 4);
		Empleado e5 = new Empleado(5, "empleado_5", 5);
		
		ingredientes i1 = new ingredientes(10, 5, "pan");
		ingredientes i2 = new ingredientes(8, 5, "carne");
		ingredientes i3 = new ingredientes(7, 5, "queso");
		ingredientes i4 = new ingredientes(12, 5, "salchicha");
		ingredientes i5 = new ingredientes(15, 5, "pollo apanado");
		ingredientes i6 = new ingredientes(8, 10, "papas fritas");
		ingredientes i7 = new ingredientes(16, 10, "tocineta");
		ingredientes i8 = new ingredientes(18, 3, "pollo asado");
		ingredientes i9 = new ingredientes(34, 2, "langosta");
		ingredientes i10 = new ingredientes(6,15,"huevo codorniz");
	}*/

	boolean estado_programa = true;
	do {

		System.out.print(
				"Selecione una opcion: \n\n (1) iniciar sesion como empleado \n (2) iniciar sesion como Gerente \n (3) nombrar gerente \n (4) generalidades sobre el software  \n (5) cerrar programa \n\n Respuesta: ");

		int Respuesta = R.nextInt(); // respuesta

		switch (Respuesta) {

			// en caso de que sea tipo empleado
			case 1:
				boolean sesion = true; // estado de seion del empleado
				System.out.print("\nBienvenido Empleado, digite su numero de empleado: ");
				int nemp = R.nextInt(); // numero de empleado
				do {
					sesion = inicio_Sesion_empledo(nemp);
				} while (sesion == true);

				break;

			// en caso de que sea tipo gerente
			case 2:
				boolean sesiong = true; // estado de sesion gerente
				System.out.print("\nbienvenido Gerente, digite la contrasena de gerente para poder acceder: ");
				String contrasena = N.nextLine(); // contrasena gerente
				do {
					sesiong = inicio_Sesion_gerente(contrasena);
				} while (sesiong == true);

				break;

			case 3:
				nombrar_gerente();

				break;
			case 4:
				System.out.println("");
				System.out.println(
						" este software cumple con la funcion de gestionar un restaurante a nivel general ademas de que sirve para administrar la nomina de empleados,");
				System.out.println(
						" es recomendable que se familiarice con que actores van a interactuar con el software, es por ello que aqui dejamos un pequeno resumen:");
				System.out.println("");
				System.out.println(
						" sobre el empleado: el empleado se encarga de tomar los pedidos y gestionar las ventas durante su turno ");
				System.out.println(
						" sobre el gerente: el gerente se encarga de gestionar el restaurante a nivel general");
				System.out.println("");
				System.out.println(
						" NOTA IMPORTANTE: el gerente por defecto tiene la siguiente informacion: cedula: 1111, nombre: nombre del gerente, telefono:1111 y contrasena:1111");
				System.out.println(
						" para cambiar esta informacion selecione la opcion nombrar nuevo gerente, debe digitar la contrasena de gerente por defecto, depues podra editar la");
				System.out.println(
						" informacion del gerente, ademas podra usar esta funcion cada que cambie el gerente o necesite editar su informacion usando la contrasena de gerente");
				System.out.println(
						" que se disponga en ese momento, sea la contrasena por defecto u otra que usted haya fijado. ");
				System.out.println("");
				break;

			case 5:
				System.out.print("\n cerrando programa :) " + "\n");
				estado_programa = false;
				Serializador.serializarTodo();
				break;

			default:
				System.out.println("\n opcion invalida \n");
		}

	}

	while (estado_programa == true);

}

}