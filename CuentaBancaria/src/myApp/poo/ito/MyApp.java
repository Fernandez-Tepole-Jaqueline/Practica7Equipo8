package myApp.poo.ito;

import java.awt.HeadlessException;

import excepciones.poo.ito.ExcepcioCuentaExistente;
import excepciones.poo.ito.ExcepcionBorraCuenta;
import excepciones.poo.ito.ExcepcionDeposito;
import excepciones.poo.ito.ExcepcionNoCuenta;
import excepciones.poo.ito.ExcepcionRetiro;
import excepciones.poo.ito.ExcepcionSaldo;

public class MyApp {
	

	public static void main(String[] args) throws HeadlessException, ExcepcionNoCuenta, ExcepcionSaldo, ExcepcionRetiro, ExcepcionDeposito, ExcepcioCuentaExistente, ExcepcionBorraCuenta{
		
			Aplicacion.menu();
			
	}
		
}


