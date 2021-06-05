package clases.ito.poo;

import excepciones.poo.ito.ExcepcioCuentaExistente;
import excepciones.poo.ito.ExcepcionBorraCuenta;
import excepciones.poo.ito.ExcepcionDeposito;
import excepciones.poo.ito.ExcepcionRetiro;
import interfaces.ito.poo.Arreglo;

import java.io.FileNotFoundException;

import clases.ito.poo.CuentaBancaria;
import escritores.poo.ito.LectorArchivoTXT;


public class CuentasBancareas implements Arreglo<CuentaBancaria> {

	static CuentaBancaria e;
	

	private CuentaBancaria cuentas[]=null;
	private int ultimo=0;
	private final int INC=5;
	
	public CuentasBancareas() {
		super();
		this.cuentas=new CuentaBancaria[INC];
		this.ultimo=-1; // Indica que esta vacio el arreglo
	}
	
	public void exceptionDeposito(float deposito) throws ExcepcionDeposito{
		if (deposito<500F||deposito>25000F)
			throw new ExcepcionDeposito("No se puede depositar menos de 500$ o más de 25,000$");
	}
	public void exceptionRetiro(float retiro) throws ExcepcionRetiro{
		if (!((retiro%100)==0) || retiro<100 || retiro>6000)
			throw new ExcepcionRetiro("No se puede retirar más de 6000$ ni menos de 100$, y el retio debe ser divisible entre 100");
	}
	
	public void exepcionCuentaExistente(CuentaBancaria item) throws ExcepcioCuentaExistente{
		if(this.existeItem(item)) {
			throw new ExcepcioCuentaExistente("La cuenta ya existe");
		}
	}
	
	public void exepcionBorraCuenta(CuentaBancaria item) throws ExcepcionBorraCuenta{
		if(item.getSaldo()>0) {
			throw new ExcepcionBorraCuenta("Una cuenta con saldo distinto a 0 no se puede eliminar");
		}
	}
	
	
	
	public void crecerArreglo() {
		CuentaBancaria copia[]=new CuentaBancaria[this.cuentas.length+INC];
		for(int i=0;i<cuentas.length;i++)
			copia[i]=this.cuentas[i];
		cuentas=copia;
	}
	
	@Override
	public boolean addItem(CuentaBancaria item) throws ExcepcioCuentaExistente {
		boolean add=false;
		
		if(this.isFull()) 
			crecerArreglo();
		int j=0;
		for(;j<=this.ultimo;j++)
			if(item.compareTo(this.cuentas[j])<0) {
				break;
				}
				
				for(int i=this.ultimo;i>=j;i--)
					cuentas[i+1]=cuentas[i];
				this.cuentas[j]=item;
				this.ultimo++;
				add=true;
			
		return add;
	}

	@Override
	public boolean existeItem(CuentaBancaria item) {
		boolean existe=false;
		for(int i=0;i<=this.ultimo;i++)
			if(item.compareTo(this.cuentas[i])==0) {
				existe=true;
				break;
			}
					
		return existe;
	}
	
	

	@Override
	public CuentaBancaria getItem(int pos) {
		CuentaBancaria cb=null;
		if(pos<=this.ultimo&&!this.isFree())
			cb=this.cuentas[pos];
		return cb;
	}

	@Override
	public int getSize() {
		return this.ultimo+1;
	}

	@Override
	public boolean clear(CuentaBancaria item) {
		boolean borrar=false;
		if(this.existeItem(item)) {
			int i=0;
			for(;i<=this.ultimo;i++)
				if(item.compareTo(this.cuentas[i])==0)
					break;
			for(;i<=this.ultimo;i++)
				cuentas[i]=cuentas[i+1];
			this.ultimo--;
			borrar=true;
		}
		return borrar;
	}

	@Override
	public boolean isFree() {
		return this.ultimo==-1;
	}

	@Override
	public boolean isFull() {
		
		return this.ultimo+1==this.cuentas.length;
	}
	

	

}
