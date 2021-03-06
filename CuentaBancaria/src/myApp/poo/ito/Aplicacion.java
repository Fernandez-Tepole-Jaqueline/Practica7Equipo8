package myApp.poo.ito;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import clases.ito.poo.CuentaBancaria;
import clases.ito.poo.CuentasBancareas;
import escritores.poo.ito.EscritorArchivoTXT;
import escritores.poo.ito.LectorArchivoTXT;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.poo.ito.ExcepcionNoCuenta;
import excepciones.poo.ito.ExcepcionSaldo;
import excepciones.poo.ito.ExcepcionDeposito;
import excepciones.poo.ito.ExcepcionRetiro;
import excepciones.poo.ito.ExcepcioCuentaExistente;
import excepciones.poo.ito.ExcepcionBorraCuenta;


public class Aplicacion {

	static CuentasBancareas c;
	static CuentaBancaria e;
	static EscritorArchivoTXT archivo;
	static LectorArchivoTXT archivo2;
	
	static void menu() throws ExcepcionNoCuenta, ExcepcionSaldo, ExcepcionRetiro, ExcepcionDeposito, HeadlessException, ExcepcioCuentaExistente, ExcepcionBorraCuenta, FileNotFoundException {
		inicializa();
		iniciaPrograma();
		final JPanel panel=new JPanel();
		boolean error=true;
		while(error) {
		try {
		boolean ciclo=true;
		int respuesta=0;
		while(ciclo) {
		String opciones="Elige la opci?n que desees:\n 1)Agregar una cuenta\n 2)Imprimir las cuentas existentes\n 3)Hacer un dep?sito a una cuenta\n "
				+ "4)Hacer un retiro a una cuenta\n 5)Dar de baja una cuenta\n 6)Hacer una consulta\n 7)Exit";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:agregarCuenta();break;
		case 2:mostrarCuentas();break;
		case 3:hacerDeposito();break;
		case 4:hacerRetiro();break;
		case 5:borrarCuenta();break;
		case 6:consulta();break;
		case 7:grabaRegistro();ciclo=false;error=false;break;
		default:JOptionPane.showMessageDialog(null,"Ingrese una de las opciones indicadas por favor");
		  }
		}
		}catch(ExcepcionNoCuenta e){
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(ExcepcionSaldo e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(ExcepcionDeposito e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(ExcepcionRetiro e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(ExcepcioCuentaExistente e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(ExcepcionBorraCuenta e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	
	static CuentaBancaria capturarCuenta() throws ExcepcionNoCuenta,ExcepcionSaldo {
		CuentaBancaria n=new CuentaBancaria();
		long l;String fecha,nombre;float saldo;
		l=Long.parseLong(JOptionPane.showInputDialog("Proporciona un n?mero de cuenta:"));
		nombre=JOptionPane.showInputDialog("Proporciona el nombre del cliente (NombreApellido):");
		saldo=Float.parseFloat(JOptionPane.showInputDialog("Proporciona el saldo de la cuenta:"));
		fecha=JOptionPane.showInputDialog("Proporciona la fecha de apertura(aaaa-mm-dd):");
		n.setNumCuenta(l);
		n.setNomCliente(nombre);
		n.setSaldo(saldo);
		n.setFechaApertura(LocalDate.parse(fecha));
		return n;
	}
	
	static void inicializa() {
		c=new CuentasBancareas();
	}
	
	static void agregarCuenta() throws ExcepcionNoCuenta, ExcepcionSaldo, HeadlessException, ExcepcioCuentaExistente {
		CuentaBancaria nueva;
		nueva=capturarCuenta();
		c.exepcionCuentaExistente(nueva);
		c.addItem(nueva);
	    JOptionPane.showMessageDialog(null,"Se ah agregado la cuenta con exito!!");
			if(c.isFull())
				c.crecerArreglo();
		
		
	}
	
	static void mostrarCuentas() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
		String cuentas="";
		for(int i=0;i<c.getSize();i++)
			cuentas=cuentas+"\n"+(c.getItem(i));
		JOptionPane.showMessageDialog(null,cuentas);
		}
	}
	
	static void hacerDeposito() throws ExcepcionSaldo, ExcepcionDeposito {
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("A que cuenta deseas hacer un deposito?\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Que cantidad desea depositar?"));
		    c.exceptionDeposito(cantidad);
		    c.getItem(pos-1).setSaldoActualizado(c.getItem(pos-1).getSaldo()+cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"Dinero depositado exitosamente!!");
		    bandera=false;
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"Esa cuenta no existe!!");
			}
		}
	}
	
	static void hacerRetiro() throws ExcepcionSaldo, ExcepcionRetiro {
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("A que cuenta deseas hacer un retiro?\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Que cantidad desea retirar?"));
		    c.exceptionRetiro(cantidad);
		    if(!(c.getItem(pos-1).getSaldo()<cantidad)) {
		    c.getItem(pos-1).setSaldoActualizado(c.getItem(pos-1).getSaldo()-cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"Dinero retirado exitosamente!!");
		    bandera=false;
		    }
		    else {
		    	JOptionPane.showMessageDialog(null,"La cantidad exede el saldo de la cuenta!!");
		    }
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"Esa cuenta no existe!!");
			}
		}
	}
	
	static void borrarCuenta() throws ExcepcionBorraCuenta {
		int pos=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Cual cuenta deseas dar de baja?\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    	c.exepcionBorraCuenta(c.getItem(pos-1));
		    	c.clear(c.getItem(pos-1));
		    	JOptionPane.showMessageDialog(null,"Cuenta dada de baja con ?xito!!");
		    	bandera=false;
		    }	
		    else
		    	JOptionPane.showMessageDialog(null,"Esa cuenta no existe!!");
		  }
		}
	}
	
	static void consulta() {
		int respuesta=0;
		boolean ciclo=true;
		while(ciclo) {
		String opciones="Elige la consulta que desees:\n 1)Monto total de las cuentas\n 2)Monto promedio de las cuentas\n"
				+ " 3)Cuentas con saldo superior de los $10,000\n "
				+ "4)Cuenta/s con saldo m?ximo\n 5)Cuenta/s con saldo m?nimo\n 6)Exit";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		case 1:montoTotal();ciclo=false;break;
		case 2:montoPromedio();ciclo=false;break;
		case 3:mayor10mil();ciclo=false;break;
		case 4:saldoMax();ciclo=false;break;
		case 5:saldoMin();ciclo=false;break;
		case 6:ciclo=false;break;
		default:JOptionPane.showMessageDialog(null,"Ingrese una de las opciones indicadas por favor");
		  }
		}
	}
	
	static void montoTotal() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
		    float montoTotal=0;
		    for(int i=0;i<c.getSize();i++) 
			    montoTotal=montoTotal+c.getItem(i).getSaldo();
		    JOptionPane.showMessageDialog(null,"El monto tota es: $"+montoTotal);
		}
	}
	
	static void montoPromedio() {
		float montoProm=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
		    float montoTotal=0;
		    for(int i=0;i<c.getSize();i++) 
		        montoTotal=montoTotal+c.getItem(i).getSaldo();
		    montoProm=montoTotal/c.getSize(); 
		    JOptionPane.showMessageDialog(null,"El monto promedio es: $"+montoProm);
		}
	}
	
	static void mayor10mil() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
			int vacio=0;
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()>10000) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"Las cuentas que tienen un saldo exedente a $10,000 son:\n"+cuentas);
		}
	}
	
	static void saldoMax() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
			int vacio=0;
			float max=c.getItem(0).getSaldo();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()>max)
					max=c.getItem(i).getSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()==max) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"La/las cuenta/cuentas con mayor saldo es/son:\n"+cuentas);
		}
		
	}
	
	static void saldoMin() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todav?a no hay ninguna cuenta");
		else {
			int vacio=0;
			float min=c.getItem(0).getSaldo();
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()<min)
					min=c.getItem(i).getSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()==min) 
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"La/las cuenta/cuentas con menor saldo es/son:\n"+cuentas);
		}
	}
	
	
	
	
	static void grabaRegistro() throws FileNotFoundException {
		if (c.isFree()) {
			
		}
		else {
			archivo=new EscritorArchivoTXT("cuentas.txt");
			for(int i=0;i<c.getSize();i++) {
				archivo.writeLong(c.getItem(i).getNumCuenta());
				archivo.writeString(c.getItem(i).getNomCliente());
				archivo.writeFloat(c.getItem(i).getSaldo());
				if(c.getItem(i).getFechaActualizacion()==null) {
					archivo.writeString(c.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn("null");	
				}
				else {
					archivo.writeString(c.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn(c.getItem(i).getFechaActualizacion().toString());	
				}
				
			}
			archivo.close();
		}
					
	}
	
	static void iniciaPrograma() throws FileNotFoundException, ExcepcionNoCuenta, ExcepcioCuentaExistente {
		boolean existe=false;
		try {
			archivo2= new LectorArchivoTXT("cuentas.txt");
			existe=true;
		}catch(FileNotFoundException e) {
			System.err.println("No existe arcvivo previo, se crear? uno nuevo");
		}
		if(existe)
			leerTXT();
		
	}
	
	static void leerTXT() throws ExcepcionNoCuenta, ExcepcioCuentaExistente {
		
		while(!archivo2.isEOF()) {
			e=new CuentaBancaria();
			e.setNumCuenta(archivo2.readLong());
			e.setNomCliente(archivo2.readString());
			e.setSaldoActualizado(archivo2.readFloat());
			e.setFechaApertura(LocalDate.parse(archivo2.readString()));
			String fechaAct;
			fechaAct=archivo2.readString();
			if(fechaAct.equals("null")) 
				e.setFechaActualizacion(null);
			else
				e.setFechaActualizacion(LocalDate.parse(fechaAct));
			c.addItem(e);
			archivo2.isFinLinea();
		
			
		}
	}

}
