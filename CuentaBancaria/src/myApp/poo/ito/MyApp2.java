package myApp.poo.ito;

import java.awt.HeadlessException;
import clases.ito.poo.CuentasBancareas;
import excepciones.poo.ito.ExcepcioCuentaExistente;
import excepciones.poo.ito.ExcepcionBorraCuenta;
import excepciones.poo.ito.ExcepcionDeposito;
import excepciones.poo.ito.ExcepcionNoCuenta;
import excepciones.poo.ito.ExcepcionRetiro;
import excepciones.poo.ito.ExcepcionSaldo;

public class MyApp2 {

	static CuentasBancareas c;
	public static void main(String[] Args) throws HeadlessException, ExcepcionNoCuenta, ExcepcionSaldo, ExcepcionRetiro, ExcepcionDeposito, ExcepcioCuentaExistente, ExcepcionBorraCuenta {
		
		Aplicacion2.menu();
		
	}
	 
}
