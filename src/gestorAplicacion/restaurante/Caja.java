package gestorAplicacion.restaurante;
import java.util.ArrayList; 
import java.util.Collections;
import java.io.Serializable;
public class Caja implements Serializable{
	private int efectivo;
	private ArrayList<Integer> ingresos;
	private ArrayList<Integer>  egresos;    
	
	
	//contructores
	public Caja() {
		this(0,new  ArrayList<Integer>(),new  ArrayList<Integer>());
	}
	public Caja(int efectivo, ArrayList<Integer> ingresos, ArrayList<Integer> egresos) { 
		this.efectivo = efectivo;
		this.ingresos = ingresos;
		this.egresos = egresos;	
	}

	
	
	//metodos get
	public int getEfectivo() {
		return efectivo;
	}
	public ArrayList<Integer> getIngresos(){
		return ingresos;
	}
	public ArrayList<Integer> getEgresos(){
		return egresos;
	}


	
	
	//metodos set 
	public void setEfectivo(int n) {
		efectivo = n;
	}

	public void nuevoIngreso(int n) {
		ingresos.add(n);
	}
	public void nuevoEgreso(int n) {
		egresos.add(n);
	}
	
	
	//metodos reales
	public String devuelta(int n, Orden orden) {    
		if (n > orden.getPrecio_total()) {
			int w = n - orden.getPrecio_total();   //devuelta 
			this.nuevoIngreso(orden.getPrecio_total());     //agrego ingresos a la lista
			return  w + "";                    // convierto el int a string 
		}
		else {
			return "Cantidad Insuficiente";
		}
	}
	
	public int arqueo() {     //devuelve la cantidad de dinero en la caja
		int x = 0;
		for (int n: ingresos) {
			x += n;
		}
		for (int i: egresos) {
			x -= i;
		}
		return x;
	}

}