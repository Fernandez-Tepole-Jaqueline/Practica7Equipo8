package myApp.poo.ito;

import clases.poo.ito.ClaseCalzado;
import clases.poo.ito.OperacionesClaseCalzado;
import javax.swing.JOptionPane;

public class Aplicación {

	static OperacionesClaseCalzado c;
	
	static void menu() {
		inicializa();
		boolean ciclo=true;
		int respuesta=0;
		while(ciclo) {
		String opciones="Elija la opción que desee:\n 1)Agregar Lote\n 2)Mostrar Lotes\n 3)Borrar Lote\n"
				+ "4)Cambiar la produccion diaria\n"
				+ "5)Hacer una consulta\n 6)Exit";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:agregarLote();/**JOptionPane.showMessageDialog(null);**/;break;
		case 2:mostrarLotes();break;
		case 3:borrarLote();break;
		case 4:cambiarProduccion();break;
		case 5:consulta();break;
		case 6:ciclo=false;break;
		default:JOptionPane.showMessageDialog(null,"Ingrese una de las opciones indicadas por favor");
		  }
		}
	}
	static ClaseCalzado capturarLote() {
		ClaseCalzado n=new ClaseCalzado();
		int clave,cantProdxDia,cantColores;@SuppressWarnings("unused")
		String material,troquel,colores;
		clave=Integer.parseInt(JOptionPane.showInputDialog("Ingresa la clave del calzado por favor"));
		material=JOptionPane.showInputDialog("Proporciona el material predominante del calzado por favor");
		troquel=JOptionPane.showInputDialog("Proporciona el troquel(marca) del calzado por favor");
		cantProdxDia=Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad que se produce en un dia por favor"));
		cantColores=Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad de colores del calzado"));
		for(int i=0;i<cantColores;i++)
			n.colores.add(colores=JOptionPane.showInputDialog("Proporciona el color "+(i+1)+" por favor"));
		n.setClave(clave);
		n.setMaterial(material);
		n.setTroquel(troquel);
		n.setCantProdxDia(cantProdxDia);
		return n;
	}
	private static void inicializa() {
		c=new OperacionesClaseCalzado();
		
	}
	static void agregarLote() {
		ClaseCalzado item;
		item=capturarLote();
		if(c.addItem(item)) {
			JOptionPane.showMessageDialog(null,"Se ah agregado la cuenta con exito!!");
			if(c.isFull())
				c.crecerArreglo();
		}
		else
			JOptionPane.showMessageDialog(null,"Error:el lote ya existe!!");
	}
	static void mostrarLotes() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ningun lote");
		else {
		String lotes="";
		for(int i=0;i<c.getSize();i++)
			lotes=lotes+"\n"+(c.getItem(i));
		JOptionPane.showMessageDialog(null,lotes);
		}
	}
	
	static void borrarLote() {
		int pos=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ningun lote");
		else {
			boolean bandera=true;
			while(bandera) {
		    String lotes="";
		    for(int i=0;i<c.getSize();i++)
			    lotes=lotes+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Cual lote desea dar de baja?\n"+lotes));
		    if((c.getSize())>=pos&&pos>0) {
		    	c.clear(c.getItem(pos-1));
		    	JOptionPane.showMessageDialog(null,"lote dada de baja con éxito!!");
		    	bandera=false;
		    }	
		    else
		    	JOptionPane.showMessageDialog(null,"Ese lote no existe!!");
		  }
		}
	}
	static void cambiarProduccion(){
		int pos=0;
		int cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("A que lote deseas aumentar la produccionXdia?\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Integer.parseInt(JOptionPane.showInputDialog("Cual va a ser la nueva produccion diaria?"));
		    if(cantidad>0) {
		    	c.getItem(pos-1).setCantProdxDia(cantidad);
			    JOptionPane.showMessageDialog(null,"Cambio realizado exitosamente!!");
			    bandera=false;
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"No puede haber una produccion igual o menor a 0!!");
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"Esa cuenta no existe!!");
			}
		}
	}
	
	static void consulta(){
		int respuesta=0;
		boolean ciclo=true;
		while(ciclo) {
		String opciones="Elige la consulta que desees:\n 1)Produccion de calzado totalXdia\n"
				+ " 2)Produccion promedio de los lotes\n"
				+ " 3)Lotes con una produccion mayor a 200 unidades*dia\n "
				+ "4)Lote/s con mayor produccion\n 5)Lote/s con menor produccion\n 6)Exit";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:proTotal();ciclo=false;break;
		case 2:proPromedio();ciclo=false;break;
		case 3:mayor200();ciclo=false;break;
		case 4:mayor();ciclo=false;break;
		case 5:menor();ciclo=false;break;
		case 6:ciclo=false;break;
		default:JOptionPane.showMessageDialog(null,"Ingrese una de las opciones indicadas por favor");
		  }
		}
	}
	static void proTotal() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
		    int produccion=0;
		    for(int i=0;i<c.getSize();i++) 
			    produccion=produccion+c.getItem(i).getCantProdxDia();
		    JOptionPane.showMessageDialog(null,"La produccion Total es de: "+produccion);
		}
	}
	
	static void proPromedio() {
		int produccionProm=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
		    int produccion=0;
		    for(int i=0;i<c.getSize();i++) 
		        produccion=produccion+c.getItem(i).getCantProdxDia();
		    produccionProm=produccion/c.getSize(); 
		    JOptionPane.showMessageDialog(null,"La produccion promedio es de: "+produccionProm);
		}
	}
	
	static void mayor200() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
			int vacio=0;
			ClaseCalzado copia[]=new ClaseCalzado[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCantProdxDia()>200) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"Los lotes con una produccion mayor a 200 son:\n"+cuentas);
		}
	}
	
	static void mayor() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
			int vacio=0;
			int max=c.getItem(0).getCantProdxDia();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCantProdxDia()>max)
					max=c.getItem(i).getCantProdxDia();
			ClaseCalzado copia[]=new ClaseCalzado[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCantProdxDia()==max) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"El/los lote/lotes con mayor produccion es/son:\n"+cuentas);
		}
	}
	
	static void menor() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta");
		else {
			int vacio=0;
			int min=c.getItem(0).getCantProdxDia();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCantProdxDia()<min)
					min=c.getItem(i).getCantProdxDia();
			ClaseCalzado copia[]=new ClaseCalzado[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getCantProdxDia()==min) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"La/las cuenta/cuentas con menor saldo es/son:\n"+cuentas);
		}
	}
	
	

}
