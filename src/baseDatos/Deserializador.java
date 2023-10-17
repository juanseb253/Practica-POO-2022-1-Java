package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

public class Deserializador {
    /**
     * Utilizamos clases genericas para permitir reutilizar la funcion para todas
     * las clases del proyecto
     * 
     * @param <E>       el generico se usa para poder agredar las clases que se
     *                  crearon
     * @param lista     Una lista de objetos
     * @param className El nombre de la clase que queremos usar como nombre del
     *                  archivo
     * @throws ClassNotFoundException
     */

    public static <E> void deserializar(ArrayList<E> obj, String className) throws ClassNotFoundException { // para arraylist
        FileInputStream fileIn; //Apuntador FileInputStream de fileIn
        try {// try catch para el manejo de archivos
            // Creamos una cadena con la ruta del archivo que vamos a cargar
            String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
           // System.out.println(path);
            // utilizamos un file para crear este archivo si no existe aun
            // File archivo = new File(path);
            // archivo.createNewFile(); // Crea un nuevo archivo si no existe
            fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<E> obj2 = (ArrayList<E>) in.readObject(); // Se instancia una lista segun sea el tipo a deserializar y se lee el objeto deserializado
            in.close();
            fileIn.close();

            for (E l : obj2) {
                obj.add(l); // se agregan los objetos creados a la instancia
            }
            
        } catch (

        FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //System.out.println("Esta vacio");
        }
        //System.out.println("Deserilizando " + className);
    }
    public static void deserializar(int n, String className) throws ClassNotFoundException{
        FileInputStream fileIn; //Apuntador FileInputStream de fileIn
        try {// try catch para el manejo de archivos
            // Creamos una cadena con la ruta del archivo que vamos a cargar
            String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
            //System.out.println(path);
            // utilizamos un file para crear este archivo si no existe aun
            // File archivo = new File(path);
            // archivo.createNewFile(); // Crea un nuevo archivo si no existe
            fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            int ndes= in.readInt();
            Empleado.numero_empleados=ndes;
            in.close();
            fileIn.close();

    }catch (

        FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           // System.out.println("Esta vacio");
        }
       // System.out.println("Deserilizando " + className);
    }

    public static <E> void deserializar(Gerente[] obj, String className) throws ClassNotFoundException { // para
                                                                                                            // arraylist
        FileInputStream fileIn; // Apuntador FileInputStream de fileIn
        try {// try catch para el manejo de archivos
             // Creamos una cadena con la ruta del archivo que vamos a cargar
            String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
           // System.out.println(path);
            // utilizamos un file para crear este archivo si no existe aun
            // File archivo = new File(path);
            // archivo.createNewFile(); // Crea un nuevo archivo si no existe
            fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Gerente[] obj2 = (Gerente[]) in.readObject(); // Se instancia una lista segun sea el tipo a  deserializar y se lee el objeto deserializado
            Gerente.arr=obj2;                                               
                                                                
            in.close();
            fileIn.close();

            /*for (Gerente l : obj2) {
                obj.add(l); // se agregan los objetos creados a la instancia
            }*/
            

        } catch (

        FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           // System.out.println("Esta vacio");
        }
       // System.out.println("Deserilizando " + className);
    }
    public static void deserializarTodo() throws ClassNotFoundException {// AQUI DESERIALIZAMOS EN UN SOLO METODO LAS CLASES DE INTERES
        Deserializador.deserializar(Gerente.arr, "Gerente");
        Deserializador.deserializar(Empleado.numero_empleados, "NumEmple");
        Deserializador.deserializar(Empleado.lista_empleados, "Empleado");
        Deserializador.deserializar(ingredientes.lista_ingredientes, "ingredientes");
        Deserializador.deserializar(Cliente.getListaSocios(), "Cliente");

    }
}