package clases.poo.ito;

import java.util.HashSet;

public class ClaseCalzado implements Comparable<ClaseCalzado>{

	public int clave = 0;
	public String material = "";
	public String troquel = "";
	public int cantProdxDia = 0;
	public HashSet<String> colores = new HashSet<String>();

	public ClaseCalzado() {

		super();
		
	}
	
	public ClaseCalzado(int clave, String material, String troquel, int cantProdxDia, HashSet<String> colores, int contColores) {
		super();
		this.clave = clave;
		this.material = material;
		this.troquel = troquel;
		this.cantProdxDia = cantProdxDia;
		this.colores = colores;
	}

	public void setcostoxLote(int newcostoxLote) {
		this.cantProdxDia = newcostoxLote;
	}

	public int getClave() {
		return this.clave;
	}


	public void setClave(int newClave) {
		this.clave = newClave;
	}


	public String getMaterial() {
		return this.material;
	}


	public void setMaterial(String newMaterial) {
		this.material = newMaterial;
	}


	public String getTroquel() {
		return this.troquel;
	}


	public void setTroquel(String newTroquel) {
		this.troquel = newTroquel;
	}


	public int getCantProdxDia() {
		return this.cantProdxDia;
	}


	public void setCantProdxDia(int newCantProdxDia) {
		this.cantProdxDia = newCantProdxDia;
	}

	public HashSet<String> getColores() {
		return this.colores;
		
	}

	public void setColores(HashSet<String> newcolores) {
		this.colores = newcolores;
	}
	
	
	@Override
	public String toString() {
		
		return "ClaseCalzado [clave=" + clave + ", material=" + material + ", troquel=" + troquel + ", cantProdxDia="
				+cantProdxDia + ", colores=" + colores + "]";
		
		
		
	}
	

	@Override
	public int compareTo(ClaseCalzado o) {
		int compare=0;
		if (this.clave<o.getClave())
			compare=-1;
		else if(this.clave>o.getClave())
			compare=1;
		return compare;
	}

	
}
