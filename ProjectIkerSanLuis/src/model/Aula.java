package model;

public class Aula {
	protected String codigo;
	protected int tamaño;
	protected int disponibles;
	protected Centro centro;
	public Aula(String codigo, int tamaño, int disponibles, Centro centro) {
		super();
		this.codigo = codigo;
		this.tamaño = tamaño;
		this.disponibles = disponibles;
		this.centro = centro;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getTamaño() {
		return tamaño;
	}
	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}
	public int getDisponibles() {
		return disponibles;
	}
	public void setDisponibles(int disponibles) {
		this.disponibles = disponibles;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	@Override
	public String toString() {
		return this.codigo;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Aula(codigo, tamaño, disponibles, centro);
	}
	
	
	
}
