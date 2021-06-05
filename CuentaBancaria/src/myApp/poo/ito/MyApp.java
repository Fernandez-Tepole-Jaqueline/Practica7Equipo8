package myApp.poo.ito;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;

import clases.ito.poo.CuentasBancareas;
import escritores.poo.ito.EscritorArchivoTXT;
import escritores.poo.ito.LectorArchivoTXT;
import excepciones.poo.ito.ExcepcioCuentaExistente;
import excepciones.poo.ito.ExcepcionBorraCuenta;
import excepciones.poo.ito.ExcepcionDeposito;
import excepciones.poo.ito.ExcepcionNoCuenta;
import excepciones.poo.ito.ExcepcionRetiro;
import excepciones.poo.ito.ExcepcionSaldo;

public class MyApp {
	
	static CuentasBancareas c=new CuentasBancareas();
	static EscritorArchivoTXT archivo;
	static LectorArchivoTXT archivo2;
	
	static void run() throws HeadlessException, ExcepcionNoCuenta, ExcepcionSaldo, ExcepcionRetiro, ExcepcionDeposito, ExcepcioCuentaExistente, ExcepcionBorraCuenta, FileNotFoundException {
		
		Aplicacion.menu();
		   
			
    }
	
	
	static void crearArchivo() throws FileNotFoundException {
		archivo = new EscritorArchivoTXT("cuentas.txt");
	}

	
	public static void main(String[] args) throws HeadlessException, ExcepcionNoCuenta, ExcepcionSaldo, ExcepcionRetiro, ExcepcionDeposito, ExcepcioCuentaExistente, ExcepcionBorraCuenta, FileNotFoundException{
	
			run();
			
			
	}
		
}


