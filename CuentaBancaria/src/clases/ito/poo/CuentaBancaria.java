package clases.ito.poo;

import java.io.Serializable;
import java.time.LocalDate;

import excepciones.poo.ito.ExcepcionNoCuenta;
import excepciones.poo.ito.ExcepcionSaldo;

@SuppressWarnings("serial")
public class CuentaBancaria implements Comparable<CuentaBancaria>, Serializable {
	
	static CuentasBancareas c;
	
	private long numCuenta = 0L;
	private String nomCliente = "";
	private float Saldo = 0F;
	private LocalDate fechaApertura = null;
	private LocalDate fechaActualizacion = null;
	
	public CuentaBancaria() {
		
		super();
		
	}
	
	private void exceptionNoDeCuenta(long numCuenta) throws ExcepcionNoCuenta{
		if (9999>=numCuenta)
			throw new ExcepcionNoCuenta("No puede haber un número de cuenta menor a 9999");
	}
	
	private void exceptionSaldo(float saldo) throws ExcepcionSaldo{
		if (saldo<5000F)
			throw new ExcepcionSaldo("No se puede iniciar una cuenta con un saldo menor a 5000$");
	}
	
	
	
	
	public CuentaBancaria(long numCuenta, String nomCliente, float saldo, LocalDate fechaApertura)
			throws ExcepcionNoCuenta,ExcepcionSaldo {
		super();
		exceptionNoDeCuenta(numCuenta);
		this.numCuenta = numCuenta;
		this.nomCliente = nomCliente;
		exceptionSaldo(saldo);
		Saldo = saldo;
		this.fechaApertura = fechaApertura;
	}
	
	public boolean Deposito(float Cantidad)throws ExcepcionSaldo {
		boolean Deposito = false;
		if(this.fechaApertura==null)
			System.out.println("La cuenta no está activa");
		else {
			Deposito = true;
			this.setSaldo(this.getSaldo()+Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		
		return Deposito;
	}
	

	public boolean Retiro(float Cantidad)throws ExcepcionSaldo {
		
		boolean Retiro = false;
		if(Cantidad<=this.getSaldo()) {
			Retiro = true;
			this.setSaldo(this.getSaldo()-Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		else
			System.out.println("La cantidad a retirar sobrepasa el saldo");
		return Retiro;
	}

	
	public long getNumCuenta() {
		
		return this.numCuenta;
	}

	public void setNumCuenta(long newNumCuenta)throws ExcepcionNoCuenta {
		exceptionNoDeCuenta(newNumCuenta);
		this.numCuenta = newNumCuenta;
	}

	
	public String getNomCliente() {
		return this.nomCliente;
	}

	
	public void setNomCliente(String newNomCliente) {
		this.nomCliente = newNomCliente;
	}

	public float getSaldo() {
		return this.Saldo;
	}

	public void setSaldo(float newSaldo)throws ExcepcionSaldo {
		exceptionSaldo(newSaldo);
		this.Saldo = newSaldo;
	}
	
	public void setSaldoActualizado(float newSaldo) {
		this.Saldo = newSaldo;
	}

	public LocalDate getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(LocalDate newFechaApertura) {
		this.fechaApertura = newFechaApertura;
	}

	public LocalDate getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate newFechaActualizacion) {
		this.fechaActualizacion = newFechaActualizacion;
	}

	@Override
	public String toString() {
		return "CuentaBancaria [numCuenta=" + numCuenta + ", nomCliente=" + nomCliente + ", Saldo=" + Saldo
				+ ", fechaApertura=" + fechaApertura + ", fechaActualizacion=" + fechaActualizacion + "]";
	}
	@Override
	public int compareTo(CuentaBancaria o) {
		int compare=0;
		if (this.numCuenta<o.getNumCuenta())
			compare=-1;
		else if(this.numCuenta>o.getNumCuenta())
			compare=1;
		return compare;
		
	}

}
