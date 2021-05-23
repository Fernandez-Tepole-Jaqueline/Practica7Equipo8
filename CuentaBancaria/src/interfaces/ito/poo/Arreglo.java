package interfaces.ito.poo;

import excepciones.poo.ito.ExcepcioCuentaExistente;

public interface Arreglo<T> {
	
	public boolean addItem(T item) throws ExcepcioCuentaExistente;
	public boolean existeItem(T item);
	public T getItem(int pos);
	public int getSize(); //Retorna cantidad de elementos
	public boolean clear(T item);
	public boolean isFree();
	public boolean isFull();
}
